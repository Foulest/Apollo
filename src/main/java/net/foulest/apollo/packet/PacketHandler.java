package net.foulest.apollo.packet;

import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import lombok.RequiredArgsConstructor;
import net.foulest.apollo.Apollo;
import net.foulest.apollo.data.PlayerData;
import net.foulest.apollo.processor.impl.*;
import net.minecraft.server.v1_8_R3.*;

@RequiredArgsConstructor
public final class PacketHandler extends ChannelDuplexHandler {

    private final PlayerData playerData;

    @Override
    public void write(ChannelHandlerContext channelHandlerContext, Object object, ChannelPromise channelPromise) throws Exception {
        super.write(channelHandlerContext, object, channelPromise);

        try {
            Packet<PacketListenerPlayOut> packet = (Packet<PacketListenerPlayOut>) object;

            Apollo.INSTANCE.getProcessorManager()
                    .getProcessor(OutgoingPacketProcessor.class)
                    .process(playerData, packet);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    @Override
    public void channelRead(ChannelHandlerContext channelHandlerContext, Object object) throws Exception {
        super.channelRead(channelHandlerContext, object);

        try {
            Packet<PacketListenerPlayIn> packet = (Packet<PacketListenerPlayIn>) object;

            Apollo.INSTANCE.getProcessorManager()
                    .getProcessor(IncomingPacketProcessor.class)
                    .process(playerData, packet);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }
}
