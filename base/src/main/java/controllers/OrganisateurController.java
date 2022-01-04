package controllers;

import entities.Organisateur;
import controllers.facades.OrganisateurFacade;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;

@ApplicationScoped
@Named("organisateurController")
public class OrganisateurController {

    @EJB
    private OrganisateurFacade organisateurFacade;

    public Organisateur getOrganisateur(String id) { return this.organisateurFacade.getOrganisateur(id); }

    public void editOrganisateur(String id) { this.organisateurFacade.editOrganisateur(id); }

    public void validerDefi(String id) { this.organisateurFacade.validerDefi(id); }

    public void rejoindreEquipe(String id) { this.organisateurFacade.rejoindreEquipe(id); }

    public void validerParticipantDansEquipe(String id) { this.organisateurFacade.validerParticipantDansEquipe(id); }

    public void posterCommentaire() { this.organisateurFacade.posterCommentaire(); }
}
