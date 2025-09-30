package fr.riot.ap2.entities;

import java.sql.Date;
import java.sql.Timestamp;

public class Client {
    private final int id;
    private final String firstName;
    private final String lastName;
    private final Date birthday;
    private final String email;
    private final String phone;
    private final Timestamp registeredAt;

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
}
