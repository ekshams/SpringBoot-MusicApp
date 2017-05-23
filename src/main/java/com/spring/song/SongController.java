//package com.spring.song;
//
//import com.spring.album.Album;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
////@RequestMapping("/albums")
//public class SongController {
//    @Autowired
//    private SongService songService;
//
//    @RequestMapping(value = "/albums/{album_id}", method = RequestMethod.GET)
//    public List<Song> getSongs(@PathVariable int album_id){
//        return  songService.getSongs(album_id);
//    }
//
//    @RequestMapping(value = "/albums/{album_id}/songs", method = RequestMethod.POST)
//    public List<Song> addSong(@RequestBody Song song, @PathVariable int album_id){
//        song.setAlbum(new Album("","",""));
//        songService.createSong(song);
//        return songService.getSongs(album_id);
//    }
//}
