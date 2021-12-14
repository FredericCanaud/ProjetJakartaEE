import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Administrateur{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String pseudo;
    private String mail;
    private String mdp;

    public Administrateur(){}
}