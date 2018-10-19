package com.codenotfound.crnk.domain.control;

import com.codenotfound.crnk.domain.model.Library;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

public class LibraryTable {


    @PersistenceContext(unitName = "test2")
    private EntityManager em;


    public List<Library> findByname(String name) {
        try {
            return em.createQuery("from  Library where name = :name",
                    Library.class)
                    .setParameter("name", name)
                    .getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Transactional
    public void insert(Library pt) {
        em.persist(pt);
    }


    @Transactional
    public Library update(Library neueigQg) {
        return em.merge(neueigQg);
    }



    @Transactional
    public void delete(Library id)  {
        em.remove(em.merge(id));
    }

    public Library findById(Integer id) {
        try {
            return em.createQuery("from Library where id = :id", Library.class)
                    .setParameter("id", id)
                    .getSingleResult();
        } catch(NoResultException ex)  {
            return null;
        }
    }

    public List<Library> findAll() {
        List<Library> all=em.createQuery("select i from Library i", Library.class)
                .getResultList();
        return all;
    }



}
