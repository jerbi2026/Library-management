/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bibliotheque_gestion;


abstract public  class Personne {
    protected String email;
    protected String username;

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

    public Personne(String email, String username) {
        this.email = email;
        this.username = username;
    }
    
    public Personne(){
        
    }
    
    
    
    
}
