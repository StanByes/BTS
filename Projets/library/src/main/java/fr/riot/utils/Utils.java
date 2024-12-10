package fr.riot.utils;

import fr.riot.Main;
import fr.riot.classes.Book;
import fr.riot.classes.Reservation;

public class Utils {
	public static int getAvailableBooks(Book book) {
		int available = book.getTotal();
		for (Reservation reservation : Main.getReservations())
			if (reservation.getBook().getId() == book.getId())
				available--;
		
		return available;
	}
	
	public static String getUnicodeCharacter(String code) {
		Integer intCode = Integer.parseInt(code, 16);
		return new String(Character.toChars(intCode));
	}
}
