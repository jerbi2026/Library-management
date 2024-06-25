
package bibliotheque_gestion;

import java.util.ArrayList;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class AuteurService implements Auteur_interface{
    
    
     public ArrayList<Auteur> getAuteurs() {
        ArrayList<Auteur> auteurs = new ArrayList<>();
        
        try (Connection connection = Connexion_BD.getConnection()) {
            String query = "SELECT * FROM auteur";
            try (Statement statement = connection.createStatement()) {
                try (ResultSet resultSet = statement.executeQuery(query)) {
                    while (resultSet.next()) {
                        Auteur auteur = new Auteur();
                        auteur.setId_auteur(resultSet.getInt("id_auteur"));
                        auteur.setAuteur_name(resultSet.getString("auteur_name"));
                        auteurs.add(auteur);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return auteurs;
    }
     
     
    public void addAuteur(Auteur auteur) {
        try (Connection connection = Connexion_BD.getConnection()) {
            String query = "INSERT INTO auteur (id_auteur, auteur_name) VALUES (?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                 
                statement.setInt(1, auteur.getId_auteur());
                statement.setString(2, auteur.getAuteur_name());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public int getNombreAuteurs() {
        int nombreAuteurs = 0;
        
        try (Connection connection = Connexion_BD.getConnection()) {
            String query = "SELECT COUNT(*) AS total FROM auteur";
            try (Statement statement = connection.createStatement()) {
                try (ResultSet resultSet = statement.executeQuery(query)) {
                    if (resultSet.next()) {
                        nombreAuteurs = resultSet.getInt("total");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return nombreAuteurs;
    }
    
     public void deleteAuteur(int idAuteur) {
        try (Connection connection = Connexion_BD.getConnection()) {
            String query = "DELETE FROM auteur WHERE id_auteur = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                
                statement.setInt(1, idAuteur);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
     
      public void updateAuteur(Auteur auteur) {
        try (Connection connection = Connexion_BD.getConnection()) {
            String query = "UPDATE auteur SET auteur_name = ? WHERE id_auteur = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
               
                statement.setString(1, auteur.getAuteur_name());
                statement.setInt(2, auteur.getId_auteur());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
      public Auteur getAuteurById(int idAuteur) {
        Auteur auteur = null;
        
        try (Connection connection = Connexion_BD.getConnection()) {
            String query = "SELECT * FROM auteur WHERE id_auteur = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, idAuteur);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        auteur = new Auteur();
                        auteur.setId_auteur(resultSet.getInt("id_auteur"));
                        auteur.setAuteur_name(resultSet.getString("auteur_name"));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return auteur;
    }
   
}
