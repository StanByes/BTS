package fr.riot.ap.model;

import org.apache.commons.lang3.RandomStringUtils;
import org.mindrot.jbcrypt.BCrypt;

public class Agent {
    private final int id;
    private final String name;
    private final String firstname;
    private final String email;
    private final String login;
    private String password;
    private final String phone;

    public Agent(int id, String login, String password) {
        this(id, null, null, null, login, password, null);
    }
    public Agent(int id, String name, String firstname, String email, String login, String password, String phone) {
        this.id = id;
        this.name = name;
        this.firstname = firstname;
        this.email = email;
        this.login = login;
        this.password = password;
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public boolean checkPassword(String typedPassword) {
        return BCrypt.checkpw(typedPassword, password);
    }

    public void setPassword(String password) {
        String salt = BCrypt.gensalt();
        this.password = BCrypt.hashpw(password, salt);
    }

    public static String generatePassword() {
        return RandomStringUtils.secure().nextAlphabetic(12);
    }
}
