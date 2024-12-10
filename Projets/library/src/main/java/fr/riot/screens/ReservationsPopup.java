package fr.riot.screens;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JPanel;

import fr.riot.Main;
import fr.riot.classes.Client;

public class ReservationsPopup extends BasePopup {
	private static final long serialVersionUID = -9160285385093683823L;
	
	public ReservationsPopup() {
		super("Liste des réservations");
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		
		setBounds(screen.width / 4, screen.height / 4, screen.width / 2, screen.height / 2);
		setLayout(null);
		setBackground(Color.white);
		setBorder(BorderFactory.createLineBorder(Color.black));
		
		DefaultComboBoxModel<String> clientsModel = new DefaultComboBoxModel<String>();
		for (Client client : Main.getClients())
			clientsModel.addElement(client.getFirstName() + " " + client.getSurName());

		JComboBox<String> clientsList = new JComboBox<>();
		clientsList.setBounds(getWidth() / 4, getHeight() / 4, getWidth() / 2, getHeight() / 2);
		clientsList.setModel(clientsModel);
		add(clientsList);
	}
}
