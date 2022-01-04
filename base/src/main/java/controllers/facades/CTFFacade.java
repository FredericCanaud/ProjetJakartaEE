package controllers.facades;

import entities.CTF;
import entities.Equipe;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import utils.NamedParameterStatement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class CTFFacade extends AbstractFacade<Equipe> {
    @PersistenceContext(unitName = "DB1")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CTFFacade() {
        super(Equipe.class);
    }

    public CTF getCTF(String id) {
        return null;
    }

    public List<CTF> getDerniersCTF() {
        List<CTF> ctf = new ArrayList<>();
        Connection con = null;
        try {
            con = DriverManager.getConnection("jdbc:derby://localhost:1527/DB1");
            NamedParameterStatement stmt = new NamedParameterStatement(con,"SELECT * FROM CTF ORDER BY dateDebut DESC, heureDebut DESC FETCH FIRST 3 ROWS ONLY");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                CTF c = em.find(CTF.class, rs.getInt("ctf_id"));
                if(c == null) {
                    c = new CTF();
                    c.setCtfId(rs.getInt("ctf_id"));
                    c.setNom(rs.getString("nom"));
                    c.setImage(rs.getString("image"));
                    c.setDateDebut(rs.getString("dateDebut"));
                    c.setHeureDebut(rs.getString("heureDebut"));
                    c.setDateFin(rs.getString("dateFin"));
                    c.setHeureFin(rs.getString("heureFin"));
                    c.setOrgaId(rs.getInt("orga_id"));
                    em.persist(c);
                }
                ctf.add(c);
            }
            con.close();

            return ctf;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<CTF> getProchainsCTF() { return null; }

    public List<CTF> getCTFFromEquipe(String id) { return null; }
}