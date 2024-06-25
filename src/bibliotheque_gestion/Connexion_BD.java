
package bibliotheque_gestion;
import java.sql.*;
public class Connexion_BD {
    public static final String url = "jdbc:mysql://localhost:3306/biblio_project";
    public static final String login = "root";
    public static final String mdp = "";
    

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, login, mdp);
    }

    

    
}
