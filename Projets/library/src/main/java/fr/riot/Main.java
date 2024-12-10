package fr.riot;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.riot.classes.Book;
import fr.riot.classes.Client;
import fr.riot.classes.Reservation;
import fr.riot.connectors.MysqlConnection;
import fr.riot.models.BookModel;
import fr.riot.models.ClientModel;
import fr.riot.models.ReservationModel;
import fr.riot.screens.Screen;

public class Main {
	public static enum Mode {
		CLIENTS("Clients"),
		BOOKS("Livres");
		
		private final String displayName;
		Mode(String displayName) {
			this.displayName = displayName;
		}
		
		public String getDisplayName() {
			return displayName;
		}
	}
	
    private static List<Book> books = new ArrayList<>();
    private static List<Client> clients = new ArrayList<>();
    private static List<Reservation> reservations = new ArrayList<>();

    private static MysqlConnection connection;
    private static Mode currentMode;

    public static List<Book> getBooks() {
        return books;
    }
    public static List<Client> getClients() {
        return clients;
    }
    public static List<Reservation> getReservations() {
        return reservations;
    }

    public static MysqlConnection getConnection() throws SQLException {
        return connection;
    }

    public static Mode getCurrentMode() {
    	return currentMode;
    }
    public static void setCurrentMode(Mode mode) {
    	currentMode = mode;
    }
    
    public static void main(String[] args) {
        connection = new MysqlConnection("mysql", "localhost", 3306, "library", "1234", "library");

        try {
            clients = ClientModel.getAllClients();
            books = BookModel.getAllBooks();
            reservations = ReservationModel.getAllReservations();
        } catch (SQLException exception) {
            System.err.println("yo");
            throw new RuntimeException(exception);
        }

        currentMode = Mode.BOOKS;
        new Screen();

    }
}