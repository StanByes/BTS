package fr.riot.screens;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import fr.riot.Main;
import fr.riot.classes.Book;
import fr.riot.classes.Client;
import fr.riot.models.BookModel;
import fr.riot.models.ClientModel;

public class CreationPopup extends BasePopup {
	private static final long serialVersionUID = -8738689057391619073L;

	public CreationPopup() {
		super("Ajouter un " + Main.getCurrentMode().getDisplayName().toLowerCase().substring(0, Main.getCurrentMode().getDisplayName().length() - 1));
		
		JLabel info = new JLabel("Veuillez remplir tous les champs !");
		info.setBackground(Color.red);
		info.setForeground(Color.white);
		info.setBounds(0, 50, getWidth(), 20);
		info.setFont(new Font("Tahoma", Font.ITALIC, 18));
		info.setHorizontalAlignment(SwingConstants.CENTER);
		
		JButton create = new JButton("Créer");
		create.setBackground(new Color(0, 100, 255));
		create.setBounds(getWidth() / 4, getHeight() - 75, getWidth() / 2, 50);
		add(create);

		Font labelFont = new Font("Tahoma", Font.PLAIN, 18);
		Font fieldFont = new Font("Arial", Font.ITALIC, 15);

		if (Main.getCurrentMode().equals(Main.Mode.BOOKS)) {
			JLabel nameLabel = new JLabel("Titre :");
			nameLabel.setFont(labelFont);
			nameLabel.setBounds(15, 75, 250, 20);
			add(nameLabel);

			JTextField nameField = new JTextField();
			nameField.setFont(fieldFont);
			nameField.setBounds(15, 95, 300, 25);
			add(nameField);
			
			JLabel authorLabel = new JLabel("Auteur(e) :");
			authorLabel.setFont(labelFont);
			authorLabel.setBounds(15, 135, 250, 20);
			add(authorLabel);

			JTextField authorField = new JTextField();
			authorField.setFont(fieldFont);
			authorField.setBounds(15, 155, 300, 25);
			add(authorField);
			add(nameField);
			
			JLabel totalLabel = new JLabel("Nombre de livres :");
			totalLabel.setFont(labelFont);
			totalLabel.setBounds(15, 195, 250, 20);
			add(totalLabel);

			JFormattedTextField totalField = new JFormattedTextField(NumberFormat.getIntegerInstance());
			totalField.setFont(fieldFont);
			totalField.setBounds(15, 215, 300, 25);
			add(totalField);
			
			JLabel publishedLabel = new JLabel("Date de publication (optionnelle | Au format 01/01/2024) :");
			publishedLabel.setFont(labelFont);
			publishedLabel.setBounds(15, 255, 500, 20);
			add(publishedLabel);

			JFormattedTextField publishedField = new JFormattedTextField(new SimpleDateFormat("dd/MM/yyyy"));
			publishedField.setFont(fieldFont);
			publishedField.setBounds(15, 275, 300, 25);
			add(publishedField);

			create.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String name = nameField.getText();
					String author = authorField.getText();
					
					if (name.isEmpty() || author.isEmpty() || totalField.getText().isEmpty() || publishedField.getText().isEmpty()) {
						add(info);
						revalidate();
						repaint();
					} else {
						long total = (long) totalField.getValue();
						Date publishedDate = (Date) publishedField.getValue();
						
						Book book = new Book(name, author, Math.toIntExact(total), new java.sql.Date(publishedDate.getTime()));
						try {
							book.setId(BookModel.createBook(book));
						} catch (SQLException exception) {
							info.setText("Une erreur est survenue !");
							add(info);
							revalidate();
							repaint();
							return;
						}
						
						Main.getBooks().add(book);
						Screen.setModelList();
						
						Screen.closePopup();
					}
				}
			});
		} else {
			JLabel surNameLabel = new JLabel("Nom :");
			surNameLabel.setFont(labelFont);
			surNameLabel.setBounds(15, 75, 250, 20);
			add(surNameLabel);

			JTextField surNameField = new JTextField();
			surNameField.setFont(fieldFont);
			surNameField.setBounds(15, 95, 300, 25);
			add(surNameField);

			JLabel firstNameLabel = new JLabel("Prénom :");
			firstNameLabel.setFont(labelFont);
			firstNameLabel.setBounds(15, 135, 250, 20);
			add(firstNameLabel);

			JTextField firstNameField = new JTextField();
			firstNameField.setFont(fieldFont);
			firstNameField.setBounds(15, 155, 300, 25);
			add(firstNameField);
			
			JLabel birthdayLabel = new JLabel("Date de naissance (Au format 01/01/2024) :");
			birthdayLabel.setFont(labelFont);
			birthdayLabel.setBounds(15, 195, 250, 20);
			add(birthdayLabel);
			
			JFormattedTextField birthdayField = new JFormattedTextField(new SimpleDateFormat("dd/MM/yyyy"));
			birthdayField.setFont(fieldFont);
			birthdayField.setBounds(15, 215, 300, 25);
			add(birthdayField);

			JLabel emailLabel = new JLabel("Email (optionel) :");
			emailLabel.setFont(labelFont);
			emailLabel.setBounds(15, 255, 300, 20);
			add(emailLabel);

			JTextField emailField = new JTextField();
			emailField.setFont(fieldFont);
			emailField.setBounds(15, 275, 300, 25);
			add(emailField);

			JLabel phoneLabel = new JLabel("Téléphone (optionel) :");
			phoneLabel.setFont(labelFont);
			phoneLabel.setBounds(330, 75, 300, 20);
			add(phoneLabel);

			JTextField phoneField = new JTextField();
			phoneField.setFont(fieldFont);
			phoneField.setBounds(330, 95, 300, 25);
			add(phoneField);
			
			create.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String surname = surNameField.getText();
					String firstname = firstNameField.getText();
					String email = emailField.getText();
					String phone = phoneField.getText();
					
					if (surname.isEmpty() || firstname.isEmpty() || birthdayField.getText().isEmpty() || email.isEmpty() || phone.isEmpty()) {
						add(info);
						revalidate();
						repaint();
					} else {
						Date birthday = (Date) birthdayField.getValue();
						
						Client client = new Client(firstname, surname, new java.sql.Date(birthday.getTime()), email, phone);
						try {
							client.setId(ClientModel.createClient(client));
						} catch (SQLException exception) {
							info.setText("Une erreur est survenue !");
							add(info);
							revalidate();
							repaint();
							return;
						}
						
						Main.getClients().add(client);
						Screen.setModelList();
						
						Screen.closePopup();
					}
				}
			});
		}
	}
}
