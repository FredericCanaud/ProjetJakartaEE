package beans.facades;

import entities.CTF;
import entities.Commentaire;
import entities.Defi;
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
        CTF c = em.find(CTF.class, Integer.parseInt(id));
        if (c == null) {
            Connection con = null;
            try {
                con = DriverManager.getConnection("jdbc:derby://localhost:1527/DB1");
                NamedParameterStatement stmt = new NamedParameterStatement(con, "SELECT * FROM CTF WHERE ctf_id = :id");
                stmt.setInt("id", Integer.parseInt(id));
                ResultSet rs = stmt.executeQuery();
                if (rs == null) {
                    return null;
                }
                c = new CTF();
                c.setCtfId(rs.getInt("ctf_id"));
                c.setNom(rs.getString("nom"));
                c.setImage(rs.getString("image"));
                c.setDateDebut(rs.getString("datedebut"));
                c.setDateFin(rs.getString("datefin"));
                c.setHeureDebut(rs.getString("heuredebut"));
                c.setHeureFin(rs.getString("heurefin"));
                em.persist(c);
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return c;
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
                    c.setFormat(rs.getString("format"));
                    c.setUrl(rs.getString("url"));
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

    public List<CTF> getCTFFromEquipe(String id) {
        List<CTF> ctfs = new ArrayList<>();
        Connection con = null;
        try {
            con = DriverManager.getConnection("jdbc:derby://localhost:1527/DB1");
            NamedParameterStatement stmt = new NamedParameterStatement(con,"SELECT * FROM ctf c JOIN scoreCTF s ON c.ctf_id = s.ctf_id WHERE s.equi_id = :id ORDER BY dateDebut, heureDebut ASC");
            stmt.setInt("id",Integer.parseInt(id));
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                CTF ctf = new CTF();
                ctf.setCtfId(rs.getInt("ctf_id"));
                ctf.setNom(rs.getString("nom"));
                ctf.setImage(rs.getString("image"));
                ctf.setDateDebut(rs.getString("dateDebut"));
                ctf.setHeureDebut(rs.getString("heureDebut"));
                ctf.setDateFin(rs.getString("dateFin"));
                ctf.setHeureFin(rs.getString("heureFin"));
                ctf.setOrgaId(rs.getInt("orga_id"));
                ctf.setFormat(rs.getString("format"));
                ctf.setUrl(rs.getString("url"));
                ctfs.add(ctf);
            }
            con.close();

            return ctfs;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Commentaire> getCommentairesFromCTF(String id) {
        List<Commentaire> commentaires = new ArrayList<>();
        Connection con = null;
        try {
            con = DriverManager.getConnection("jdbc:derby://localhost:1527/DB1");
            NamedParameterStatement stmt = new NamedParameterStatement(con,"SELECT * FROM commentaire WHERE ctf_id = :id ORDER BY date ASC");
            stmt.setInt("id",Integer.parseInt(id));
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Commentaire com = new Commentaire();
                com.setComId(rs.getInt("com_id"));
                com.setCtfId(rs.getInt("ctf_id"));
                com.setUtiId(rs.getInt("uti_id"));
                com.setMessage(rs.getString("message"));
                com.setDate(rs.getString("date"));
                commentaires.add(com);
            }
            con.close();

            return commentaires;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<CTF> getCTFValides() {
        List<CTF> ctf = new ArrayList<>();
        Connection con = null;
        try {
            con = DriverManager.getConnection("jdbc:derby://localhost:1527/DB1");
            NamedParameterStatement stmt = new NamedParameterStatement(con,"SELECT * FROM CTF WHERE valide = true");
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
                    c.setFormat(rs.getString("format"));
                    c.setUrl(rs.getString("url"));
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

    public List<CTF> getCTFNonValides() {
        List<CTF> ctf = new ArrayList<>();
        Connection con = null;
        try {
            con = DriverManager.getConnection("jdbc:derby://localhost:1527/DB1");
            NamedParameterStatement stmt = new NamedParameterStatement(con,"SELECT * FROM CTF WHERE valide = false");
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
                    c.setFormat(rs.getString("format"));
                    c.setUrl(rs.getString("url"));
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

    public List<CTF> getCTFFromOrganisateur(String id) {
        List<CTF> ctf = new ArrayList<>();
        Connection con = null;
        try {
            con = DriverManager.getConnection("jdbc:derby://localhost:1527/DB1");
            NamedParameterStatement stmt = new NamedParameterStatement(con,"SELECT * FROM CTF WHERE orga_id = id");
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
                    c.setFormat(rs.getString("format"));
                    c.setUrl(rs.getString("url"));
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
}