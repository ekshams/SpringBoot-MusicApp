package com.spring;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/albums")
public class AlbumController {
    private List<Album> albums;
    public AlbumController(){
        albums = new ArrayList<>();

        albums.add(new Album("The Dark Side of Moon", "logo1.jpg","English"));
        albums.add(new Album("Priyathaman", "logo2.jpg","Malayalam"));
        albums.add(new Album("Kuch hoi", "logo3.jpg","Hindi"));

    }
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<Album> getAll(){
        return albums;
    }


}
