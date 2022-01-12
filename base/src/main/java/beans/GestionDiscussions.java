package beans;

import beans.facades.DiscussionFacade;
import entities.Discussion;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import java.util.List;

@ApplicationScoped
@Named("beanDiscussions")
public class GestionDiscussions {

    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @EJB
    private DiscussionFacade discussionFacade;

    public List<Discussion> getDiscussions(String uti_id1, String uti_id2) {
        return this.discussionFacade.getDiscussions(uti_id1, uti_id2);
    }

    public void posterMessage(String uti_id1, String uti_id2){
        this.discussionFacade.posterMessage(uti_id1, uti_id2, message);
    }
}
