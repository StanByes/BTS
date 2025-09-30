package fr.riot.ap2.entities;

import java.sql.Date;

public class Author {
    private final int id;
    private final String firstName;
    private final String lastName;
    private final String description;
    private final Date birthday;

    public Author(int id, String firstName, String lastName, String description, Date birthday) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.description = description;
        this.birthday = birthday;
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

    public String getDescription() {
        return description;
    }

    public Date getBirthday() {
        return birthday;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }
}
