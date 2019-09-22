package com.jakimenko.testnetty.domain;

import java.io.Serializable;

/**
 * @author konst
 */
public abstract class Entity implements Serializable {

    protected int id;

    Entity(int id) {
        this.id = id;
    }

    Entity() {
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
