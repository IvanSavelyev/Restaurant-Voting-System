package com.github.ivansavelyev.votingsystem.to;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.ivansavelyev.votingsystem.HasId;

public abstract class BaseTo implements HasId {
    @JsonIgnore
    protected Integer id;

    public BaseTo() {
    }

    public BaseTo(Integer id) {
        this.id = id;
    }

    @Override
    @JsonIgnore
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }
}
