package com.codenotfound.crnk.domain.endP;

import com.codenotfound.crnk.domain.control.PersonTable;
import com.codenotfound.crnk.domain.model.Person;
import io.crnk.core.queryspec.QuerySpec;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

public class PersonEndPoint  extends APIEndpointV2<Person, Long> {

    @Inject
    private PersonTable igQZgTable;


    @Override
    public Person findByID(Long id) {
        try {
            return igQZgTable.findById(id);
        } catch (NumberFormatException ignored) {
        }
        return null;
    }


    public List<Person> findBy(QuerySpec querySpec) {
        Optional<String> name = getFilterParam(querySpec, "name");

        if(name.isPresent()) return igQZgTable.findByname(name.get());
        return  igQZgTable.findAll();
    }

    @Override
    public <S extends  Person> S create (S s) {

        igQZgTable.insert(s);
        S al=(S)findByID(s.getId());

        return al;
    }


    public void delete(Person id){

        igQZgTable.delete(id);
    }



    @Override
    public <S extends Person> S save(S s) {

        return (S)igQZgTable.update(s);


    }
}
