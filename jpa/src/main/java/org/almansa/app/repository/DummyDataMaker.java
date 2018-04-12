package org.almansa.app.repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.almansa.app.domain.PersonBase;
import org.almansa.app.domain.album.Album;
import org.almansa.app.domain.album.AlbumBuilder;
import org.almansa.app.domain.album.AlbumType;
import org.almansa.app.domain.album.Artist;
import org.almansa.app.domain.album.CategoryTag;
import org.almansa.app.domain.album.Lable;
import org.almansa.app.domain.album.Producer;
import org.almansa.app.domain.album.Song;
import org.almansa.app.domain.merchandise.AlbumMerchandise;
import org.almansa.app.domain.order.PurchaseOrder;
import org.almansa.app.domain.user.ApplicationUser;
import org.almansa.app.domain.value.Money;
import org.almansa.app.util.DateUtil;
import org.springframework.stereotype.Repository;

@Transactional
@Repository
public class DummyDataMaker {

    @PersistenceContext
    private EntityManager em;

    public void makeDummies() {
        CategoryTag tag1 = new CategoryTag();
        tag1.setName("HipHop");

        CategoryTag tag2 = new CategoryTag();
        tag2.setName("Show me the Money");

        CategoryTag tag3 = new CategoryTag();
        tag3.setName("album of the year");

        Lable jm = new Lable();
        jm.changeName("just musick");
        em.persist(jm);

        Lable illionaire = new Lable();
        illionaire.changeName("Illionaire");
        em.persist(illionaire);

        Lable aomg = new Lable();
        aomg.changeName("AOMG");
        em.persist(aomg);

        Producer nochang = new Producer();
        nochang.changeName("nochang");
        nochang.setLable(jm);
        em.persist(nochang);

        Producer chachamalon = new Producer();
        chachamalon.changeName("cha cha malon");
        chachamalon.setLable(aomg);
        em.persist(chachamalon);

        Artist swings = new Artist();
        swings.changeName("swings");
        swings.changeLable(jm);
        em.persist(swings);

        Artist theQ = new Artist();
        theQ.changeName("the quiett");
        theQ.changeLable(illionaire);
        theQ.setBornDate(1996, 1, 3);
        em.persist(theQ);

        Artist jayPark = new Artist();
        jayPark.changeName("jay park");
        jayPark.changeLable(aomg);
        jayPark.setBornDate(1988, 3, 4);
        em.persist(jayPark);

        Artist kidMilli = new Artist();
        kidMilli.changeName("kid milli");
        kidMilli.changeLable(null);
        em.persist(kidMilli);

        Artist dok2 = new Artist();
        dok2.changeName("dok2");
        dok2.changeLable(illionaire);
        em.persist(dok2);

        Song song = new Song();
        song.changeName("holy");
        song.setOwnerArtist(swings);
        song.setMainProducer(nochang);
        em.persist(song);

        Song song2 = new Song();
        song2.changeName("shit is real");
        song2.setOwnerArtist(swings);
        song2.setMainProducer(chachamalon);
        List<PersonBase> list = new ArrayList<PersonBase>();
        list.add(theQ);
        list.add(kidMilli);
        song2.setArtists(list);
        em.persist(song2);

        Song song3 = new Song();
        song3.changeName("intro");
        song3.setOwnerArtist(swings);
        song3.setMainProducer(chachamalon);
        em.persist(song3);

        // TODO to Builder
        Album album = new AlbumBuilder()
                .artist(swings)
                .name("upgrade3")
                .releaseDate(new Date())
                .thisIsLPType()
                .Build();
        album.addSong(song, 1, false);
        album.addSong(song2, 2, false);
        album.addCategory(tag1);
        album.addCategory(tag2);
        
        em.persist(album);

        Album album2 = new AlbumBuilder()
                .artist(swings)
                .name("shit is real single")
                .releaseDate(new Date())
                .albumType(AlbumType.Single).Build();
        em.persist(album2);

        AlbumMerchandise am1 = new AlbumMerchandise();
        am1.setAlbum(album);
        am1.setPrice(new Money(new BigDecimal("12000")));
        am1.setAmountOfStock(new Long(500));
        em.persist(am1);

        AlbumMerchandise am2 = new AlbumMerchandise();
        am2.setAlbum(album2);
        am2.setPrice(new Money(new BigDecimal("17500")));
        am2.setAmountOfStock(new Long(500));
        em.persist(am2);

        ApplicationUser user = new ApplicationUser();
        user.changeName("skennel");
        em.persist(user);

        PurchaseOrder order = new PurchaseOrder(user, null, DateUtil.toDate(2018, 11, 2));
        order.addOrderLine(am1, 2);
        order.addOrderLine(am2, 1);

        em.persist(order);

    }

    public Song getSong(Long id) {
        return em.find(Song.class, id);
    }
}
