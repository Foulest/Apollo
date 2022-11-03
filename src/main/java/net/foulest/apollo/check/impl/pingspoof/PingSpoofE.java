package net.foulest.apollo.check.impl.pingspoof;

import net.foulest.apollo.check.CheckData;
import net.foulest.apollo.check.type.PacketCheck;
import net.foulest.apollo.data.PlayerData;
import net.foulest.apollo.util.Pair;
import net.foulest.apollo.wrapper.impl.client.WrappedPlayInKeepAlive;
import net.foulest.apollo.wrapper.impl.server.WrappedPlayOutKeepAlive;

import java.util.LinkedList;
import java.util.Queue;

// Detects players sending unknown KeepAlive packets
@CheckData(name = "PingSpoof (E)")
public final class PingSpoofE extends PacketCheck {

    public final Queue<Pair<Integer, Long>> keepAliveMap = new LinkedList<>();

    public PingSpoofE(PlayerData playerData) {
        super(playerData);
    }

    @Override
    public void process(Object object) {
        if (object instanceof WrappedPlayOutKeepAlive) {
            WrappedPlayOutKeepAlive packet = (WrappedPlayOutKeepAlive) object;
            keepAliveMap.add(new Pair<>(packet.getId(), System.nanoTime()));

        } else if (object instanceof WrappedPlayInKeepAlive) {
            WrappedPlayInKeepAlive packet = (WrappedPlayInKeepAlive) object;
            int id = packet.getId();
            boolean hasId = false;

            for (Pair<Integer, Long> iterator : keepAliveMap) {
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
                    data = keepAliveMap.poll();
                    if (data == null) {
                        break;
                    }
                } while (data.getFirst() != id);
            }
        }
    }
}
