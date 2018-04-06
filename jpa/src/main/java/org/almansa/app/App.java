package org.almansa.app;

import org.almansa.app.repository.DummyDataMaker;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = null;
        try {
            context = new AnnotationConfigApplicationContext(AppConfig.class);
            context.scan("org.almansa.app");

            makeDummies(context.getBean(DummyDataMaker.class));

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            context.close();
        }
    }

    public static void makeDummies(DummyDataMaker dummyDataMaker) {
        dummyDataMaker.makeDummies();
    }

    public static void print(String str) {
        System.out.println("App:");
        System.out.print("\t");
        System.out.println(str);
    }
}
