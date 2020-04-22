/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

    public void botaoPesquisar() {
        listaUnidadeMedidas = unidadeMedidaSBean.pesquisar(parametroPesquisa);
    }

    public String botaoSalvar() {
        unidadeMedidaSBean.salvar(unidadeMedida);
        unidadeMedida = new UnidadeMedida();
        return "consUnidadeMedida?faces-redirect=true";
    }

    public String botaoEditar() {
        
        return "cadUnidadeMedida?faces-redirect=true";
    }

    public void botaoExcluir() {
        unidadeMedidaSBean.excluir(unidadeMedida);
        unidadeMedida = new UnidadeMedida();
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
