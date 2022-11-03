package net.foulest.apollo.update;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Location;

@AllArgsConstructor
@Getter
@Setter
public final class PositionUpdate {

    private final Location from;
    private final Location to;
    private final boolean onGround;
}
