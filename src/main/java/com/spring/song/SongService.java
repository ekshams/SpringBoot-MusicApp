//package com.spring.song;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Service
//public class SongService {
//
//    @Autowired
//    private SongRepository songRepository;
//
//    public List<Song> getSongs(int album_id){
//        List<Song> songs = new ArrayList<>();
//        songRepository.findByAlbumId(album_id).forEach(songs::add);
//        return songs;
//    }
//
//    public void createSong(Song song){
//        songRepository.save(song);
//    }
//}
