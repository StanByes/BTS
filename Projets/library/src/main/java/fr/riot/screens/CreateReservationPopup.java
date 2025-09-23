package fr.riot.screens;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.management.RuntimeErrorException;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import fr.riot.Main;
import fr.riot.classes.Book;
import fr.riot.classes.Client;
import fr.riot.classes.Reservation;
import fr.riot.models.ReservationModel;
import fr.riot.utils.QRUtils;
import fr.riot.utils.ScreenUtils;
import fr.riot.utils.Utils;

public class CreateReservationPopup extends BasePopup {

	protected CreateReservationPopup() {
		super("Emprunter un livre");

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
						for (Client client : Main.getClients()) {
							boolean skip = false;
							for (Reservation reservation : Main.getReservations()) {
								if (reservation.getBook().equals(book) && reservation.getClient().equals(client)) {
									skip = true;
									break;
								}
							}

							if (skip)
								continue;

							JPanel reservationEntry = new JPanel();
							reservationEntry.setLayout(null);
							reservationEntry.setBounds(0, i * 30, scrollPane.getWidth(), 30);
							reservationEntry.setBackground(new Color(230, 230, 230));
							reservationEntry.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.black));

							JLabel clientName = new JLabel(client.getFirstName() + " " + client.getSurName());
							clientName.setBounds(10, reservationEntry.getHeight() / 2 - 10, 100, 20);
							reservationEntry.add(clientName);

							JButton add = new JButton("\u2705");
							add.setBounds(reservationEntry.getWidth() - 40, reservationEntry.getHeight() / 2 - 10, 30, 20);
							add.setForeground(Color.green);
							add.setBackground(null);
							add.setBorder(null);
							add.addActionListener(new ActionListener() {
								@Override
								public void actionPerformed(ActionEvent e) {
									Reservation reservation = new Reservation(client, book);
									try {
										reservation.setId(ReservationModel.createReservation(reservation));
									} catch (SQLException exception) {
										throw new RuntimeException(exception);
									}

									Main.getReservations().add(reservation);
                                    QRUtils.generateAndSaveReservation(reservation);

									if (Main.getCurrentMode().equals(Main.Mode.BOOKS))
										Screen.setModelList();

									reservationsList.remove(reservationEntry);
									reservationsList.revalidate();
									reservationsList.repaint();
								}
							});
							reservationEntry.add(add);
							reservationsList.add(reservationEntry);

							i++;
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

    @Override
    void onClose() {
        // IGNORED
    }
}
