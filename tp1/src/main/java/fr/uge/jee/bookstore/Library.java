package fr.uge.jee.bookstore;

import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class Library {
    private Set<Book> books;

    public Library(Set<Book> books) {
        Objects.requireNonNull(books);
        this.books = Set.copyOf(books);
    };

    @Override
    public String toString(){
        return books.stream().map(Object::toString).collect(Collectors.joining(",","<",">"));
    }


}
