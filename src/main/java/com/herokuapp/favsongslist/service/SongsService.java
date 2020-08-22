package com.herokuapp.favsongslist.service;

import com.herokuapp.favsongslist.exception.SongNotFoundException;
import com.herokuapp.favsongslist.model.Song;
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

    public Song getSongInfo(String artist, String song) throws SongNotFoundException {
        String searchParam = getSearchParam(artist, song);

        JSONObject json = null;

        try {
            int id = getSongId(searchParam);

            json = new JSONObject(getSong(id));
        } catch (SongNotFoundException ex) {
            throw ex;
        }

        return createSongObject(json.getJSONObject("response").getJSONObject("song"));
    }

    private String getSearchParam(String artist, String song) {
        artist = artist.replaceAll(" ", "+");
        song = song.replaceAll(" ", "+");

        return artist + "+" + song;
    }

    private int getSongId(String searchParam) throws SongNotFoundException {
        int id = -1;

        //TODO add better exception handling
        try {
            ResponseEntity<String> response = restTemplate.exchange(GENIUS_SEARCH_URL + searchParam, HttpMethod.GET,
                                                                    headersEntity, String.class);

            JSONObject json = new JSONObject(response.getBody());

            id = json.getJSONObject("response")
                         .getJSONArray("hits")
                         .getJSONObject(0)
                         .getJSONObject("result")
                         .getInt("id");
        } catch (Exception ex) {
            throw new SongNotFoundException("Song not found");
        }

        return id;
    }

    private String getSong(int id) {
        ResponseEntity<String> response = restTemplate.exchange(GENIUS_SONGS_URL + id, HttpMethod.GET,
                                                                 headersEntity, String.class);

        return response.getBody();
    }

    private Song createSongObject(JSONObject json) {
        Song song = new Song();

        song.setId(json.getLong("id"));

        song.setArtist(json.getJSONObject("album").getJSONObject("artist").getString("name"));

        song.setTitle(json.getString("title"));

        song.setAlbumName(json.getJSONObject("album").getString("name"));

        song.setSongArtUrl(json.getString("song_art_image_thumbnail_url"));

        return song;
    }
}
