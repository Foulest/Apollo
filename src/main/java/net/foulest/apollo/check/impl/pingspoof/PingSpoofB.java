package net.foulest.apollo.check.impl.pingspoof;

import net.foulest.apollo.check.type.PacketCheck;
import net.foulest.apollo.exempt.type.ExemptType;
import net.foulest.apollo.wrapper.impl.client.WrappedPlayInFlying;
import net.foulest.apollo.check.CheckData;
import net.foulest.apollo.data.PlayerData;

@CheckData(name = "PingSpoof (B)")
public final class PingSpoofB extends PacketCheck {

    public PingSpoofB(PlayerData playerData) {
        super(playerData);
    }

    @Override
    public void process(Object object) {
        if (isExempt(ExemptType.LAGGING, ExemptType.TPS, ExemptType.TELEPORTING, ExemptType.CHUNK)) {
            return;
        }

        if (object instanceof WrappedPlayInFlying) {
            long transactionPing = playerData.getTransactionPing().get();
            long keepAlivePing = playerData.getKeepAlivePing().get();
            long pingDiff = Math.abs(keepAlivePing - transactionPing);
            boolean joined = playerData.getTicks().get() - playerData.getJoined().get() < 10;

            if (!joined && keepAlivePing > transactionPing && pingDiff > 98) {
                playerData.kick(getCheckName(), "DIFF=" + pingDiff);
            }
        }
    }
}
