package net.foulest.apollo.check.impl.pingspoof;

import net.foulest.apollo.check.CheckData;
import net.foulest.apollo.check.type.PacketCheck;
import net.foulest.apollo.data.PlayerData;
import net.foulest.apollo.wrapper.impl.client.WrappedPlayInFlying;
import net.foulest.apollo.wrapper.impl.client.WrappedPlayInKeepAlive;
import net.foulest.apollo.wrapper.impl.server.WrappedPlayOutTransaction;

// Detects various fake lag modules
@CheckData(name = "PingSpoof (L)")
public final class PingSpoofL extends PacketCheck {

    public int transactionCount;
    public int keepAliveCount;
    public int lastTransactionCount;
    public int lastKeepAliveCount;
    public int lastTicks;
    public double fakeLagCount;

    public PingSpoofL(PlayerData playerData) {
        super(playerData);
    }

    @Override
    public void process(Object object) {
        if (object instanceof WrappedPlayOutTransaction) {
            lastTicks = playerData.getTicks().get();
            lastTransactionCount = transactionCount;
            ++transactionCount;
            check();

        } else if (object instanceof WrappedPlayInKeepAlive) {
            lastTicks = playerData.getTicks().get();
            lastKeepAliveCount = keepAliveCount;
            ++keepAliveCount;
            check();

        } else if (object instanceof WrappedPlayInFlying) {
            lastTicks = playerData.getTicks().get();
            lastKeepAliveCount = keepAliveCount;
            lastTransactionCount = transactionCount;
            keepAliveCount = 0;
            transactionCount = 0;
        }
    }

    public void check() {
        if (playerData.getTicks().get() < 20) {
            return;
        }

        if (keepAliveCount > 1 && transactionCount > 1) {
            if (playerData.getTicks().get() == lastTicks) {
                // Player is actually lagging.
                fakeLagCount = Math.max(fakeLagCount - 0.5, 0);
//                MessageUtil.log("&c(Lag)"
//                        + " KeepAlive=" + keepAliveCount
//                        + " Transaction=" + transactionCount
//                        + " Ticks=" + playerData.getTicks().get());
            }

        } else if (keepAliveCount == 0 && lastKeepAliveCount == 0 && transactionCount >= 4) {
            ++fakeLagCount;
//            MessageUtil.log("&e(Lag)"
//                    + " KeepAlive=" + keepAliveCount
//                    + " Transaction=" + transactionCount
//                    + " Ticks=" + playerData.getTicks().get());

        } else if (transactionCount == 0 && lastTransactionCount == 0 && keepAliveCount >= 3) {
            ++fakeLagCount;
//            MessageUtil.log("&6(Lag)"
//                    + " KeepAlive=" + keepAliveCount
//                    + " Transaction=" + transactionCount
//                    + " Ticks=" + playerData.getTicks().get());

        } else {
            fakeLagCount = Math.max(fakeLagCount - 0.25, 0);
//            MessageUtil.log("KeepAlive=" + keepAliveCount
//                    + " Transaction=" + transactionCount
//                    + " Ticks=" + playerData.getTicks().get());
        }

        if (fakeLagCount >= 10) {
            playerData.kick(getCheckName(), "");
        }
    }
}
