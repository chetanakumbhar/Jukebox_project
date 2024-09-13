package com.project.Bean;

public class songInPlaylist {
    private int playlistId;
    private int songId;

    public songInPlaylist() {
    }

    public songInPlaylist(int playlistId, int songId) {
        this.playlistId = playlistId;
        this.songId = songId;
    }

    public int getPlaylistId() {
        return playlistId;
    }

    public void setPlaylistId(int playlistId) {
        this.playlistId = playlistId;
    }

    public int getSongId() {
        return songId;
    }

    public void setSongId(int songId) {
        this.songId = songId;
    }

    @Override
    public String toString() {
        return "songInPlaylist{" +
                "playlistId=" + playlistId +
                ", songId=" + songId +
                '}';
    }
}
