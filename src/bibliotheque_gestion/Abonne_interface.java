/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package bibliotheque_gestion;

import java.util.ArrayList;

/**
 *
 * @author wadani
 */
public interface Abonne_interface {
    public ArrayList<Abonne> getAbonnees();
    void addAbonne(Abonne abonne);
    int getNombreAbonnes();
    void deleteAbonne(int idAbonne);
    Abonne getAbonneById(int idAbonne);
    
    
}
