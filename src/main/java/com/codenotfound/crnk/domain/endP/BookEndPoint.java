package com.codenotfound.crnk.domain.endP;

import com.codenotfound.crnk.domain.control.BookTable;
import com.codenotfound.crnk.domain.model.Book;
import io.crnk.core.queryspec.QuerySpec;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

public class BookEndPoint  extends APIEndpointV2<Book, Long> {

    @Inject
    private BookTable igQZgTable;


    @Override
    public Book findByID(Long id) {
        try {
            return igQZgTable.findById(id);
        } catch (NumberFormatException ignored) {
        }
        return null;
    }


    public List<Book> findBy(QuerySpec querySpec) {
        Optional<String> name = getFilterParam(querySpec, "name");

        if(name.isPresent()) return igQZgTable.findByname(name.get());
        return  igQZgTable.findAll();
    }

    @Override
    public <S extends  Book> S create (S s) {

        igQZgTable.insert(s);
        S al=(S)findByID(s.getId());

        return al;
    }


    public void delete(Book id){

        igQZgTable.delete(id);
    }



    @Override
    public <S extends Book> S save(S s) {

        return (S)igQZgTable.update(s);


    }
}
