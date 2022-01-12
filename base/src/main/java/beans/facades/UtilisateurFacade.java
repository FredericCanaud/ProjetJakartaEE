package beans.facades;

import entities.*;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.servlet.http.Part;
import utils.NamedParameterStatement;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Stateless
public class UtilisateurFacade extends AbstractFacade<Utilisateur>{
    @PersistenceContext(unitName = "DB1")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public Utilisateur getUtilisateur(String id) {
            try {
                Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/DB1");
                NamedParameterStatement stmt = new NamedParameterStatement(con, "SELECT * FROM utilisateur WHERE uti_id = :id");
                stmt.setInt("id", Integer.parseInt(id));
                ResultSet rs = stmt.executeQuery();

                if (rs.next()) {
                    Utilisateur u = new Utilisateur();
                    u.setId(rs.getInt("uti_id"));
                    u.setPseudo(rs.getString("pseudo"));
                    u.setMail(rs.getString("mail"));
                    u.setAvatar(rs.getString("avatar"));
                    u.setMdp(rs.getString("mdp"));
                    u.setRole(rs.getString("role"));
                    con.close();
                    return u;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        return null;
    }

    public UtilisateurFacade() {
        super(Utilisateur.class);
    }

    public void editUtilisateur(String id) {}

    public Utilisateur login(String mail, String motDePasse, String role) throws NoSuchAlgorithmException {
        Connection con;
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        String hash = new String(digest.digest(motDePasse.getBytes(StandardCharsets.UTF_8))) + "myL1ttleS4lt";
        byte[] finalHash = digest.digest(hash.getBytes(StandardCharsets.UTF_8));
        String encoded = Base64.getEncoder().encodeToString(digest.digest(finalHash));
        Utilisateur utilisateur = null;

        try {
            con = DriverManager.getConnection("jdbc:derby://localhost:1527/DB1");
            NamedParameterStatement stmt = new NamedParameterStatement(con,"SELECT * FROM utilisateur WHERE role = :role AND mdp = :mdp AND mail = :mail");
            stmt.setString("mail", mail);
            stmt.setString("mdp", encoded);
            stmt.setString("role", role);

            ResultSet rs = stmt.executeQuery();
            if (!rs.next()) {
                return null;
            }
            switch(role){
                case "participant":
                    utilisateur = new Participant();
                    break;
                case "admin":
                    utilisateur = new Administrateur();
                    break;
                case "organisateur":
                    utilisateur = new Organisateur();
                    break;
                default:
                    return null;
            }
            utilisateur.setId(rs.getInt("uti_id"));
            utilisateur.setMail(rs.getString("mail"));
            utilisateur.setRole(rs.getString("role"));
            utilisateur.setMdp(rs.getString("mdp"));
            utilisateur.setPseudo(rs.getString("pseudo"));
            em.persist(utilisateur);
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return utilisateur;
    }

    public String inscription(String pseudo, Part avatar, String mail, String motDePasse, String role) throws NoSuchAlgorithmException {
            // String nomFichier = Helpers.randomString();
            // System.out.println(avatar.getSubmittedFileName());
            // System.out.println(nomFichier);
            // File file = new File(StringUtils.substringBetween(avatar.toString(), "StoreLocation=", ","));
            // String connectStr = System.getenv("AZURE_STORAGE_CONNECTION_STRING");
            // BlobServiceClient blobServiceClient = new BlobServiceClientBuilder().connectionString(connectStr).buildClient();
            // System.out.println(avatar.getContentType());

        Connection con;
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        String hash = new String(digest.digest(motDePasse.getBytes(StandardCharsets.UTF_8))) + "myL1ttleS4lt";
        byte[] finalHash = digest.digest(hash.getBytes(StandardCharsets.UTF_8));
        String encoded = Base64.getEncoder().encodeToString(digest.digest(finalHash));
        try {
            con = DriverManager.getConnection("jdbc:derby://localhost:1527/DB1");
            NamedParameterStatement stmt = new NamedParameterStatement(con,"SELECT * FROM utilisateur WHERE pseudo = :pseudo OR mail = :mail");
            stmt.setString("mail", mail);
            stmt.setString("pseudo", pseudo);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return "Le pseudo ou l'adresse mail est déjà utilisé !";
            }

            stmt = new NamedParameterStatement(con,"INSERT INTO utilisateur VALUES (DEFAULT, :pseudo, 'default.jpg',  :mail, :mdp, :role)");
            stmt.setString("pseudo", pseudo);
            stmt.setString("role", role);
            stmt.setString("mdp", encoded);
            stmt.setString("mail", mail);
            stmt.executeUpdate();

            Utilisateur utilisateur;
            switch(role){
                case "participant":
                    utilisateur = new Participant();
                    break;
                case "admin":
                    utilisateur = new Administrateur();
                    break;
                case "organisateur":
                    utilisateur = new Organisateur();
                    break;
                default:
                    return "Erreur de role !";
            }
            utilisateur.setPseudo(pseudo);
            utilisateur.setMail(mail);
            utilisateur.setRole(role);
            em.persist(utilisateur);
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void rejoindreEquipe(String id){

    }

    public void validerParticipantDansEquipe(String id){

    }

    public void posterCommentaire(int ctf_id, String uti_id, String message){
        try {
            Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/DB1");
            NamedParameterStatement stmt = new NamedParameterStatement(con,"INSERT INTO commentaire VALUES (DEFAULT, :ctf_id, :date, :message, :uti_id)");
            stmt.setInt("uti_id", Integer.parseInt(uti_id));
            stmt.setString("date", LocalDate.now().toString());
            stmt.setString("message", message);
            stmt.setInt("ctf_id", ctf_id);
            stmt.executeUpdate();

            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Utilisateur> getClassementGeneral(){
        List<Utilisateur> utilisateurs = new ArrayList<>();
        Connection con = null;
        try {
            con = DriverManager.getConnection("jdbc:derby://localhost:1527/DB1");
            NamedParameterStatement stmt = new NamedParameterStatement(con,"SELECT u.uti_id, u.pseudo, COALESCE(SUM(s.score),0)+COALESCE(SUM(d.points),0) AS scoreTotal FROM utilisateur u LEFT JOIN appartientA a on u.uti_id = a.uti_id LEFT JOIN scoreCTF s on a.equi_id = s.equi_id LEFT JOIN valide ON valide.uti_id = u.uti_id LEFT JOIN defi d on valide.defi_id = d.defi_id GROUP BY u.uti_id, u.pseudo ORDER BY scoreTotal DESC");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Utilisateur u = new Utilisateur();
                u.setId(rs.getInt("uti_id"));
                u.setPseudo(rs.getString("pseudo"));
                u.setScore(rs.getInt("scoreTotal"));
                utilisateurs.add(u);
            }
            con.close();

            return utilisateurs;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Utilisateur> getMembresEquipe(String equi_id){
        List<Utilisateur> utilisateurs = new ArrayList<>();
        try {
            Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/DB1");
            NamedParameterStatement stmt = new NamedParameterStatement(con,"SELECT u.uti_id, u.pseudo FROM utilisateur u JOIN appartientA a ON u.uti_id = a.uti_id WHERE a.accepte = true AND equi_id = :id");
            stmt.setInt("id", Integer.parseInt(equi_id));
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Utilisateur u = new Utilisateur();
                u.setId(rs.getInt("uti_id"));
                u.setPseudo(rs.getString("pseudo"));
                utilisateurs.add(u);
            }
            con.close();

            return utilisateurs;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean estChef(int id) {
        try {
            Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/DB1");
            NamedParameterStatement stmt = new NamedParameterStatement(con, "SELECT * FROM equipe WHERE chef_id = :id");
            stmt.setInt("id", id);
            ResultSet rs = stmt.executeQuery();

            if (rs != null) {
                con.close();
                return true;
            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Utilisateur> getMembresNonAdmin() {
        List<Utilisateur> utilisateurs = new ArrayList<>();
        try {
            Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/DB1");
            NamedParameterStatement stmt = new NamedParameterStatement(con,"SELECT * FROM utilisateur u WHERE role != 'admin'");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Utilisateur u = new Utilisateur();
                u.setId(rs.getInt("uti_id"));
                u.setPseudo(rs.getString("pseudo"));
                u.setRole(rs.getString("role"));
                u.setMail(rs.getString("mail"));
                utilisateurs.add(u);
            }
            con.close();

            return utilisateurs;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Utilisateur> getMembresSaufMoi(String utiId) {
        List<Utilisateur> utilisateurs = new ArrayList<>();
        try {
            Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/DB1");
            NamedParameterStatement stmt = new NamedParameterStatement(con,"SELECT * FROM utilisateur u WHERE uti_id != :id");
            stmt.setString("id", utiId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Utilisateur u = new Utilisateur();
                u.setId(rs.getInt("uti_id"));
                u.setPseudo(rs.getString("pseudo"));
                u.setRole(rs.getString("role"));
                u.setMail(rs.getString("mail"));
                utilisateurs.add(u);
            }
            con.close();

            return utilisateurs;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getPseudoFromMembre(String utiId) {
        Utilisateur u = em.find(Utilisateur.class, Integer.parseInt(utiId));
        if(u != null) {
            return u.getPseudo();
        } else {
            try {
                Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/DB1");
                NamedParameterStatement stmt = new NamedParameterStatement(con,"SELECT * FROM utilisateur u WHERE uti_id != :id");
                stmt.setString("id", utiId);
                ResultSet rs = stmt.executeQuery();
                if(rs.next()){
                    u = new Utilisateur();
                    u.setId(rs.getInt("uti_id"));
                    u.setPseudo(rs.getString("pseudo"));
                    u.setRole(rs.getString("role"));
                    u.setMail(rs.getString("mail"));
                    u.setMdp(rs.getString("mdp"));
                    u.setAvatar(rs.getString("avatar"));
                    con.close();
                    return u.getPseudo();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}