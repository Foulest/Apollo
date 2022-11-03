package net.foulest.apollo.data.impl;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import net.foulest.apollo.observable.Observable;
import net.foulest.apollo.util.EvictingList;
import net.foulest.apollo.util.MathUtil;
import net.foulest.apollo.data.PlayerData;

@Getter
@RequiredArgsConstructor
public final class ActionManager {

    private final PlayerData playerData;
    private final EvictingList<Integer> clicks = new EvictingList<>(10);

    private final Observable<Boolean> placing = new Observable<>(false);
    private final Observable<Boolean> attacking = new Observable<>(false);
    private final Observable<Boolean> swinging = new Observable<>(false);
    private final Observable<Boolean> digging = new Observable<>(false);
    private final Observable<Boolean> delayed = new Observable<>(false);
    private final Observable<Boolean> teleported = new Observable<>(false);
    private final Observable<Boolean> steer = new Observable<>(false);
    private final Observable<Boolean> packetDigging = new Observable<>(false);
    private final Observable<Boolean> ridingJumping = new Observable<>(false);

    private int lastAttack = 0;
    private int lastDig = 0;
    private int lastFlying = 0;
    private int lastDelayedFlying = 0;
    private int lastTeleport = 0;
    private int lastPlace = 0;
    private int lastRidingJump = 0;
    private int lastStartSprinting = 0;
    private int lastStopSprinting = 0;
    private int lastStartSneaking = 0;
    private int lastStopSneaking = 0;
    private int lastStartSleeping = 0;
    private int lastStopSleeping = 0;
    private int lastOpenEntityInventory = 0;
    private int lastCloseEntityInventory = 0;

    private int movements = 0;

    public void onArmAnimation() {
        swinging.set(true);

        click:
        {
            if (digging.get() || movements > 5) {
                break click;
            }

            clicks.add(movements);
        }

        if (clicks.size() > 5) {
            double cps = MathUtil.getCps(clicks);
            double rate = cps * movements;

            playerData.getCps().set(cps);
            playerData.getRate().set(rate);
        }

        movements = 0;
    }

    public void onAttack() {
        attacking.set(true);
        lastAttack = playerData.getTicks().get();
    }

    public void onPlace() {
        placing.set(true);
        lastPlace = playerData.getTicks().get();
    }

    public void onDig() {
        packetDigging.set(true);
        lastDig = playerData.getTicks().get();
    }

    public void onRidingJump() {
        ridingJumping.set(true);
        lastRidingJump = playerData.getTicks().get();
    }

    public void onSprinting(boolean start) {
        if (start) {
            lastStartSprinting = playerData.getTicks().get();
        } else {
            lastStopSprinting = playerData.getTicks().get();
        }
    }

    public void onSneaking(boolean start) {
        if (start) {
            lastStartSneaking = playerData.getTicks().get();
        } else {
            lastStopSneaking = playerData.getTicks().get();
        }
    }

    public void onSleeping(boolean start) {
        if (start) {
            lastStartSleeping = playerData.getTicks().get();
        } else {
            lastStopSleeping = playerData.getTicks().get();
        }
    }

    public void onEntityInventory(boolean start) {
        if (start) {
            lastOpenEntityInventory = playerData.getTicks().get();
        } else {
            lastCloseEntityInventory = playerData.getTicks().get();
        }
    }

    public void onFlying() {
        int now = playerData.getTicks().get();
        int attack = now - lastAttack;

        boolean delayed = now - lastFlying > 2;
        boolean digging = now - lastDig < 15 || packetDigging.get();
        boolean lagging = now - lastDelayedFlying < 2;
        boolean teleporting = now - lastTeleport < 2;
        boolean recent = attack < 200;

        placing.set(false);
        attacking.set(false);
        swinging.set(false);
        attacking.set(false);
        steer.set(false);
        packetDigging.set(false);
        ridingJumping.set(false);

        this.digging.set(digging);
        this.delayed.set(lagging);
        teleported.set(teleporting);

        lastDelayedFlying = delayed ? now : lastDelayedFlying;
        lastFlying = now;

        playerData.getTarget().set(recent ? playerData.getTarget().get() : null);
        playerData.getTicks().set(now + 1);

        movements++;
    }

    public void onSteerVehicle() {
        steer.set(true);
    }

    public void onTeleport() {
        lastTeleport = playerData.getTicks().get();
    }

    public void onBukkitDig() {
        lastDig = playerData.getTicks().get();
    }
}
