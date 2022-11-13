package net.foulest.apollo.alert;

import com.google.common.collect.Lists;
import lombok.RequiredArgsConstructor;
import net.foulest.apollo.check.Check;
import net.foulest.apollo.util.MessageUtil;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import net.foulest.apollo.Apollo;
import net.foulest.apollo.data.PlayerData;
import net.foulest.apollo.util.ColorUtil;

import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
public final class AlertManager {

    private final Check<?> check;
    private final String base = ColorUtil.format("&a(Apollo) &f%s &7failed &f%s &c(x%s) %s");
    private final String broadcast = ColorUtil.format("&c%s has been banned for cheating.");
    private final List<Long> alerts = Lists.newArrayList();

    public void fail(String verbose, boolean ban) {
        long now = System.currentTimeMillis();
        PlayerData playerData = check.getPlayerData();
        Player player = playerData.getBukkitPlayer();

        if (playerData.getKicking().get()) {
            return;
        }

        if (alerts.contains(now)) {
            return;
        }

        alerts.add(now);

        int violations = (int) (alerts.stream().filter(violation -> violation + 9000L > System.currentTimeMillis()).count());
        int threshold = check.getThreshold();

        String alert = String.format(base, player.getName(), check.getCheckName(), violations,
                (Objects.equals(verbose, "") ? "" : "&7(" + verbose + ")"));
        String message = String.format(broadcast, player.getName());

        if (violations > threshold) {
            if (ban) {
                playerData.ban(check.getCheckName(), verbose, "Unfair Advantage", true);
                Bukkit.broadcastMessage(message);
            } else {
                playerData.kick(check.getCheckName(), verbose, "Disconnected");
            }

            alerts.clear();
        }

        sendAlert(alert);
    }

    public static void sendAlert(String alert) {
        // Execute the alert on a separate thread as we need to loop
        Apollo.INSTANCE.getExecutorAlert().execute(() -> Bukkit.getOnlinePlayers()
                .stream()
                .filter(send -> send.hasPermission("apollo.alerts"))
                .forEach(send -> MessageUtil.messagePlayer(send, alert)));
    }
}
