//package com.spring.song;
//
//import com.fasterxml.jackson.annotation.JsonBackReference;
//import com.spring.album.Album;
//
//import javax.persistence.*;
//
//@Entity
//public class Song {
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private int id;
//    private String title;
//    private String artist;
//    private String logo;
//
//    @ManyToOne
//    private Album album;
//
//    public Song(String title, String artist, String logo) {
//        super();
//        this.title = title;
//        this.artist = artist;
//        this.logo = logo;
//        this.album = new Album("","","");
//    }
//
//
//    public Song() {
//    }
//
//    public Album getAlbum() {
//        return album;
//    }
//
//    public void setAlbum(Album album) {
//        this.album = album;
//    }
//
//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    public String getTitle() {
//        return title;
//    }
//
//    public void setTitle(String title) {
//        this.title = title;
//    }
//
//    public String getArtist() {
//        return artist;
//    }
//
//    public void setArtist(String artist) {
//        this.artist = artist;
//    }
//
//    public String getLogo() {
//        return logo;
//    }
//
//    public void setLogo(String logo) {
//        this.logo = logo;
//    }
//}
