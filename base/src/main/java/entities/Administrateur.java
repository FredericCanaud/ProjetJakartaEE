package entities;

import jakarta.persistence.*;

@Entity
@Table(name="ADMINISTRATEUR")
public class Administrateur{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "pseudo")
    private String pseudo;

    @Column(name = "mail")
    private String mail;

    @Column(name = "mdp")
    private String mdp;

    public Administrateur(){}
}
