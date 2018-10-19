package com.codenotfound.crnk.domain.control;


import com.codenotfound.crnk.domain.model.Book;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

public class BookTable {


    @PersistenceContext(unitName = "test2")
    private EntityManager em;


    public List<Book> findByname(String title) {
        try {
            return em.createQuery("from  Book where title = :title",
                    Book.class)
                    .setParameter("title", title)
                    .getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Transactional
    public void insert(Book pt) {
        em.persist(pt);
    }


    @Transactional
    public Book update(Book neueigQg) {
        return em.merge(neueigQg);
    }



    @Transactional
    public void delete(Book id)  {
        em.remove(em.merge(id));
    }

    public Book findById(Long id) {
        try {
            return em.createQuery("from Book where id = :id", Book.class)
                    .setParameter("id", id)
                    .getSingleResult();
        } catch(NoResultException ex)  {
            return null;
        }
    }

    public List<Book> findAll() {
        List<Book> all=em.createQuery("select i from Book i", Book.class)
                .getResultList();
        return all;
    }



}



