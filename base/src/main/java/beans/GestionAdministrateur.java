package beans;

import beans.facades.AdministrateurFacade;
import beans.facades.UtilisateurFacade;
import entities.Organisateur;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;

@ApplicationScoped
@Named("beanAdministrateur")
public class GestionAdministrateur {

    @EJB
    private AdministrateurFacade administrateurFacade;

    public void bannirUtilisateur(String id, String mail) { this.administrateurFacade.bannirUtilisateur(id, mail); }

    public void supprimerCTF(String id) { this.administrateurFacade.supprimerCTF(id); }

    public void validerCTF(String id) {
        this.administrateurFacade.validerCTF(id);
    }
}
