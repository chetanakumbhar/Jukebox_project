package com.project.DAO;

import com.project.Bean.Songs;
import com.project.Util.DbUtil;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import java.util.stream.Collectors;

public class SongsDAO {
    private static Connection connection = DbUtil.getDbConnection();
    PlayListDAO playListDAO = new PlayListDAO();
    Songs songs = new Songs();

    public void sortAllSongs(List<Songs> Songs, String sortCategory) {
        List<Songs> songs = new ArrayList<>();
        songs = getAllSongs();
        switch (sortCategory) {
            case "title":
                Comparator<Songs> songsComparator = (o1, o2) -> o1.getSongTitle().compareToIgnoreCase(o2.getSongTitle());
                List<Songs> songlist = new ArrayList<>();
                for (Songs songs1 : songs) {
                    songlist.add(songs1);
                    Collections.sort(songlist, songsComparator);
                }
                System.out.println("*-------------------------------------------------*-----------------------------------------------------------*");
                System.out.format("|  %-8s|\t%-20s|\t%-35s|\t%-18s|\t%-15s|\t\n", "songId", "songTitle", "artistName", " genre", "duration");
                System.out.println("*-------------------------------------------------*-----------------------------------------------------------*");
                List<Songs> songlist1 = new ArrayList<>();
                for (Songs songs1:songlist) {
                    songlist1.add(songs1);
                    System.out.format("|  %-8d|\t%-20s|\t%-35s|\t%-18s|\t%-15s|\t\n", songs1.getSongId(),songs1.getSongTitle(), songs1.getArtistName(), songs1.getGenre(), songs1.getDuration());
                }
                System.out.println("*-------------------------------------------------*----------------------------------------------------------*-");
                break;

            case "genre":
                Comparator<Songs> genreComparator = (o1, o2) -> o1.getGenre().compareToIgnoreCase(o2.getGenre());
                List<Songs> list1 = new ArrayList<>();
                for (Songs songs1 : songs) {
                    list1.add(songs1);
                    Collections.sort(list1, genreComparator);
                }
                System.out.println("*-------------------------------------------------*-----------------------------------------------------------*");
                System.out.format("|  %-8s|\t%-20s|\t%-35s|\t%-18s|\t%-15s|\t\n", "songId", "songTitle", "artistName", " genre", "duration");
                System.out.println("*-------------------------------------------------*-----------------------------------------------------------*");
                List<Songs> songlist2 = new ArrayList<>();
                for (Songs songs1:list1) {
                    songlist2.add(songs1);
                    System.out.format("|  %-8d|\t%-20s|\t%-35s|\t%-18s|\t%-15s|\t\n", songs1.getSongId(),songs1.getSongTitle(), songs1.getArtistName(), songs1.getGenre(), songs1.getDuration());
                }
                System.out.println("*-------------------------------------------------*----------------------------------------------------------*-");
                break;

            case "artist":
                Comparator<Songs> artistComparator = (o1, o2) -> o1.getArtistName().compareToIgnoreCase(o2.getArtistName());
                List<Songs> list2 = new ArrayList<>();
                for (Songs songs1 : songs) {
                    list2.add(songs1);
                    Collections.sort(list2, artistComparator);
                }
                System.out.println("*-------------------------------------------------*-----------------------------------------------------------*");
                System.out.format("|  %-8s|\t%-20s|\t%-35s|\t%-18s|\t%-15s|\t\n", "songId", "songTitle", "artistName", " genre", "duration");
                System.out.println("*-------------------------------------------------*-----------------------------------------------------------*");
                List<Songs> songlist3 = new ArrayList<>();
                for (Songs songs1:list2) {
                    songlist3.add(songs1);
                    System.out.format("|  %-8d|\t%-20s|\t%-35s|\t%-18s|\t%-15s|\t\n", songs1.getSongId(),songs1.getSongTitle(), songs1.getArtistName(), songs1.getGenre(), songs1.getDuration());
                }
                System.out.println("*-------------------------------------------------*----------------------------------------------------------*-");
                break;
        }


    }
    public List<Songs> SortedSongsInAlphabeticalOrder(List<Songs> songsList){
        List<Songs> list = new ArrayList<>();
        Comparator<Songs> songsComparator = (o1,o2) -> o1.getSongTitle().compareToIgnoreCase(o2.getSongTitle());
        for(Songs songs1 : songsList){
            list.add(songs1);
        }
        Collections.sort(list,songsComparator);
        return list;
    }

    public List<Songs> getAllSongs() {
        List<Songs> songList = new ArrayList<>();
        try {
            String sql = "SELECT * FROM songs";
            Statement stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery(sql);
            while (resultSet.next()) {
                int songId = resultSet.getInt(1);
                String songTitle = resultSet.getString(2);
                String artistName = resultSet.getString(3);
                String genre = resultSet.getString(4);
                String duration = resultSet.getString(5);
                String filepath = resultSet.getString(6);
                Songs songsBean = new Songs(songId, songTitle, artistName, genre, duration, filepath);
                songList.add(songsBean);

//            System.out.format("%-5d\t%-20s\t%-35s\t%-18s\t%-15s\t\n",songId,songTitle,artistName,genre,duration);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return songList;
    }

    public void searchByArtist(String name) throws SQLException {

        String sql = "SELECT * FROM songs WHERE artist = '" + name + "'";

        Statement stmt = connection.createStatement();
        ResultSet resultSet = stmt.executeQuery(sql);
        while (resultSet.next()) {
            int songId = resultSet.getInt(1);
            String songTitle = resultSet.getString(2);
            String artistName = resultSet.getString(3);
            String genre = resultSet.getString(4);
            String duration = resultSet.getString(5);

            System.out.format("%-5d\t%-20s\t%-35s\t%-18s\t%-15s\t\n", songId, songTitle, artistName, genre, duration);
        }
    }

    public void searchBySongId(int id) throws SQLException {

          String sql = "SELECT * FROM songs WHERE songId = '"+ id + "'";

             Statement stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery(sql);
            while (resultSet.next()){
                int songId = resultSet.getInt(1);
                String songTitle = resultSet.getString(2);
                String artistName = resultSet.getString(3);
                String genre = resultSet.getString(4);
                String duration = resultSet.getString(5);

                System.out.format("%-5d\t%-20s\t%-35s\t%-18s\t%-15s\t\n", songId, songTitle, artistName, genre, duration);
            }
    }



    public void searchByGenre(String name) throws SQLException {

        String sql = "SELECT * FROM songs WHERE genre = '" + name + "'";

        Statement stmt = connection.createStatement();
        ResultSet resultSet = stmt.executeQuery(sql);
        while (resultSet.next()) {
            int songId = resultSet.getInt(1);
            String songTitle = resultSet.getString(2);
            String artistName = resultSet.getString(3);
            String genre = resultSet.getString(4);
            String duration = resultSet.getString(5);

            System.out.format("%-5d\t%-20s\t%-35s\t%-18s\t%-15s\t\n", songId, songTitle, artistName, genre, duration);
        }
    }

}
