/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package bibliotheque_gestion;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public interface Livre_interface {
    ArrayList<Livre> getLivres();
    void addLivre(Livre livre);
    void deleteLivreByTitre(String titre);
    Livre getLivreByISBN(int isbn);
    int getNombreLivres();
    int getNombreGenres();
    List<String> getGenres();
    int getCountByGenre(String genre);
    Map<String, Integer> getNombreLivresParAuteur();
    
}
