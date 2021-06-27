package beans;

import entidade.Fornecedor;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import sessionbean.FornecedorSBean;
import uteis.jsf.UteisJsf;


/**
 *
 * @author wender
 */
@Named(value = "fornecedorMBean")
@SessionScoped
public class FornecedorMBean implements Serializable {

    @EJB
    private FornecedorSBean fornecedorSBean;
   
    
    private Fornecedor fornecedor;

    private List<Fornecedor> listaFornecedor;
    
    

    private String valorPesquisar;
    
   
   

    public FornecedorMBean() {

    }

    @PostConstruct
    public void init() {       
        this.listaFornecedor = new ArrayList<>();
    }
    
   
    

    public String botaoNovo() {
        this.fornecedor = new Fornecedor();       
        return "cadFornecedor?faces-redirect=true";
    }

    public void botaoSalvar() {
        try {
            this.fornecedorSBean.salvar(this.fornecedor);
            UteisJsf.addMensagemInfo("Salva com sucesso.","");
            this.fornecedor = new Fornecedor();
        } catch (Exception ex) {
            UteisJsf.addMensagemErro("Erro ao Salvar. ", ex.getMessage());           
        }                
    }

    public void botaoPesquisar() {
        try {
            this.listaFornecedor = this.fornecedorSBean.pesquisar(this.valorPesquisar);
        } catch (Exception ex) {
            UteisJsf.addMensagemErro(ex.getMessage());
        }
    }

    public void botaoExcluir() {
        try {
            this.fornecedorSBean.excluir(this.fornecedor);
            UteisJsf.addMensagemInfo("Excluido com sucesso.", "");
        } catch (Exception ex) {
            UteisJsf.addMensagemErro("Erro ao excluir", ex.getMessage());
        }
    }

    public String botaoEditar() {
        return "cadFornecedor?faces-redirect=true";
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    public List<Fornecedor> getListaFornecedor() {
        return listaFornecedor;
    }

    public void setListaFornecedor(List<Fornecedor> listaFornecedor) {
        this.listaFornecedor = listaFornecedor;
    }

    

    
    public String getValorPesquisar() {
        return valorPesquisar;
    }

    public void setValorPesquisar(String valorPesquisar) {
        this.valorPesquisar = valorPesquisar;
    }

   
    
 

}
