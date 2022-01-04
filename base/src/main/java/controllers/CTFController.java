package controllers;

import entities.CTF;
import controllers.facades.CTFFacade;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;

import java.util.List;

@ApplicationScoped
@Named("ctfController")
public class CTFController {

    @EJB
    private CTFFacade CTFFacade;

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
}
