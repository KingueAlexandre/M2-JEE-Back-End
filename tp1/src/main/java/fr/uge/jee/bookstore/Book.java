package fr.uge.jee.bookstore;

import java.util.Objects;

public class Book {
    private String title;
    private long ref;

    public Book(String title,long ref){
        Objects.requireNonNull(title);
        Objects.requireNonNull(ref);
        this.title = title;
        this.ref = ref;
    };

    @Override
    public String toString() {
        return "["+ title + "," + ref + "]";
    }
}

