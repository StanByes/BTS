package fr.riot.ap2.entities;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class Client {
    private final int id;
    private String firstName;
    private String lastName;
    private final Date birthday;
    private String email;
    private String phone;
    private final Timestamp registeredAt;
    private final List<Book> books = new ArrayList<>();

    public Client(int id, String firstName, String lastName, Date birthday, String email, String phone, Timestamp registeredAt) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.email = email;
        this.phone = phone;
        this.registeredAt = registeredAt;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public Date getBirthday() {
        return birthday;
    }
    public String getEmail() {
        return email;
    }
    public String getPhone() {
        return phone;
    }
    public Timestamp getRegisteredAt() {
        return registeredAt;
    }
    public List<Book> getBooks() {
        return books;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
}
