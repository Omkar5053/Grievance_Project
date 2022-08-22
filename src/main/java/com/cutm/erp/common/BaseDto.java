package com.cutm.erp.common;
import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseDto {

    @Transient
    private List<String> actions = new ArrayList<>();

    public List<String> getActions() {
        return actions;
    }

    public void setActions(List<String> actions) {
        this.actions = actions;
    }
}
