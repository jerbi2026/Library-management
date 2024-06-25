
package bibliotheque_gestion;

import java.util.ArrayList;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class AbonneService implements Abonne_interface{
    
    public ArrayList<Abonne> getAbonnees() {
    ArrayList<Abonne> abonnees = new ArrayList<>();
    
    try (Connection connection = Connexion_BD.getConnection()) {
        String query = "SELECT * FROM abonne";
        try (Statement statement = connection.createStatement()) {
            try (ResultSet resultSet = statement.executeQuery(query)) {
                while (resultSet.next()) {
                    Abonne abonne = new Abonne();
                    abonne.setId_abonne(resultSet.getInt("id_abonne"));
                    abonne.setEmail(resultSet.getString("email"));
                    abonne.setUsername(resultSet.getString("username"));
                    abonnees.add(abonne);
                }
            }
        }
    }   catch (SQLException e) {
        e.printStackTrace();
        }
    
        return abonnees;
    }
    
    
    public void addAbonne(Abonne abonne) {
        try (Connection connection = Connexion_BD.getConnection()) {
            String query = "INSERT INTO abonne (id_abonne, email, username) VALUES (?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                 
                statement.setInt(1, abonne.getId_abonne());
                statement.setString(2, abonne.getEmail());
                statement.setString(3, abonne.getUsername());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
   }
    
   public int getNombreAbonnes() {
    int nombreAbonnes = 0;
    
        try (Connection connection = Connexion_BD.getConnection()) {
            String query = "SELECT COUNT(*) AS total FROM abonne";
            try (Statement statement = connection.createStatement()) {
                try (ResultSet resultSet = statement.executeQuery(query)) {
                    if (resultSet.next()) {
                        nombreAbonnes = resultSet.getInt("total");
                    }
                }
            }
        } catch (SQLException e) {
        e.printStackTrace();
        }
    
        return nombreAbonnes;
    }
   
    
   public void deleteAbonne(int idAbonne) {
        try (Connection connection = Connexion_BD.getConnection()) {
            String query = "DELETE FROM abonne WHERE id_abonne = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                   
                statement.setInt(1, idAbonne);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
   
   
   public void updateAbonne(Abonne abonne) {
    try (Connection connection = Connexion_BD.getConnection()) {
        String query = "UPDATE abonne SET email = ?, username = ? WHERE id_abonne = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
             
            statement.setString(1, abonne.getEmail());
            statement.setString(2, abonne.getUsername());
            statement.setInt(3, abonne.getId_abonne());
            statement.executeUpdate();
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
}
   
   public Abonne getAbonneById(int idAbonne) {
    Abonne abonne = null;
    
    try (Connection connection = Connexion_BD.getConnection()) {
        String query = "SELECT * FROM abonne WHERE id_abonne = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, idAbonne);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    abonne = new Abonne();
                    abonne.setId_abonne(resultSet.getInt("id_abonne"));
                    abonne.setEmail(resultSet.getString("email"));
                    abonne.setUsername(resultSet.getString("username"));
                }
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    
    return abonne;
}

   




    
    

    
}
