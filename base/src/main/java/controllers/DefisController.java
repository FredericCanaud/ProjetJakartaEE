package controllers;

import entities.Defi;
import controllers.facades.DefiFacade;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;

import java.security.NoSuchAlgorithmException;
import java.util.List;

@ApplicationScoped
@Named("defisController")
public class DefisController {

    @EJB
    private DefiFacade defiFacade;

    public List<Defi> getDefisFromType(String categorie) throws NoSuchAlgorithmException {
        return this.defiFacade.getDefis(categorie);
    }

    public Defi getDefi(String id) {
        return this.defiFacade.getDefi(id);
    }

}
