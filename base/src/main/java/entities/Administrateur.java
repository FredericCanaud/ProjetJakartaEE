package entities;

import jakarta.persistence.Entity;

@Entity
public class Administrateur extends Utilisateur {

    public Administrateur(String pseudo, String mail, String mdp, String avatar, String role){
        super(pseudo, mail, mdp, avatar, role);
    }
    public Administrateur(){

    }
}
