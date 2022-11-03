package net.foulest.apollo.processor.impl;

import net.foulest.apollo.check.type.PacketCheck;
import net.foulest.apollo.data.PlayerData;
import net.foulest.apollo.data.impl.PositionManager;
import net.foulest.apollo.data.impl.RotationManager;
import net.foulest.apollo.processor.type.Processor;
import net.foulest.apollo.util.NmsUtil;
import net.foulest.apollo.wrapper.impl.client.*;
import net.minecraft.server.v1_8_R3.*;
import org.bukkit.World;
import org.bukkit.entity.Entity;

@SuppressWarnings("unchecked")
public final class IncomingPacketProcessor implements Processor<Packet<PacketListenerPlayIn>> {

    // -1 = don't set
    // 0 is the tick to let flying be true
    // 1 is the tick to apply this
    int setFlyToFalse = -1;
    boolean hasSetFlying = false;

    @Override
    public void process(PlayerData playerData, Packet<PacketListenerPlayIn> packet) {
        if (packet instanceof PacketPlayInAbilities) {
            WrappedPlayInAbilities wrapper = new WrappedPlayInAbilities(packet);

            if (hasSetFlying && !wrapper.isFlying()) {
                hasSetFlying = false;
                setFlyToFalse = 0;
                return;
            }

            if (wrapper.isFlying()) {
                hasSetFlying = true;
            }

            playerData.getFlying().set(wrapper.isFlying() && playerData.getFlightAllowed().get());

            playerData.getCheckManager().getChecks().stream()
                    .filter(PacketCheck.class::isInstance)
                    .forEach(check -> check.process(wrapper));

        } else if (packet instanceof PacketPlayInArmAnimation) {
            WrappedPlayInArmAnimation wrapper = new WrappedPlayInArmAnimation(packet);

            playerData.getActionManager().onArmAnimation();

            playerData.getCheckManager().getChecks().stream()
                    .filter(PacketCheck.class::isInstance)
                    .forEach(check -> check.process(wrapper));

        } else if (packet instanceof PacketPlayInBlockDig) {
            WrappedPlayInBlockDig wrapper = new WrappedPlayInBlockDig(packet);

            switch (wrapper.getDigType()) {
                case START_DESTROY_BLOCK:
                case ABORT_DESTROY_BLOCK:
                case STOP_DESTROY_BLOCK: {
                    playerData.getActionManager().onDig();
                    break;
                }
            }

            playerData.getCheckManager().getChecks().stream()
                    .filter(PacketCheck.class::isInstance)
                    .forEach(check -> check.process(wrapper));

        } else if (packet instanceof PacketPlayInBlockPlace) {
            WrappedPlayInBlockPlace wrapper = new WrappedPlayInBlockPlace(packet);

            playerData.getActionManager().onPlace();

            playerData.getCheckManager().getChecks().stream()
                    .filter(PacketCheck.class::isInstance)
                    .forEach(check -> check.process(wrapper));

        } else if (packet instanceof PacketPlayInChat) {
            WrappedPlayInChat wrapper = new WrappedPlayInChat(packet);

            playerData.getCheckManager().getChecks().stream()
                    .filter(PacketCheck.class::isInstance)
                    .forEach(check -> check.process(wrapper));

        } else if (packet instanceof PacketPlayInClientCommand) {
            WrappedPlayInClientCommand wrapper = new WrappedPlayInClientCommand(packet);

            playerData.getCheckManager().getChecks().stream()
                    .filter(PacketCheck.class::isInstance)
                    .forEach(check -> check.process(wrapper));

        } else if (packet instanceof PacketPlayInCloseWindow) {
            WrappedPlayInCloseWindow wrapper = new WrappedPlayInCloseWindow(packet);

            playerData.getCheckManager().getChecks().stream()
                    .filter(PacketCheck.class::isInstance)
                    .forEach(check -> check.process(wrapper));

        } else if (packet instanceof PacketPlayInCustomPayload) {
            WrappedPlayInCustomPayload wrapper = new WrappedPlayInCustomPayload(packet);

            playerData.getCheckManager().getChecks().stream()
                    .filter(PacketCheck.class::isInstance)
                    .forEach(check -> check.process(wrapper));

        } else if (packet instanceof PacketPlayInEntityAction) {
            WrappedPlayInEntityAction wrapper = new WrappedPlayInEntityAction(packet);

            switch (wrapper.getAction()) {
                case START_SPRINTING: {
                    playerData.getSprinting().set(true);
                    playerData.getActionManager().onSprinting(true);
                    break;
                }

                case STOP_SPRINTING: {
                    playerData.getSprinting().set(false);
                    playerData.getActionManager().onSprinting(false);
                    break;
                }

                case START_SNEAKING: {
                    playerData.getSneaking().set(true);
                    playerData.getActionManager().onSneaking(true);
                    break;
                }

                case STOP_SNEAKING: {
                    playerData.getSneaking().set(false);
                    playerData.getActionManager().onSneaking(false);
                    break;
                }

                case RIDING_JUMP: {
                    playerData.getActionManager().onRidingJump();
                    break;
                }

                case STOP_SLEEPING: {
                    playerData.getSleeping().set(false);
                    playerData.getActionManager().onSleeping(false);
                    break;
                }

                case OPEN_INVENTORY: {
                    playerData.getEntityInventoryOpen().set(true);
                    playerData.getActionManager().onEntityInventory(true);
                    break;
                }
            }

            playerData.getCheckManager().getChecks().stream()
                    .filter(PacketCheck.class::isInstance)
                    .forEach(check -> check.process(wrapper));

        } else if (packet instanceof PacketPlayInEnchantItem) {
            WrappedPlayInEnchantItem wrapper = new WrappedPlayInEnchantItem(packet);

            playerData.getCheckManager().getChecks().stream()
                    .filter(PacketCheck.class::isInstance)
                    .forEach(check -> check.process(wrapper));

        } else if (packet instanceof PacketPlayInFlying) {
            WrappedPlayInFlying wrapper = new WrappedPlayInFlying(packet);
            double posX = wrapper.getX();
            double posY = wrapper.getY();
            double posZ = wrapper.getZ();
            float yaw = wrapper.getYaw();
            float pitch = wrapper.getPitch();
            boolean hasPos = wrapper.hasPos();
            boolean hasLook = wrapper.hasLook();
            boolean onGround = wrapper.isOnGround();

            if (hasPos) {
                PositionManager positionManager = playerData.getPositionManager();
                World world = playerData.getBukkitPlayer().getWorld();
                positionManager.handle(world, posX, posY, posZ, onGround);
            }

            if (hasLook) {
                RotationManager rotationManager = playerData.getRotationManager();
                rotationManager.handle(yaw, pitch);
            }

            hasSetFlying = false;

            if (setFlyToFalse == 0) {
                setFlyToFalse = 1;
            } else if (setFlyToFalse == 1) {
                playerData.getFlying().set(false);
                setFlyToFalse = -1;
            }

            playerData.getVelocityManager().apply();
            playerData.getActionManager().onFlying();

            playerData.getCheckManager().getChecks().stream()
                    .filter(PacketCheck.class::isInstance)
                    .forEach(check -> check.process(wrapper));

        } else if (packet instanceof PacketPlayInHeldItemSlot) {
            WrappedPlayInHeldItemSlot wrapper = new WrappedPlayInHeldItemSlot(packet);

            playerData.getCheckManager().getChecks().stream()
                    .filter(PacketCheck.class::isInstance)
                    .forEach(check -> check.process(wrapper));

        } else if (packet instanceof PacketPlayInKeepAlive) {
            WrappedPlayInKeepAlive wrapper = new WrappedPlayInKeepAlive(packet);

            playerData.getConnectionManager().onKeepAlive(wrapper.getId(), System.currentTimeMillis());

            playerData.getCheckManager().getChecks().stream()
                    .filter(PacketCheck.class::isInstance)
                    .forEach(check -> check.process(wrapper));

        } else if (packet instanceof PacketPlayInResourcePackStatus) {
            WrappedPlayInResourcePackStatus wrapper = new WrappedPlayInResourcePackStatus(packet);

            playerData.getCheckManager().getChecks().stream()
                    .filter(PacketCheck.class::isInstance)
                    .forEach(check -> check.process(wrapper));

        } else if (packet instanceof PacketPlayInSetCreativeSlot) {
            WrappedPlayInSetCreativeSlot wrapper = new WrappedPlayInSetCreativeSlot(packet);

            playerData.getCheckManager().getChecks().stream()
                    .filter(PacketCheck.class::isInstance)
                    .forEach(check -> check.process(wrapper));

        } else if (packet instanceof PacketPlayInSettings) {
            WrappedPlayInSettings wrapper = new WrappedPlayInSettings(packet);

            playerData.getCheckManager().getChecks().stream()
                    .filter(PacketCheck.class::isInstance)
                    .forEach(check -> check.process(wrapper));

        } else if (packet instanceof PacketPlayInSpectate) {
            WrappedPlayInSpectate wrapper = new WrappedPlayInSpectate(packet);

            playerData.getCheckManager().getChecks().stream()
                    .filter(PacketCheck.class::isInstance)
                    .forEach(check -> check.process(wrapper));

        } else if (packet instanceof PacketPlayInSteerVehicle) {
            WrappedPlayInSteerVehicle wrapper = new WrappedPlayInSteerVehicle(packet);

            playerData.getActionManager().onSteerVehicle();

            playerData.getCheckManager().getChecks().stream()
                    .filter(PacketCheck.class::isInstance)
                    .forEach(check -> check.process(wrapper));

        } else if (packet instanceof PacketPlayInTabComplete) {
            WrappedPlayInTabComplete wrapper = new WrappedPlayInTabComplete(packet);

            playerData.getCheckManager().getChecks().stream()
                    .filter(PacketCheck.class::isInstance)
                    .forEach(check -> check.process(wrapper));

        } else if (packet instanceof PacketPlayInTransaction) {
            WrappedPlayInTransaction wrapper = new WrappedPlayInTransaction(packet);
            long now = System.currentTimeMillis();

            playerData.getConnectionManager().onTransaction(wrapper.getHash(), now);

            playerData.getCheckManager().getChecks().stream()
                    .filter(PacketCheck.class::isInstance)
                    .forEach(check -> check.process(wrapper));

        } else if (packet instanceof PacketPlayInUpdateSign) {
            WrappedPlayInUpdateSign wrapper = new WrappedPlayInUpdateSign(packet);

            playerData.getCheckManager().getChecks().stream()
                    .filter(PacketCheck.class::isInstance)
                    .forEach(check -> check.process(wrapper));

        } else if (packet instanceof PacketPlayInUseEntity) {
            WrappedPlayInUseEntity wrapper = new WrappedPlayInUseEntity(packet);

            if (wrapper.getAction() == PacketPlayInUseEntity.EnumEntityUseAction.ATTACK) {
                playerData.getActionManager().onAttack();

                Entity entity = wrapper.getTarget(NmsUtil.getWorld(playerData.getBukkitPlayer().getWorld()));
                playerData.getTarget().set(entity);
            }

            playerData.getCheckManager().getChecks().stream()
                    .filter(PacketCheck.class::isInstance)
                    .forEach(check -> check.process(wrapper));

        } else if (packet instanceof PacketPlayInWindowClick) {
            WrappedPlayInWindowClick wrapper = new WrappedPlayInWindowClick(packet);

            playerData.getCheckManager().getChecks().stream()
                    .filter(PacketCheck.class::isInstance)
                    .forEach(check -> check.process(wrapper));
        }
    }
}
