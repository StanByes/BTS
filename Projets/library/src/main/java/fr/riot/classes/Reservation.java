package fr.riot.classes;

import java.sql.Date;

public class Reservation {
    private int id;
    private final Client client;
    private final Book book;
    private final Date bookedAt;

    public Reservation(Client client, Book book) {
    	this(-1, client, book, new Date(System.currentTimeMillis()));
    }
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
    
    public void setId(int id) {
    	this.id = id;
    }
}
