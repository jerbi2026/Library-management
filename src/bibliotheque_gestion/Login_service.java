
package bibliotheque_gestion;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import org.mindrot.jbcrypt.BCrypt;

public class Login_service implements Login_Interface {
    
    public void ajouterManager(String nom, String email, String password) {
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());

        try (Connection connection = Connexion_BD.getConnection();
             PreparedStatement statement = connection.prepareStatement("INSERT INTO manager (username, email, password) VALUES (?, ?, ?)")) {

            statement.setString(1, nom);
            statement.setString(2, email);
            statement.setString(3, hashedPassword);

            // Exécution de la requête
            statement.executeUpdate();

            System.out.println("Nouveau manager ajouté avec succès !");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    
    


    @Override
   public int login(String username, String password) {
    int ok = 0;
    try (Connection connection = Connexion_BD.getConnection();
         
         PreparedStatement statement2 = connection.prepareStatement("SELECT password FROM manager WHERE email = ?");
         ){
        

        
            statement2.setString(1, username);
            ResultSet resultSet2 = statement2.executeQuery();
            if (resultSet2.next()) {
                String hashedPassword = resultSet2.getString("password");
                if (BCrypt.checkpw(password, hashedPassword)) {
                    ok = 2;
                   
                }
           
            }
        
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return ok;
}

    
}
