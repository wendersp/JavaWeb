package beans;


import entidade.Usuario;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import sessionbean.UsuarioSBean;
import uteis.jsf.AuthenticationUtils;
import uteis.jsf.UteisJsf;


/**
 *
 * @author wender
 */
@Named(value = "usuarioMBean")
@SessionScoped
public class UsuarioMBean implements Serializable {

    @EJB
    private UsuarioSBean usuarioSBean;

    private Usuario usuario;

    private List<Usuario> listaUsuario;

    private String valorPesquisar;
    
    private String senha;

    public UsuarioMBean() {

    }

    @PostConstruct
    public void init() {
        this.usuario = new Usuario();
        this.listaUsuario = new ArrayList<>();
    }

    public String botaoNovo() {
        this.usuario = new Usuario();
        return "cadUsuario?faces-redirect=true";
    }

    public void botaoSalvar() {
        try {
            if ((senha != null) && (!senha.equals(""))) {
                usuario.setSenha(AuthenticationUtils.encodeSHA256(senha));
            }
            usuarioSBean.salvar(usuario);
            UteisJsf.addMensagemInfo("Usuario Salvo com sucesso.","");
            usuario = new Usuario();
        } catch (Exception ex) {
            UteisJsf.addMensagemErro("Erro ao Salvar Usuiaro", ex.getMessage());           
        }                
    }

    public void botaoPesquisar() {
        try {
            listaUsuario = usuarioSBean.pesquisar(valorPesquisar);
        } catch (Exception ex) {
             UteisJsf.addMensagemErro("Erro ao pesquisar usuario. ", ex.getMessage());      
        }
    }

    public void botaoExcluir() {
        try {
            usuarioSBean.excluir(usuario);
            UteisJsf.addMensagemInfo("INFO: - ", "Usuario Excluido com sucesso.");
        } catch (Exception ex) {
            UteisJsf.addMensagemErro("ATENÇÃO: - ", ex.getMessage());
        }
    }

    public String botaoEditar() {
        return "cadUsuario?faces-redirect=true";
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Usuario> getListaUsuario() {
        return listaUsuario;
    }

    public String getValorPesquisar() {
        return valorPesquisar;
    }

    public void setValorPesquisar(String valorPesquisar) {
        this.valorPesquisar = valorPesquisar;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    
}
