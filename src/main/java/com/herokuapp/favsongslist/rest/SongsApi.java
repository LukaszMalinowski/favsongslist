package com.herokuapp.favsongslist.rest;

import com.herokuapp.favsongslist.exception.SongNotFoundException;
import com.herokuapp.favsongslist.model.Song;
import com.herokuapp.favsongslist.service.SongsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class SongsApi {

    SongsService service;

    public SongsApi(SongsService service) {
        this.service = service;
    }

    @GetMapping(value = "song", params = {"artist", "song"})
    public Song getSongInfo(@RequestParam("artist") String artist, @RequestParam("song") String song) {

        Song songInfo = null;
        try {
            songInfo = service.getSongInfo(artist, song);
        } catch (SongNotFoundException ex) {
            throw ex;
        }

        return songInfo;
    }

}
