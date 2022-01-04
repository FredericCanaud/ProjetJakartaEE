package entities;

import jakarta.persistence.*;

@Entity
@Table(name = "CTF")
public class CTF {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="ctf_id")
    private int ctfId;

    @Column(name="nom")
    private String nom;

    @Column(name="image")
    private String image;

    @Column(name="dateDebut")
    private String dateDebut;

    @Column(name="heureDebut")
    private String heureDebut;

    @Column(name="dateFin")
    private String dateFin;

    @Column(name="heureFin")
    private String heureFin;

    @Column(name="orga_id")
    private int orgaId;

    public int getCtfId() {
        return ctfId;
    }

    public void setCtfId(int ctfId) {
        this.ctfId = ctfId;
    }

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

    public String getHeureDebut() {
        return heureDebut;
    }

    public void setHeureDebut(String heureDebut) {
        this.heureDebut = heureDebut;
    }

    public String getDateFin() {
        return dateFin;
    }

    public void setDateFin(String dateFin) {
        this.dateFin = dateFin;
    }

    public String getHeureFin() {
        return heureFin;
    }

    public void setHeureFin(String heureFin) {
        this.heureFin = heureFin;
    }

    public int getOrgaId() {
        return orgaId;
    }

    public void setOrgaId(int orgaId) {
        this.orgaId = orgaId;
    }
}
