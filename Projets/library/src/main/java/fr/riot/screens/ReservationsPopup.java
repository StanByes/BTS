package fr.riot.screens;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;

import fr.riot.Main;
import fr.riot.classes.Book;
import fr.riot.classes.Client;
import fr.riot.classes.Reservation;
import fr.riot.models.ReservationModel;
import fr.riot.utils.ScreenUtils;

public class ReservationsPopup extends BasePopup {
	private static final long serialVersionUID = -9160285385093683823L;
	
	public ReservationsPopup() {
		super("Liste des emprunts");
		
		JPanel reservationsList = new JPanel();
		reservationsList.setLayout(null);
		reservationsList.setBackground(Color.gray);

		JScrollPane scrollPane = new JScrollPane(reservationsList);
		scrollPane.setBounds(25, getHeight() - 200, getWidth() - 50, getHeight() - 200);
		add(scrollPane);

		JComboBox<String> booksList = ScreenUtils.createBooksList();
		booksList.setBounds(getWidth() / 4, getHeight() / 4, getWidth() / 2, 30);
		booksList.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				int index = booksList.getSelectedIndex();
				reservationsList.removeAll();

				if (index == 0) {
					reservationsList.setBackground(Color.gray);
				} else {
					reservationsList.setBackground(new Color(200, 200, 200));
					
					Book book = Main.getBooks().get(index - 1);
					if (book != null) {
						int i = 0;
						for (Reservation reservation : Main.getReservations()) {
							if (reservation.getBook().equals(book)) {
								JPanel reservationEntry = new JPanel();
								reservationEntry.setLayout(null);
								reservationEntry.setBounds(0, i * 30, scrollPane.getWidth(), 30);
								reservationEntry.setBackground(new Color(230, 230, 230));
								reservationEntry.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.black));
								
								JLabel clientName = new JLabel(reservation.getClient().getFirstName() + " " + reservation.getClient().getSurName());
								clientName.setBounds(10, reservationEntry.getHeight() / 2 - 10, 100, 20);
								reservationEntry.add(clientName);
								
								JButton remove = new JButton("\u274c");
								remove.setBounds(reservationEntry.getWidth() - 40, reservationEntry.getHeight() / 2 - 10, 30, 20);
								remove.setForeground(Color.red);
								remove.setBackground(null);
								remove.setBorder(null);
								remove.addActionListener(new ActionListener() {
									@Override
									public void actionPerformed(ActionEvent e) {
										try {
											ReservationModel.deleteReservation(reservation);
										} catch (SQLException exception) {
											throw new RuntimeException(exception);
										}

										Main.getReservations().remove(reservation);
										if (Main.getCurrentMode().equals(Main.Mode.BOOKS))
											Screen.setModelList();
										
										reservationsList.remove(reservationEntry);
										reservationsList.revalidate();
										reservationsList.repaint();
									}
								});
								reservationEntry.add(remove);
								
								reservationsList.add(reservationEntry);
								i++;
							}
						}
						
						if (reservationsList.getComponentCount() > 0)
							((JPanel) reservationsList.getComponent(i - 1)).setBorder(BorderFactory.createEmptyBorder());
					}
				}

				reservationsList.revalidate();
				reservationsList.repaint();
			}
		});
		add(booksList);
	}
}
