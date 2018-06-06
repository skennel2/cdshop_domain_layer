package org.almansa.app.repository;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.almansa.app.domain.album.Album;
import org.almansa.app.domain.album.AlbumBuilder;
import org.almansa.app.domain.album.AlbumType;
import org.almansa.app.domain.album.Artist;
import org.almansa.app.domain.album.Lable;
import org.almansa.app.domain.album.Producer;
import org.almansa.app.domain.album.ProducerRole;
import org.almansa.app.domain.album.Song;
import org.almansa.app.domain.merchandise.AlbumMerchandise;
import org.almansa.app.domain.order.PurchaseOrder;
import org.almansa.app.domain.user.ApplicationUser;
import org.almansa.app.domain.user.PersonalInfomation;
import org.almansa.app.domain.value.EmailAddress;
import org.almansa.app.domain.value.Money;
import org.almansa.app.util.DateUtil;
import org.springframework.stereotype.Repository;

@Transactional
@Repository
public class DummyDataMaker {

    @PersistenceContext
    private EntityManager em;

    public void makeDummies() {

        Lable jm = new Lable("just musick");
        em.persist(jm);

        Lable illionaire = new Lable("Illionaire");
        em.persist(illionaire);

        Lable aomg = new Lable("AOMG");
        em.persist(aomg);

        Producer nochang = new Producer();
        nochang.changeName("nochang");
        nochang.setLable(jm);
        em.persist(nochang);

        Producer chachamalon = new Producer();
        chachamalon.changeName("cha cha malon");
        chachamalon.setLable(aomg);
        em.persist(chachamalon);

        Artist swings = new Artist("swings", DateUtil.toDate(1987, 3, 3) ,jm);
        em.persist(swings);

        Artist theQ = new Artist("the quiett", DateUtil.toDate(1996, 1, 3), illionaire);
        em.persist(theQ);

        Artist jayPark = new Artist("jay park", DateUtil.toDate(1988, 3, 4), aomg);
        em.persist(jayPark);

        Artist kidMilli = new Artist("kid milli", null, null);
        em.persist(kidMilli);

        Artist dok2 = new Artist("dok2", null, illionaire);
        em.persist(dok2);

        Song song = new Song("holy", swings, null, "");
        song.addPersonAsSongWriter(nochang, ProducerRole.MainProducer);
        em.persist(song);

        Song song2 = new Song("shit is real", swings, null, "");
        song2.addPersonAsSongWriter(theQ, ProducerRole.Featuring);
        song2.addPersonAsSongWriter(kidMilli, ProducerRole.Featuring);
        em.persist(song2);

        Song song3 = new Song("intro", swings, null, "");
        song3.addPersonAsSongWriter(chachamalon, ProducerRole.MainProducer);
        em.persist(song3);

        Album album = new AlbumBuilder().artist(swings).name("upgrade3").releaseDate(new Date()).thisIsLPType()
                .addSong(song, 1, true).addSong(song2, 2, false).Build();

        em.persist(album);

        Album album2 = new AlbumBuilder().artist(swings).name("shit is real single").releaseDate(new Date())
                .albumType(AlbumType.Single).Build();
        em.persist(album2);

        AlbumMerchandise am1 = new AlbumMerchandise(new Long(500), new Money(new BigDecimal("12000")), album);
        em.persist(am1);

        AlbumMerchandise am2 = new AlbumMerchandise(new Long(500), new Money(new BigDecimal("17500")), album2);
        em.persist(am2);

        ApplicationUser user = new ApplicationUser("skennel", "skennel", "1234");
        em.persist(user);

        PersonalInfomation userInfo = new PersonalInfomation(user, new EmailAddress("skennel@naver.com"), new Date());
        em.persist(userInfo);

        user.setPersonalInfomation(userInfo);

        ApplicationUser user2 = new ApplicationUser("gaeko14", "gaeko14", "3333");
        em.persist(user2);

        PersonalInfomation userInfo2 = new PersonalInfomation(user2, new EmailAddress("gaeko14@naver.com"), new Date());
        em.persist(userInfo2);

        user2.setPersonalInfomation(userInfo2);

        PurchaseOrder order = new PurchaseOrder(user, null, DateUtil.toDate(2018, 11, 2));
        order.addOrderLine(am1, 2);
        order.addOrderLine(am2, 1);

        em.persist(order);

    }
}
