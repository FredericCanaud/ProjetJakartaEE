package beans.facades;

import jakarta.ejb.Stateless;
import utils.NamedParameterStatement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Stateless
public class OrganisateurFacade extends UtilisateurFacade {

    public OrganisateurFacade() { super(); }

    public void creerCTF(String nom, String image, String dateDebut, String heureDebut, String dateFin, String heureFin, int orga_id, String format, String URL){
        try {
            Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/DB1");
            NamedParameterStatement stmt = new NamedParameterStatement(con,"INSERT INTO ctf VALUES (:nom, :image, :dateDebut, :heureDebut, :dateFin, :heureFin, :orga_id, :format, :url, false)");
            stmt.setString("nom", nom);
            stmt.setString("image", image);
            stmt.setString("dateDebut", dateDebut);
            stmt.setString("heureDebut", heureDebut);
            stmt.setString("dateFin", dateFin);
            stmt.setString("heureFin", heureFin);
            stmt.setInt("orga_id", orga_id);
            stmt.setString("format", format);
            stmt.setString("url", URL);

            stmt.executeUpdate();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void supprimerCTF(int ctfId) {
        try {
            Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/DB1");
            NamedParameterStatement stmt = new NamedParameterStatement(con, "DELETE FROM ctf WHERE ctf_id = :id");
            stmt.setInt("id", ctfId);
            stmt.executeUpdate();

            con.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}