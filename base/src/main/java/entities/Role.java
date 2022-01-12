package entities;

import jakarta.persistence.*;

@Entity
@Table(name = "role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "uti_id")
    private int utiId;

    @Column(name = "role")
    private String role;

    public int getUtiId() {
        return utiId;
    }

    public void setUtiId(int utiId) {
        this.utiId = utiId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}