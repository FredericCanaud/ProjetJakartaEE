package beans;

import entities.Defi;
import beans.facades.DefiFacade;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;

import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Map;

@ApplicationScoped
@Named("beanDefis")
public class GestionDefis {

    private String motDePasse, errorMsg, validMsg;

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getValidMsg() {
        return validMsg;
    }

    public void setValidMsg(String validMsg) {
        this.validMsg = validMsg;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public DefiFacade getDefiFacade() {
        return defiFacade;
    }

    public void setDefiFacade(DefiFacade defiFacade) {
        this.defiFacade = defiFacade;
    }

    @EJB
    private DefiFacade defiFacade;

    public List<Defi> getDefisFromType(String categorie) throws NoSuchAlgorithmException {
        return this.defiFacade.getDefis(categorie);
    }

    public Defi getDefi(String id) {
        return this.defiFacade.getDefi(id);
    }

    public String validerDefi(){
        System.out.println("sqidhsjqs");
        clearAll();
        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String,String> params =
                fc.getExternalContext().getRequestParameterMap();

        String res = this.defiFacade.validerDefi(params.get("uti_id"), params.get("defi_id"), motDePasse);

        if(res == null) {
            validMsg += "Bien joué, vous remportez les points du défi !";
        } else {
            errorMsg += res;
        }
        return "index.html";
    }

    public void clearAll(){
        motDePasse="";
        validMsg="";
        errorMsg="";
    }
}
