package net.foulest.apollo.check.impl.killaura;

import net.foulest.apollo.check.type.PacketCheck;
import net.foulest.apollo.exempt.type.ExemptType;
import net.foulest.apollo.wrapper.impl.client.WrappedPlayInFlying;
import net.foulest.apollo.wrapper.impl.client.WrappedPlayInUseEntity;
import net.minecraft.server.v1_8_R3.PacketPlayInUseEntity;
import net.foulest.apollo.check.CheckData;
import net.foulest.apollo.data.PlayerData;

@CheckData(name = "KillAura (E)")
public final class KillAuraE extends PacketCheck {

    private int movements = 0;
    private int lastMovements = 0;
    private int total = 0;
    private int invalid = 0;

    public KillAuraE(PlayerData playerData) {
        super(playerData);
    }

    @Override
    public void process(Object object) {
        if (isExempt(ExemptType.LAGGING, ExemptType.TPS)) {
            return;
        }

        if (object instanceof WrappedPlayInUseEntity) {
            WrappedPlayInUseEntity wrapper = (WrappedPlayInUseEntity) object;

            if (wrapper.getAction() == PacketPlayInUseEntity.EnumEntityUseAction.ATTACK) {
                boolean proper = playerData.getCps().get() > 7.2 && movements < 4 && lastMovements < 4;

                if (proper) {
                    boolean flag = movements == lastMovements;

                    if (flag) {
                        ++invalid;
                    }

                    if (++total == 30) {

                        if (invalid > 28) {
                            playerData.kick(getCheckName(), "");
                        }

                        total = 0;
                    }
                }

                lastMovements = movements;
                movements = 0;
            }

        } else if (object instanceof WrappedPlayInFlying) {
            ++movements;
        }
    }
}
