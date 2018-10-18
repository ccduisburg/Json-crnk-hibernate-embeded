package com.codenotfound.crnk.client;

import com.codenotfound.crnk.domain.model.*;

import java.util.List;

public class Tester {
    public static void main(String[] args) {
        BlogClient client = new BlogClient();
        client.init();
        System.out.println("<<Addresses>>");
       // List<Address> addresses = client.findAllAddresses();
       // addresses.forEach(System.out::println);
        System.out.println("\n<<Books>>");
        List<Book> books = client.findAllBooks();
        books.forEach(System.out::println);

        System.out.println("\n<<BookCategories>>");
        List<BookCategory> bookCategories =  client.findAllBookCategories();
        bookCategories.forEach(System.out::println);

        System.out.println("\n<<Libraries>>");
        List<Library> libraries= client.findAllLibraries();
        libraries.forEach(System.out::println);

        System.out.println("\n<<Libraries address through relationships>>");
        libraries.forEach(l-> {
       //     System.out.println(client.findLibraryAddress(l.getId()));
        });

        System.out.println("\n<<BookPersonAddressLibrary>>");
        //List<BookPersonAddressLibrary> bookPersonAddressLibraries = client.findBookPersonAddressLibrary();
      //  bookPersonAddressLibraries.forEach(s-> System.out.println(s.toString()));
    }
}
