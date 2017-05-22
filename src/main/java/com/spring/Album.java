package com.spring;

import org.springframework.data.annotation.Id;


public class Album {

//    private int id;
    private String title;
    private String logo;
    private String language;

    public Album(String title, String logo, String language) {
        this.title = title;
        this.logo = logo;
        this.language = language;
    }

    public String getTitle() {
        return title;
    }

    public String getLogo() {
        return logo;
    }

    public String getLanguage() {
        return language;
    }
}
