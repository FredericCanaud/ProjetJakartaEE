package controllers.facades;

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

    public Participant getUtilisateur(String id) {
        return null;
    }

    public UtilisateurFacade() {
        super(Utilisateur.class);
    }

    public void editUtilisateur(String id) {}

    public boolean login(String mail, String motDePasse, String role) throws NoSuchAlgorithmException {
        Connection con;
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        String hash = new String(digest.digest(motDePasse.getBytes(StandardCharsets.UTF_8))) + "myL1ttleS4lt";
        byte[] finalHash = digest.digest(hash.getBytes(StandardCharsets.UTF_8));
        String encoded = Base64.getEncoder().encodeToString(digest.digest(finalHash));

        try {
            con = DriverManager.getConnection("jdbc:derby://localhost:1527/DB1");
            NamedParameterStatement stmt = new NamedParameterStatement(con,"SELECT * FROM utilisateur WHERE role = :role AND mdp = :mdp AND mail = :mail");

            ResultSet rs = stmt.executeQuery();
            if (!rs.next()) {
                return false;
            }
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
                    return false;
            }
            utilisateur.setMail(rs.getString("mail"));
            utilisateur.setRole(rs.getString("role"));
            em.persist(utilisateur);
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
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

    public void validerDefi(String id){

    }

    public void rejoindreEquipe(String id){

    }

    public void validerParticipantDansEquipe(String id){

    }

    public void posterCommentaire(){

    }

    public List<Participant> getClassementGeneral(){
        return null;
    }
}