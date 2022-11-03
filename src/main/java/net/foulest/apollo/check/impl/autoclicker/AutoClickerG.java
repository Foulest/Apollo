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

@CheckData(name = "AutoClicker (G)")
public final class AutoClickerG extends PacketCheck {

    private final Deque<Integer> samples = Lists.newLinkedList();
    private int movements = 0;

    public AutoClickerG(PlayerData playerData) {
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

            // Sample size is assigned to 15
            if (samples.size() == 15) {
                Pair<List<Double>, List<Double>> outlierPair = MathUtil.getOutliers(samples);

                double skewness = MathUtil.getSkewness(samples);
                double kurtosis = MathUtil.getKurtosis(samples);
                double outliers = outlierPair.getFirst().size() + outlierPair.getSecond().size();

                // See if skewness and kurtosis is exceeding a specific limit.
                if (skewness < 0.035 && kurtosis < 0.1 && outliers < 2) {
                    playerData.kick(getCheckName(), "");
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
