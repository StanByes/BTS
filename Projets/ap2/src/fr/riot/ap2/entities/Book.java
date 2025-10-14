package fr.riot.ap2.entities;

import com.sun.istack.internal.Nullable;

import java.sql.Date;

public class Book {
    private final int id;
    private final String name;
    private final String ISBN;
    private final Author author;
    private @Nullable Client client;
    private final Date publishedAt;

    public Book(int id, String name, String ISBN, Author author, Client client, Date publishedAt) {
        this.id = id;
        this.name = name;
        this.ISBN = ISBN;
        this.author = author;
        this.client = client;
        this.publishedAt = publishedAt;
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
    public Date getPublishedAt() {
        return publishedAt;
    }

    public void setClient(@Nullable Client client) {
        this.client = client;
    }
}
