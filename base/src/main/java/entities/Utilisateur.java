package entities;

import jakarta.persistence.*;

@Entity
@Table(name = "utilisateur")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
public class Utilisateur {

    @Id
    @Column(name = "uti_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "pseudo")
    protected String pseudo;

    @Column(name = "mail")
    private String mail;

    @Column(name = "mdp")
    private String mdp;

    @Column(name = "avatar")
    private String avatar;

    @Column(name = "role")
    private String role;

    public Utilisateur(String pseudo, String mail, String mdp, String avatar, String role){
        this.pseudo = pseudo;
        this.mail = mail;
        this.mdp = mdp;
        this.avatar = avatar;
        this.role = role;
    }

    public Utilisateur() { }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
