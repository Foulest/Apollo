package net.foulest.apollo.check.impl.autoblock;

import net.foulest.apollo.check.type.PacketCheck;
import net.foulest.apollo.exempt.type.ExemptType;
import net.foulest.apollo.wrapper.impl.client.WrappedPlayInUseEntity;
import net.minecraft.server.v1_8_R3.PacketPlayInUseEntity.EnumEntityUseAction;
import net.foulest.apollo.check.CheckData;
import net.foulest.apollo.data.PlayerData;

// Detects attacking while placing
@CheckData(name = "AutoBlock (D)")
public final class AutoBlockD extends PacketCheck {

    public AutoBlockD(PlayerData playerData) {
        super(playerData);
    }

    @Override
    public void process(Object object) {
        if (isExempt(ExemptType.LAGGING, ExemptType.TPS)) {
            return;
        }

        if (object instanceof WrappedPlayInUseEntity) {
            WrappedPlayInUseEntity wrapper = (WrappedPlayInUseEntity) object;

            if (wrapper.getAction() != EnumEntityUseAction.ATTACK) {
                return;
            }

            boolean placing = playerData.getActionManager().getPlacing().get();

            if (placing) {
                playerData.kick(getCheckName(), "Attacking While Placing");
            }
        }
    }
}
