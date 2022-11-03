package net.foulest.apollo.update;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public final class RotationUpdate {

    private float deltaYaw;
    private float deltaPitch;
}
