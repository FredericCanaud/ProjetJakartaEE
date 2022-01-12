package beans;

import entities.Participant;
import beans.facades.ParticipantFacade;
import entities.Utilisateur;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;

import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Map;

@ApplicationScoped
@Named("beanParticipants")
public class GestionParticipant {
    private String avatar, mail, mdp, newMdp, newMdpConfirm, errorMsg;

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
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

    public String getNewMdp() {
        return newMdp;
    }

    public void setNewMdp(String newMdp) {
        this.newMdp = newMdp;
    }

    public String getNewMdpConfirm() {
        return newMdpConfirm;
    }

    public void setNewMdpConfirm(String newMdpConfirm) {
        this.newMdpConfirm = newMdpConfirm;
    }

    @EJB
    private ParticipantFacade participantFacade;

    public Participant getParticipant(String id) { return this.participantFacade.getParticipant(id); }

    public String editParticipant() throws NoSuchAlgorithmException {
        errorMsg="";
        Map<String,String> params =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        String uti_id = params.get("uti_id");
        boolean error=false;
        if (mail.equals("") || mdp.equals("")){
            errorMsg="Certains champs requis ne sont pas remplis !";
            return null;
        }
        if (!newMdp.equals(newMdpConfirm)){
            errorMsg+="Les mot de passes ne se correspondent pas !";
            error=true;
        }
        if(newMdp.equals(newMdpConfirm)&&newMdp.length()<6){
            errorMsg+="Le mot de passe doit contenir au moins 6 caractères !";
            error=true;
        }

        if (!error){
            String errorDB = participantFacade.editParticipant(Integer.parseInt(uti_id), mail, newMdp);
            if(errorDB != null){
                errorMsg+=errorDB;
                return null;
            }
            clearAll();
            return("profil.xhtml");
        }
        return null;
    }

    public void validerDefi(String id) { this.participantFacade.validerDefi(id); }

    public void rejoindreEquipe(String id) { this.participantFacade.rejoindreEquipe(id); }

    public void validerParticipantDansEquipe(String id) { this.participantFacade.validerParticipantDansEquipe(id); }


    public void clearAll(){
        mail="";
        errorMsg="";
        avatar="";
        mdp="";
        newMdp="";
        newMdpConfirm="";
    }
}
