package fr.riot.ap2.entities;

import com.sun.istack.internal.Nullable;

public class Book {
    private final int id;
    private final String name;
    private final String ISBN;
    private final Author author;
    private final @Nullable Client client;

    public Book(int id, String name, String ISBN, Author author, Client client) {
        this.id = id;
        this.name = name;
        this.ISBN = ISBN;
        this.author = author;
        this.client = client;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getISBN() {
        return ISBN;
    }

    public Author getAuthor() {
        return author;
    }

    public Client getClient() {
        return client;
    }
}
