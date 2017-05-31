package com.spring.controller;

import com.spring.MusicApplication;
import com.spring.album.Album;
import com.spring.song.Song;
import org.json.JSONException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MusicApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SongControllerTest {

    @LocalServerPort
    private int port;
    TestRestTemplate restTemplate = new TestRestTemplate();
    HttpHeaders headers = new HttpHeaders();

    @Test
    public void testRetrieveSongsByAlbum() throws JSONException {
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/albums/"+1),
                HttpMethod.GET, entity, String.class);

        String expected = "[]";

        JSONAssert.assertEquals(expected, response.getBody(), false);
    }


    @Test
    public void testAddSong() throws JSONException {
        Song song = new Song("song1","artist1","abc.jpg");
        HttpEntity<Song> entity = new HttpEntity<Song>(song, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/albums/"+1+"/songs"),
                HttpMethod.POST, entity, String.class);

        String expected = "[{id:1, title:song1, logo:abc.jpg, artist:artist1}]";

        JSONAssert.assertEquals(expected, response.getBody(), false);
    }

    @Test
    public void testUpdateSong() throws JSONException {
        Song song = new Song(1,"song1","artist1","abc.jpg");
        Album album = new Album(1,"abc","aa.jpg","eng");
        song.setAlbum(album);

        HttpEntity<Song> entity = new HttpEntity<Song>(song, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/albums/"+1+"/songs/"+1),
                HttpMethod.PUT, entity, String.class);

        String expected = "[]";

        JSONAssert.assertEquals(expected, response.getBody(), false);
    }


    @Test
    public void testDeleteSong() throws JSONException {
        HttpEntity<Integer> entity = new HttpEntity<Integer>(null,headers);

        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/albums/1/songs/1"),
                HttpMethod.DELETE, entity, String.class);

        String expected = "[]";

        JSONAssert.assertEquals(expected, response.getBody(), false);
    }


    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }
}
