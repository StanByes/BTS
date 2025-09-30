package fr.riot.ap2.views;

import fr.riot.ap2.controllers.BookController;

import javax.swing.*;
import java.awt.*;

public class HomeView extends JPanel {
    public HomeView() {
        JButton allBooks = new JButton("CATALOGUE");
        allBooks.setFont(new Font("Arial", Font.PLAIN, 15));
        allBooks.addActionListener(e -> BookController.index());
        allBooks.setBounds(450, 145, 450, 65);
        add(allBooks);

        JButton account = new JButton("MON COMPTE");
        account.setFont(new Font("Arial", Font.PLAIN, 15));
        account.setBounds(450, 255, 450, 65);
        add(account);

        JButton borrow = new JButton("EMPRUNTER");
        borrow.setFont(new Font("Arial", Font.PLAIN, 15));
        borrow.setBounds(185, 365, 450, 65);
        add(borrow);

        JButton giveBack = new JButton("RENDRE");
        giveBack.setFont(new Font("Arial", Font.PLAIN, 15));
        giveBack.setBounds(715, 365, 450, 65);
        add(giveBack);
    }
}
