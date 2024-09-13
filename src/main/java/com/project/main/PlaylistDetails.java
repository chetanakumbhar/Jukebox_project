package com.project.main;

import com.project.Bean.Songs;
import com.project.DAO.PlayListDAO;
import com.project.DAO.PlaySongs;
import com.project.DAO.SongsDAO;
import com.project.DAO.SongsInPlaylistDAO;
import com.project.Util.DbUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Scanner;

public class PlaylistDetails {
    private static Connection connection = DbUtil.getDbConnection();
    PlaySongs playSongs = new PlaySongs();
    public void operation() throws Exception {
        Scanner sc = new Scanner(System.in);
        SongsDAO songsDAO = new SongsDAO();
        Scanner scan = new Scanner(System.in);
        PlayListDAO playListDAO = new PlayListDAO();
        SongsInPlaylistDAO songs = new SongsInPlaylistDAO();
        jukebox jukebox1 =new jukebox();
        while (true) {
            System.out.println("-----------*-------------" +
                    "\n1. Create PlayList " +
                    "\n2. Add New Song in PlayList " +
                    "\n3. PlayList Names " +
                    "\n4. After Sorting " +
                    "\n5. Search Song " +
                    "\n6. Playlist Song " +
                    "\n7. Play Playlist  " +
                    "\n8. Exit " +
                    "\n-----------*-------------");
            int ch = sc.nextInt();
            switch (ch) {
                case 1:
                    playListDAO.createPlaylist();
                    break;
                case 2:
                    PlayListDAO playListDAO4 = new PlayListDAO();
                    int rows = playListDAO4.insertSongsInPlaylist();
                    if (rows == 1) {
                        System.out.println("Song added successfully..");
                    }
                    break;
                case 3:
                    System.out.println(" ");
                    playListDAO.getExistingPlayList();
                    System.out.println(" ");
                    break;
                case 4:
                    System.out.println("Enter Category Name");
                    System.out.println();
                    String cat = scan.next().toLowerCase();
                    List<Songs> list2 = songsDAO.getAllSongs();
                    songsDAO.sortAllSongs(list2,cat);
                    List<Songs> list1 = songsDAO.getAllSongs();
                    songsDAO.SortedSongsInAlphabeticalOrder(list1);
                    for (Songs s : list1) {
                        System.out.println(s);
                    }
                    break;
                case 5:
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
                        int sid1=sc.nextInt();
                        songsDAO.searchBySongId(sid1);
                   }
                    if(i==3)
                    {
                        System.out.println("Enter genre category");
                        String cate=scan.next();
                        songsDAO.searchByGenre(cate);
                    }
                    System.out.println();
                    break;
                case 6:
                    playListDAO.getExistingPlayList();
                    PlayListDAO playListDAO2 = new PlayListDAO();
                    System.out.println("Enter PlaylistId Which You Want To Play");
                    int id = scan.nextInt();
                    playListDAO2.getPlaylistById(id);
                    playListDAO2.playSongInPlaylist(id);
                    playSongs.displayCurrentPlayingSong(id);
                    break;
                case 7:
                    String filePath ="";
                    PlayListDAO playListDAO1 = new PlayListDAO();
                    System.out.println("This is Existing Playlist");
                    playListDAO1.getExistingPlayList();
                    System.out.println("Enter Playlist Id Which You Want To Play");
                    int id2 = scan.nextInt();
                    playListDAO.getPlaylistById(id2);
                    System.out.println("Enter song Id which You want to play");
                    int sid1 = scan.nextInt();
                    playSongs.displayCurrentPlayingSong(sid1);
                    String sql1 = "SELECT filepath FROM songs WHERE songId = '" + sid1 + "'";
                    Statement stmt1 = connection.createStatement();
                    ResultSet rs1 = stmt1.executeQuery(sql1);
                    while (rs1.next()) {
                        filePath = rs1.getString("filepath");
                    }
                    jukebox1.readAudio(filePath);
                    break;
                case 8:
                    System.exit(0);
                    break;

                default:
                    System.out.println("invalid input");
                    break;
            }
        }
    }
}
