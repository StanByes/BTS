package fr.riot.utils;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

import fr.riot.Main;
import fr.riot.classes.Book;

public class ScreenUtils {
	public static JComboBox<String> createBooksList() {
		DefaultComboBoxModel<String> books = new DefaultComboBoxModel<String>();
		books.addElement("Selectionnez un livre");
		for (Book book : Main.getBooks())
			books.addElement(book.getName() + " - " + book.getAuthor());
		
		JComboBox<String> booksList = new JComboBox<>();
		booksList.setModel(books);
		return booksList;
	}
}
