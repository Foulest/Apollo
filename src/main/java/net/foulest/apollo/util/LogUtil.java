package net.foulest.apollo.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class LogUtil {

    public void log(String log) {
        System.out.println("[Apollo]: " + log);
    }
}
