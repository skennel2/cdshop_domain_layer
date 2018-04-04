package org.almansa.app.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.almansa.app.domain.AgencyCompany;
import org.almansa.app.domain.Artist;
import org.almansa.app.domain.Producer;
import org.almansa.app.domain.Song;
import org.springframework.stereotype.Repository;

@Transactional
@Repository
public class TestRepository {

	@PersistenceContext
	private EntityManager em;
	
	public void test() {
		AgencyCompany company = new AgencyCompany();
		company.changeName("just musik");
		em.persist(company);
		
		Producer producer = new Producer();
		producer.setName("nochang");
		em.persist(producer);
		
		Producer producer2 = new Producer();
		producer2.setName("chachamalon");
		em.persist(producer2);		
		
		Artist artist = new Artist();
		artist.changeName("swings");
		artist.changeAgencyCompany(company);
		em.persist(artist);
		
		Artist artist2 = new Artist();
		artist2.changeName("the quiett");
		artist2.changeAgencyCompany(null);
		em.persist(artist2);	

		Artist artist3 = new Artist();
		artist3.changeName("kidmilli");
		artist3.changeAgencyCompany(null);
		em.persist(artist3);	
		
		Song song = new Song();
		song.setName("holy");
		song.setOwnerArtist(artist);
		song.setProducer(producer);
		em.persist(song);
		
		Song song2 = new Song();
		song2.setName("shit is real");
		song2.setOwnerArtist(artist);
		song2.setProducer(producer2); 
		List<Artist> list =  new ArrayList<Artist>();
		list.add(artist2);
		list.add(artist3);
		song2.setArtists(list);
		em.persist(song2);
	}
	
	public Song getSong(Long id) {
		return em.find(Song.class, id);
	}
	
}
