package controllers.facades;

import entities.Participant;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;

@Stateless
public class ParticipantFacade extends AbstractFacade<Participant>{
    @PersistenceContext(unitName = "DB1")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public Participant getParticipant(String id) {
        return null;
    }

    public ParticipantFacade() {
        super(Participant.class);
    }

    public void editParticipant(String id) {}

    public void login(){

    }

    public void inscription(){

    }

    public void validerDefi(String id){

    }

    public void rejoindreEquipe(String id){

    }

    public void validerParticipantDansEquipe(String id){

    }

    public void posterCommentaire(){

    }

    public List<Participant> getClassementGeneral(){
        return null;
    }
}