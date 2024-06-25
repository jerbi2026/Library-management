/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package bibliotheque_gestion;

import java.util.ArrayList;


public interface Reservation_interface {
    
    ArrayList<Reservation> getReservations();
    void addReservation(Reservation reservation);
    int getNombreReservations();
    void updateReservation(Reservation reservation);
    Reservation getReservationById(int idReservation);
    int getNombreReservationsDateRetourInferieureAujourdhui();
    
}
