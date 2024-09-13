package com.project.Bean;

import java.util.Objects;

public class Songs {
    private int songId;
    private String songTitle;
    private String artistName;
    private String genre;
    private String duration;
    private String filepath;
    private int playlistId;

    public Songs() {
    }

    public Songs(int songId, String songTitle, String artistName, String genre, String duration, String filepath) {
        this.songId = songId;
        this.songTitle = songTitle;
        this.artistName = artistName;
        this.genre = genre;
        this.duration = duration;
        this.filepath = filepath;
    }

    public int getPlaylistId() {
        return playlistId;
    }

    public void setPlaylistId(int playlistId) {
        this.playlistId = playlistId;
    }

    public String getSongTitle() {
        return songTitle;
    }

    public void setSongTitle(String songTitle) {
        this.songTitle = songTitle;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    public int getSongId() {
        return songId;
    }

    public void setSongId(int songId) {
        this.songId = songId;
    }

    @Override
    public String toString() {
        return "SongsBean{" +
                "songId=" + songId +
                ", songTitle='" + songTitle + '\'' +
                ", artistName='" + artistName + '\'' +
                ", genre='" + genre + '\'' +
                ", duration='" + duration + '\'' +
                ", filepath='" + filepath + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Songs songsBean = (Songs) o;
        return songId == songsBean.songId && Objects.equals(songTitle, songsBean.songTitle) && Objects.equals(artistName, songsBean.artistName) && Objects.equals(genre, songsBean.genre) && Objects.equals(duration, songsBean.duration) && Objects.equals(filepath, songsBean.filepath);
    }

    @Override
    public int hashCode() {
        return Objects.hash(songId, songTitle, artistName, genre, duration, filepath);
    }
}

