package uteis.jsf;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author wender
 */
public class UteisJsf {

    public static void addMensagemErro(String sumario, String mensagem) {
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, sumario, mensagem));

    }

    public static void addMensagemErro(String sumario) {
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, sumario, ""));

    }

    public static void addMensagemInfo(String sumario, String mensagem) {
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, sumario, mensagem));

    }

    public static void addMensagemInfo(String sumario) {
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, sumario, ""));

    }

    public static void setObjectSession(String nameObject, Object object) {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpSession session = request.getSession();
        session.setAttribute(nameObject, object);
    }

    public static Object getObjectSession(String nameObject) {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpSession session = request.getSession();
        return session.getAttribute(nameObject);
    }

    public static void removeObjectSession(String nameObject) {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpSession session = request.getSession();
        session.removeAttribute(nameObject);
    }

}
