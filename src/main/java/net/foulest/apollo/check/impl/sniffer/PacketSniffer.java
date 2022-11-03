package net.foulest.apollo.check.impl.sniffer;

import net.foulest.apollo.check.CheckData;
import net.foulest.apollo.check.type.PacketCheck;
import net.foulest.apollo.data.PlayerData;
import net.foulest.apollo.util.LogUtil;
import net.foulest.apollo.util.MessageUtil;
import net.foulest.apollo.wrapper.impl.client.*;

import java.util.Arrays;

@CheckData(name = "PacketSniffer")
public final class PacketSniffer extends PacketCheck {

    public static boolean sniffingIncoming;
    public static boolean sniffingOutgoing;
    public static boolean sniffingFlying;
    public static boolean sniffingTransaction;
    public static boolean sniffingKeepAlive;

    public PacketSniffer(PlayerData playerData) {
        super(playerData);
    }

    @Override
    public void process(Object object) {
        if (object instanceof WrappedPlayInChat) {
            if (playerData.getBukkitPlayer().hasPermission("apollo.sniff")) {
                WrappedPlayInChat packet = (WrappedPlayInChat) object;
                String message = packet.getMessage();

                if (message.equalsIgnoreCase("sniffsniff none")) {
                    sniffingIncoming = false;
                    sniffingOutgoing = false;
                    sniffingFlying = false;
                    sniffingTransaction = false;
                    sniffingKeepAlive = false;
                    MessageUtil.messagePlayer(playerData.getBukkitPlayer(), "&cSniffing packets has been disabled.");

                } else if (message.equalsIgnoreCase("sniffsniff in")) {
                    sniffingIncoming = !sniffingIncoming;
                    MessageUtil.messagePlayer(playerData.getBukkitPlayer(), "&cSniffing Incoming: &f" + sniffingIncoming);

                } else if (message.equalsIgnoreCase("sniffsniff out")) {
                    sniffingOutgoing = !sniffingOutgoing;
                    MessageUtil.messagePlayer(playerData.getBukkitPlayer(), "&cSniffing Outgoing: &f" + sniffingOutgoing);

                } else if (message.equalsIgnoreCase("sniffsniff flying")) {
                    sniffingFlying = !sniffingFlying;
                    MessageUtil.messagePlayer(playerData.getBukkitPlayer(), "&cSniffing Flying: &f" + sniffingFlying);

                } else if (message.equalsIgnoreCase("sniffsniff transaction")) {
                    sniffingTransaction = !sniffingTransaction;
                    MessageUtil.messagePlayer(playerData.getBukkitPlayer(), "&cSniffing Transaction: &f" + sniffingTransaction);

                } else if (message.equalsIgnoreCase("sniffsniff keepalive")) {
                    sniffingKeepAlive = !sniffingKeepAlive;
                    MessageUtil.messagePlayer(playerData.getBukkitPlayer(), "&cSniffing KeepAlive: &f" + sniffingKeepAlive);
                }
            }
        }

        if (sniffingIncoming) {
            if (object instanceof WrappedPlayInAbilities) {
                LogUtil.log("(" + playerData.getBukkitPlayer().getName() + " - " + playerData.getTicks().get() + ")"
                        + " [" + object.getClass().getSimpleName() + "]"
                        + " INVULNERABLE=" + ((WrappedPlayInAbilities) object).isInvulnerable()
                        + " FLYING=" + ((WrappedPlayInAbilities) object).isFlying()
                        + " FLIGHT_ALLOWED=" + ((WrappedPlayInAbilities) object).isFlightAllowed()
                        + " CREATIVE" + ((WrappedPlayInAbilities) object).isInCreative()
                        + " FLY_SPEED=" + ((WrappedPlayInAbilities) object).getFlySpeed()
                        + " FOV_MODIFIER=" + ((WrappedPlayInAbilities) object).getFovModifier());

            } else if (object instanceof WrappedPlayInArmAnimation) {
                LogUtil.log("(" + playerData.getBukkitPlayer().getName() + " - " + playerData.getTicks().get() + ")"
                        + " [" + object.getClass().getSimpleName() + "]");

            } else if (object instanceof WrappedPlayInBlockDig) {
                LogUtil.log("(" + playerData.getBukkitPlayer().getName() + " - " + playerData.getTicks().get() + ")"
                        + " [" + object.getClass().getSimpleName() + "]"
                        + " BLOCK_POS=" + ((WrappedPlayInBlockDig) object).getBlockPosition()
                        + " DIRECTION=" + ((WrappedPlayInBlockDig) object).getDirection()
                        + " DIG_TYPE=" + ((WrappedPlayInBlockDig) object).getDigType());

            } else if (object instanceof WrappedPlayInBlockPlace) {
                LogUtil.log("(" + playerData.getBukkitPlayer().getName() + " - " + playerData.getTicks().get() + ")"
                        + " [" + object.getClass().getSimpleName() + "]"
                        + " BLOCK_POS=" + ((WrappedPlayInBlockPlace) object).getBlockPosition()
                        + " FACE=" + ((WrappedPlayInBlockPlace) object).getFace()
                        + " ITEM_STACK=" + ((WrappedPlayInBlockPlace) object).getItemStack()
                        + " E=" + ((WrappedPlayInBlockPlace) object).getE()
                        + " F=" + ((WrappedPlayInBlockPlace) object).getF()
                        + " G=" + ((WrappedPlayInBlockPlace) object).getG());

            } else if (object instanceof WrappedPlayInChat) {
                LogUtil.log("(" + playerData.getBukkitPlayer().getName() + " - " + playerData.getTicks().get() + ")"
                        + " [" + object.getClass().getSimpleName() + "]"
                        + " MESSAGE=" + ((WrappedPlayInChat) object).getMessage());

            } else if (object instanceof WrappedPlayInClientCommand) {
                LogUtil.log("(" + playerData.getBukkitPlayer().getName() + " - " + playerData.getTicks().get() + ")"
                        + " [" + object.getClass().getSimpleName() + "]"
                        + " COMMAND=" + ((WrappedPlayInClientCommand) object).getCommand());

            } else if (object instanceof WrappedPlayInCloseWindow) {
                LogUtil.log("(" + playerData.getBukkitPlayer().getName() + " - " + playerData.getTicks().get() + ")"
                        + " [" + object.getClass().getSimpleName() + "]"
                        + " WINDOW_ID=" + ((WrappedPlayInCloseWindow) object).getWindowId());

            } else if (object instanceof WrappedPlayInCustomPayload) {
                LogUtil.log("(" + playerData.getBukkitPlayer().getName() + " - " + playerData.getTicks().get() + ")"
                        + " [" + object.getClass().getSimpleName() + "]"
                        + " PAYLOAD=" + ((WrappedPlayInCustomPayload) object).getPayload()
                        + " DATA=" + ((WrappedPlayInCustomPayload) object).getData());

            } else if (object instanceof WrappedPlayInEnchantItem) {
                LogUtil.log("(" + playerData.getBukkitPlayer().getName() + " - " + playerData.getTicks().get() + ")"
                        + " [" + object.getClass().getSimpleName() + "]"
                        + " A=" + ((WrappedPlayInEnchantItem) object).getA()
                        + " B=" + ((WrappedPlayInEnchantItem) object).getB());

            } else if (object instanceof WrappedPlayInEntityAction) {
                LogUtil.log("(" + playerData.getBukkitPlayer().getName() + " - " + playerData.getTicks().get() + ")"
                        + " [" + object.getClass().getSimpleName() + "]"
                        + " ACTION=" + ((WrappedPlayInEntityAction) object).getAction());

            } else if (object instanceof WrappedPlayInHeldItemSlot) {
                LogUtil.log("(" + playerData.getBukkitPlayer().getName() + " - " + playerData.getTicks().get() + ")"
                        + " [" + object.getClass().getSimpleName() + "]"
                        + " SLOT=" + ((WrappedPlayInHeldItemSlot) object).getSlot());

            } else if (object instanceof WrappedPlayInResourcePackStatus) {
                LogUtil.log("(" + playerData.getBukkitPlayer().getName() + " - " + playerData.getTicks().get() + ")"
                        + " [" + object.getClass().getSimpleName() + "]"
                        + " HASH=" + ((WrappedPlayInResourcePackStatus) object).getHash()
                        + " STATUS=" + ((WrappedPlayInResourcePackStatus) object).getStatus());

            } else if (object instanceof WrappedPlayInSettings) {
                LogUtil.log("(" + playerData.getBukkitPlayer().getName() + " - " + playerData.getTicks().get() + ")"
                        + " [" + object.getClass().getSimpleName() + "]"
                        + " LOCALE=" + ((WrappedPlayInSettings) object).getLocale()
                        + " VIEW_DISTANCE=" + ((WrappedPlayInSettings) object).getViewDistance()
                        + " CHAT_VISIBILITY=" + ((WrappedPlayInSettings) object).getChatVisibility()
                        + " D=" + ((WrappedPlayInSettings) object).getD()
                        + " E=" + ((WrappedPlayInSettings) object).getE());

            } else if (object instanceof WrappedPlayInSetCreativeSlot) {
                LogUtil.log("(" + playerData.getBukkitPlayer().getName() + " - " + playerData.getTicks().get() + ")"
                        + " [" + object.getClass().getSimpleName() + "]"
                        + " SLOT=" + ((WrappedPlayInSetCreativeSlot) object).getSlot()
                        + " ITEM_STACK=" + ((WrappedPlayInSetCreativeSlot) object).getItemStack());

            } else if (object instanceof WrappedPlayInSpectate) {
                LogUtil.log("(" + playerData.getBukkitPlayer().getName() + " - " + playerData.getTicks().get() + ")"
                        + " [" + object.getClass().getSimpleName() + "]"
                        + " UUID=" + ((WrappedPlayInSpectate) object).getUUID());

            } else if (object instanceof WrappedPlayInTabComplete) {
                LogUtil.log("(" + playerData.getBukkitPlayer().getName() + " - " + playerData.getTicks().get() + ")"
                        + " [" + object.getClass().getSimpleName() + "]"
                        + " MESSAGE=" + ((WrappedPlayInTabComplete) object).getMessage()
                        + " BLOCK_POS=" + ((WrappedPlayInTabComplete) object).getBlockPosition());

            } else if (object instanceof WrappedPlayInUseEntity) {
                LogUtil.log("(" + playerData.getBukkitPlayer().getName() + " - " + playerData.getTicks().get() + ")"
                        + " [" + object.getClass().getSimpleName() + "]"
                        + " ENTITY_ID=" + ((WrappedPlayInUseEntity) object).getEntityId()
                        + " VECTOR=" + ((WrappedPlayInUseEntity) object).getVector()
                        + " ACTION=" + ((WrappedPlayInUseEntity) object).getAction());

            } else if (object instanceof WrappedPlayInUpdateSign) {
                LogUtil.log("(" + playerData.getBukkitPlayer().getName() + " - " + playerData.getTicks().get() + ")"
                        + " [" + object.getClass().getSimpleName() + "]"
                        + " BLOCK_POS=" + ((WrappedPlayInUpdateSign) object).getBlockPosition()
                        + " LINES=" + Arrays.toString(((WrappedPlayInUpdateSign) object).getLines()));

            } else if (object instanceof WrappedPlayInWindowClick) {
                LogUtil.log("(" + playerData.getBukkitPlayer().getName() + " - " + playerData.getTicks().get() + ")"
                        + " [" + object.getClass().getSimpleName() + "]"
                        + " WINDOW_ID=" + ((WrappedPlayInWindowClick) object).getWindowId()
                        + " CLICK_TYPE=" + ((WrappedPlayInWindowClick) object).getClickType()
                        + " SLOT=" + ((WrappedPlayInWindowClick) object).getSlot()
                        + " ITEM_STACK=" + ((WrappedPlayInWindowClick) object).getItemStack()
                        + " BUTTON=" + ((WrappedPlayInWindowClick) object).getButton()
                        + " SHIFT=" + ((WrappedPlayInWindowClick) object).getShift());

            } else if (object instanceof WrappedPlayInFlying) {
                if (((WrappedPlayInFlying) object).hasPos() || ((WrappedPlayInFlying) object).hasLook()) {
                    LogUtil.log("(" + playerData.getBukkitPlayer().getName() + " - " + playerData.getTicks().get() + ")"
                            + " [" + object.getClass().getSimpleName() + "]"
                            + " X=" + ((WrappedPlayInFlying) object).getX()
                            + " Y=" + ((WrappedPlayInFlying) object).getY()
                            + " Z=" + ((WrappedPlayInFlying) object).getZ()
                            + " YAW=" + ((WrappedPlayInFlying) object).getYaw()
                            + " PITCH=" + ((WrappedPlayInFlying) object).getPitch()
                            + " LOOK=" + ((WrappedPlayInFlying) object).hasLook()
                            + " POS=" + ((WrappedPlayInFlying) object).hasPos()
                            + " ON_GROUND=" + ((WrappedPlayInFlying) object).isOnGround());
                }
            }
        }

        if (sniffingOutgoing) {
            // TODO: Write packet sniffers for all remaining outbound packets
        }

        if (sniffingFlying) {
            if (object instanceof WrappedPlayInFlying) {
                if (!((WrappedPlayInFlying) object).hasPos() && !((WrappedPlayInFlying) object).hasLook()) {
                    LogUtil.log("(" + playerData.getBukkitPlayer().getName() + " - " + playerData.getTicks().get() + ")"
                            + " [" + object.getClass().getSimpleName() + "]"
                            + " X=" + ((WrappedPlayInFlying) object).getX()
                            + " Y=" + ((WrappedPlayInFlying) object).getY()
                            + " Z=" + ((WrappedPlayInFlying) object).getZ()
                            + " YAW=" + ((WrappedPlayInFlying) object).getYaw()
                            + " PITCH=" + ((WrappedPlayInFlying) object).getPitch()
                            + " LOOK=" + ((WrappedPlayInFlying) object).hasLook()
                            + " POS=" + ((WrappedPlayInFlying) object).hasPos()
                            + " ON_GROUND=" + ((WrappedPlayInFlying) object).isOnGround());
                }
            }
        }

        if (sniffingTransaction) {
            if (object instanceof WrappedPlayInTransaction) {
                LogUtil.log("(" + playerData.getBukkitPlayer().getName() + " - " + playerData.getTicks().get() + ")"
                        + " [" + object.getClass().getSimpleName() + "]"
                        + " HASH=" + ((WrappedPlayInTransaction) object).getHash()
                        + " ID=" + ((WrappedPlayInTransaction) object).getId()
                        + " ACCEPTED=" + ((WrappedPlayInTransaction) object).isAccepted());
            }
        }

        if (sniffingKeepAlive) {
            if (object instanceof WrappedPlayInKeepAlive) {
                LogUtil.log("(" + playerData.getBukkitPlayer().getName() + " - " + playerData.getTicks().get() + ")"
                        + " [" + object.getClass().getSimpleName() + "]"
                        + " ID=" + ((WrappedPlayInKeepAlive) object).getId());
            }
        }
    }
}
