package com.codenotfound.crnk.domain.control;

import com.codenotfound.crnk.domain.model.BookCategory;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

public class BookCategoryTable {


    @PersistenceContext(unitName = "test2")
    private EntityManager em;


    public List<BookCategory> findByname(String name) {
        try {
            return em.createQuery("from  BookCategory where name = :name",
                    BookCategory.class)
                    .setParameter("name", name)
                    .getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Transactional
    public void insert(BookCategory pt) {
        em.persist(pt);
    }


    @Transactional
    public BookCategory update(BookCategory neueigQg) {
        return em.merge(neueigQg);
    }



    @Transactional
    public void delete(BookCategory id)  {
        em.remove(em.merge(id));
    }

    public BookCategory findById(Long id) {
        try {
            return em.createQuery("from BookCategory where id = :id", BookCategory.class)
                    .setParameter("id", id)
                    .getSingleResult();
        } catch(NoResultException ex)  {
            return null;
        }
    }

    public List<BookCategory> findAll() {
        List<BookCategory> all=em.createQuery("select i from BookCategory i", BookCategory.class)
                .getResultList();
        return all;
    }



}
