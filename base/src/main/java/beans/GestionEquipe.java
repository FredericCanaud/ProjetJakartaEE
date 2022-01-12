package beans;

import entities.Equipe;
import beans.facades.EquipeFacade;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;

import java.util.List;

@ApplicationScoped
@Named("beanEquipes")
public class GestionEquipe {

    @EJB
    private EquipeFacade equipeFacade;

    public List<Equipe> getEquipes() {
        return this.equipeFacade.getEquipes();
    }

    public List<Equipe> getBestEquipes() { return this.equipeFacade.getBestEquipes(); }

    public List<Equipe> getBestEquipesFromCTF(String ctfId) { return this.equipeFacade.getBestEquipesFromCTF(); }

    public List<Equipe> getEquipesFromCTF(String ctfId) { return this.equipeFacade.getEquipesFromCTF(); }

    public Equipe getEquipe(String id) {
        return this.equipeFacade.getEquipe(id);
    }
}
