package com.project.main;

import com.project.Bean.Songs;
import com.project.DAO.PlayListDAO;
import com.project.DAO.PlaySongs;
import com.project.DAO.SongsDAO;
import com.project.Util.DbUtil;

import javax.sound.sampled.Clip;
import javax.sound.sampled.Line;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class jukebox {

    private static Connection connection = DbUtil.getDbConnection();
    Songs songs = new Songs();
    public void displayAll() throws SQLException,LineUnavailableException,UnsupportedAudioFileException,IOException{
        Scanner scan = new Scanner(System.in);

        PlaySongs playSongs = new PlaySongs();
        PlaylistDetails playlistDetails = new PlaylistDetails();
        jukebox jukebox1 = new jukebox();
        SongsDAO songsDAO = new SongsDAO();
        System.out.println();
        System.out.println("----------------*--------------------Welcome To JockBox----------------*------------------");
        System.out.println();
        System.out.println("---------------*---------------------------*----------------------");
        System.out.println("choose any one option: ");
        System.out.println("----------------*--------------------------*----------------------");
        System.out.println("1)  Play");
        System.out.println("2)  Playlist");
        System.out.println("3)  Create Playlist");
        System.out.println("4)  Search");
        System.out.println("5)  Sort");
        System.out.println("6)  Songs in Alphabetical order");
        System.out.println("7)  Exit");
        System.out.println("----------------*--------------------------*----------------------");
        int choice;
        while (true) {
            System.out.println("Choose Any one Option To Proceed..");
            choice = scan.nextInt();
            switch (choice) {
                case 1:
                    String filePath ="";
                        System.out.println("Enter SongId Which You Want To Play");
                        int sid = scan.nextInt();
                        playSongs.playById(sid);
                    String sql = "SELECT filepath FROM songs WHERE songId = '" + sid + "'";
                    Statement stmt = connection.createStatement();
                    ResultSet rs = stmt.executeQuery(sql);
                    while (rs.next()) {
                        filePath = rs.getString("filepath");
                    }
                        PlayListDAO playListDAO1=new PlayListDAO();
                            playListDAO1.getAllSongsPlayList();
                            System.out.println();
                            System.out.println("-------------------*-------------------------------*----------------------*-----------------");
                            playSongs.displayCurrentPlayingSong(sid);
                            readAudio(filePath);
                    break;
                case 2:
                    PlayListDAO playListDAO = new PlayListDAO();
                    System.out.println(" ");
                    playListDAO.getAllSongsPlayList();
                    System.out.println(" ");
                    break;
                case 3:
                    try {
                        playlistDetails.operation();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case 4:
                    System.out.println();
                    System.out.println("search on the basis of \n1.Artist \n2.SongID \n3.Genre");
                    int i =scan.nextInt();
                    if(i==1)
                    {
                        System.out.println("Enter Artist Name");
                        scan.nextLine();
                        String artist=scan.nextLine();
                        songsDAO.searchByArtist(artist);
                    }
                    if(i==2)
                    {
                        System.out.println("Enter SongId ");
                        int id = scan.nextInt();
                        songsDAO.searchBySongId(id);
                    }
                    if(i==3)
                    {
                        System.out.println("Enter genre category");
                        String cate=scan.next();
                        songsDAO.searchByGenre(cate);
                    }
                    System.out.println();
                    break;
                case 5:
                        System.out.println("Enter Category Name");
                        System.out.println();
                        String cat = scan.next().toLowerCase();
                        List<Songs> list2 = songsDAO.getAllSongs();
                       songsDAO.sortAllSongs(list2,cat);

                    break;
                case 6:
                    List<Songs> list1 = songsDAO.getAllSongs();
                    songsDAO.SortedSongsInAlphabeticalOrder(list1);
                    for (Songs s : list1) {
                        System.out.println(s);
                    }
                    System.out.println("------------------*------------------------------------------*----------------------------------");
                    break;
                case 7:
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid input");
            }
        }

    }
    public jukebox(){
    }
    public void readAudio(String audioSrcFile) {
        PlaySongs playAudio = new PlaySongs();
        int choice = 0;
        try {

            playAudio.playSong(audioSrcFile);

            playAudio.play();
            Scanner scanner = new Scanner(System.in);
            while (true) {

                System.out.println("---------------------------------------*---------------------------------------*-------------------");
                System.out.println();
                System.out.println("choose from following options......");
                System.out.println();
                System.out.println("        1. pause        2. resume      3. restart  ");
                System.out.println("        4. main menu    5. next Song   6. Stop ");
                System.out.println();

                System.out.println("-------------------*-----------------------------*-----------------------*----------------------");
                System.out.println();

                if (scanner.hasNextInt()) {
                    choice = scanner.nextInt();
                } else {
                    break;
                }
                switch (choice) {
                    case 1:
                        playAudio.pause();
                        break;
                    case 2:
                        playAudio.resumeAudio();
                        break;
                    case 3:
                        playAudio.restart();
                        break;
                    case 4:
                        PlaySongs playSongs=new PlaySongs();
                        displayAll();
                        playSongs.stop();
                        break;
                    case 5:
                        playAudio.playPrevious();
                        break;
                    case 6:
                        playAudio.stop();
                        break;
                }
            }
        } catch (Exception ex) {
            System.out.println("Error with playing sound." + ex);
        }
    }
}
