package fr.riot.screens;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.SimpleDateFormat;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;

import fr.riot.classes.Book;
import fr.riot.utils.Utils;

public class BookRender implements ListCellRenderer<Book> {
	private final SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

	@Override
	public Component getListCellRendererComponent(JList<? extends Book> list, Book value, int index,
			boolean isSelected, boolean cellHasFocus) {
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		int listW = (int) screen.getWidth() - 20;

        JPanel panel = new JPanel();
        panel.setLayout(null);

        JLabel info = new JLabel(value.getName() + " - " + value.getAuthor());
        info.setBounds(5, 0, listW - 100, 20);
        panel.add(info);
        
        JLabel publishedDate = new JLabel("Publié le " + formatDate(value.getPublishedAt()));
        publishedDate.setBounds(5, 20, listW - 100, 20);
        panel.add(publishedDate);

        JLabel total = new JLabel("Total : " + value.getTotal());
        total.setBounds(listW - 100, 10, 100, 20);
        panel.add(total);
        
        int availableBooks = Utils.getAvailableBooks(value);
        JLabel available = new JLabel("Disponible : " + (availableBooks == 0 ? "Aucun" : availableBooks));
        available.setBounds(listW - 100, 30, 100, 20);
        panel.add(available);
        
        JButton reservations = new JButton("Liste des réservations");
        reservations.setBounds(listW / 3, 5, listW / 3, 25);
        reservations.setBackground(availableBooks == 0 ? new Color(255, 175, 0) : new Color(175, 255, 0));
        reservations.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Screen.openPopup(new ReservationsPopup());
			}
		});
        panel.add(reservations);
        
        JButton reservation = new JButton("Réserver");
        reservation.setBounds(listW / 3, 45, listW / 3, 25);
        reservation.setBackground(availableBooks == 0 ? new Color(200, 200, 200) : new Color(0, 255, 150));
        reservation.setEnabled(availableBooks != 0);
        panel.add(reservation);

    	panel.setBackground(availableBooks == 0 ? Color.red : Color.green);
        panel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, java.awt.Color.GRAY));
        
        return panel;
	}
	
	public String formatDate(Date date) {
		return formatter.format(date);
	}

}
