package org.almansa.app;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

@Repository
@Transactional
public class StudentRepository {
    @PersistenceContext
    private EntityManager em;
    
    
    public void save(Student student) {
        em.persist(student);
    }
    
    public Student getById(Long id) {
        return em.find(Student.class, id);
    }
}
