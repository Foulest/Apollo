package net.foulest.apollo.check.impl.pingspoof;

import net.foulest.apollo.check.CheckData;
import net.foulest.apollo.check.type.PacketCheck;
import net.foulest.apollo.data.PlayerData;
import net.foulest.apollo.wrapper.impl.client.WrappedPlayInFlying;
import net.foulest.apollo.wrapper.impl.client.WrappedPlayInSteerVehicle;

// Detects delaying the Position idle packet
@CheckData(name = "PingSpoof (H)")
public final class PingSpoofH extends PacketCheck {

    public int noReminderTicks;

    public PingSpoofH(PlayerData playerData) {
        super(playerData);
    }

    @Override
    public void process(Object object) {
        if (object instanceof WrappedPlayInFlying) {
            WrappedPlayInFlying packet = (WrappedPlayInFlying) object;

            if (packet.hasPos()) {
                noReminderTicks = 0;
            } else {
                ++noReminderTicks;
            }

        } else if (object instanceof WrappedPlayInSteerVehicle) {
            noReminderTicks = 0; // Exempt vehicles
        }

        if (noReminderTicks > 20) {
            playerData.kick(getCheckName(), "Idle Packet");
        }
    }
}
