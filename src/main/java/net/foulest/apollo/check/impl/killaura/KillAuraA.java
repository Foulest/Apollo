package net.foulest.apollo.check.impl.killaura;

import net.foulest.apollo.check.type.PacketCheck;
import net.foulest.apollo.exempt.type.ExemptType;
import net.foulest.apollo.wrapper.impl.client.WrappedPlayInFlying;
import net.foulest.apollo.wrapper.impl.client.WrappedPlayInUseEntity;
import net.minecraft.server.v1_8_R3.PacketPlayInUseEntity;
import net.foulest.apollo.check.CheckData;
import net.foulest.apollo.data.PlayerData;

@CheckData(name = "KillAura (A)")
public final class KillAuraA extends PacketCheck {

    private boolean sent = false;
    private long lastFlying = 0L;
    private long lastPacket = 0L;
    private double buffer = 0.0d;

    public KillAuraA(PlayerData playerData) {
        super(playerData);
    }

    @Override
    public void process(Object object) {
        if (isExempt(ExemptType.LAGGING, ExemptType.TPS)) {
            return;
        }

        if (object instanceof WrappedPlayInFlying) {
            long now = System.currentTimeMillis();
            long delay = now - lastPacket;

            if (sent) {
                if (delay > 40L && delay < 100L) {
                    buffer += 0.25;

                    if (buffer > 0.5) {
                        playerData.kick(getCheckName(), "");
                    }
                } else {
                    buffer = Math.max(buffer - 0.025, 0);
                }

                sent = false;
            }

            lastFlying = now;

        } else if (object instanceof WrappedPlayInUseEntity) {
            WrappedPlayInUseEntity wrapper = (WrappedPlayInUseEntity) object;

            if (wrapper.getAction() != PacketPlayInUseEntity.EnumEntityUseAction.ATTACK) {
                return;
            }

            long now = System.currentTimeMillis();
            long delay = now - lastFlying;

            if (delay < 10L) {
                lastPacket = now;
                sent = true;
            } else {
                buffer = Math.max(buffer - 0.025, 0.0);
            }
        }
    }
}
