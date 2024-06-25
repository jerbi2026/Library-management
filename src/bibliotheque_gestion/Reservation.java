package bibliotheque_gestion;

import java.time.LocalDate;

public class Reservation {
    private int id_reservation;
    private int id_abonne;
    private int isbn;
    private LocalDate date_reservation;
    private LocalDate date_retour;

    public int getId_reservation() {
        return id_reservation;
    }

    public void setId_reservation(int id_reservation) {
        this.id_reservation = id_reservation;
    }

    public int getId_abonne() {
        return id_abonne;
    }

    public void setId_abonne(int id_abonne) {
        this.id_abonne = id_abonne;
    }

    public int getIsbn() {
        return isbn;
    }

    public void setIsbn(int isbn) {
        this.isbn = isbn;
    }

    public LocalDate getDate_reservation() {
        return date_reservation;
    }

    public void setDate_reservation(LocalDate date_reservation) {
        this.date_reservation = date_reservation;
    }

    public LocalDate getDate_retour() {
        return date_retour;
    }

    public void setDate_retour(LocalDate date_retour) {
        this.date_retour = date_retour;
    }

    public Reservation(int id_reservation, int id_abonne, int isbn, LocalDate date_reservation, LocalDate date_retour) {
        this.id_reservation = id_reservation;
        this.id_abonne = id_abonne;
        this.isbn = isbn;
        this.date_reservation = date_reservation;
        this.date_retour = date_retour;
    }

    public Reservation(int id_abonne, int isbn, LocalDate date_reservation, LocalDate date_retour) {
        this.id_abonne = id_abonne;
        this.isbn = isbn;
        this.date_reservation = date_reservation;
        this.date_retour = date_retour;
    }
    
    public Reservation(){
        
    }
    
    
    
    
    
    
    
}
