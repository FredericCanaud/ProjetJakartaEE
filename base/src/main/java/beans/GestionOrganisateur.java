package beans;

import entities.Organisateur;
import beans.facades.OrganisateurFacade;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;

@ApplicationScoped
@Named("beanOrganisateurs")
public class GestionOrganisateur {

    private String nom, image, dateDebut, dateFin, heureDebut, heureFin, format, URL;

    @EJB
    private OrganisateurFacade organisateurFacade;

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(String dateDebut) {
        this.dateDebut = dateDebut;
    }

    public String getDateFin() {
        return dateFin;
    }

    public void setDateFin(String dateFin) {
        this.dateFin = dateFin;
    }

    public String getHeureDebut() {
        return heureDebut;
    }

    public void setHeureDebut(String heureDebut) {
        this.heureDebut = heureDebut;
    }

    public String getHeureFin() {
        return heureFin;
    }

    public void setHeureFin(String heureFin) {
        this.heureFin = heureFin;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public void creerCTF(int orga_id){
        this.organisateurFacade.creerCTF(nom, image, dateDebut, heureDebut, dateFin, heureFin, orga_id, format, URL);
    }

    public void supprimerCTF(int ctfId) {
        this.organisateurFacade.supprimerCTF(ctfId);
    }
}
