package net.foulest.apollo.data.impl;

import com.google.common.collect.ClassToInstanceMap;
import com.google.common.collect.ImmutableClassToInstanceMap;
import lombok.Getter;
import net.foulest.apollo.check.Check;
import net.foulest.apollo.check.impl.aimassist.*;
import net.foulest.apollo.check.impl.aimassist.cinematic.Cinematic;
import net.foulest.apollo.check.impl.autoblock.*;
import net.foulest.apollo.check.impl.autoclicker.*;
import net.foulest.apollo.check.impl.autoheal.AutoHealA;
import net.foulest.apollo.check.impl.autoheal.AutoHealB;
import net.foulest.apollo.check.impl.autoheal.AutoHealC;
import net.foulest.apollo.check.impl.badpackets.*;
import net.foulest.apollo.check.impl.clientbrand.ClientBrand;
import net.foulest.apollo.check.impl.hitbox.HitboxA;
import net.foulest.apollo.check.impl.inventory.*;
import net.foulest.apollo.check.impl.killaura.*;
import net.foulest.apollo.check.impl.pingspoof.*;
import net.foulest.apollo.check.impl.sniffer.PacketSniffer;
import net.foulest.apollo.check.impl.timer.TimerA;
import net.foulest.apollo.data.PlayerData;

import java.util.Collection;

@Getter
public final class CheckManager {

    private final ClassToInstanceMap<Check> checks;

    public CheckManager(PlayerData playerData) {
        checks = new ImmutableClassToInstanceMap.Builder<Check>()
                .put(PacketSniffer.class, new PacketSniffer(playerData))

                .put(ClientBrand.class, new ClientBrand(playerData))

                .put(KillAuraA.class, new KillAuraA(playerData))
                .put(KillAuraB.class, new KillAuraB(playerData))
                .put(KillAuraC.class, new KillAuraC(playerData))
                .put(KillAuraD.class, new KillAuraD(playerData))
                .put(KillAuraE.class, new KillAuraE(playerData))
                .put(KillAuraF.class, new KillAuraF(playerData))
                .put(KillAuraG.class, new KillAuraG(playerData))
                .put(KillAuraH.class, new KillAuraH(playerData))
                .put(KillAuraI.class, new KillAuraI(playerData))
                .put(KillAuraJ.class, new KillAuraJ(playerData))

                .put(HitboxA.class, new HitboxA(playerData))

                .put(Cinematic.class, new Cinematic(playerData))
                .put(AimAssistA.class, new AimAssistA(playerData))
                .put(AimAssistB.class, new AimAssistB(playerData))
                .put(AimAssistC.class, new AimAssistC(playerData))
                .put(AimAssistD.class, new AimAssistD(playerData))
                .put(AimAssistE.class, new AimAssistE(playerData))

                .put(AutoBlockA.class, new AutoBlockA(playerData))
                .put(AutoBlockB.class, new AutoBlockB(playerData))
                .put(AutoBlockC.class, new AutoBlockC(playerData))
                .put(AutoBlockD.class, new AutoBlockD(playerData))
                .put(AutoBlockE.class, new AutoBlockE(playerData))
                .put(AutoBlockF.class, new AutoBlockF(playerData))

                .put(AutoHealA.class, new AutoHealA(playerData))
                .put(AutoHealB.class, new AutoHealB(playerData))
                .put(AutoHealC.class, new AutoHealC(playerData))

                .put(BadPacketsA.class, new BadPacketsA(playerData))
                .put(BadPacketsB.class, new BadPacketsB(playerData))
                .put(BadPacketsC.class, new BadPacketsC(playerData))
                .put(BadPacketsD.class, new BadPacketsD(playerData))
                .put(BadPacketsE.class, new BadPacketsE(playerData))
                .put(BadPacketsF.class, new BadPacketsF(playerData))
                .put(BadPacketsG.class, new BadPacketsG(playerData))
                .put(BadPacketsH.class, new BadPacketsH(playerData))
                .put(BadPacketsI.class, new BadPacketsI(playerData))
                .put(BadPacketsJ.class, new BadPacketsJ(playerData))
                .put(BadPacketsK.class, new BadPacketsK(playerData))
                .put(BadPacketsL.class, new BadPacketsL(playerData))
                .put(BadPacketsM.class, new BadPacketsM(playerData))
                .put(BadPacketsN.class, new BadPacketsN(playerData))
                .put(BadPacketsP.class, new BadPacketsP(playerData))

                .put(AutoClickerC.class, new AutoClickerC(playerData))
                .put(AutoClickerD.class, new AutoClickerD(playerData))
                .put(AutoClickerF.class, new AutoClickerF(playerData))
                .put(AutoClickerG.class, new AutoClickerG(playerData))

                .put(InventoryB.class, new InventoryB(playerData))
                .put(InventoryC.class, new InventoryC(playerData))
                .put(InventoryD.class, new InventoryD(playerData))
                .put(InventoryE.class, new InventoryE(playerData))
                .put(InventoryF.class, new InventoryF(playerData))
                .put(InventoryG.class, new InventoryG(playerData))
                .put(InventoryH.class, new InventoryH(playerData))
                .put(InventoryI.class, new InventoryI(playerData))
                .put(InventoryJ.class, new InventoryJ(playerData))
                .put(InventoryK.class, new InventoryK(playerData))
                .put(InventoryL.class, new InventoryL(playerData))

                .put(TimerA.class, new TimerA(playerData))

                .put(PingSpoofA.class, new PingSpoofA(playerData))
                .put(PingSpoofB.class, new PingSpoofB(playerData))
                .put(PingSpoofC.class, new PingSpoofC(playerData))
                .put(PingSpoofD.class, new PingSpoofD(playerData))
                .put(PingSpoofE.class, new PingSpoofE(playerData))
                .put(PingSpoofF.class, new PingSpoofF(playerData))
                .put(PingSpoofG.class, new PingSpoofG(playerData))
                .put(PingSpoofH.class, new PingSpoofH(playerData))
                .put(PingSpoofI.class, new PingSpoofI(playerData))
                .put(PingSpoofJ.class, new PingSpoofJ(playerData))
                .put(PingSpoofK.class, new PingSpoofK(playerData))
                .put(PingSpoofL.class, new PingSpoofL(playerData))
                .build();
    }

    public Collection<Check> getChecks() {
        return checks.values();
    }

    public Check<?> getCheck(Class<? extends Check> clazz) {
        return checks.getInstance(clazz);
    }
}
