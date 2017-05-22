package com.spring.album;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class AlbumService {

    @Autowired
    private AlbumRepository albumRepository;


    private List<Album> albums = new ArrayList<>(Arrays.asList(

        new Album(1,"The Dark Side of Moon", "logo1.jpg","English"),
        new Album(2,"Priyathaman", "logo2.jpg","Malayalam"),
        new Album(3,"Kuch hoi", "logo3.jpg","Hindi")
    ));
    private List<Album> byLanguage;

    public List<Album> getAllAlbums(){
        List<Album> albums = new ArrayList<>();
        albumRepository.findAll().forEach(albums::add);
        return albums;
    }

    public List<Album> getByLanguage(String language) {
        return albumRepository.findByLanguage(language);
    }

    public void addAlbum(Album album) {
        albumRepository.save(album);
    }

    public void updateAlbum(Album album, int id) {
        albumRepository.save(album);

    }

    public void deleteAlbum(int id) {
        albumRepository.delete(id);
    }
}
