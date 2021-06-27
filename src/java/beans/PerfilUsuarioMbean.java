package beans;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import sessionbean.PerfilUsuarioSBean;
import entidade.PerfilUsuario;
import uteis.jsf.UteisJsf;

/**
 *
 * @author wender
 */
@Named(value = "perfilUsuarioMbean")
@SessionScoped
public class PerfilUsuarioMbean implements Serializable {

    private PerfilUsuario perfilUsuario;
    private String parametroPesquisa;
    private List<PerfilUsuario> listaPerfilUsuarios;

    @EJB
    private PerfilUsuarioSBean perfilUsuarioSBean;

    public PerfilUsuarioMbean() {

    }

    @PostConstruct
    public void init() {
        perfilUsuario = new PerfilUsuario();
        listaPerfilUsuarios = new ArrayList<>();

    }

    public String botaoNovo() {
        perfilUsuario = new PerfilUsuario();
        return "cadPerfilUsuario";
    }
    
    public void botaoPesquisar() {
        try {
            listaPerfilUsuarios = perfilUsuarioSBean.pesquisar(parametroPesquisa.toUpperCase());
        } catch (Exception ex) {
            UteisJsf.addMensagemErro(ex.getMessage(), "");
        }
    }

    public void botaoSalvar() {
        try {
            perfilUsuarioSBean.salvar(perfilUsuario);
            perfilUsuario = new PerfilUsuario();
            UteisJsf.addMensagemInfo("Unidade de Medida salva com sucesso.", "");
        } catch (Exception ex) {
            UteisJsf.addMensagemErro(ex.getMessage(), "");
        }
    }

    public String botaoNavPesquisar() {
        return "consPerfilUsuario?faces-redirect=true";
    }

    public String botaoEditar() {

        return "cadPerfilUsuario?faces-redirect=true";
    }

    public void botaoExcluir() {
        try {
            perfilUsuarioSBean.excluir(perfilUsuario);
            perfilUsuario = new PerfilUsuario();
            UteisJsf.addMensagemInfo("Unidade de Medida apagada com sucesso.", "");
        } catch (Exception ex) {
            UteisJsf.addMensagemErro(ex.getMessage(), "");
        }

    }

    public PerfilUsuario getPerfilUsuario() {
        return perfilUsuario;
    }

    public void setPerfilUsuario(PerfilUsuario perfilUsuario) {
        this.perfilUsuario = perfilUsuario;
    }

    public String getParametroPesquisa() {
        return parametroPesquisa;
    }

    public void setParametroPesquisa(String parametroPesquisa) {
        this.parametroPesquisa = parametroPesquisa;
    }

    public List getListaPerfilUsuarios() {
        return listaPerfilUsuarios;
    }

    public void setListaPerfilUsuarios(List listaPerfilUsuarios) {
        this.listaPerfilUsuarios = listaPerfilUsuarios;
    }

}
