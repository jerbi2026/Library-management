/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bibliotheque_gestion;


public class Manager extends Personne{
    private int id_manager;
    private String password;

    public int getId_manager() {
        return id_manager;
    }

    public void setId_manager(int id_manager) {
        this.id_manager = id_manager;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    
    public Manager(String email, String username) {
        super(email, username);
    }
    
    public Manager(){
        
    }

    public Manager(int id_manager, String password, String email, String username) {
        super(email, username);
        this.id_manager = id_manager;
        this.password = password;
    }
    
    public Manager( String password, String email, String username) {
        super(email, username);
        this.password = password;
    }
    
    
    
    
    
}
