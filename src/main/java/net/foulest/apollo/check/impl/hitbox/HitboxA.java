package net.foulest.apollo.check.impl.hitbox;

import net.foulest.apollo.check.type.PacketCheck;
import net.foulest.apollo.exempt.type.ExemptType;
import net.foulest.apollo.wrapper.impl.client.WrappedPlayInUseEntity;
import net.minecraft.server.v1_8_R3.AxisAlignedBB;
import net.minecraft.server.v1_8_R3.PacketPlayInUseEntity;
import org.bukkit.GameMode;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.util.Vector;
import net.foulest.apollo.Apollo;
import net.foulest.apollo.check.CheckData;
import net.foulest.apollo.data.PlayerData;
import net.foulest.apollo.util.MathUtil;

@CheckData(name = "Hitbox (A)")
public final class HitboxA extends PacketCheck {

    private double buffer = 0.0;

    public HitboxA(PlayerData playerData) {
        super(playerData);
    }

    @Override
    public void process(Object object) {
        if (isExempt(ExemptType.LAGGING, ExemptType.TPS)) {
            return;
        }

        if (object instanceof WrappedPlayInUseEntity) {
            WrappedPlayInUseEntity wrapper = (WrappedPlayInUseEntity) object;
            Entity target = playerData.getTarget().get();

            if (!(target instanceof LivingEntity)
                    || playerData.getTargetLocations().size() < 30) {
                return;
            }

            if (wrapper.getAction() != PacketPlayInUseEntity.EnumEntityUseAction.ATTACK
                    || playerData.getBukkitPlayer().getGameMode() == GameMode.CREATIVE) {
                return;
            }

            int now = Apollo.INSTANCE.getTickManager().getTicks();
            int ping = MathUtil.getPingInTicks(playerData.getKeepAlivePing().get()) + 3;

            Vector origin = playerData.getPositionUpdate().get().getTo().toVector();

            double distance = playerData.getTargetLocations().stream()
                    .filter(pair -> Math.abs(now - pair.getSecond() - ping) < 2)
                    .mapToDouble(pair -> {
                        AxisAlignedBB box = pair.getFirst();
                        double widthX = Math.abs(box.a - box.d) / 2;
                        double widthZ = Math.abs(box.c - box.f) / 2;
                        Vector loc = new Vector(box.a + widthX, 0, box.c + widthZ);
                        return origin.setY(0).distance(loc) - MathUtil.magnitude(widthX, widthZ) - .1f;
                    }).min().orElse(-1);

            if (distance > 3) {
                buffer += 1.5;

                if (buffer > 3) {
                    playerData.kick(getCheckName(), "");
                }
            } else {
                buffer = Math.max(buffer - 0.75, 0);
            }
        }
    }
}
