package net.foulest.apollo.check.impl.killaura;

import com.google.common.collect.Lists;
import net.foulest.apollo.check.type.RotationCheck;
import net.foulest.apollo.exempt.type.ExemptType;
import net.foulest.apollo.update.RotationUpdate;
import net.foulest.apollo.check.CheckData;
import net.foulest.apollo.data.PlayerData;

import java.util.Deque;
import java.util.concurrent.atomic.AtomicInteger;

@CheckData(name = "KillAura (C)")
public final class KillAuraC extends RotationCheck {

    private final Deque<Float> samplesYaw = Lists.newLinkedList();
    private final Deque<Float> samplesPitch = Lists.newLinkedList();

    public KillAuraC(PlayerData playerData) {
        super(playerData);
    }

    @Override
    public void process(RotationUpdate rotationUpdate) {
        if (isExempt(ExemptType.LAGGING, ExemptType.TPS, ExemptType.TELEPORTING)) {
            return;
        }

        float deltaYaw = rotationUpdate.getDeltaYaw();
        float deltaPitch = rotationUpdate.getDeltaPitch();

        if (deltaYaw > 0.0 && deltaPitch > 0.0) {
            samplesPitch.add(deltaPitch);
            samplesYaw.add(deltaYaw);
        }

        if (samplesPitch.size() == 10 && samplesYaw.size() == 10) {
            AtomicInteger level = new AtomicInteger(0);

            double averageYaw = samplesYaw.stream().mapToDouble(d -> d).average().orElse(0.0);
            double averagePitch = samplesPitch.stream().mapToDouble(d -> d).average().orElse(0.0);

            samplesYaw.stream().filter(delta -> delta % 1.0 == 0.0).forEach(delta -> level.incrementAndGet());
            samplesPitch.stream().filter(delta -> delta % 1.0 == 0.0).forEach(delta -> level.incrementAndGet());

            if (level.get() >= 8 && averageYaw > 1.d && averagePitch > 1.d) {
                playerData.kick(getCheckName(), "");
            }
        }
    }
}
