
package bibliotheque_gestion;

import java.util.ArrayList;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ReservationService implements Reservation_interface{
    
    public ArrayList<Reservation> getReservations() {
    ArrayList<Reservation> reservations = new ArrayList<>();
    
    try (Connection connection = Connexion_BD.getConnection()) {
        String query = "SELECT * FROM reservations";
        try (Statement statement = connection.createStatement()) {
            try (ResultSet resultSet = statement.executeQuery(query)) {
                while (resultSet.next()) {
                    Reservation reservation = new Reservation();
                    reservation.setId_reservation(resultSet.getInt("id_reservation"));
                    reservation.setId_abonne(resultSet.getInt("id_abonne"));
                    reservation.setIsbn(resultSet.getInt("isbn"));
                    reservation.setDate_reservation(resultSet.getDate("date_reservation").toLocalDate());
                    reservation.setDate_retour(resultSet.getDate("date_retour").toLocalDate());
                    reservations.add(reservation);
                }
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    
    return reservations;
}

public void addReservation(Reservation reservation) {
    try (Connection connection = Connexion_BD.getConnection()) {
        if(reservation.getDate_reservation().isAfter(reservation.getDate_retour())){
            System.out.println("erreur de date reservation");
        }
        else{
            String query = "INSERT INTO reservations (id_abonne, isbn, date_reservation, date_retour) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
           
            statement.setInt(1, reservation.getId_abonne());
            statement.setInt(2, reservation.getIsbn());
            statement.setDate(3, Date.valueOf(reservation.getDate_reservation()));
            statement.setDate(4, Date.valueOf(reservation.getDate_retour()));
            statement.executeUpdate();
        }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

public int getNombreReservations() {
    int nombreReservations = 0;
    
    try (Connection connection = Connexion_BD.getConnection()) {
        String query = "SELECT COUNT(*) AS total FROM reservations";
        try (Statement statement = connection.createStatement()) {
            try (ResultSet resultSet = statement.executeQuery(query)) {
                if (resultSet.next()) {
                    nombreReservations = resultSet.getInt("total");
                }
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    
    return nombreReservations;
}

public void deleteReservation(int idReservation) {
    try (Connection connection = Connexion_BD.getConnection()) {
        String query = "DELETE FROM reservations WHERE id_reservation = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
           
            statement.setInt(1, idReservation);
            statement.executeUpdate();
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

public void updateReservation(Reservation reservation) {
    try (Connection connection = Connexion_BD.getConnection()) {
         if(reservation.getDate_reservation().isAfter(reservation.getDate_retour())){
            System.out.println("erreur de date reservation");
        }
         else{
              String query = "UPDATE reservations SET id_abonne = ?, isbn = ?, date_reservation = ?, date_retour = ? WHERE id_reservation = ?";
                try (PreparedStatement statement = connection.prepareStatement(query)) {
             
            statement.setInt(1, reservation.getId_abonne());
            statement.setInt(2, reservation.getIsbn());
            statement.setDate(3, Date.valueOf(reservation.getDate_reservation()));
            statement.setDate(4, Date.valueOf(reservation.getDate_retour()));
            statement.setInt(5, reservation.getId_reservation());
            statement.executeUpdate();
        }
             
         }
       
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

public Reservation getReservationById(int idReservation) {
    Reservation reservation = null;
    
    try (Connection connection = Connexion_BD.getConnection()) {
        String query = "SELECT * FROM reservations WHERE id_reservation = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, idReservation);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    reservation = new Reservation();
                    reservation.setId_reservation(resultSet.getInt("id_reservation"));
                    reservation.setId_abonne(resultSet.getInt("id_abonne"));
                    reservation.setIsbn(resultSet.getInt("isbn"));
                    reservation.setDate_reservation(resultSet.getDate("date_reservation").toLocalDate());
                    reservation.setDate_retour(resultSet.getDate("date_retour").toLocalDate());
                }
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    
    return reservation;
}

public int getNombreReservationsDateRetourInferieureAujourdhui() {
    int nombreReservations = 0;
    LocalDate dateAujourdhui = LocalDate.now();
    
    try (Connection connection = Connexion_BD.getConnection()) {
        String query = "SELECT COUNT(*) AS total FROM reservations WHERE date_retour < ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setDate(1, Date.valueOf(dateAujourdhui));
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    nombreReservations = resultSet.getInt("total");
                }
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    
    return nombreReservations;
}


    
}
