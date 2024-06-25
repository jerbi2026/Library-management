
package bibliotheque_gestion;

public class Livre {
    private int isbn;
    private String titre;
    private int id_auteur;
    private String description;
    private String genre;

    public int getIsbn() {
        return isbn;
    }

    public void setIsbn(int isbn) {
        this.isbn = isbn;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public int getId_auteur() {
        return id_auteur;
    }

    public void setId_auteur(int id_auteur) {
        this.id_auteur = id_auteur;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Livre() {
    }

    public Livre(int isbn, String titre, int id_auteur, String description, String genre) {
        this.isbn = isbn;
        this.titre = titre;
        this.id_auteur = id_auteur;
        this.description = description;
        this.genre = genre;
    }

    public Livre(String titre, int id_auteur, String description, String genre) {
        this.titre = titre;
        this.id_auteur = id_auteur;
        this.description = description;
        this.genre = genre;
    }
    
    
}
