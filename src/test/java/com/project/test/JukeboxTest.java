package com.project.test;

import com.project.Bean.Songs;
import com.project.DAO.SongsDAO;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class JukeboxTest
{
    SongsDAO songsDAO = new SongsDAO();
@Before
    public void setUp(){

}
    @After
    public void tearDown(){

    }
    @Test
    public void SortedSongsInAlphabeticalOrder(){
        List<Songs> output = songsDAO.getAllSongs();
        assertEquals("Songs list not returned correctly",8,output.size());
    }
    @Test
    public void getAllSongs(){
        List<Songs> output = songsDAO.getAllSongs();
        assertEquals(8,output.size());
    }
}
