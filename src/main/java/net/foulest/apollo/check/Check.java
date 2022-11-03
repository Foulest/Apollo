package net.foulest.apollo.check;

import com.google.common.collect.Lists;
import lombok.Getter;
import net.foulest.apollo.exempt.type.ExemptType;
import net.foulest.apollo.alert.AlertManager;
import net.foulest.apollo.data.PlayerData;
import net.foulest.apollo.util.LogUtil;

import java.util.List;

@Getter
public abstract class Check<T> {

    protected final PlayerData playerData;
    private final AlertManager alertManager = new AlertManager(this);
    private final List<Long> violations = Lists.newArrayList();
    private String checkName;
    private int threshold;

    public Check(PlayerData playerData) {
        this.playerData = playerData;
        Class<?> checkClass = getClass();

        if (checkClass.isAnnotationPresent(CheckData.class)) {
            CheckData checkData = checkClass.getAnnotation(CheckData.class);
            checkName = checkData.name();
            threshold = checkData.threshold();
        } else {
            LogUtil.log("Check annotation not found in class: " + checkClass.getSimpleName());
        }
    }

    protected void fail() {
        alertManager.fail("");
    }

    protected void fail(String verbose) {
        alertManager.fail(verbose);
    }

    protected boolean isExempt(ExemptType exemptType) {
        return playerData.getExemptManager().isExempt(exemptType);
    }

    protected boolean isExempt(ExemptType... exemptTypes) {
        return playerData.getExemptManager().isExempt(exemptTypes);
    }

    public abstract void process(T object);
}
