package com.codenotfound.crnk.domain.endP;

import com.codenotfound.crnk.domain.control.BookCategoryTable;
import com.codenotfound.crnk.domain.model.BookCategory;
import io.crnk.core.queryspec.QuerySpec;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

public class BookCategoryEndPoint extends APIEndpointV2<BookCategory, Long> {

    @Inject
    private BookCategoryTable igQZgTable;


    @Override
    public BookCategory findByID(Long id) {
        try {
            return igQZgTable.findById(id);
        } catch (NumberFormatException ignored) {
        }
        return null;
    }


    public List<BookCategory> findBy(QuerySpec querySpec) {
        Optional<String> name = getFilterParam(querySpec, "name");

        if(name.isPresent()) return igQZgTable.findByname(name.get());
        return  igQZgTable.findAll();
    }

    @Override
    public <S extends  BookCategory> S create (S s) {

        igQZgTable.insert(s);
        S al=(S)findByID(s.getId());

        return al;
    }


    public void delete(BookCategory id){

        igQZgTable.delete(id);
    }



    @Override
    public <S extends BookCategory> S save(S s) {

        return (S)igQZgTable.update(s);


    }
}
