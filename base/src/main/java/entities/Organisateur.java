package entities;

import jakarta.persistence.Entity;

@Entity
public class Organisateur extends Utilisateur {

    public Organisateur(String pseudo, String mail, String mdp, String avatar, String role){
        super(pseudo, mail, mdp, avatar, role);
    }

    public Organisateur(){

    }
}