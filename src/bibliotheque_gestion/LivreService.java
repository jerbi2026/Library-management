/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bibliotheque_gestion;

import java.sql.Connection;
import java.util.ArrayList;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class LivreService implements Livre_interface{
    
    
    public ArrayList<Livre> getLivres() {
        ArrayList<Livre> livres = new ArrayList<>();
        
        try (Connection connection = Connexion_BD.getConnection()) {
            String query = "SELECT * FROM livre";
            try (Statement statement = connection.createStatement()) {
                try (ResultSet resultSet = statement.executeQuery(query)) {
                    while (resultSet.next()) {
                        Livre livre = new Livre();
                        livre.setIsbn(resultSet.getInt("isbn"));
                        livre.setTitre(resultSet.getString("titre"));
                        livre.setId_auteur(resultSet.getInt("id_auteur"));
                        livre.setDescription(resultSet.getString("description"));
                        livre.setGenre(resultSet.getString("genre"));
                        livres.add(livre);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return livres;
    }
    
    
    public void addLivre(Livre livre) {
        try (Connection connection = Connexion_BD.getConnection()) {
            String query = "INSERT INTO livre (titre, id_auteur, description, genre) VALUES ( ?, ?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
              
                statement.setString(1, livre.getTitre());
                statement.setInt(2, livre.getId_auteur());
                statement.setString(3, livre.getDescription());
                statement.setString(4, livre.getGenre());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void deleteLivreByTitre(String titre) {
        try (Connection connection = Connexion_BD.getConnection()) {
            String query = "DELETE FROM livre WHERE titre = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                
                statement.setString(1, titre);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void updateLivre(Livre livre) {
        try (Connection connection = Connexion_BD.getConnection()) {
            String query = "UPDATE livre SET titre = ?, id_auteur = ?, description = ?, genre = ? WHERE isbn = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                
                statement.setString(1, livre.getTitre());
                statement.setInt(2, livre.getId_auteur());
                statement.setString(3, livre.getDescription());
                statement.setString(4, livre.getGenre());
                statement.setInt(5, livre.getIsbn()); 
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public Livre getLivreByISBN(int isbn) {
        Livre livre = null;
        
        try (Connection connection = Connexion_BD.getConnection()) {
            String query = "SELECT * FROM livre WHERE isbn = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, isbn);
                
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        livre = new Livre();
                        livre.setIsbn(resultSet.getInt("isbn"));
                        livre.setTitre(resultSet.getString("titre"));
                        livre.setId_auteur(resultSet.getInt("id_auteur"));
                        livre.setDescription(resultSet.getString("description"));
                        livre.setGenre(resultSet.getString("genre"));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return livre;
    }
    public int getNombreLivres() {
        int nombreLivres = 0;
        String query = "SELECT COUNT(*) AS total FROM livre";
        
        try (Connection connection = Connexion_BD.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            
            if (resultSet.next()) {
                nombreLivres = resultSet.getInt("total");
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return nombreLivres;
    }
    
    public int getNombreGenres() {
        int nombreGenres = 0;
        Set<String> genres = new HashSet<>();
        String query = "SELECT DISTINCT genre FROM livre"; 
        
        try (Connection connection = Connexion_BD.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            
            while (resultSet.next()) {
                String genre = resultSet.getString("genre");
                genres.add(genre);
            }
            
            nombreGenres = genres.size();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return nombreGenres;
    }
    
    
     public List<String> getGenres() {
        List<String> genres = new ArrayList<>();

        try (Connection connection = Connexion_BD.getConnection()) {
            String query = "SELECT DISTINCT genre FROM livre"; // Sélectionnez les genres distincts des livres
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        String genre = resultSet.getString("genre");
                        genres.add(genre);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return genres;
    }
     
     public Map<String, Integer> getNombreLivresParAuteur() {
        Map<String, Integer> nombreLivresParAuteur = new HashMap<>();

        try (Connection connection = Connexion_BD.getConnection()) {
            String query = "SELECT a.auteur_name, COUNT(l.isbn) as nombre_livres " +
                           "FROM livre l " +
                           "JOIN auteur a ON l.id_auteur = a.id_auteur " +
                           "GROUP BY a.auteur_name";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        String auteurName = resultSet.getString("auteur_name");
                        int nombreLivres = resultSet.getInt("nombre_livres");
                        nombreLivresParAuteur.put(auteurName, nombreLivres);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return nombreLivresParAuteur;
    }
     
     public int getCountByGenre(String genre) {
        int count = 0;

        try (Connection connection = Connexion_BD.getConnection()) {
            String query = "SELECT COUNT(*) AS total FROM livre WHERE genre = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, genre);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        count = resultSet.getInt("total");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return count;
    }

    
    
    
    
    
}
