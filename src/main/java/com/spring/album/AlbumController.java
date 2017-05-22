package com.spring.album;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/albums")
public class AlbumController {
    @Autowired
    private AlbumService albumService;

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<Album> getAll(){
        return albumService.getAllAlbums();
    }

    @RequestMapping(value="/language/{language}", method = RequestMethod.GET)
    public List<Album> getAlbumsByLanguage(@PathVariable String language){
        return albumService.getByLanguage(language);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public List<Album> addAlbum(@RequestBody Album album){
        albumService.addAlbum(album);
        return albumService.getAllAlbums();
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
    public void updateAlbum(@RequestBody Album album, @PathVariable int id){
        albumService.updateAlbum(album, id);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public void deleteAlbum(@PathVariable int id){
        albumService.deleteAlbum(id);
    }
}
