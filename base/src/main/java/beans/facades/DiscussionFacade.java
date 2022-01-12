package beans.facades;

import entities.Defi;
import entities.Discussion;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import utils.NamedParameterStatement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class DiscussionFacade extends AbstractFacade<Discussion>{

    @PersistenceContext(unitName = "DB1")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return this.em;
    }

    public DiscussionFacade() {
        super(Discussion.class);
    }


    public List<Discussion> getDiscussions(String uti_id1, String uti_id2) {
        List<Discussion> discussions = new ArrayList<>();
        Connection con = null;
        try {
            con = DriverManager.getConnection("jdbc:derby://localhost:1527/DB1");
            NamedParameterStatement stmt = new NamedParameterStatement(con, "SELECT * FROM discussion WHERE (UTI_ID_DEST = :id1 AND UTI_ID_EXP = :id2) OR (UTI_ID_DEST = :id2 AND UTI_ID_EXP = :id1) ORDER BY date, heure");
            stmt.setInt("id1", Integer.parseInt(uti_id1));
            stmt.setInt("id2", Integer.parseInt(uti_id2));
            System.out.println(uti_id1);
            System.out.println(uti_id2);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Discussion d = em.find(Discussion.class, rs.getInt("disc_id"));
                if (d == null) {
                    d = new Discussion();
                    d.setDiscId(rs.getInt("disc_id"));
                    d.setUtiIdExp(rs.getInt("uti_id_exp"));
                    d.setUtiIdDest(rs.getInt("uti_id_dest"));
                    d.setMessage(rs.getString("message"));
                    d.setDate(rs.getString("date"));
                    d.setHeure(rs.getString("heure"));
                }
                discussions.add(d);
            }
            con.close();

            return discussions;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void posterMessage(String uti_id1, String uti_id2, String message) {
        Connection con = null;
        try {
            con = DriverManager.getConnection("jdbc:derby://localhost:1527/DB1");
            NamedParameterStatement stmt = new NamedParameterStatement(con, "INSERT INTO discussion VALUES (:id1, :id2, :message, :date, :heure");
            stmt.setInt("id1", Integer.parseInt(uti_id1));
            stmt.setInt("id2", Integer.parseInt(uti_id2));
            stmt.setString("message", message);
            stmt.setString("date", LocalDate.now().toString());
            stmt.setString("heure", LocalTime.now().toString());
            stmt.executeUpdate();

            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}