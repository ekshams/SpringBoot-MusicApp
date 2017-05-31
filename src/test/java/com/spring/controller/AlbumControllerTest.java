package com.spring.controller;

import com.spring.MusicApplication;
import com.spring.album.Album;
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
public class AlbumControllerTest {

@LocalServerPort
private int port;
TestRestTemplate restTemplate = new TestRestTemplate();
HttpHeaders headers = new HttpHeaders();

@Test
    public void testRetrieveAllAlbum() throws JSONException {
    HttpEntity<String> entity = new HttpEntity<String>(null, headers);

    ResponseEntity<String> response = restTemplate.exchange(
            createURLWithPort("/albums"),
            HttpMethod.GET, entity, String.class);

    String expected = "[]";

    JSONAssert.assertEquals(expected, response.getBody(), false);
    }

    @Test
    public void testAddAlbum() throws JSONException {
        Album album = new Album("abc","aa.jpg","eng");
        HttpEntity<Album> entity = new HttpEntity<Album>(album, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/albums"),
                HttpMethod.POST, entity, String.class);

        String expected = "[{id:1, title:abcde, logo:aa.jpg, language:eng},{id:2, title:abc, logo:aa.jpg, language:eng}]";

        JSONAssert.assertEquals(expected, response.getBody(), false);
    }

    @Test
    public void testUpdateAlbum() throws JSONException {
        Album album = new Album(1,"abcde","aa.jpg","eng");
        HttpEntity<Album> entity = new HttpEntity<Album>(album, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/albums"),
                HttpMethod.PUT, entity, String.class);

        String expected = "[{id:1, title:abcde, logo:aa.jpg, language:eng}]";

        JSONAssert.assertEquals(expected, response.getBody(), false);
    }

    @Test
    public void testDeleteAlbum() throws JSONException {
        HttpEntity<Integer> entity = new HttpEntity<Integer>(null,headers);

        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/albums/"+1),
                HttpMethod.DELETE, entity, String.class);

        String expected = "{}";

        JSONAssert.assertEquals(expected, response.getBody(), false);
    }


    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }
}
