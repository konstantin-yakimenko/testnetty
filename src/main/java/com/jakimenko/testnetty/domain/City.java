package com.jakimenko.testnetty.domain;

/**
 * @author konst
 */
public class City {
    private Long id;
    private String title;

    public City(Long id, String title) {
        this.id = id;
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
