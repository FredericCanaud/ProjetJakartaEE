package entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="discussion")
public class Discussion {

    @Id
    @Column(name="disc_id")
    private int discId;

    @Column(name="uti_id_exp")
    private int utiIdExp;

    @Column(name="uti_id_dest")
    private int utiIdDest;

    @Column(name="message")
    private String message;

    @Column(name="date")
    private String date;

    @Column(name="heure")
    private String heure;

    public int getDiscId() {
        return discId;
    }

    public void setDiscId(int discId) {
        this.discId = discId;
    }

    public int getUtiIdExp() {
        return utiIdExp;
    }

    public void setUtiIdExp(int utiIdExp) {
        this.utiIdExp = utiIdExp;
    }

    public int getUtiIdDest() {
        return utiIdDest;
    }

    public void setUtiIdDest(int utiIdDest) {
        this.utiIdDest = utiIdDest;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHeure() {
        return heure;
    }

    public void setHeure(String heure) {
        this.heure = heure;
    }
}
