package entities;

import jakarta.persistence.Entity;

@Entity
public class Participant extends Utilisateur {

    public Participant(String pseudo, String mail, String mdp, String avatar, String role){
        super(pseudo, mail, mdp, avatar, role);
    }

    public Participant(){

    }
}