package org.almansa.app;

import java.util.List;

import org.almansa.app.domain.Lable;
import org.almansa.app.domain.PersonBase;
import org.almansa.app.domain.Song;
import org.almansa.app.repository.DummyDataMaker;
import org.almansa.app.service.LableService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        context.scan("org.almansa.app");

        DummyDataMaker repo = context.getBean(DummyDataMaker.class);
    
        repo.makeDummies();
        Song song = repo.getSong(new Long(15));
        
        List<PersonBase> list = song.getArtists();
        
        for (PersonBase artist : list) {
        	System.out.println(artist.toString());
		}
        
        System.out.println(song.toString());
        
        hadleLableService(context.getBean(LableService.class));
        
        context.close();
    }
    
    public static void hadleLableService(LableService service) {
    	Lable lable = new Lable();
    	lable.changeName("ambition musick");
    	
    	service.addLable(lable);
    }
}
