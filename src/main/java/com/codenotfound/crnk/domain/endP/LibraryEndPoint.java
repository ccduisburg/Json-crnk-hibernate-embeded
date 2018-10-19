package com.codenotfound.crnk.domain.endP;

import com.codenotfound.crnk.domain.control.LibraryTable;
import com.codenotfound.crnk.domain.model.Library;
import io.crnk.core.queryspec.QuerySpec;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

public class LibraryEndPoint extends APIEndpointV2<Library, Long> {

    @Inject
    private LibraryTable igQZgTable;


    @Override
    public Library findByID(Long id) {
        try {
            return igQZgTable.findById(id);
        } catch (NumberFormatException ignored) {
        }
        return null;
    }


    public List<Library> findBy(QuerySpec querySpec) {
        Optional<String> name = getFilterParam(querySpec, "name");

        if(name.isPresent()) return igQZgTable.findByname(name.get());
        return  igQZgTable.findAll();
    }

    @Override
    public <S extends  Library> S create (S s) {

        igQZgTable.insert(s);
        S al=(S)findByID(s.getId());

        return al;
    }


    public void delete(Library id){

        igQZgTable.delete(id);
    }



    @Override
    public <S extends Library> S save(S s) {

        return (S)igQZgTable.update(s);


    }
}
