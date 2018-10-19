package com.codenotfound.crnk;


import com.codenotfound.crnk.domain.model.*;
import com.codenotfound.crnk.domain.repository.BookCategoryRepository;
import com.codenotfound.crnk.domain.repository.BookRepository;
import com.codenotfound.crnk.domain.repository.LibraryRepository;
import com.codenotfound.crnk.domain.repository.PersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Configuration
//@ComponentScan(basePackages = {"com.cemoli.crnk.domain"})
public class SpringCrnkAppConf {
    private static final Logger LOGGER = LoggerFactory.getLogger(SpringCrnkAppConf.class);
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookCategoryRepository bookCategoryRepository;

    @Autowired
    private LibraryRepository libraryRepository;


    @Autowired
    PersonRepository personRepository;

    @PostConstruct
    public void init() {
        LOGGER.info("################## inside init #######################");
        Book b1,b2,b3;
        b1=new Book("book1","bok1 hakkinda");
        b2=new Book("book2","bok2 hakkinda");
        b3= new Book("book3","bok3 hakkinda");

        Name morrisname=new Name("Herr","Kabalas","Morris","","Dr.");
        Name lorrisname=new Name("Herr","Mabile","Lorris","","");
        Name lauraname=new Name("Frau","Salama","Laura","","Eng.");

        Person person1 = new Person(lorrisname,"Student");
        Person person2 = new Person(lauraname, "Eng");
        Person person3 =new Person(morrisname,"");

        b1.setPeople(Stream.of(person1).collect(Collectors.toList()));
        b2.setPeople(Stream.of(person2).collect(Collectors.toList()));
        b3.setPeople(Stream.of(person3).collect(Collectors.toList()));

        person1.setBooks(Stream.of(b1).collect(Collectors.toList()));
        person2.setBooks(Stream.of(b2).collect(Collectors.toList()));
        person3.setBooks(Stream.of(b3).collect(Collectors.toList()));

        BookCategory bc1,bc2;
        bc1=new BookCategory("Birinci");
        bc2=new BookCategory("Ikinci");

        Address a1, a2, a3;
        a1= new Address("Herbertshofstrasse", 10, "45123", "Essen");
        a2= new Address("Grabenstr",6,"23233", "Essen");
        a3 = new Address("Sumperkamp", 46, "44801", "Bochum");

        person1.setAddress(a1);
        person2.setAddress(a2);
        person3.setAddress(a3);


        Library l1 = new Library();
        l1.setLname("Zentralbibliothek RUB");
        l1.setAddress(a1);
        b1.setBookCategory(bc1);
        b2.setBookCategory(bc1);
        b3.setBookCategory(bc2);
        b1.setLibrary(l1);
        b2.setLibrary(l1);
        b3.setLibrary(l1);
        l1.setBooks(Stream.of(b1,b2,b3).collect(Collectors.toList()));


        bc1.setBooks(Stream.of(b1,b2).collect(Collectors.toSet()));
        bc2.setBooks(Stream.of(b3).collect(Collectors.toSet()));

//        bookRepository.create(b1);
//        bookRepository.create(b2);
//        bookRepository.create(b3);
        bookCategoryRepository.create(bc1);
        bookCategoryRepository.create(bc2);
       // addressRepository.create(a1);
//        addressRepository.create(a2);
        libraryRepository.create(l1);

        personRepository.create(person1);
        personRepository.create(person2);
        personRepository.create(person3);




    }

    @Bean
    public EmbeddedServletContainerFactory servletContainer() {
        TomcatEmbeddedServletContainerFactory factory =
                new TomcatEmbeddedServletContainerFactory();
        return factory;
    }
}