package net.foulest.apollo.util;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.UtilityClass;
import org.bukkit.ChatColor;

import java.util.Collections;
import java.util.List;

@UtilityClass
public class GraphUtil {

    public GraphResult getGraph(List<Double> values) {
        StringBuilder graph = new StringBuilder();
        double largest = Collections.max(values);
        int graphHeight = 2;
        int positives = 0;
        int negatives = 0;

        for (int i = graphHeight - 1; i > 0; i -= 1) {
            StringBuilder sb = new StringBuilder();

            for (double index : values) {
                double value = graphHeight * index / largest;

                if (value > i && value < i + 1) {
                    ++positives;
                    sb.append(String.format("%s+", ChatColor.GREEN));
                } else {
                    ++negatives;
                    sb.append(String.format("%s-", ChatColor.RED));
                }
            }

            graph.append(sb);
        }

        return new GraphResult(graph.toString(), positives, negatives);
    }

    @Getter
    @Setter
    @RequiredArgsConstructor
    public class GraphResult {
        private final String graph;
        private final int positives;
        private final int negatives;
    }
}
