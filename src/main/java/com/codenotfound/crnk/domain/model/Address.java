package com.codenotfound.crnk.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.crnk.core.resource.annotations.JsonApiId;
import io.crnk.core.resource.annotations.JsonApiRelation;
import io.crnk.core.resource.annotations.JsonApiResource;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

//@Entity
//@Table(name="address")
@Embeddable
@Getter
@Setter
//@JsonApiResource(type="adresses")
//public class Address implements Serializable {
public class Address{

//    @GeneratedValue
//    @JsonApiId
//    private long id;

    @Column//(nullable = false)
    private String strasse;

    @Column
    private Integer hnummer;

    @Column
    private String PLZ;

    @Column
    private String stadt;


//    @OneToOne(fetch = FetchType.LAZY)
//    @JsonIgnore
//    @JoinColumn(name = "library_id")
//    @JsonApiRelation(opposite = "address")
//    private Library library;
//
//    @OneToOne(fetch = FetchType.LAZY)
//    @JsonIgnore
//    @JoinColumn(name = "person_id")
//    @JsonApiRelation(opposite = "address")
//    private Person people;

    public Address(String strasse, Integer hnummer, String PLZ, String stadt) {
        this.strasse = strasse;
        this.hnummer = hnummer;
        this.PLZ = PLZ;
        this.stadt = stadt;
    }

    public Address() {
    }

    public String toString() {
        return String.format("Address(%d,%s,%d,%s,%s)", strasse, hnummer, PLZ, stadt);
    }
}
