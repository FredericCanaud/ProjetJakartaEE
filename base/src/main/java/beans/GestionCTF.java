package beans;

import entities.CTF;
import beans.facades.CTFFacade;
import entities.Commentaire;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;

import java.util.List;

@ApplicationScoped
@Named("beanCTF")
public class GestionCTF {

    private String message;
    
    @EJB
    private CTFFacade CTFFacade;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<CTF> getDerniersCTF() {
        return this.CTFFacade.getDerniersCTF();
    }

    public List<CTF> getProchainsCTF() {
        return this.CTFFacade.getProchainsCTF();
    }

    public List<CTF> getCTFFromEquipe(String id) { return this.CTFFacade.getCTFFromEquipe(id); }

    public CTF getCTF(String id) {
        return this.CTFFacade.getCTF(id);
    }

    public List<Commentaire> getCommentairesFromCTF(String id){
        return this.CTFFacade.getCommentairesFromCTF(id);
    }

    public List<CTF> getCTFValides() {
        return this.CTFFacade.getCTFValides();
    }

    public List<CTF> getCTFNonValides() {
        return this.CTFFacade.getCTFNonValides();
    }

    public List<CTF> getCTFFromOrganisateur(String id) {
        return this.CTFFacade.getCTFFromOrganisateur(id);
    }
}
