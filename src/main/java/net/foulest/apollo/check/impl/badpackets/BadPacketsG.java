package net.foulest.apollo.check.impl.badpackets;

import net.foulest.apollo.check.type.PacketCheck;
import net.foulest.apollo.exempt.type.ExemptType;
import net.foulest.apollo.wrapper.impl.client.WrappedPlayInSteerVehicle;
import net.foulest.apollo.check.CheckData;
import net.foulest.apollo.data.PlayerData;

// Detects sending invalid SteerVehicle packets
@CheckData(name = "BadPackets (G)")
public final class BadPacketsG extends PacketCheck {

    public BadPacketsG(PlayerData playerData) {
        super(playerData);
    }

    @Override
    public void process(Object object) {
        if (isExempt(ExemptType.LAGGING, ExemptType.TPS)) {
            return;
        }

        if (object instanceof WrappedPlayInSteerVehicle) {
            WrappedPlayInSteerVehicle wrapper = (WrappedPlayInSteerVehicle) object;
            float forward = Math.abs(wrapper.getForward());
            float side = Math.abs(wrapper.getSide());

//            if (isExempt(ExemptType.VEHICLE)) {
//                playerData.kick(getCheckName(), "Not in Vehicle");
//            }

            if (side > 0.9800000190734863F || forward > 0.9800000190734863F) {
                playerData.kick(getCheckName(), "Invalid Steer");
            }
        }
    }
}
