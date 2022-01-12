package beans;

import beans.facades.UtilisateurFacade;
import entities.Utilisateur;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import jakarta.servlet.http.Part;

import java.security.NoSuchAlgorithmException;

@ApplicationScoped
@Named("beanConnexion")
public class GestionConnexion {
    private String pseudo, role, mail, motDePasse, confirm, errorMsg;
    private Part avatar;
    private Utilisateur utilisateur;
    private boolean estChef;

    @EJB
    private UtilisateurFacade utilisateurFacade;

    public String getErrorMsg() { return errorMsg; }

    public void setErrorMsg(String errorMsg) { this.errorMsg = errorMsg; }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getRole() {
        return role;
    }

    public Part getAvatar() {
        return avatar;
    }

    public void setAvatar(Part avatar) {
        this.avatar = avatar;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public String getConfirm() {
        return confirm;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public boolean estChef() {
        return estChef;
    }

    public void setConfirm(String confirm) {
        this.confirm = confirm;
    }

    public String inscription() throws NoSuchAlgorithmException {
        errorMsg="";
        boolean error=false;
        if (pseudo.equals("") || mail.equals("") || role.equals("") || motDePasse.equals("") || confirm.equals("")){
            errorMsg="Certains champs requis ne sont pas remplis !";
            return null;
        }
        else {
            if (!motDePasse.equals(confirm)){
                errorMsg+="Les mot de passes ne se correspondent pas !";
                error=true;
            }
            if(motDePasse.equals(confirm)&&motDePasse.length()<6){
                errorMsg+="Le mot de passe doit contenir au moins 6 caractères !";
                error=true;
            }
        }

        if (!error){
            String errorDB = utilisateurFacade.inscription(pseudo, avatar, mail, motDePasse, role);
            if(errorDB != null){
                errorMsg+=errorDB;
                return null;
            }
            clearAll();
            return("connexion.xhtml");
        }
        return null;
    }

    public String login() throws NoSuchAlgorithmException {
        errorMsg="";
        boolean error=false;
        if (mail.equals("") || motDePasse.equals("") || role.equals("")){
            errorMsg="Certains champs requis ne sont pas remplis !";
            return null;
        }
        else {
            if(motDePasse.equals(confirm)&&motDePasse.length()<6){
                errorMsg+="Le mot de passe doit contenir au moins 6 caractères !";
                error=true;
            }
        }

        if (!error){
            Utilisateur utilisateur = utilisateurFacade.login(mail, motDePasse, role);
            if(utilisateur != null){
                this.utilisateur = utilisateur;
                estChef = this.utilisateurFacade.estChef(this.utilisateur.getId());
                clearAll();
                return("index.xhtml");
            } else {
                errorMsg+="L'adresse mail ou le mot de passe est incorrect";
            }
        }
        return null;
    }

    public void clearAll(){
        pseudo="";
        mail="";
        role="";
        confirm="";
        errorMsg="";
    }

    public Object deconnexion() {
        this.utilisateur = null;
        return("index.xhtml");
    }
}
