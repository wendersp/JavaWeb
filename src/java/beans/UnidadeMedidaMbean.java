package beans;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import sessionbean.UnidadeMedidaSBean;
import entidade.UnidadeMedida;
import uteis.jsf.UteisJsf;

/**
 *
 * @author wender
 */
@Named(value = "unidadeMedidaMbean")
@SessionScoped
public class UnidadeMedidaMbean implements Serializable {

    private UnidadeMedida unidadeMedida;
    private String parametroPesquisa;
    private List<UnidadeMedida> listaUnidadeMedidas;

    @EJB
    private UnidadeMedidaSBean unidadeMedidaSBean;

    public UnidadeMedidaMbean() {

    }

    @PostConstruct
    public void init() {
        unidadeMedida = new UnidadeMedida();
        listaUnidadeMedidas = new ArrayList<>();

    }

    public String botaoNovo() {
        unidadeMedida = new UnidadeMedida();
        return "cadUnidadeMedida";
    }
    
    public void botaoPesquisar() {
        try {
            listaUnidadeMedidas = unidadeMedidaSBean.pesquisar(parametroPesquisa.toUpperCase());
        } catch (Exception ex) {
            UteisJsf.addMensagemErro(ex.getMessage(), "");
        }
    }

    public void botaoSalvar() {
        try {
            unidadeMedidaSBean.salvar(unidadeMedida);
            unidadeMedida = new UnidadeMedida();
            UteisJsf.addMensagemInfo("Unidade de Medida salva com sucesso.", "");
        } catch (Exception ex) {
            UteisJsf.addMensagemErro(ex.getMessage(), "");
        }
    }

    public String botaoNavPesquisar() {
        return "consUnidadeMedida?faces-redirect=true";
    }

    public String botaoEditar() {

        return "cadUnidadeMedida?faces-redirect=true";
    }

    public void botaoExcluir() {
        try {
            unidadeMedidaSBean.excluir(unidadeMedida);
            unidadeMedida = new UnidadeMedida();
            UteisJsf.addMensagemInfo("Unidade de Medida apagada com sucesso.", "");
        } catch (Exception ex) {
            UteisJsf.addMensagemErro(ex.getMessage(), "");
        }

    }

    public UnidadeMedida getUnidadeMedida() {
        return unidadeMedida;
    }

    public void setUnidadeMedida(UnidadeMedida unidadeMedida) {
        this.unidadeMedida = unidadeMedida;
    }

    public String getParametroPesquisa() {
        return parametroPesquisa;
    }

    public void setParametroPesquisa(String parametroPesquisa) {
        this.parametroPesquisa = parametroPesquisa;
    }

    public List getListaUnidadeMedidas() {
        return listaUnidadeMedidas;
    }

    public void setListaUnidadeMedidas(List listaUnidadeMedidas) {
        this.listaUnidadeMedidas = listaUnidadeMedidas;
    }

}
