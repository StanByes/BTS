package fr.riot.classes;

import java.sql.Date;

public class Book extends ListItems {
    private int id;
    private String name;
    private String author;
    private int total;
    private Date publishedAt;

    public Book(String name, String author, int total, Date publishedAt) {
    	this(-1, name, author, total, publishedAt);
    }
    public Book(int id, String name, String author, int total, Date publishedAt) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.total = total;
        this.publishedAt = publishedAt;
    }

    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getAuthor() {
        return author;
    }
    public int getTotal() {
        return total;
    }
    public Date getPublishedAt() {
        return publishedAt;
    }
    
    public void setId(int id) {
    	this.id = id;
    }
}
