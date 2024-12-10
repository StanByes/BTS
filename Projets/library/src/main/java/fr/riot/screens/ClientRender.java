package fr.riot.screens;

import javax.swing.ListCellRenderer;

import fr.riot.classes.Client;

import javax.swing.JList;
import java.sql.Date;
import java.text.SimpleDateFormat;

import javax.swing.JLabel;
import java.awt.Component;

public class ClientRender extends JLabel implements ListCellRenderer<Client> {
	private static final long serialVersionUID = -1605743646128514354L;

	public ClientRender() {
	}
	private final SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

	@Override
	public Component getListCellRendererComponent(JList<? extends Client> list, Client value, int index,
			boolean isSelected, boolean cellHasFocus) {
		setText(value.getFirstName() + " " + value.getSurName() + " - Né(e) le " + formatDate(value.getBirthDay()));
		return this;
	}
	
	public String formatDate(Date date) {
		return formatter.format(date);
	}
}
