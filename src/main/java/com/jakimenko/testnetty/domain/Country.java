package com.jakimenko.testnetty.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * @author konst
 */
public class Country extends Entity {
    private String title;

    public Country(Integer id, String title) {
        super(id);
        this.title = title;
    }

    public Country() {
    }

    public int getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Country{" +
            "title='" + title + '\'' +
            ", id=" + id +
            '}';
    }
}
