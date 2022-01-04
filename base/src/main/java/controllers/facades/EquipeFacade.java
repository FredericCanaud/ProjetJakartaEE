package controllers.facades;

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
public class EquipeFacade extends AbstractFacade<Equipe> {
    @PersistenceContext(unitName = "DB1")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EquipeFacade() {
        super(Equipe.class);
    }

    public List<Equipe> getEquipes() {
        return null;
    }

    public List<Equipe> getBestEquipes() {
        List<Equipe> equipes = new ArrayList<>();
        Connection con = null;
        try {
            con = DriverManager.getConnection("jdbc:derby://localhost:1527/DB1");
            NamedParameterStatement stmt = new NamedParameterStatement(con, "SELECT e.equi_id, chef_id, nom, avatar, SUM(score) AS scoreTotal FROM equipe e JOIN scoreCTF ON e.equi_id = scoreCTF.equi_id GROUP BY e.equi_id, chef_id, nom, avatar ORDER BY scoreTotal DESC");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Equipe e = em.find(Equipe.class, rs.getInt("equi_id"));
                if (e == null) {
                    e = new Equipe();
                    e.setEquiId(rs.getInt("equi_id"));
                    e.setNom(rs.getString("nom"));
                    e.setAvatar(rs.getString("avatar"));
                    e.setChefId(rs.getInt("chef_id"));
                    e.setScoreTotal(rs.getInt("scoreTotal"));
                    em.persist(e);
                }
                equipes.add(e);
            }
            con.close();
            return equipes;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Equipe> getBestEquipesFromCTF() {
        return null;
    }

    public Equipe getEquipe(String id) {
        return null;
    }

    public List<Equipe> getEquipesFromCTF() {
        return null;
    }

    public List<Equipe> getEquipesNotInCTF() {
        return null;
    }
}