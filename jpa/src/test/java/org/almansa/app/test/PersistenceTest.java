package org.almansa.app.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import org.almansa.app.AppConfig;
import org.almansa.app.domain.album.Album;
import org.almansa.app.domain.album.AlbumType;
import org.almansa.app.domain.album.Artist;
import org.almansa.app.domain.album.Lable;
import org.almansa.app.domain.album.Song;
import org.almansa.app.domain.album.SongInAlbum;
import org.almansa.app.domain.merchandise.AlbumMerchandise;
import org.almansa.app.domain.order.PurchaseOrder;
import org.almansa.app.domain.user.ApplicationUser;
import org.almansa.app.domain.value.Money;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { AppConfig.class })
@Transactional
public class PersistenceTest {

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
    public void persistAndFindTest() {
        Album album = new Album();
        album.setName("illmatic");
        album.setAlbumType(AlbumType.LP);

        em.persist(album);
        em.flush();

        Album albumFind = em.find(Album.class, album.getId());

        boolean isEquals = album.equals(albumFind);
        assertEquals(true, isEquals);
        assertEquals("illmatic", albumFind.getName());
    }

    @Test
    public void joinQueryTest() {
        TypedQuery<Artist> query = em.createQuery(
                "SELECT B FROM Album A INNER JOIN A.albumArtist B WHERE B.name = :aritst_name", Artist.class);

        query.setParameter("aritst_name", "the quiett");
        Artist artist = query.getSingleResult();

        assertEquals("the quiett", artist.getName());
        System.out.println(artist);
    }

    @Test
    public void OrderPersistAndDomainTest() {
        TypedQuery<Album> q = em.createQuery("SELECT A FROM Album A WHERE A.name = :name", Album.class);
        q.setParameter("name", "Q Train");

        Album album = q.getSingleResult();
        AlbumMerchandise albumMd1 = new AlbumMerchandise();
        albumMd1.setAlbum(album);
        albumMd1.setAmountOfStock(new Long(200));
        albumMd1.setPrice(new Money(25000));
        em.persist(albumMd1);

        q.setParameter("name", "Millionaire Poetry");

        Album album2 = q.getSingleResult();
        AlbumMerchandise albumMd2 = new AlbumMerchandise();
        albumMd2.setAlbum(album2);
        albumMd2.setAmountOfStock(new Long(200));
        albumMd2.setPrice(new Money(10000));
        em.persist(albumMd2);

        ApplicationUser user = new ApplicationUser();
        user.setName("skennel");
        em.persist(user);

        if (album != null) {
            PurchaseOrder newOrder = new PurchaseOrder();
            newOrder.addOrderLine(albumMd1, 10);
            newOrder.setOrderer(user);
            newOrder.setOrderDate(new Date());
            em.persist(newOrder);

            assertEquals(newOrder.calculateTotalPrice(), new Money(250000));

            newOrder.addOrderLine(albumMd2, 5);
            newOrder.setOrderer(user);
            newOrder.setOrderDate(new Date());

            assertEquals(newOrder.calculateTotalPrice(), new Money(300000));
        } else {
            fail("album not found");
        }
    }
}