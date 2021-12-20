package entities;

public abstract class Personne{
    protected String pseudo;
    private String mail;
    private String mdp;

    protected Personne(String pseudo, String mail, String mdp){
        this.pseudo = pseudo;
        this.mail = mail;
        this.mdp = mdp;
    }
}
