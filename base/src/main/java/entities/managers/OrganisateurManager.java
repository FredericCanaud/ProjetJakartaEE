package entities.managers;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class OrganisateurManager {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String pseudo;
    private String mail;
    private String mdp;

    public OrganisateurManager(){}

    public OrganisateurManager(long id, String pseudo, String mail, String mdp){
        this.id = id;
        this.pseudo = pseudo;
        this.mail = mail;
        this.mdp = mdp;
    }
}
