package entities;

import jakarta.persistence.*;

@Entity
@Table(name = "equipe")
public class Equipe {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="equi_id")
    private int equiId;

    @Column(name="chef_id")
    private int chefId;

    @Column(name="nom")
    private String nom;

    @Column(name="avatar")
    private String avatar;

    private int scoreTotal;

    public int getEquiId() {
        return equiId;
    }

    public void setEquiId(int equiId) {
        this.equiId = equiId;
    }

    public int getChefId() {
        return chefId;
    }

    public void setChefId(int chefId) {
        this.chefId = chefId;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getScoreTotal() {
        return scoreTotal;
    }

    public void setScoreTotal(int scoreTotal) {
        this.scoreTotal = scoreTotal;
    }
}
