package net.foulest.apollo.check.impl.aimassist.cinematic;

import com.google.common.collect.Lists;
import net.foulest.apollo.check.type.RotationCheck;
import net.foulest.apollo.update.RotationUpdate;
import net.foulest.apollo.check.CheckData;
import net.foulest.apollo.data.PlayerData;
import net.foulest.apollo.util.GraphUtil;

import java.util.List;

@CheckData(name = "Cinematic")
public final class Cinematic extends RotationCheck {

    private final List<Double> yawSamples = Lists.newArrayList();
    private final List<Double> pitchSamples = Lists.newArrayList();
    private long lastSmooth = 0L;
    private long lastHighRate = 0L;
    private double lastDeltaYaw = 0.0;
    private double lastDeltaPitch = 0.0;

    public Cinematic(PlayerData playerData) {
        super(playerData);
    }

    @Override
    public void process(RotationUpdate rotationUpdate) {
        long now = System.currentTimeMillis();
        double deltaYaw = rotationUpdate.getDeltaYaw();
        double deltaPitch = rotationUpdate.getDeltaPitch();
        double differenceYaw = Math.abs(deltaYaw - lastDeltaYaw);
        double differencePitch = Math.abs(deltaPitch - lastDeltaPitch);
        double joltYaw = Math.abs(differenceYaw - deltaYaw);
        double joltPitch = Math.abs(differencePitch - deltaPitch);
        boolean cinematic = (now - lastHighRate > 250L) || now - lastSmooth < 9000L;

        if (joltYaw > 1.0 && joltPitch > 1.0) {
            lastHighRate = now;
        }

        if (deltaYaw > 0.0 && deltaPitch > 0.0) {
            yawSamples.add(deltaYaw);
            pitchSamples.add(deltaPitch);
        }

        if (yawSamples.size() == 20 && pitchSamples.size() == 20) {
            // Get the cerberus/positive graph of the sample-lists
            GraphUtil.GraphResult resultsYaw = GraphUtil.getGraph(yawSamples);
            GraphUtil.GraphResult resultsPitch = GraphUtil.getGraph(pitchSamples);

            // Negative values
            int negativesYaw = resultsYaw.getNegatives();
            int negativesPitch = resultsPitch.getNegatives();

            // Positive values
            int positivesYaw = resultsYaw.getPositives();
            int positivesPitch = resultsPitch.getPositives();

            // Cinematic camera usually does this on *most* speeds and is accurate for the most part.
            if (positivesYaw > negativesYaw || positivesPitch > negativesPitch) {
                lastSmooth = now;
            }

            yawSamples.clear();
            pitchSamples.clear();
        }

        playerData.getCinematic().set(cinematic);

        lastDeltaYaw = deltaYaw;
        lastDeltaPitch = deltaPitch;
    }
}
