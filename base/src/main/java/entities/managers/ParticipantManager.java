package entities.managers;

import entities.Participant;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

@Stateless
public class ParticipantManager {
    @PersistenceContext(name = "default")
    private EntityManager em;

    public void addPersonne(String pseudo, String mail, String motDePasse, String type) throws SQLException {
        Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/DB1");
        Statement stmt = con.createStatement();
        String sql = "INSERT INTO APP.ADMINISTRATEUR VALUES ('Alex')";
        stmt.executeUpdate(sql);
        con.close();
    }
}
