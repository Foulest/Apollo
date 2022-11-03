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

@CheckData(name = "AutoClicker (C)")
public final class AutoClickerC extends PacketCheck {

    private final Deque<Integer> samples = Lists.newLinkedList();
    private int movements = 0;
    private int buffer = 0;

    public AutoClickerC(PlayerData playerData) {
        super(playerData);
    }

    @Override
    public void process(Object object) {
        if (isExempt(ExemptType.LAGGING, ExemptType.TPS)) {
            return;
        }

        if (object instanceof WrappedPlayInArmAnimation) {
            boolean valid = movements < 4 && !playerData.getActionManager().getDigging().get();

            if (valid) {
                samples.add(movements);
            }

            //Sample size is adjustable. Can flag as low as 12CPS or lower depending on clicker patterns.
            if (samples.size() == 15) {
                Pair<List<Double>, List<Double>> outlierPair = MathUtil.getOutliers(samples);

                double skewness = MathUtil.getSkewness(samples);
                double kurtosis = MathUtil.getKurtosis(samples);
                double outliers = outlierPair.getFirst().size() + outlierPair.getSecond().size();

                // See if skewness and kurtosis is exceeding a specific limit.
                if (skewness < 0.75 && kurtosis < 0.0 && outliers < 2) {
                    if (++buffer > 1) {
                        playerData.kick(getCheckName(), "");
                    }
                } else {
                    buffer = 0;
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
