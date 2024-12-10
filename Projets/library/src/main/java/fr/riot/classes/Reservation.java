package fr.riot.classes;

import java.sql.Date;

public class Reservation {
    private final int id;
    private final Client client;
    private final Book book;
    private final Date bookedAt;

    public Reservation(int id, Client client, Book book, Date bookedAt) {
        this.id = id;
        this.client = client;
        this.book = book;
        this.bookedAt = bookedAt;
    }

    public int getId() {
        return id;
    }
    public Client getClient() {
        return client;
    }
    public Book getBook() {
        return book;
    }
    public Date getBookedAt() {
        return bookedAt;
    }
}
