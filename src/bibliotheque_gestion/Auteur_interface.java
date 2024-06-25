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
public interface Auteur_interface {
    ArrayList<Auteur> getAuteurs();
    void addAuteur(Auteur auteur);
    int getNombreAuteurs();
    void updateAuteur(Auteur auteur);
    Auteur getAuteurById(int idAuteur);
    
}
