package org.almansa.app.test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.almansa.app.AppConfig;
import org.almansa.app.domain.album.Album;
import org.almansa.app.domain.album.AlbumType;
import org.almansa.app.domain.album.Artist;
import org.almansa.app.domain.album.Lable;
import org.almansa.app.domain.album.Song;
import org.almansa.app.domain.album.SongInAlbum;
import org.almansa.app.repository.AlbumRepository;
import org.almansa.app.util.DateUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { AppConfig.class }, loader = AnnotationConfigContextLoader.class)
@Transactional
public class AlbumRepositoryTest {

    @Autowired
    private AlbumRepository albumRepo;

    @PersistenceContext
    private EntityManager em;

    @Before
    public void makeDummies() {
        Lable illionaire = new Lable();
        illionaire.setName("Illionaire");
        em.persist(illionaire);

        Artist theQ = new Artist();
        theQ.setName("the quiett");
        theQ.changeLable(illionaire);
        theQ.setBornDate(1996, 1, 3);
        em.persist(theQ);

        Song song1 = new Song();
        song1.setName("song1");
        song1.setOwnerArtist(theQ);
        em.persist(song1);

        Song song2 = new Song();
        song2.setName("song2");
        song2.setOwnerArtist(theQ);
        em.persist(song2);

        Album album = new Album();
        album.setAlbumArtist(theQ);
        album.setAlbumType(AlbumType.LP);
        album.setName("Q Train");

        List<SongInAlbum> songList = new ArrayList<SongInAlbum>();
        songList.add(new SongInAlbum(album, song1, 1, false));
        songList.add(new SongInAlbum(album, song2, 2, false));
        album.setSongs(songList);
        em.persist(album);

        Album album2 = new Album();
        album2.setAlbumArtist(theQ);
        album2.setAlbumType(AlbumType.LP);
        album2.setName("Millionaire Poetry");
        em.persist(album2);

        em.flush();
    }

    @Test
    public void findByArtistNameTest() {
        String artistName = "the quiett";

        List<Album> list = albumRepo.findByArtistName(artistName);

        for (Album album : list) {
            assertEquals(artistName, album.getAlbumArtist().getName());
        }
    }

    @Test
    public void findByArtistNameNotExistTest() {
        String artistName = "Not Exists";

        List<Album> list = albumRepo.findByArtistName(artistName);

        assertEquals(0, list.size());
    }

    @Test
    public void findByArtistNameNullTest() {
        String artistName = null;

        List<Album> list = albumRepo.findByArtistName(artistName);

        assertEquals(0, list.size());
    }

    @Test
    public void findByAlbumNameTest() {
        String albumName = "Millionaire Poetry";

        List<Album> list = albumRepo.findByName(albumName);

        for (Album album : list) {
            assertEquals(albumName, album.getName());
        }
    }

    @Test
    public void findByAlbumNameNotExistTest() {
        String albumName = "Not Exists";

        List<Album> list = albumRepo.findByName(albumName);

        for (Album album : list) {
            assertEquals(albumName, album.getName());
        }
    }

    @Test
    public void findByAlbumNameNullTest() {
        String albumName = null;

        List<Album> list = albumRepo.findByName(albumName);

        for (Album album : list) {
            assertEquals(albumName, album.getName());
        }
    }

    @Test
    public void setAlbumNameUpdateTest() {
        String albumName = "Millionaire Poetry";
        List<Album> list = albumRepo.findByName(albumName);

        for (Album album : list) {
            album.setName("Millionaire Poetry2");
        }

        em.flush();

        albumName = "Millionaire Poetry2";
        list = albumRepo.findByName(albumName);

        for (Album album : list) {
            assertEquals(albumName, album.getName());
        }
    }
    
    @Test
    public void saveTest() {
        Album newAlbum = new Album();
        newAlbum.setAlbumArtist(null);
        newAlbum.setName("newAlbum");
        newAlbum.setReleaseDate(DateUtil.toDate(2017, 12, 1));
        
        albumRepo.save(newAlbum);
        em.flush();
        
        List<Album> list = albumRepo.findByName("newAlbum");      
        System.out.println(list.get(0).getName());
        assertEquals(1, list.size());
    }    
}
