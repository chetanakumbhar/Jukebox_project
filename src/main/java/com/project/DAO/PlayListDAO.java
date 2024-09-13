package com.project.DAO;

import com.project.Bean.Playlist;
import com.project.Bean.Songs;
import com.project.Bean.songInPlaylist;
import com.project.Util.DbUtil;
import com.project.main.PlaylistDetails;
import com.project.main.jukebox;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PlayListDAO {
    String filePath;
    private static Connection connection = DbUtil.getDbConnection();
    jukebox jukebox1 = new jukebox();
    Scanner sc = new Scanner(System.in);
    songInPlaylist songInPlaylist = new songInPlaylist();
//    PlayListDAO playListDAO = new PlayListDAO();
    public void getAllSongsPlayList() {
        try {
            String sql = "SELECT * FROM songs";
            Statement stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery(sql);
            System.out.println("*-------------------------------------------------------*-----------------------------------------------------------*");
            System.out.format("|  %-8s|\t%-20s|\t%-35s|\t%-18s|\t%-15s|\t\n", "songId", "songTitle", "artistName", " genre", "duration");
            System.out.println("*-------------------------------------------------------*-----------------------------------------------------------*");
            while (resultSet.next()) {
                int songId = resultSet.getInt(1);
                String songTitle = resultSet.getString(2);
                String artistName = resultSet.getString(3);
                String genre = resultSet.getString(4);
                String duration = resultSet.getString(5);
                String filepath = resultSet.getString(6);
                Songs songs = new Songs(songId, songTitle, artistName, genre, duration, filepath);

                System.out.format("|  %-8d|\t%-20s|\t%-35s|\t%-18s|\t%-15s|\t\n", songId, songTitle, artistName, genre, duration);
            }
            System.out.println("*-------------------------------------------------------*----------------------------------------------------------*-");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    Playlist playlist = new Playlist();
    public void createPlaylist() throws SQLException {

        System.out.println("----------*------------*-----------");
        System.out.println("    Create New PlayList     ");
        System.out.println("----------*------------*-----------");
        System.out.println("Enter PlayList Name");
        playlist.setPlaylist_name(sc.next());
        System.out.println("----------*------------*-----------*------------*----------");
        System.out.println("*    Your Playlist Has Been Created Successfully     *");
        System.out.println("----------*------------*-----------*------------*----------");
        System.out.println();

        String q="insert into Playlist values(?,?)";
        PreparedStatement ps= connection.prepareStatement(q);
        ps.setInt(1,playlist.getPlaylist_id());
        ps.setString(2,playlist.getPlaylist_name());
        ps.executeUpdate();

    }
    public void getExistingPlayList(){
        String sql = "SELECT * FROM playlist";
        try{
        Statement stmt = connection.createStatement();
        ResultSet resultSet = stmt.executeQuery(sql);
            System.out.println("*-------------------------*----------------------*");
        System.out.format("|  %-12s   |\t\t\t%-20s|\t\n","PlaylistId", "PlayListName");
            System.out.println("*-------------------------*----------------------*");
        while (resultSet.next()) {
            int playlistId = resultSet.getInt(1);
            String playlistName = resultSet.getString(2);
            Playlist songs = new Playlist(playlistId, playlistName);

            System.out.format("|  %-12d   |\t\t\t%-20s|\t\n",playlistId, playlistName);
        }
            System.out.println("*-------------------------*----------------------*");
    } catch (Exception e) {
        e.printStackTrace();
    }
    }

    public int insertSongsInPlaylist() {
        int rows = 0;
        PlayListDAO playListDAO = new PlayListDAO();
        Scanner sc = new Scanner(System.in);
        playListDAO.getExistingPlayList();
        System.out.println("Choose playlist to add Song  ");
        songInPlaylist.setPlaylistId(sc.nextInt());

            do {
                try {
                playListDAO.getAllSongsPlayList();
                System.out.println("Enter song id to add song in playlist");
                songInPlaylist.setSongId(sc.nextInt());
                String sql = "INSERT INTO songsInPlaylist VALUES(?,?)";
                PreparedStatement ps = connection.prepareStatement(sql);
                ps.setInt(1, songInPlaylist.getPlaylistId());
                ps.setInt(2, songInPlaylist.getSongId());
                System.out.println(songInPlaylist.getPlaylistId());
                System.out.println(songInPlaylist.getSongId());
                rows = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rows;
            }while (true);
    }
    public void playSongInPlaylist(int id) throws SQLException{
            String sql = "SELECT filepath FROM songs where songId = '"+id+"'";
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                filePath = rs.getString("filepath");
                jukebox1.readAudio(filePath);
            }
        }
        public void getPlaylistById(int id) {
            String sql ="SELECT songs.songId,songs.song_title,songs.artist,songs.genre," +
                    "songs.duration,playlist.playlistId,playlist.playlist_name FROM songs JOIN songsInPlaylist ON" +
                    " songs.songId = songsInPlaylist.songId JOIN playlist ON " +
                    " playlist.playlistId = songsInPlaylist.playlistId WHERE playlist.playlistId = "+ id ;
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
