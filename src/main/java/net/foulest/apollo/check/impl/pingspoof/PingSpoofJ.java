package net.foulest.apollo.check.impl.pingspoof;

import net.foulest.apollo.check.CheckData;
import net.foulest.apollo.check.type.PacketCheck;
import net.foulest.apollo.data.PlayerData;
import net.foulest.apollo.util.EvictingList;
import net.foulest.apollo.wrapper.impl.client.WrappedPlayInFlying;
import net.foulest.apollo.wrapper.impl.client.WrappedPlayInKeepAlive;
import net.foulest.apollo.wrapper.impl.client.WrappedPlayInTransaction;

// Detects delaying packets, timed on KeepAlive
@CheckData(name = "PingSpoof (J)")
public final class PingSpoofJ extends PacketCheck {

    public final EvictingList<Long> changeFlyingList = new EvictingList<>(40);
    public final EvictingList<Long> changeTransactionList = new EvictingList<>(20);
    public final EvictingList<Integer> flyingCountList = new EvictingList<>(5);
    public final EvictingList<Integer> transactionCountList = new EvictingList<>(5);
    public long lastFlying;
    public long lastTransaction;
    public double lastAverageFlyingTimePercent;
    public double lastAverageTransactionTimePercent;
    public int flyingCount;
    public int transactionCount;

    public PingSpoofJ(PlayerData playerData) {
        super(playerData);
    }

    @Override
    public void process(Object object) {
        if (object instanceof WrappedPlayInFlying) {
            ++flyingCount;
            long timeBetweenFlying = System.nanoTime() - lastFlying;
            lastFlying = System.nanoTime();
            changeFlyingList.add(timeBetweenFlying);

            // Gets the average time between Flying packets
            if (changeFlyingList.size() == changeFlyingList.getMaxSize()) {
                long lastAverageFlyingTime = (long) changeFlyingList.stream().mapToDouble(d -> d).average().orElse(0.0);
                lastAverageFlyingTimePercent = ((lastAverageFlyingTime * 100.0) / 50000000) - 100;
                lastAverageFlyingTimePercent = Math.round(lastAverageFlyingTimePercent * 1000.0) / 1000.0;
                changeFlyingList.clear();
            }

        } else if (object instanceof WrappedPlayInTransaction) {
            ++transactionCount;
            long timeBetweenTransaction = System.nanoTime() - lastTransaction;
            lastTransaction = System.nanoTime();
            changeTransactionList.add(timeBetweenTransaction);

            // Gets the average time between Transaction packets
            if (changeTransactionList.size() == changeTransactionList.getMaxSize()) {
                long lastAverageTransactionTime = (long) changeTransactionList.stream().mapToDouble(d -> d).average().orElse(0.0);
                lastAverageTransactionTimePercent = ((lastAverageTransactionTime * 100.0) / 100000000) - 100;
                lastAverageTransactionTimePercent = Math.round(lastAverageTransactionTimePercent * 1000.0) / 1000.0;
                changeTransactionList.clear();
            }

        } else if (object instanceof WrappedPlayInKeepAlive) {
            if (!transactionCountList.isEmpty() && !flyingCountList.isEmpty()
                    && (flyingCount != 0 && transactionCount != 0)) {
                boolean inTransactionRange = Math.abs(lastAverageTransactionTimePercent) <= 5.0
                        && transactionCount >= 20 && transactionCount <= 25;
                boolean lastInTransactionRange = Math.abs(lastAverageTransactionTimePercent) <= 5.0
                        && transactionCountList.getLast() >= 20 && transactionCountList.getLast() <= 25;

                // Detects sending higher amount of Flying packets per KeepAlive
                if (flyingCount > 42 && flyingCountList.getLast() > 42
                        && inTransactionRange && lastInTransactionRange) {
                    playerData.kick(getCheckName(), "(High Count F) " + "[FLYING] " + lastAverageFlyingTimePercent + "% (" + flyingCount + ") "
                            + " [TRANS] " + lastAverageTransactionTimePercent + "% (" + transactionCount + ")");
                }

//                // Detects sending lower amount of Flying packets per KeepAlive
//                if (!playerData.getClientVersion().isNewerThanOrEquals(ClientVersion.V_1_9)) {
//                    if (flyingCount < 37 && flyingCountList.getLast() < 37 && flyingCount != 0
//                            && inTransactionRange && lastInTransactionRange) {
//                        playerData.kick(getCheckName(), "(Low Count F) " + "[FLYING] " + lastAverageFlyingTimePercent + "% (" + flyingCount + ") "
//                                + " [TRANS] " + lastAverageTransactionTimePercent + "% (" + transactionCount + ")");
//                    }
//                }

                // Detects sending zero Flying packets per KeepAlive
                if (flyingCount == 0 && transactionCount != 0) {
                    playerData.kick(getCheckName(), "(Zero Count F) " + "[FLYING] " + lastAverageFlyingTimePercent + "% (" + flyingCount + ") "
                            + " [TRANS] " + lastAverageTransactionTimePercent + "% (" + transactionCount + ")");
                }

                // Detects sending lower amount of Transaction packets per KeepAlive
                if (transactionCount < 20 && transactionCountList.getLast() < 20 && transactionCount != 0
                        && flyingCount == 41 && flyingCountList.getLast() == 41) {
                    playerData.kick(getCheckName(), "(Low Count T) " + "[FLYING] " + lastAverageFlyingTimePercent + "% (" + flyingCount + ") "
                            + " [TRANS] " + lastAverageTransactionTimePercent + "% (" + transactionCount + ")");
                }

                // Detects sending zero Transaction packets per KeepAlive
                if (transactionCount == 0 && flyingCount != 0) {
                    playerData.kick(getCheckName(), "(Zero Count T) " + "[FLYING] " + lastAverageFlyingTimePercent + "% (" + flyingCount + ") "
                            + " [TRANS] " + lastAverageTransactionTimePercent + "% (" + transactionCount + ")");
                }
            }

            flyingCountList.add(flyingCount);
            transactionCountList.add(transactionCount);
            flyingCount = 0;
            transactionCount = 0;
        }
    }
}
