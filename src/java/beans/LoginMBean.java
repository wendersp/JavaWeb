package beans;

import entidade.Usuario;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import sessionbean.UsuarioSBean;
import uteis.jsf.AuthenticationUtils;
import uteis.jsf.UteisJsf;

/**
 *
 * @author wender
 */
@Named(value = "loginMBean")
@SessionScoped
public class LoginMBean implements Serializable {

    private String userName = "";
    private String senha = "";

    private Usuario usuarioLogado = null;

    @EJB
    private UsuarioSBean usuarioSBean;

    public LoginMBean() {
        
    }

    public String logar() {
        try {
            String senhaCrip = AuthenticationUtils.encodeSHA256(senha);
            this.usuarioLogado = this.usuarioSBean.login(userName, senhaCrip);
            if (this.usuarioLogado != null) {
                UteisJsf.setObjectSession("usuarioLogado", this.usuarioLogado);
                return "home?faces-redirect=true";
            }
        } catch (Exception ex) {
            UteisJsf.addMensagemErro(ex.getMessage(),"");
        }
        return null;
    }
    
    public String sair() {
        UteisJsf.removeObjectSession("usuarioLogado");
        return "login??faces-redirect=true";
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

}
