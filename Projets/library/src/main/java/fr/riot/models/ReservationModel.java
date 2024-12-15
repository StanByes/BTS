package fr.riot.models;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.riot.Main;
import fr.riot.classes.Book;
import fr.riot.classes.Client;
import fr.riot.classes.Reservation;

public class ReservationModel {
    private static final String GET_ALL_RESERVATIONS = "SELECT `id`, `client_id`, `book_id`, `booked_at` FROM `reservations`";
    private static final String CREATE_RESERVATION = "INSERT INTO `reservations`(`client_id`, `book_id`, `booked_at`) VALUES (?, ?, NOW())";
    private static final String DELETE_RESERVATION = "DELETE FROM `reservations` WHERE `id` = ?";

    public static List<Reservation> getAllReservations() throws SQLException {
        List<Reservation> reservations = new ArrayList<>();

        try (PreparedStatement preparedStatement = Main.getConnection().getConnection().prepareStatement(GET_ALL_RESERVATIONS)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Client reservationClient = null;
                    for (Client client : Main.getClients()) {
                        if (client.getId() == resultSet.getInt(2)) {
                            reservationClient = client;
                            break;
                        }
                    }

                    Book reservationBook = null;
                    for (Book book : Main.getBooks()) {
                        if (book.getId() == resultSet.getInt(3)) {
                            reservationBook = book;
                            break;
                        }
                    }

                    reservations.add(new Reservation(resultSet.getInt(1), reservationClient, reservationBook, resultSet.getDate(4)));
                }
            }
        } finally {
            Main.getConnection().releaseConnection();
        }

        return reservations;
    }
    
    public static int createReservation(Reservation reservation) throws SQLException {
    	try (PreparedStatement preparedStatement = Main.getConnection().getConnection().prepareStatement(CREATE_RESERVATION, Statement.RETURN_GENERATED_KEYS)) {
    		preparedStatement.setInt(1, reservation.getClient().getId());
    		preparedStatement.setInt(2, reservation.getBook().getId());
    		preparedStatement.executeUpdate();
    		
    		try (ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
    			if (resultSet.next())
    				return resultSet.getInt(1);

    			return -1;
    		}
    	} finally {
    		Main.getConnection().releaseConnection();
    	}
    }
    
    public static void deleteReservation(Reservation reservation) throws SQLException {
    	try (PreparedStatement preparedStatement = Main.getConnection().getConnection().prepareStatement(DELETE_RESERVATION)) {
    		preparedStatement.setInt(1, reservation.getId());
    		preparedStatement.executeUpdate();
    	} finally {
    		Main.getConnection().releaseConnection();
    	}
    }
}
