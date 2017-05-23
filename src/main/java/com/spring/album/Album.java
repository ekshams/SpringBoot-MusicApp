package com.spring.album;



import javax.persistence.*;

@Entity
public class Album {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String title;
    private String logo;
    private String language;

    public Album( String title, String logo, String language) {
        this.title = title;
        this.logo = logo;
        this.language = language;
    }

    public Album() {
    }

    public int getId() {
        return id;
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

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}
