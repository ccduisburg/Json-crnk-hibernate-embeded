package com.codenotfound.crnk.domain.control;

import com.codenotfound.crnk.domain.model.Person;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

public class PersonTable {

    @PersistenceContext(unitName = "test2")
    private EntityManager em;


    public List<Person> findByname(String name) {
        try {
            return em.createQuery("from  Person where name = :name",
                    Person.class)
                    .setParameter("name", name)
                    .getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Transactional
    public void insert(Person pt) {
        em.persist(pt);
    }


    @Transactional
    public Person update(Person neueigQg) {
        return em.merge(neueigQg);
    }



    @Transactional
    public void delete(Person id)  {
        em.remove(em.merge(id));
    }

    public Person findById(Long id) {
        try {
            return em.createQuery("from Person where id = :id", Person.class)
                    .setParameter("id", id)
                    .getSingleResult();
        } catch(NoResultException ex)  {
            return null;
        }
    }

    public List<Person> findAll() {
        List<Person> all=em.createQuery("select i from Person i", Person.class)
                .getResultList();
        return all;
    }
    
}
