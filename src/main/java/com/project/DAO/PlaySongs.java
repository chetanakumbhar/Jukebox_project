package com.project.DAO;

import com.project.Bean.Songs;
import com.project.Util.DbUtil;
import com.project.main.jukebox;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.Collectors;

import static java.util.Collections.shuffle;

public class PlaySongs {
    private static Connection connection = DbUtil.getDbConnection();
    Clip clip;
    AudioInputStream audioInputStream;
    private int currentIndex = -1;
    private List<Songs> playlist;
    String filePath;
    Long currentFrame;
    String status;

    public PlaySongs() {

    }

    public void playById(int id) throws SQLException {
        if (id <= 1010) {
            String sql = "SELECT filepath FROM songs WHERE songId = '" + id + "'";
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                filePath = rs.getString("filepath");
//                jukebox1.readAudio(filePath);
            }
        }
    }

    public void playSong(String filePath) throws SQLException, UnsupportedAudioFileException, IOException, LineUnavailableException {
        this.filePath = filePath;
        audioInputStream = AudioSystem.getAudioInputStream(new File(filePath).getAbsoluteFile());
        clip = AudioSystem.getClip();
        clip.open(audioInputStream);
    }

    public List<Songs> displayCurrentPlayingSong(int SongId) throws SQLException {
        List<Songs> list = new ArrayList<>();
        String sql = "SELECT * FROM songs WHERE songId = '" + SongId + "'";

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
            list.add(songsBean);
//            System.out.println("*--------------------------------------------------------------------------------------------------------*");
            System.out.format("  %-5d\t%-20s\t%-35s\t%-18s\t%-15s\t\n", songId, songTitle, artistName, genre, duration);
//            System.out.println("*--------------------------------------------------------------------------------------------------------*");
        }
        return list;
    }


    public void play(){

        clip.start();
        status = "play";
    }
    public void pause() {
        if (status.equals("paused")) {
            System.out.println("Audio is already paused");
            return;
        }
        currentFrame = clip.getMicrosecondPosition();
        clip.stop();
        status = "paused";
    }

    public void resumeAudio() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        if (status.equals("play"))
        {
            System.out.println("Audio is already being played");
            return;
        }
        clip.close();
        resetAudioStream();
        clip.setMicrosecondPosition(currentFrame);
        this.play();
    }

    public void restart() throws IOException, LineUnavailableException, UnsupportedAudioFileException {
        clip.stop();
        clip.close();
        resetAudioStream();
        currentFrame = 0L;
        clip.setMicrosecondPosition(0);
        play();
    }
    public void shuffle() {

    }

    public void stop() {
        currentFrame = 0L;
        clip.stop();
        clip.close();
    }

    public void resetAudioStream() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        audioInputStream = AudioSystem.getAudioInputStream(new File(filePath).getAbsoluteFile());
        clip.open(audioInputStream);
    }

    public void setPlaylist(List<Songs> playlist) {
        this.playlist = playlist;
    }

    public void playNext() throws SQLException, UnsupportedAudioFileException, IOException, LineUnavailableException {
        if (playlist == null || playlist.isEmpty()) {
            System.out.println("Playlist is empty or not set. Cannot play next song.");
            return;
        }

        if (currentIndex < playlist.size() - 1) {
            currentIndex++;
        } else {
            currentIndex = 0; // Wrap around to the first song if at the end
        }
        playSong(playlist.get(currentIndex).getFilepath());
    }

    public void playPrevious() throws SQLException, UnsupportedAudioFileException, IOException, LineUnavailableException {
        if (playlist == null || playlist.isEmpty()) {
            System.out.println("Playlist is empty or not set. Cannot play previous song.");
            return;
        }

        if (currentIndex > 0) {
            currentIndex--;
        } else {
            currentIndex = playlist.size() - 1; // Wrap around to the last song if at the beginning
        }
        playSong(playlist.get(currentIndex).getFilepath());
    }

}

