package beans;

import entidade.Usuario;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import uteis.jsf.UteisJsf;

/**
 *
 * @author wender
 */
@Named(value = "homeMBean")
@RequestScoped
public class homeMBean {

    private Usuario usuarioLogado;
    
    /**
     * Creates a new instance of homeMBean
     */
    public homeMBean() {
        
    }
    
    @PostConstruct
    public void init() {
        this.usuarioLogado = (Usuario)UteisJsf.getObjectSession("usuarioLogado");
    }

    public Usuario getUsuarioLogado() {
        return usuarioLogado;
    }

    public void setUsuarioLogado(Usuario usuarioLogado) {
        this.usuarioLogado = usuarioLogado;
    }
    
    
    
}
