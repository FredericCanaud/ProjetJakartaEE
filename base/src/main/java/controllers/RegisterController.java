package controllers;

import entities.managers.ParticipantManager;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;

import java.sql.SQLException;

@ApplicationScoped
@Named("registerController")
public class RegisterController {
    private String pseudo, type, mail, motDePasse, confirm, errorMsg;

    @EJB
    private ParticipantManager personneManager;

    public String getErrorMsg() { return errorMsg; }

    public void setErrorMsg(String errorMsg) { this.errorMsg = errorMsg; }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public void setConfirm(String confirm) {
        this.confirm = confirm;
    }

    public String inscription() throws SQLException {
        errorMsg="";
        boolean error=false;
        if (pseudo.equals("") || mail.equals("") || type.equals("") || motDePasse.equals("") || confirm.equals("")){
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
            personneManager.addPersonne(pseudo, mail, motDePasse, type);
            clearAll();
            return("login");
        }

        return null;
    }

    public void clearAll(){
        pseudo="";
        mail="";
        type="";
        confirm="";
        errorMsg="";
    }
}
