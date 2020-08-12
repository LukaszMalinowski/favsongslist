package com.herokuapp.favsongslist.rest;

import com.herokuapp.favsongslist.model.Song;
import com.herokuapp.favsongslist.service.SongsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class SongsApi {

    SongsService service;

    public SongsApi(SongsService service) {
        this.service = service;
    }

    @GetMapping(value = "song", params = {"artist", "song"})
    public Song getSongInfo(@RequestParam("artist") String artist, @RequestParam("song") String song) {
        return service.getSongInfo(artist, song);
    }

}
