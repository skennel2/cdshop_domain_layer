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
    	AnnotationConfigApplicationContext context = null;
    	try {
	        context = new AnnotationConfigApplicationContext(AppConfig.class);	        
	        context.scan("org.almansa.app");
	
	        makeDummies(context.getBean(DummyDataMaker.class));
	        hadleLableService(context.getBean(LableService.class));
	        
    	}catch(Exception ex) {
    		ex.printStackTrace();
    	}finally {
    		context.close();	
		}        
    }
    
    public static void makeDummies(DummyDataMaker dummyDataMaker) {
    	dummyDataMaker.makeDummies();
    }
    
    public static void hadleLableService(LableService service) {
    	Lable lable = new Lable();
    	lable.changeName("ambition musick");    	     
    	
    	service.addLable(lable);
    	
    	List<Lable> lables = service.getByName("ambition musick");
    	
        for (Lable item : lables) {
        	print(item.toString());
		}
        
    }
    
    public static void print(String str) {
    	System.out.println("App:");
    	System.out.print("\t");
    	System.out.println(str);
    }
}
