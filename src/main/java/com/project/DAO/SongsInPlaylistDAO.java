package com.project.DAO;

import com.project.Bean.Playlist;
import com.project.Bean.Songs;
import com.project.Bean.songInPlaylist;
import com.project.Util.DbUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SongsInPlaylistDAO {
    private static Connection connection = DbUtil.getDbConnection();

    Playlist list = new Playlist();
    Songs song = new Songs();
    songInPlaylist songs = new songInPlaylist();
    public void displayPlayListSong() throws SQLException {
        String sql ="select songs.songId,songs.song_title,songs.artist,songs.genre," +
                "songs.duration,playlist.playlistId,playlist.playlist_name from songs,playlist,songsInPlaylist where " +
                "songsInPlaylist.playlistId = playlist.playlistId and songs.songId = songsInPlaylist.songId";
        try {
            Statement stmt1 = connection.createStatement();
            ResultSet resultSet = stmt1.executeQuery(sql);
            while (resultSet.next()) {
                int songId = resultSet.getInt(1);
                String songTitle = resultSet.getString(2);
                String artistName = resultSet.getString(3);
                String genre = resultSet.getString(4);
                String duration = resultSet.getString(5);
                int playlistId = resultSet.getInt(6);
                String playlistName = resultSet.getString(7);
                System.out.format("%-5d\t%-20s\t%-35s\t%-18s\t%-15s\t\n", songId, songTitle, artistName, genre, duration,playlistId,playlistName);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
