
package bibliotheque_gestion;

public class Auteur {
    private int id_auteur;
    private String auteur_name;

    public int getId_auteur() {
        return id_auteur;
    }

    public void setId_auteur(int id_auteur) {
        this.id_auteur = id_auteur;
    }

    public String getAuteur_name() {
        return auteur_name;
    }

    public void setAuteur_name(String auteur_name) {
        this.auteur_name = auteur_name;
    }

    public Auteur(int id_auteur, String auteur_name) {
        this.id_auteur = id_auteur;
        this.auteur_name = auteur_name;
    }
    
     public Auteur( String auteur_name) {
        this.auteur_name = auteur_name;
    }
     
     public Auteur(){
         
     }
    
     
    
}
