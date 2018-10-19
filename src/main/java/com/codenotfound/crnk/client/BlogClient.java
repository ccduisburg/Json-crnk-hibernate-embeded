package com.codenotfound.crnk.client;

import com.codenotfound.crnk.domain.model.*;
import io.crnk.client.CrnkClient;
import io.crnk.core.queryspec.QuerySpec;
import io.crnk.core.repository.RelationshipRepositoryV2;
import io.crnk.core.repository.ResourceRepositoryV2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
public class BlogClient {

  private static final Logger LOGGER = LoggerFactory.getLogger(BlogClient.class);

  private CrnkClient crnkClient = new CrnkClient("http://localhost:9090/codenotfound/api");

  private ResourceRepositoryV2<Article, Long> articleResourceRepositoryV2;
  private ResourceRepositoryV2<Person, Long> personResourceRepositoryV2;
  private ResourceRepositoryV2<Book, Long> bookLongResourceRepositoryV2;
  private ResourceRepositoryV2<BookCategory, Long> bookCategoryLongResourceRepositoryV2;
  private ResourceRepositoryV2<Library, Long> libraryLongResourceRepositoryV2;


  private RelationshipRepositoryV2<Book, Long, Library, Long> bookLongLibraryLongRelationshipRepositoryV2;
  private RelationshipRepositoryV2<Book, Long, Person, Long> bookLongPersonLongRelationshipRepositoryV2;


  @PostConstruct
  public void init() {
   // articleResourceRepositoryV2 = crnkClient.getRepositoryForType(Article.class);
    personResourceRepositoryV2 = crnkClient.getRepositoryForType(Person.class);
    bookLongResourceRepositoryV2 = crnkClient.getRepositoryForType(Book.class);
    bookCategoryLongResourceRepositoryV2 = crnkClient.getRepositoryForType(BookCategory.class);
    libraryLongResourceRepositoryV2 = crnkClient.getRepositoryForType(Library.class);
    bookLongLibraryLongRelationshipRepositoryV2 = crnkClient.getRepositoryForType(Book.class, Library.class);
    bookLongPersonLongRelationshipRepositoryV2 = crnkClient.getRepositoryForType(Book.class, Person.class);

  }


  public Book findOneBook(long id) {
    Book result = bookLongResourceRepositoryV2.findOne(id, new QuerySpec(Book.class));

    LOGGER.info("found {}", result.toString());
    return result;
  }

  public Person findOnePerson(long id) {
    Person result = personResourceRepositoryV2.findOne(id, new QuerySpec(Person.class));

    LOGGER.info("found {}", result.toString());
    return result;
  }


  public List<Book> findAllBooks() {
    return bookLongResourceRepositoryV2.findAll(new QuerySpec(Book.class));
  }

  public List<BookCategory> findAllBookCategories() {
    return bookCategoryLongResourceRepositoryV2.findAll(new QuerySpec(BookCategory.class));
  }

  public List<Library> findAllLibraries() {
    return libraryLongResourceRepositoryV2.findAll(new QuerySpec(Library.class));
  }


  public List<Person> findAllPerson() {
    return personResourceRepositoryV2.findAll(new QuerySpec(Person.class));
  }

  public List<BookPersonAddressLibrary> findBookPersonAddressLibrary() {
    List<BookPersonAddressLibrary> ret = new ArrayList<>();
    List<Book> books = bookLongResourceRepositoryV2.findAll(new QuerySpec(Book.class));
    books.forEach(book -> {
//      Person firstPerson = book.getPeople().get(0);
      Person firstPerson = bookLongPersonLongRelationshipRepositoryV2.findManyTargets(book.getId(), "people", new QuerySpec(Book.class)).get(0);
//      Library library = book.getLibrary();
      Library library = bookLongLibraryLongRelationshipRepositoryV2.findOneTarget(book.getId(), "library", new QuerySpec(Book.class));

      //  Address personAddress = personLongAddressLongRelationshipRepositoryV2.findOneTarget(firstPerson.getId(), "address", new QuerySpec(Person.class));
      ret.add(new BookPersonAddressLibrary(firstPerson.getName().getLastname(), firstPerson.getName().getVorname(), firstPerson.getBeruf(),
              firstPerson.getAddress().getStrasse(), firstPerson.getAddress().getHnummer(), firstPerson.getAddress().getPLZ(),
              firstPerson.getAddress().getStadt(),
              book.getTitle(), book.getDescription(), library.getLname()));
    });
    return ret;
  }
//-------------------------------create---------------------------------

    public void createPerson(Person person){

        personResourceRepositoryV2.create(person);
//        List<Book> book=person.getBooks();
//        bookLongResourceRepositoryV2.create(book.get(0));

    }


    public void createLibrary(Library library){
        libraryLongResourceRepositoryV2.create(library);

    }

    public void createbookCategory(BookCategory bookCategory){

        bookCategoryLongResourceRepositoryV2.create(bookCategory);
    }


    public void cretateBook(Book book){
        bookLongResourceRepositoryV2.create(book);

    }
//--------------------------------------------------Update-----------------------
    public void updatePerson(Person person){

        personResourceRepositoryV2.save(person);

    }


    public void updateLibrary(Library library){
        libraryLongResourceRepositoryV2.save(library);

    }

    public void updatebookCategory(BookCategory bookCategory){

        bookCategoryLongResourceRepositoryV2.save(bookCategory);
    }


    public void updateBook(Book book){
        bookLongResourceRepositoryV2.save(book);

    }

    //-----------------------------Delete-----------------------------------------------

    public void deletePerson(Long id){

        personResourceRepositoryV2.delete(id);

    }


    public void deleteLibrary(Long library){
        libraryLongResourceRepositoryV2.delete(library);

    }

    public void updatebookCategory(Long bookCategory){

        bookCategoryLongResourceRepositoryV2.delete(bookCategory);
    }


    public void updateBook(Long book){
        bookLongResourceRepositoryV2.delete(book);

    }

}
