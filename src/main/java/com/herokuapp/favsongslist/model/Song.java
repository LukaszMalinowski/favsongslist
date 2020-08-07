package com.herokuapp.favsongslist.model;

public class Song {

    private Long id;

    private String artist;

    private String title;

    private String albumName;

    private String songArtUrl;

    public Song(Long id, String artist, String title, String albumName, String songArtUrl) {
        this.id = id;
        this.artist = artist;
        this.title = title;
        this.albumName = albumName;
        this.songArtUrl = songArtUrl;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public String getSongArtUrl() {
        return songArtUrl;
    }

    public void setSongArtUrl(String songArtUrl) {
        this.songArtUrl = songArtUrl;
    }

    @Override
    public String toString() {
        return "Song{" +
                "id=" + id +
                ", artist='" + artist + '\'' +
                ", title='" + title + '\'' +
                ", albumName='" + albumName + '\'' +
                ", songArtUrl='" + songArtUrl + '\'' +
                '}';
    }
}
