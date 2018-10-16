package com.codenotfound.crnk.domain.model;

import io.crnk.core.resource.annotations.JsonApiId;
import io.crnk.core.resource.annotations.JsonApiRelation;
import io.crnk.core.resource.annotations.JsonApiResource;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="book")
@Getter
@Setter
@JsonApiResource(type="books")
public class Book implements Serializable {
    @Id
    @JsonApiId
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private String description;

    @ManyToOne
    @JsonApiRelation(opposite = "books")
    private BookCategory bookCategory;

    @ManyToOne
    @JsonApiRelation(opposite = "books")
    private Library library;

    public Book(){}
    {
        this.description = description;
    }




    public Book(Long id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }

    public Book(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public String toString() {
        return String.format("Book(%d,%s,%s)", id, title, description);
    }
}