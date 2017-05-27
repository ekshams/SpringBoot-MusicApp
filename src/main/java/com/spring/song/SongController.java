package com.spring.song;

import com.spring.album.Album;
import com.spring.album.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@RequestMapping("/albums")
public class SongController {
    @Autowired
    private SongService songService;
    @Autowired
    private AlbumService albumService;

    @RequestMapping(value = "/albums/{album_id}", method = RequestMethod.GET)
    public List<Song> getSongs(@PathVariable int album_id){
        return  songService.getSongs(album_id);
    }

    @RequestMapping(value = "/albums/{album_id}/songs", method = RequestMethod.POST)
    public List<Song> addSong(@RequestBody Song song, @PathVariable int album_id){
        Album album = albumService.getAlbumById(album_id);
        song.setAlbum(album);
        songService.createSong(song);
        return songService.getAllSongs();
    }
    @RequestMapping(value="/albums/{album_id}/songs/{song_id}", method=RequestMethod.PUT)
    public List<Song> updateSong(@RequestBody Song song, @PathVariable int album_id, @PathVariable  int song_id){
        Album album = albumService.getAlbumById(album_id);
        song.setAlbum(album);
        songService.updateSong(song);
        return songService.getSongs(album_id);
    }
    @RequestMapping(value = "/albums/{album_id}/songs/{song_id}", method = RequestMethod.DELETE)
    public List<Song> deleteSong(@PathVariable int album_id, @PathVariable int song_id){
        songService.deleteSong(song_id);
        return getSongs(album_id);

    }

}
