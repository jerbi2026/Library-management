
package bibliotheque_gestion;


public class Abonne extends Personne{
    
    private int id_abonne;

    public Abonne(int id_abonne, String email, String username) {
        super(email, username);
        this.id_abonne = id_abonne;
    }

    public Abonne() {
    }

    public Abonne(String email, String username) {
        super(email, username);
    }

    public int getId_abonne() {
        return id_abonne;
    }

    public void setId_abonne(int id_abonne) {
        this.id_abonne = id_abonne;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
    
    
}
