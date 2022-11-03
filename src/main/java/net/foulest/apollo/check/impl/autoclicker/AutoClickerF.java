package net.foulest.apollo.check.impl.autoclicker;

import com.google.common.collect.Lists;
import net.foulest.apollo.check.type.PacketCheck;
import net.foulest.apollo.exempt.type.ExemptType;
import net.foulest.apollo.wrapper.impl.client.WrappedPlayInArmAnimation;
import net.foulest.apollo.wrapper.impl.client.WrappedPlayInFlying;
import net.foulest.apollo.check.CheckData;
import net.foulest.apollo.data.PlayerData;
import net.foulest.apollo.util.MathUtil;
import net.foulest.apollo.util.Pair;

import java.util.Deque;
import java.util.List;

@CheckData(name = "AutoClicker (F)")
public final class AutoClickerF extends PacketCheck {

    private final Deque<Integer> samples = Lists.newLinkedList();
    private int movements = 0;
    private double buffer = 0.0d;

    public AutoClickerF(PlayerData playerData) {
        super(playerData);
    }

    @Override
    public void process(Object object) {
        if (isExempt(ExemptType.LAGGING, ExemptType.TPS)) {
            return;
        }

        if (object instanceof WrappedPlayInArmAnimation) {
            boolean valid = movements < 4 && !playerData.getActionManager().getDigging().get();

            // If the movements is smaller than 4 and the player isn't digging
            if (valid) {
                samples.add(movements);
            }

            // Once the samples size is equal to 15
            if (samples.size() == 15) {
                Pair<List<Double>, List<Double>> outlierPair = MathUtil.getOutliers(samples);

                // Get the deviation outliers the cps from the math util
                double deviation = MathUtil.getStandardDeviation(samples);
                double outliers = outlierPair.getFirst().size() + outlierPair.getSecond().size();
                double cps = playerData.getCps().get();

                // If the deviation is relatively low along with the outliers and the cps is rounded
                if (deviation < 0.3 && outliers < 2 && cps % 1.0 == 0.0) {
                    buffer += 0.25;

                    if (buffer > 0.75) {
                        playerData.kick(getCheckName(), "");
                    }
                } else {
                    buffer = Math.max(buffer - 0.2, 0);
                }

                // Clear the samples
                samples.clear();
            }

            // Reset the movements
            movements = 0;

        } else if (object instanceof WrappedPlayInFlying) {
            ++movements;
        }
    }
}
