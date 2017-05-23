package com.spring.album;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AlbumController {
    @Autowired
    private AlbumService albumService;

    @RequestMapping(value = "/albums", method = RequestMethod.GET)
    public List<Album> getAll(){
        return albumService.getAllAlbums();
    }

    @RequestMapping(value="/albums/{language}", method = RequestMethod.GET)
    public List<Album> getAlbumsByLanguage(@PathVariable String language){
        return albumService.getByLanguage(language);
    }

    @RequestMapping(value = "/albums", method = RequestMethod.POST)
    public List<Album> addAlbum(@RequestBody Album album){
        albumService.addAlbum(album);
        return albumService.getAllAlbums();
    }

    @RequestMapping(value = "/albums/{id}", method = RequestMethod.PUT)
    public void updateAlbum(@RequestBody Album album, @PathVariable int id){
        albumService.updateAlbum(album, id);
    }

    @RequestMapping(value = "/albums/{id}", method = RequestMethod.DELETE)
        public List<Album> deleteAlbum(@PathVariable int id){
        albumService.deleteAlbum(id);
        return albumService.getAllAlbums();

    }
}
