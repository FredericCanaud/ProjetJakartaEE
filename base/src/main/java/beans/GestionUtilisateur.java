package beans;

import beans.facades.ParticipantFacade;
import beans.facades.UtilisateurFacade;
import entities.Participant;
import entities.Utilisateur;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;

import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Map;

@ApplicationScoped
@Named("beanUtilisateurs")
public class GestionUtilisateur {

    @EJB
    private UtilisateurFacade utilisateurFacade;

    public Utilisateur getUtilisateur(String id) {
        Utilisateur u = this.utilisateurFacade.getUtilisateur(id);
        return u;
    }

    public void posterCommentaire(int ctf_id, String uti_id, String message){
        this.utilisateurFacade.posterCommentaire(ctf_id, uti_id, message);
    }

    public List<Utilisateur> getClassementGeneral() { return this.utilisateurFacade.getClassementGeneral(); }

    public List<Utilisateur> getMembresEquipe(String equiId) { return this.utilisateurFacade.getMembresEquipe(equiId); }

    public List<Utilisateur>  getGetMembresNonAdmin() {
        return this.utilisateurFacade.getMembresNonAdmin();
    }

    public String getPseudoFromMembre(String utiId) { return this.utilisateurFacade.getPseudoFromMembre(utiId); }
}
