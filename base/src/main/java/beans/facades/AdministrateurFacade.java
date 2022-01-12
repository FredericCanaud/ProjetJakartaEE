package beans.facades;

import entities.Administrateur;
import entities.Utilisateur;
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
public class AdministrateurFacade extends AbstractFacade<Administrateur> {
    @PersistenceContext(unitName = "DB1")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AdministrateurFacade() {
        super(Administrateur.class);
    }

    public void validerCTF(String id){
        try {
            Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/DB1");
            NamedParameterStatement stmt = new NamedParameterStatement(con, "UPDATE CTF SET ctf.VALIDE = true WHERE ctf_id = :id");
            stmt.setString("id", id);
            stmt.executeUpdate();

            con.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void supprimerCTF(String id){
        try {
            Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/DB1");
            NamedParameterStatement stmt = new NamedParameterStatement(con, "DELETE FROM ctf WHERE ctf_id = :id");
            stmt.setString("id", id);
            stmt.executeUpdate();

            con.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void bannirUtilisateur(String id, String mail){
        try {
            Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/DB1");
            NamedParameterStatement stmt = new NamedParameterStatement(con,"INSERT INTO banni VALUES (:mail)");
            stmt.setString("mail", mail);
            stmt.executeUpdate();

            stmt = new NamedParameterStatement(con, "DELETE FROM utilisateur WHERE uti_id = :id");
            stmt.setString("id", id);
            stmt.executeUpdate();

            con.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}