package org.almansa.app;

import org.almansa.app.domain.Student;
import org.almansa.app.repository.StudentRepository;
import org.almansa.app.service.MessageProvider;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        context.scan("org.almansa.app");

        MessageProvider provider = context.getBean(MessageProvider.class);
        
        System.out.println(provider.getMessage());
        
        StudentRepository repo = context.getBean(StudentRepository.class);
        Student student = new Student();
        student.setName("Nys");
        
        repo.save(student);
        
        Student student2 = repo.getById(new Long(1));
        System.out.println(student2.toString());
        
        context.close();
    }
}
