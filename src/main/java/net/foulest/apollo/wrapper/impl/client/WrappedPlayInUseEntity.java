package net.foulest.apollo.wrapper.impl.client;

import net.minecraft.server.v1_8_R3.Packet;
import net.minecraft.server.v1_8_R3.PacketPlayInUseEntity;
import net.minecraft.server.v1_8_R3.Vec3D;
import net.minecraft.server.v1_8_R3.World;
import org.bukkit.entity.Entity;
import org.bukkit.util.Vector;
import net.foulest.apollo.wrapper.PacketWrapper;

public final class WrappedPlayInUseEntity extends PacketWrapper {

    public WrappedPlayInUseEntity(Packet<?> instance) {
        super(instance, PacketPlayInUseEntity.class);
    }

    public long getTimestamp() {
        return System.currentTimeMillis();
    }

    public PacketPlayInUseEntity.EnumEntityUseAction getAction() {
        return get("action");
    }

    public int getEntityId() {
        return get("a");
    }

    public Entity getTarget(World world) {
        int entityId = get("a");
        return world.a(entityId).getBukkitEntity();
    }

    public Vector getVector() {
        Vec3D vec3D = get("c");
        return new Vector(vec3D.a, vec3D.b, vec3D.c);
    }
}
