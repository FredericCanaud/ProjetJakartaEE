package controllers.facades;

import entities.Administrateur;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Stateless
public class AdministrateurFacade extends AbstractFacade<Administrateur> {
    @PersistenceContext(unitName = "DB1")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AdministrateurFacade() {
        super(Administrateur.class);
    }

    public void editAdministrateur(){

    }

    public void validerCTF(String id){

    }

    public void editerCTF(String id){

    }

    public void supprimerCTF(String id){

    }

    public void bannirUtilisateur(String id){

    }
}