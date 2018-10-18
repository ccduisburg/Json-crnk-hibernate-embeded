package com.codenotfound.crnk.domain.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.crnk.core.resource.annotations.JsonApiId;
import io.crnk.core.resource.annotations.JsonApiRelation;
import io.crnk.core.resource.annotations.JsonApiResource;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="person")
@Getter
@Setter
@JsonApiResource(type = "person")
public class Person implements Serializable {
@Id
@JsonApiId
@GeneratedValue
  private Long id;
 // @Column
  @Embedded
  private Name name;

  @Column
  private String beruf;

  @Embedded
  private Address address;



//  @OneToOne(mappedBy = "people", cascade = CascadeType.ALL,
//  fetch = FetchType.EAGER, optional = false)
////    @OneToOne(cascade = CascadeType.ALL,
////        fetch = FetchType.EAGER)
//  @JsonApiRelation(opposite = "people")
//  private Address address;

  @ManyToMany(mappedBy = "people", cascade = CascadeType.ALL,
          fetch = FetchType.EAGER)
  @JsonApiRelation(opposite = "people")
  @JsonIgnore
  private List<Book> books;

  public Person() {
      }

  public Person(Name name, String beruf, Address address) {
    this.name = name;
    this.beruf = beruf;
    this.address = address;
  }

  public Person(Name name) {
    this.name = name;
  }
  public Person(Name name,String beruf) {
    this.name = name;
    this.beruf=beruf;
  }
  @Override
  public String toString() {
    return "person[id=" + id + ", name=" + name + "]"+ " beruf= [" +beruf+"]";
  }
}
