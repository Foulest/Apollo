package net.foulest.apollo.processor.impl;

import net.foulest.apollo.check.type.PacketCheck;
import net.foulest.apollo.data.PlayerData;
import net.foulest.apollo.processor.type.Processor;
import net.foulest.apollo.wrapper.impl.server.*;
import net.minecraft.server.v1_8_R3.*;

@SuppressWarnings("unchecked")
public final class OutgoingPacketProcessor implements Processor<Packet<PacketListenerPlayOut>> {

    @Override
    public void process(PlayerData playerData, Packet<PacketListenerPlayOut> packet) {
        if (packet instanceof PacketPlayOutAbilities) {
            WrappedPlayOutAbilities wrapper = new WrappedPlayOutAbilities(packet);

            playerData.getCheckManager().getChecks().stream()
                    .filter(PacketCheck.class::isInstance)
                    .forEach(check -> check.process(wrapper));

        } else if (packet instanceof PacketPlayOutAnimation) {
            WrappedPlayOutAnimation wrapper = new WrappedPlayOutAnimation(packet);

            playerData.getCheckManager().getChecks().stream()
                    .filter(PacketCheck.class::isInstance)
                    .forEach(check -> check.process(wrapper));

        } else if (packet instanceof PacketPlayOutAttachEntity) {
            WrappedPlayOutAttachEntity wrapper = new WrappedPlayOutAttachEntity(packet);

            playerData.getCheckManager().getChecks().stream()
                    .filter(PacketCheck.class::isInstance)
                    .forEach(check -> check.process(wrapper));

        } else if (packet instanceof PacketPlayOutBed) {
            WrappedPlayOutBed wrapper = new WrappedPlayOutBed(packet);

            playerData.getCheckManager().getChecks().stream()
                    .filter(PacketCheck.class::isInstance)
                    .forEach(check -> check.process(wrapper));

        } else if (packet instanceof PacketPlayOutBlockAction) {
            WrappedPlayOutBlockAction wrapper = new WrappedPlayOutBlockAction(packet);

            playerData.getCheckManager().getChecks().stream()
                    .filter(PacketCheck.class::isInstance)
                    .forEach(check -> check.process(wrapper));

        } else if (packet instanceof PacketPlayOutBlockBreakAnimation) {
            WrappedPlayOutBlockBreakAnimation wrapper = new WrappedPlayOutBlockBreakAnimation(packet);

            playerData.getCheckManager().getChecks().stream()
                    .filter(PacketCheck.class::isInstance)
                    .forEach(check -> check.process(wrapper));

        } else if (packet instanceof PacketPlayOutBlockChange) {
            WrappedPlayOutBlockChange wrapper = new WrappedPlayOutBlockChange(packet);

            playerData.getCheckManager().getChecks().stream()
                    .filter(PacketCheck.class::isInstance)
                    .forEach(check -> check.process(wrapper));

        } else if (packet instanceof PacketPlayOutCamera) {
            WrappedPlayOutCamera wrapper = new WrappedPlayOutCamera(packet);

            playerData.getCheckManager().getChecks().stream()
                    .filter(PacketCheck.class::isInstance)
                    .forEach(check -> check.process(wrapper));

        } else if (packet instanceof PacketPlayOutChat) {
            WrappedPlayOutChat wrapper = new WrappedPlayOutChat(packet);

            playerData.getCheckManager().getChecks().stream()
                    .filter(PacketCheck.class::isInstance)
                    .forEach(check -> check.process(wrapper));

        } else if (packet instanceof PacketPlayOutCloseWindow) {
            WrappedPlayOutCloseWindow wrapper = new WrappedPlayOutCloseWindow(packet);

            playerData.getCheckManager().getChecks().stream()
                    .filter(PacketCheck.class::isInstance)
                    .forEach(check -> check.process(wrapper));

        } else if (packet instanceof PacketPlayOutCollect) {
            WrappedPlayOutCollect wrapper = new WrappedPlayOutCollect(packet);

            playerData.getCheckManager().getChecks().stream()
                    .filter(PacketCheck.class::isInstance)
                    .forEach(check -> check.process(wrapper));

        } else if (packet instanceof PacketPlayOutCombatEvent) {
            WrappedPlayOutCombatEvent wrapper = new WrappedPlayOutCombatEvent(packet);

            playerData.getCheckManager().getChecks().stream()
                    .filter(PacketCheck.class::isInstance)
                    .forEach(check -> check.process(wrapper));

        } else if (packet instanceof PacketPlayOutCustomPayload) {
            WrappedPlayOutCustomPayload wrapper = new WrappedPlayOutCustomPayload(packet);

            playerData.getCheckManager().getChecks().stream()
                    .filter(PacketCheck.class::isInstance)
                    .forEach(check -> check.process(wrapper));

        } else if (packet instanceof PacketPlayOutEntity) {
            WrappedPlayOutEntity wrapper = new WrappedPlayOutEntity(packet);

            playerData.getCheckManager().getChecks().stream()
                    .filter(PacketCheck.class::isInstance)
                    .forEach(check -> check.process(wrapper));

        } else if (packet instanceof PacketPlayOutEntityDestroy) {
            WrappedPlayOutEntityDestroy wrapper = new WrappedPlayOutEntityDestroy(packet);

            playerData.getCheckManager().getChecks().stream()
                    .filter(PacketCheck.class::isInstance)
                    .forEach(check -> check.process(wrapper));

        } else if (packet instanceof PacketPlayOutEntityEffect) {
            WrappedPlayOutEntityEffect wrapper = new WrappedPlayOutEntityEffect(packet);

            playerData.getCheckManager().getChecks().stream()
                    .filter(PacketCheck.class::isInstance)
                    .forEach(check -> check.process(wrapper));

        } else if (packet instanceof PacketPlayOutEntityEquipment) {
            WrappedPlayOutEntityEquipment wrapper = new WrappedPlayOutEntityEquipment(packet);

            playerData.getCheckManager().getChecks().stream()
                    .filter(PacketCheck.class::isInstance)
                    .forEach(check -> check.process(wrapper));

        } else if (packet instanceof PacketPlayOutEntityHeadRotation) {
            WrappedPlayOutEntityHeadRotation wrapper = new WrappedPlayOutEntityHeadRotation(packet);

            playerData.getCheckManager().getChecks().stream()
                    .filter(PacketCheck.class::isInstance)
                    .forEach(check -> check.process(wrapper));

        } else if (packet instanceof PacketPlayOutEntityMetadata) {
            WrappedPlayOutEntityMetadata wrapper = new WrappedPlayOutEntityMetadata(packet);

            playerData.getCheckManager().getChecks().stream()
                    .filter(PacketCheck.class::isInstance)
                    .forEach(check -> check.process(wrapper));

        } else if (packet instanceof PacketPlayOutEntityStatus) {
            WrappedPlayOutEntityStatus wrapper = new WrappedPlayOutEntityStatus(packet);

            playerData.getCheckManager().getChecks().stream()
                    .filter(PacketCheck.class::isInstance)
                    .forEach(check -> check.process(wrapper));

        } else if (packet instanceof PacketPlayOutEntityTeleport) {
            WrappedPlayOutEntityTeleport wrapper = new WrappedPlayOutEntityTeleport(packet);
            int entityId = wrapper.getEntityId();
            int playerId = playerData.getBukkitPlayer().getEntityId();

            if (entityId == playerId) {
                playerData.getActionManager().onTeleport();
            }

            playerData.getCheckManager().getChecks().stream()
                    .filter(PacketCheck.class::isInstance)
                    .forEach(check -> check.process(wrapper));

        } else if (packet instanceof PacketPlayOutEntityVelocity) {
            WrappedPlayOutEntityVelocity wrapper = new WrappedPlayOutEntityVelocity(packet);
            int packetEntityId = wrapper.getEntityId();
            int playerEntityId = playerData.getBukkitPlayer().getEntityId();

            if (packetEntityId == playerEntityId) {
                double velocityX = wrapper.getX();
                double velocityY = wrapper.getY();
                double velocityZ = wrapper.getZ();

                playerData.getVelocityManager().addVelocityEntry(velocityX, velocityY, velocityZ);
            }

            playerData.getCheckManager().getChecks().stream()
                    .filter(PacketCheck.class::isInstance)
                    .forEach(check -> check.process(wrapper));

        } else if (packet instanceof PacketPlayOutExperience) {
            WrappedPlayOutExperience wrapper = new WrappedPlayOutExperience(packet);

            playerData.getCheckManager().getChecks().stream()
                    .filter(PacketCheck.class::isInstance)
                    .forEach(check -> check.process(wrapper));

        } else if (packet instanceof PacketPlayOutExplosion) {
            WrappedPlayOutExplosion wrapper = new WrappedPlayOutExplosion(packet);

            playerData.getCheckManager().getChecks().stream()
                    .filter(PacketCheck.class::isInstance)
                    .forEach(check -> check.process(wrapper));

        } else if (packet instanceof PacketPlayOutGameStateChange) {
            WrappedPlayOutGameStateChange wrapper = new WrappedPlayOutGameStateChange(packet);

            playerData.getCheckManager().getChecks().stream()
                    .filter(PacketCheck.class::isInstance)
                    .forEach(check -> check.process(wrapper));

        } else if (packet instanceof PacketPlayOutHeldItemSlot) {
            WrappedPlayOutHeldItemSlot wrapper = new WrappedPlayOutHeldItemSlot(packet);

            playerData.getCheckManager().getChecks().stream()
                    .filter(PacketCheck.class::isInstance)
                    .forEach(check -> check.process(wrapper));

        } else if (packet instanceof PacketPlayOutKeepAlive) {
            WrappedPlayOutKeepAlive wrapper = new WrappedPlayOutKeepAlive(packet);

            playerData.getCheckManager().getChecks().stream()
                    .filter(PacketCheck.class::isInstance)
                    .forEach(check -> check.process(wrapper));

        } else if (packet instanceof PacketPlayOutKickDisconnect) {
            WrappedPlayOutKickDisconnect wrapper = new WrappedPlayOutKickDisconnect(packet);

            playerData.getCheckManager().getChecks().stream()
                    .filter(PacketCheck.class::isInstance)
                    .forEach(check -> check.process(wrapper));

        } else if (packet instanceof PacketPlayOutLogin) {
            WrappedPlayOutLogin wrapper = new WrappedPlayOutLogin(packet);

            playerData.getCheckManager().getChecks().stream()
                    .filter(PacketCheck.class::isInstance)
                    .forEach(check -> check.process(wrapper));

        } else if (packet instanceof PacketPlayOutMap) {
            WrappedPlayOutMap wrapper = new WrappedPlayOutMap(packet);

            playerData.getCheckManager().getChecks().stream()
                    .filter(PacketCheck.class::isInstance)
                    .forEach(check -> check.process(wrapper));

        } else if (packet instanceof PacketPlayOutMapChunk) {
            WrappedPlayOutMapChunk wrapper = new WrappedPlayOutMapChunk(packet);

            playerData.getCheckManager().getChecks().stream()
                    .filter(PacketCheck.class::isInstance)
                    .forEach(check -> check.process(wrapper));

        } else if (packet instanceof PacketPlayOutMapChunkBulk) {
            WrappedPlayOutMapChunkBulk wrapper = new WrappedPlayOutMapChunkBulk(packet);

            playerData.getCheckManager().getChecks().stream()
                    .filter(PacketCheck.class::isInstance)
                    .forEach(check -> check.process(wrapper));

        } else if (packet instanceof PacketPlayOutMultiBlockChange) {
            WrappedPlayOutMultiBlockChange wrapper = new WrappedPlayOutMultiBlockChange(packet);

            playerData.getCheckManager().getChecks().stream()
                    .filter(PacketCheck.class::isInstance)
                    .forEach(check -> check.process(wrapper));

        } else if (packet instanceof PacketPlayOutNamedEntitySpawn) {
            WrappedPlayOutNamedEntitySpawn wrapper = new WrappedPlayOutNamedEntitySpawn(packet);

            playerData.getCheckManager().getChecks().stream()
                    .filter(PacketCheck.class::isInstance)
                    .forEach(check -> check.process(wrapper));

        } else if (packet instanceof PacketPlayOutNamedSoundEffect) {
            WrappedPlayOutNamedSoundEffect wrapper = new WrappedPlayOutNamedSoundEffect(packet);

            playerData.getCheckManager().getChecks().stream()
                    .filter(PacketCheck.class::isInstance)
                    .forEach(check -> check.process(wrapper));

        } else if (packet instanceof PacketPlayOutOpenSignEditor) {
            WrappedPlayOutOpenSignEditor wrapper = new WrappedPlayOutOpenSignEditor(packet);

            playerData.getCheckManager().getChecks().stream()
                    .filter(PacketCheck.class::isInstance)
                    .forEach(check -> check.process(wrapper));

        } else if (packet instanceof PacketPlayOutOpenWindow) {
            WrappedPlayOutOpenWindow wrapper = new WrappedPlayOutOpenWindow(packet);

            playerData.getCheckManager().getChecks().stream()
                    .filter(PacketCheck.class::isInstance)
                    .forEach(check -> check.process(wrapper));

        } else if (packet instanceof PacketPlayOutPosition) {
            WrappedPlayOutPosition wrapper = new WrappedPlayOutPosition(packet);

            playerData.getActionManager().onTeleport();

            playerData.getCheckManager().getChecks().stream()
                    .filter(PacketCheck.class::isInstance)
                    .forEach(check -> check.process(wrapper));

        } else if (packet instanceof PacketPlayOutPlayerInfo) {
            WrappedPlayOutPlayerInfo wrapper = new WrappedPlayOutPlayerInfo(packet);

            playerData.getCheckManager().getChecks().stream()
                    .filter(PacketCheck.class::isInstance)
                    .forEach(check -> check.process(wrapper));

        } else if (packet instanceof PacketPlayOutPlayerListHeaderFooter) {
            WrappedPlayOutPlayerListHeaderFooter wrapper = new WrappedPlayOutPlayerListHeaderFooter(packet);

            playerData.getCheckManager().getChecks().stream()
                    .filter(PacketCheck.class::isInstance)
                    .forEach(check -> check.process(wrapper));

        } else if (packet instanceof PacketPlayOutRemoveEntityEffect) {
            WrappedPlayOutRemoveEntityEffect wrapper = new WrappedPlayOutRemoveEntityEffect(packet);

            playerData.getCheckManager().getChecks().stream()
                    .filter(PacketCheck.class::isInstance)
                    .forEach(check -> check.process(wrapper));

        } else if (packet instanceof PacketPlayOutResourcePackSend) {
            WrappedPlayOutResourcePackSend wrapper = new WrappedPlayOutResourcePackSend(packet);

            playerData.getCheckManager().getChecks().stream()
                    .filter(PacketCheck.class::isInstance)
                    .forEach(check -> check.process(wrapper));

        } else if (packet instanceof PacketPlayOutRespawn) {
            WrappedPlayOutRespawn wrapper = new WrappedPlayOutRespawn(packet);

            playerData.getCheckManager().getChecks().stream()
                    .filter(PacketCheck.class::isInstance)
                    .forEach(check -> check.process(wrapper));

        } else if (packet instanceof PacketPlayOutScoreboardDisplayObjective) {
            WrappedPlayOutScoreboardDisplayObjective wrapper = new WrappedPlayOutScoreboardDisplayObjective(packet);

            playerData.getCheckManager().getChecks().stream()
                    .filter(PacketCheck.class::isInstance)
                    .forEach(check -> check.process(wrapper));

        } else if (packet instanceof PacketPlayOutScoreboardObjective) {
            WrappedPlayOutScoreboardObjective wrapper = new WrappedPlayOutScoreboardObjective(packet);

            playerData.getCheckManager().getChecks().stream()
                    .filter(PacketCheck.class::isInstance)
                    .forEach(check -> check.process(wrapper));

        } else if (packet instanceof PacketPlayOutScoreboardScore) {
            WrappedPlayOutScoreboardScore wrapper = new WrappedPlayOutScoreboardScore(packet);

            playerData.getCheckManager().getChecks().stream()
                    .filter(PacketCheck.class::isInstance)
                    .forEach(check -> check.process(wrapper));

        } else if (packet instanceof PacketPlayOutScoreboardTeam) {
            WrappedPlayOutScoreboardTeam wrapper = new WrappedPlayOutScoreboardTeam(packet);

            playerData.getCheckManager().getChecks().stream()
                    .filter(PacketCheck.class::isInstance)
                    .forEach(check -> check.process(wrapper));

        } else if (packet instanceof PacketPlayOutServerDifficulty) {
            WrappedPlayOutServerDifficulty wrapper = new WrappedPlayOutServerDifficulty(packet);

            playerData.getCheckManager().getChecks().stream()
                    .filter(PacketCheck.class::isInstance)
                    .forEach(check -> check.process(wrapper));

        } else if (packet instanceof PacketPlayOutSetCompression) {
            WrappedPlayOutSetCompression wrapper = new WrappedPlayOutSetCompression(packet);

            playerData.getCheckManager().getChecks().stream()
                    .filter(PacketCheck.class::isInstance)
                    .forEach(check -> check.process(wrapper));

        } else if (packet instanceof PacketPlayOutSetSlot) {
            WrappedPlayOutSetSlot wrapper = new WrappedPlayOutSetSlot(packet);

            playerData.getCheckManager().getChecks().stream()
                    .filter(PacketCheck.class::isInstance)
                    .forEach(check -> check.process(wrapper));

        } else if (packet instanceof PacketPlayOutSpawnEntity) {
            WrappedPlayOutSpawnEntity wrapper = new WrappedPlayOutSpawnEntity(packet);

            playerData.getCheckManager().getChecks().stream()
                    .filter(PacketCheck.class::isInstance)
                    .forEach(check -> check.process(wrapper));

        } else if (packet instanceof PacketPlayOutSpawnEntityExperienceOrb) {
            WrappedPlayOutSpawnEntityExperienceOrb wrapper = new WrappedPlayOutSpawnEntityExperienceOrb(packet);

            playerData.getCheckManager().getChecks().stream()
                    .filter(PacketCheck.class::isInstance)
                    .forEach(check -> check.process(wrapper));

        } else if (packet instanceof PacketPlayOutSpawnEntityLiving) {
            WrappedPlayOutSpawnEntityLiving wrapper = new WrappedPlayOutSpawnEntityLiving(packet);

            playerData.getCheckManager().getChecks().stream()
                    .filter(PacketCheck.class::isInstance)
                    .forEach(check -> check.process(wrapper));

        } else if (packet instanceof PacketPlayOutSpawnEntityPainting) {
            WrappedPlayOutSpawnEntityPainting wrapper = new WrappedPlayOutSpawnEntityPainting(packet);

            playerData.getCheckManager().getChecks().stream()
                    .filter(PacketCheck.class::isInstance)
                    .forEach(check -> check.process(wrapper));

        } else if (packet instanceof PacketPlayOutSpawnEntityWeather) {
            WrappedPlayOutSpawnEntityWeather wrapper = new WrappedPlayOutSpawnEntityWeather(packet);

            playerData.getCheckManager().getChecks().stream()
                    .filter(PacketCheck.class::isInstance)
                    .forEach(check -> check.process(wrapper));

        } else if (packet instanceof PacketPlayOutSpawnPosition) {
            WrappedPlayOutSpawnPosition wrapper = new WrappedPlayOutSpawnPosition(packet);

            playerData.getCheckManager().getChecks().stream()
                    .filter(PacketCheck.class::isInstance)
                    .forEach(check -> check.process(wrapper));

        } else if (packet instanceof PacketPlayOutStatistic) {
            WrappedPlayOutStatistic wrapper = new WrappedPlayOutStatistic(packet);

            playerData.getCheckManager().getChecks().stream()
                    .filter(PacketCheck.class::isInstance)
                    .forEach(check -> check.process(wrapper));

        } else if (packet instanceof PacketPlayOutTabComplete) {
            WrappedPlayOutTabComplete wrapper = new WrappedPlayOutTabComplete(packet);

            playerData.getCheckManager().getChecks().stream()
                    .filter(PacketCheck.class::isInstance)
                    .forEach(check -> check.process(wrapper));

        } else if (packet instanceof PacketPlayOutTitle) {
            WrappedPlayOutTitle wrapper = new WrappedPlayOutTitle(packet);

            playerData.getCheckManager().getChecks().stream()
                    .filter(PacketCheck.class::isInstance)
                    .forEach(check -> check.process(wrapper));

        } else if (packet instanceof PacketPlayOutTileEntityData) {
            WrappedPlayOutTileEntityData wrapper = new WrappedPlayOutTileEntityData(packet);

            playerData.getCheckManager().getChecks().stream()
                    .filter(PacketCheck.class::isInstance)
                    .forEach(check -> check.process(wrapper));

        } else if (packet instanceof PacketPlayOutTransaction) {
            WrappedPlayOutTransaction wrapper = new WrappedPlayOutTransaction(packet);

            playerData.getCheckManager().getChecks().stream()
                    .filter(PacketCheck.class::isInstance)
                    .forEach(check -> check.process(wrapper));

        } else if (packet instanceof PacketPlayOutUpdateAttributes) {
            WrappedPlayOutUpdateAttributes wrapper = new WrappedPlayOutUpdateAttributes(packet);

            playerData.getCheckManager().getChecks().stream()
                    .filter(PacketCheck.class::isInstance)
                    .forEach(check -> check.process(wrapper));

        } else if (packet instanceof PacketPlayOutUpdateEntityNBT) {
            WrappedPlayOutUpdateEntityNBT wrapper = new WrappedPlayOutUpdateEntityNBT(packet);

            playerData.getCheckManager().getChecks().stream()
                    .filter(PacketCheck.class::isInstance)
                    .forEach(check -> check.process(wrapper));

        } else if (packet instanceof PacketPlayOutUpdateHealth) {
            WrappedPlayOutUpdateHealth wrapper = new WrappedPlayOutUpdateHealth(packet);

            playerData.getCheckManager().getChecks().stream()
                    .filter(PacketCheck.class::isInstance)
                    .forEach(check -> check.process(wrapper));

        } else if (packet instanceof PacketPlayOutUpdateSign) {
            WrappedPlayOutUpdateSign wrapper = new WrappedPlayOutUpdateSign(packet);

            playerData.getCheckManager().getChecks().stream()
                    .filter(PacketCheck.class::isInstance)
                    .forEach(check -> check.process(wrapper));

        } else if (packet instanceof PacketPlayOutUpdateTime) {
            WrappedPlayOutUpdateTime wrapper = new WrappedPlayOutUpdateTime(packet);

            playerData.getCheckManager().getChecks().stream()
                    .filter(PacketCheck.class::isInstance)
                    .forEach(check -> check.process(wrapper));

        } else if (packet instanceof PacketPlayOutWindowData) {
            WrappedPlayOutWindowData wrapper = new WrappedPlayOutWindowData(packet);

            playerData.getCheckManager().getChecks().stream()
                    .filter(PacketCheck.class::isInstance)
                    .forEach(check -> check.process(wrapper));

        } else if (packet instanceof PacketPlayOutWindowItems) {
            WrappedPlayOutWindowItems wrapper = new WrappedPlayOutWindowItems(packet);

            playerData.getCheckManager().getChecks().stream()
                    .filter(PacketCheck.class::isInstance)
                    .forEach(check -> check.process(wrapper));

        } else if (packet instanceof PacketPlayOutWorldBorder) {
            WrappedPlayOutWorldBorder wrapper = new WrappedPlayOutWorldBorder(packet);

            playerData.getCheckManager().getChecks().stream()
                    .filter(PacketCheck.class::isInstance)
                    .forEach(check -> check.process(wrapper));

        } else if (packet instanceof PacketPlayOutWorldEvent) {
            WrappedPlayOutWorldEvent wrapper = new WrappedPlayOutWorldEvent(packet);

            playerData.getCheckManager().getChecks().stream()
                    .filter(PacketCheck.class::isInstance)
                    .forEach(check -> check.process(wrapper));

        } else if (packet instanceof PacketPlayOutWorldParticles) {
            WrappedPlayOutWorldParticles wrapper = new WrappedPlayOutWorldParticles(packet);

            playerData.getCheckManager().getChecks().stream()
                    .filter(PacketCheck.class::isInstance)
                    .forEach(check -> check.process(wrapper));
        }
    }
}
