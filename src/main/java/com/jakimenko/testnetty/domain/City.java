package com.jakimenko.testnetty.domain;

/**
 * @author konst
 */
public class City extends Entity {
    private String title;

    public City(Integer id, String title) {
        super(id);
        this.title = title;
    }

    public City() {
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
        return "City{" +
            "id=" + id +
            ", title='" + title + '\'' +
            '}';
    }
}
