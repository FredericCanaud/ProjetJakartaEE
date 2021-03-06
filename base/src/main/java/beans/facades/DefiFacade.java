package beans.facades;

import entities.Defi;
import jakarta.ejb.Stateless;
import jakarta.persistence.*;
import utils.NamedParameterStatement;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class DefiFacade extends AbstractFacade<Defi> {
    @PersistenceContext(unitName = "DB1")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DefiFacade() {
        super(Defi.class);
    }

    public List<Defi> findAllDefis() {
        TypedQuery<Defi> query = em.createNamedQuery("Defi.getDefis", Defi.class);
        System.out.println(query.getResultStream().findFirst());
        return query.getResultList();
    }

    public List<Defi> getDefis(String categorie) throws NoSuchAlgorithmException {

        List<Defi> defis = new ArrayList<>();
        Connection con = null;
        try {
            con = DriverManager.getConnection("jdbc:derby://localhost:1527/DB1");
            NamedParameterStatement stmt = new NamedParameterStatement(con, "SELECT * FROM DEFI WHERE categorie = :categorie");
            stmt.setInt("categorie", Integer.parseInt(categorie));
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Defi d = em.find(Defi.class, rs.getInt("defi_id"));
                if (d == null) {
                    d = new Defi();
                    d.setDefiId(rs.getInt("defi_id"));
                    d.setNom(rs.getString("nom"));
                    d.setSousTitre(rs.getString("sous_titre"));
                    d.setValidations(rs.getInt("validations"));
                    d.setEssais(rs.getInt("essais"));
                    d.setPoints(rs.getInt("points"));
                    em.persist(d);
                }
                defis.add(d);
            }
            con.close();

            return defis;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Defi getDefi(String defiId) {
        Defi d = em.find(Defi.class, Integer.parseInt(defiId));
        if (d == null) {
            Connection con = null;
            try {
                con = DriverManager.getConnection("jdbc:derby://localhost:1527/DB1");
                NamedParameterStatement stmt = new NamedParameterStatement(con, "SELECT * FROM DEFI WHERE defi_id = :id");
                stmt.setInt("id", Integer.parseInt(defiId));
                ResultSet rs = stmt.executeQuery();
                if (rs == null) {
                    return null;
                }
                d = new Defi();
                d.setDefiId(rs.getInt("defi_id"));
                d.setNom(rs.getString("nom"));
                d.setDescription(rs.getString("description"));
                d.setSousTitre(rs.getString("sous_titre"));
                d.setValidations(rs.getInt("validations"));
                d.setEssais(rs.getInt("essais"));
                d.setPoints(rs.getInt("points"));
                em.persist(d);
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return d;
    }

    public List<Defi> findDefisByCategorie(int categorie) {
        TypedQuery<Defi> query = em.createNamedQuery("Defi.getDefisByCategorie", Defi.class);
        query.setParameter("categorie", categorie);
        System.out.println(em.isOpen());
        return query.getResultList();
    }

    public String validerDefi(String uti_id, String defi_id, String motDePasse) {
        Connection con = null;
        try {
            con = DriverManager.getConnection("jdbc:derby://localhost:1527/DB1");

            NamedParameterStatement stmt = new NamedParameterStatement(con, "SELECT * FROM defi WHERE defi_id = :defi_id AND mdp = :mdp");
            stmt.setInt("defi_id", Integer.parseInt(uti_id));
            stmt.setString("mdp", motDePasse);
            ResultSet rs = stmt.executeQuery();
            if(rs == null){
                return "Le mot de passe saisi est incorrect !";
            }

            stmt = new NamedParameterStatement(con, "SELECT * FROM valide WHERE uti_id = :uti_id AND defi_id = :defi_id");
            stmt.setInt("uti_id", Integer.parseInt(uti_id));
            stmt.setInt("defi_id", Integer.parseInt(uti_id));
            rs = stmt.executeQuery();
            if(rs != null){
                return "Bien jou??, mais vous avez d??j?? remport?? les points !";
            }

            stmt = new NamedParameterStatement(con, "INSERT INTO valide VALUES (:uti_id, :defi_id)");
            stmt.setInt("uti_id", Integer.parseInt(uti_id));
            stmt.setInt("defi_id", Integer.parseInt(uti_id));
            stmt.executeQuery();

            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}