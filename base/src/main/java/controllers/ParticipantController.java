package controllers;

import entities.Participant;
import controllers.facades.ParticipantFacade;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;

import java.util.List;

@ApplicationScoped
@Named("participantController")
public class ParticipantController {

    @EJB
    private ParticipantFacade participantFacade;

    public Participant getParticipant(String id) { return this.participantFacade.getParticipant(id); }

    public void editParticipant(String id) { this.participantFacade.editParticipant(id); }

    public void login() { this.participantFacade.login(); }

    public void inscription() {
        this.participantFacade.inscription();
    }

    public void validerDefi(String id) { this.participantFacade.validerDefi(id); }

    public void rejoindreEquipe(String id) { this.participantFacade.rejoindreEquipe(id); }

    public void validerParticipantDansEquipe(String id) { this.participantFacade.validerParticipantDansEquipe(id); }

    public void posterCommentaire() { this.participantFacade.posterCommentaire(); }

    public List<Participant> getClassementGeneral() { return this.participantFacade.getClassementGeneral(); }
}
