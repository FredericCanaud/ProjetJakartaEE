package beans.facades;

import entities.*;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import utils.NamedParameterStatement;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Stateless
public class ParticipantFacade extends AbstractFacade<Participant>{
    @PersistenceContext(unitName = "DB1")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public Participant getParticipant(String id) {
        return null;
    }

    public ParticipantFacade() {
        super(Participant.class);
    }

    public String editParticipant(int uti_id, String mail, String mdp) throws NoSuchAlgorithmException {
        Connection con;
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        String hash = new String(digest.digest(mdp.getBytes(StandardCharsets.UTF_8))) + "myL1ttleS4lt";
        byte[] finalHash = digest.digest(hash.getBytes(StandardCharsets.UTF_8));
        String encoded = Base64.getEncoder().encodeToString(digest.digest(finalHash));
        try {
            con = DriverManager.getConnection("jdbc:derby://localhost:1527/DB1");
            NamedParameterStatement stmt = new NamedParameterStatement(con,"SELECT * FROM utilisateur WHERE mail = :mail");
            stmt.setString("mail", mail);
            stmt.executeUpdate();
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return "L'adresse mail est déjà utilisée !";
            }

            Utilisateur u = em.find(Utilisateur.class, uti_id);
            if(mdp.equals("")){
                stmt = new NamedParameterStatement(con,"UPDATE utilisateur u SET u.mail = :mail WHERE u.uti_id = :uti_id");
            } else {
                stmt = new NamedParameterStatement(con,"UPDATE utilisateur u SET u.mdp = :mdp, u.mail = :mail WHERE u.uti_id = :uti_id");
                stmt.setString("mdp", encoded);
                u.setRole(mdp);
            }
            stmt.setString("mail", mail);
            stmt.setInt("uti_id", uti_id);
            stmt.executeQuery();

            u.setMail(mail);
            em.merge(u);
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public void validerDefi(String id){

    }

    public void rejoindreEquipe(String id){

    }

    public void validerParticipantDansEquipe(String id){

    }
}