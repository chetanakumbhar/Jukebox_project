package com.project.main;

import com.project.Bean.Songs;
import com.project.DAO.PlayListDAO;
import com.project.DAO.PlaySongs;
import com.project.DAO.SongsDAO;
import com.project.Util.DbUtil;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class jukeboxImpl {
    private static Connection connection = DbUtil.getDbConnection();
    public static void main(String[] args) throws SQLException,IOException,UnsupportedAudioFileException,LineUnavailableException{

        Songs songs = new Songs();
        SongsDAO songsDAO = new SongsDAO();
        jukebox jukebox = new jukebox();
        jukebox.displayAll();
        PlaySongs playSongs = new PlaySongs();
        PlaylistDetails playlistDetails = new PlaylistDetails();
        try {
            playlistDetails.operation();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
