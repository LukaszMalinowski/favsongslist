package com.herokuapp.favsongslist.service;

import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class SongsService {
    private static final String GENIUS_SEARCH_URL = "https://api.genius.com/search?q=";
    private static final String GENIUS_SONGS_URL = "https://api.genius.com/songs/";
    private static final String ACCESS_TOKEN = "ABvYa45O8eb-imO1dnRdjlkczDkhnu3Mt6ZdAupSFVxMyUdVd-zh6AMLMZxVWvaX";

    RestTemplate restTemplate;
    HttpEntity<String> headersEntity;

    public SongsService() {
        restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth("ABvYa45O8eb-imO1dnRdjlkczDkhnu3Mt6ZdAupSFVxMyUdVd-zh6AMLMZxVWvaX");
        headersEntity = new HttpEntity<>("body", headers);
    }

    public JSONObject getSongInfo(String artist, String song) {
        String searchParam = getSearchParam(artist, song);

        int id = getSongId(searchParam);

        JSONObject json = new JSONObject(getSong(id));

        System.out.println(json);

        return json;
    }

    private String getSearchParam(String artist, String song) {
        artist = artist.replaceAll(" ", "+");
        song = song.replaceAll(" ", "+");

        return artist + "+" + song;
    }

    private int getSongId(String searchParam) {
        ResponseEntity<String> response = restTemplate.exchange(GENIUS_SEARCH_URL + searchParam, HttpMethod.GET,
                                                                headersEntity, String.class);

        JSONObject json = new JSONObject(response.getBody());

        int id = json.getJSONObject("response")
                        .getJSONArray("hits")
                        .getJSONObject(0)
                        .getJSONObject("result")
                        .getInt("id");

        return id;
    }

    private String getSong(int id) {
        ResponseEntity<String> response = restTemplate.exchange(GENIUS_SONGS_URL + id, HttpMethod.GET,
                                                                 headersEntity, String.class);

        return response.getBody();
    }

}
