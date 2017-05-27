package com.spring.album;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

@RestController
public class AlbumController {
    @Autowired
    private AlbumService albumService;


    @RequestMapping(value = "/albums", method = RequestMethod.GET)
    public List<Album> getAll(){
        return albumService.getAllAlbums();
    }

//    @RequestMapping(value="/albums/{language}", method = RequestMethod.GET)
//    public List<Album> getAlbumsByLanguage(@PathVariable String language){
//        return albumService.getByLanguage(language);
//    }

    @RequestMapping(value = "/albums", method = RequestMethod.POST)
    public List<Album> addAlbum(@RequestBody Album album){
        albumService.addAlbum(album);
        return albumService.getAllAlbums();
    }

    @RequestMapping(value = "/albums/{id}", method = RequestMethod.PUT)
    public List<Album> updateAlbum(@RequestBody Album album, @PathVariable int id){
        albumService.updateAlbum(album, id);
        return albumService.getAllAlbums();
    }

    @RequestMapping(value = "/albums/{id}", method = RequestMethod.DELETE)
        public List<Album> deleteAlbum(@PathVariable int id){
        albumService.deleteAlbum(id);
        return albumService.getAllAlbums();

    }
    @RequestMapping(value="/upload", method=RequestMethod.POST)
    public String singleSave(@RequestParam("file") MultipartFile file){

        String fileName = null;
        if (!file.isEmpty()) {
            try {
                fileName = file.getOriginalFilename();
                byte[] bytes = file.getBytes();
                BufferedOutputStream buffStream =
                        new BufferedOutputStream(new FileOutputStream(new File("public/images/" + fileName)));
                buffStream.write(bytes);
                buffStream.close();
                return "You have successfully uploaded " + fileName;
            } catch (Exception e) {
                return "You failed to upload " + fileName + ": " + e.getMessage();
            }
        } else {
            return "Unable to upload. File is empty.";
        }
    }
}
