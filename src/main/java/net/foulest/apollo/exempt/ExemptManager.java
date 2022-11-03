package net.foulest.apollo.exempt;

import lombok.RequiredArgsConstructor;
import net.foulest.apollo.exempt.type.ExemptType;
import net.foulest.apollo.data.PlayerData;

import java.util.Arrays;
import java.util.function.Function;

@RequiredArgsConstructor
public final class ExemptManager {

    private final PlayerData playerData;

    /**
     * @param exceptType - The type of exception you want return.
     * @return - True/False depending on appliance.
     */
    public boolean isExempt(ExemptType exceptType) {
        return exceptType.getException().apply(playerData);
    }

    /**
     * @param exceptTypes - An array of possible exceptions.
     * @return - True/False depending on if any match the appliance.
     */
    public boolean isExempt(ExemptType... exceptTypes) {
        return Arrays.stream(exceptTypes).anyMatch(this::isExempt);
    }

    /**
     * @param exception - A custom function-based exception
     * @return - True/False depending on appliance.
     */
    public boolean isExempt(Function<PlayerData, Boolean> exception) {
        return exception.apply(playerData);
    }
}
