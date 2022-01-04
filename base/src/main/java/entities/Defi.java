package entities;


import jakarta.persistence.*;

@Entity
@Table(name = "defi")
@NamedQueries({
        @NamedQuery(name = "Defi.getDefis", query = "SELECT d FROM Defi d"),
        @NamedQuery(name = "Defi.getDefisByCategorie", query = "SELECT d.defiId FROM Defi d WHERE d.categorie = :categorie"),
        @NamedQuery(name = "Defi.getDefiById", query = "SELECT d FROM Defi d WHERE d.defiId = :defiId")
})
public class Defi {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="defi_id")
    private int defiId;

    @Column(name="points")
    private int points;

    @Column(name="categorie")
    private int categorie;

    @Column(name="nom")
    private String nom;

    @Column(name="sous_titre")
    private String sousTitre;

    @Column(name="description")
    private String description;

    @Column(name="validations")
    private int validations;

    @Column(name="essais")
    private int essais;

    @Column(name="mdp")
    private String mdp;

    public int getDefiId() {
        return defiId;
    }

    public void setDefiId(int defiId) {
        this.defiId = defiId;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getCategorie() {
        return categorie;
    }

    public void setCategorie(int categorie) {
        this.categorie = categorie;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getSousTitre() {
        return sousTitre;
    }

    public void setSousTitre(String sousTitre) {
        this.sousTitre = sousTitre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getValidations() {
        return validations;
    }

    public void setValidations(int validations) {
        this.validations = validations;
    }

    public int getEssais() {
        return essais;
    }

    public void setEssais(int essais) {
        this.essais = essais;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }
}
