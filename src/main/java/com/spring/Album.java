package com.spring;

import org.springframework.data.annotation.Id;

/**
 * Created by ekshams on 22/5/17.
 */
public class Album {
    @Id
    private int id;
    private String title;
    private String logo;
    private String language;

    public Album(int id, String title, String logo, String language) {
        this.id = id;
        this.title = title;
        this.logo = logo;
        this.language = language;
    }

    public Album() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}
