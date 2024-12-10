package fr.riot.classes;

import java.sql.Date;

public class Client extends ListItems {
    private int id;
    private String firstName;
    private String surName;
    private Date birthDay;
    private String email;
    private String phoneNumber;
    private Date registeredAt;

    public Client(String firstName, String surName, Date birthDay, String email, String phoneNumber) {
    	this(-1, firstName, surName, birthDay, email, phoneNumber, new Date(System.currentTimeMillis()));
    }
    public Client(int id, String firstName, String surName, Date birthDay, String email, String phoneNumber, Date registeredAt) {
        this.id = id;
        this.firstName = firstName;
        this.surName = surName;
        this.birthDay = birthDay;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.registeredAt = registeredAt;
    }

    public int getId() {
        return id;
    }
    public String getFirstName() {
        return firstName;
    }
    public String getSurName() {
        return surName;
    }
    public Date getBirthDay() {
        return birthDay;
    }
    public String getEmail() {
        return email;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public Date getRegisteredAt() {
        return registeredAt;
    }
    
    public void setId(int id) {
    	this.id = id;
    }
}
