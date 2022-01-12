package entities;

import jakarta.persistence.*;

@Entity
@Table(name = "commentaire")
public class Commentaire {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="com_id")
    private int comId;

    @Column(name="ctf_id")
    private int ctfId;

    @Column(name="uti_id")
    private int utiId;

    @Column(name="date")
    private String date;

    @Column(name="message")
    private String message;

    public int getComId() {
        return comId;
    }

    public void setComId(int comId) {
        this.comId = comId;
    }

    public int getCtfId() {
        return ctfId;
    }

    public void setCtfId(int ctfId) {
        this.ctfId = ctfId;
    }

    public int getUtiId() {
        return utiId;
    }

    public void setUtiId(int utiId) {
        this.utiId = utiId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
