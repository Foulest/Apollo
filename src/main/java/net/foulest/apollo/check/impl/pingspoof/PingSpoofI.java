package net.foulest.apollo.check.impl.pingspoof;

import net.foulest.apollo.check.CheckData;
import net.foulest.apollo.check.type.PacketCheck;
import net.foulest.apollo.data.PlayerData;
import net.foulest.apollo.util.Pair;
import net.foulest.apollo.wrapper.impl.client.WrappedPlayInTransaction;
import net.foulest.apollo.wrapper.impl.server.WrappedPlayOutTransaction;

import java.util.LinkedList;
import java.util.Queue;

// Detects players sending unknown Transaction packets
@CheckData(name = "PingSpoof (I)")
public final class PingSpoofI extends PacketCheck {

    public final Queue<Pair<Integer, Long>> transactionMap = new LinkedList<>();

    public PingSpoofI(PlayerData playerData) {
        super(playerData);
    }

    @Override
    public void process(Object object) {
        if (object instanceof WrappedPlayOutTransaction) {
            WrappedPlayOutTransaction packet = (WrappedPlayOutTransaction) object;
            transactionMap.add(new Pair<>(packet.getId(), System.nanoTime()));

        } else if (object instanceof WrappedPlayInTransaction) {
            WrappedPlayInTransaction packet = (WrappedPlayInTransaction) object;
            long id = packet.getId();
            boolean hasId = false;

            if (id == 0) {
                return;
            }

            for (Pair<Integer, Long> iterator : transactionMap) {
                if (iterator.getFirst() == id) {
                    hasId = true;
                    break;
                }
            }

            if (!hasId) {
                playerData.kick(getCheckName(), "Unknown (ID=" + id + ")");
            } else { // Found the ID, remove stuff until we get to it (to stop very slow memory leaks)
                Pair<Integer, Long> data;
                do {
                    data = transactionMap.poll();
                    if (data == null) {
                        break;
                    }
                } while (data.getFirst() != id);
            }
        }
    }
}
