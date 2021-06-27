package beans;

import entidade.Produto;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import sessionbean.ProdutoSBean;
import entidade.UnidadeMedida;
import java.math.BigDecimal;
import java.math.RoundingMode;
import sessionbean.UnidadeMedidaSBean;
import uteis.jsf.UnidadeMedidaConverter;
import uteis.jsf.UteisJsf;

/**
 *
 * @author wender
 */
@Named(value = "produtoMbean")
@SessionScoped
public class ProdutoMbean implements Serializable {

    @EJB
    private ProdutoSBean produtoSBean;
    @EJB
    private UnidadeMedidaSBean unidadeMedidaSBean;

    private Produto produto;
    private String parametroPesquisa;
    private List<Produto> listaProdutos;
    private List<UnidadeMedida> listaUnidadeMedida;

    private UnidadeMedidaConverter unidadeMedidaConverter;

    public ProdutoMbean() {

    }

    @PostConstruct
    public void init() {
        parametroPesquisa = "";
        produto = new Produto();
        listaProdutos = new ArrayList<>();
    }

    private void inicioFormularioCadastro() {
        try {
            listaUnidadeMedida = unidadeMedidaSBean.pesquisar("");
            unidadeMedidaConverter = new UnidadeMedidaConverter();
            unidadeMedidaConverter.setUnidadeMedidaSBean(unidadeMedidaSBean);
        } catch (Exception ex) {
            UteisJsf.addMensagemErro(ex.getMessage(), "");
        }
    }

    public void calcularPrecoVenda() {
        //precoVenda = ((margem/100)*precoCusto)+precoCusto  
        try {
            if (produto.getPrecoCusto() != null && produto.getMargemLucro() != null) {
                produto.setPrecoVenda(
                        produto.getMargemLucro().divide(BigDecimal.valueOf(100), 4, RoundingMode.HALF_UP)
                                .multiply(produto.getPrecoCusto())
                                .add(produto.getPrecoCusto()));
            } else {
                produto.setPrecoVenda(null);
            }
        } catch (NullPointerException ex) {
            UteisJsf.addMensagemErro("Erro ao calcular o preço de venda.", ex.getMessage());
        } catch (Exception ex) {
             UteisJsf.addMensagemErro("Erro ao calcular o preço de venda.", ex.getMessage());
        }
    }

    public void calcularMargemLucro() {
        //margem = ((precoVenda-custo)/custo)*100  
        try {
            if (produto.getPrecoCusto() != null && produto.getPrecoVenda() != null) {
                produto.setMargemLucro(produto.getPrecoVenda().subtract(produto.getPrecoCusto())
                        .divide(produto.getPrecoCusto(), 4, RoundingMode.HALF_UP).multiply(BigDecimal.valueOf(100)));
            } else {
                produto.setMargemLucro(null);
            }
        } catch (NullPointerException ex) {
            UteisJsf.addMensagemErro("Erro ao calcular a margem de lucro.", ex.getMessage());
        }catch (Exception ex) {
             UteisJsf.addMensagemErro("Erro ao calcular o preço de venda.", ex.getMessage());
        }

    }

    public void botaoPesquisar() {
        try {
            listaProdutos = produtoSBean.pesquisar(parametroPesquisa.toUpperCase());
        } catch (Exception ex) {
            UteisJsf.addMensagemErro(ex.getMessage(), "");
        }
    }

    public String botaoNavPesquisar() {
        return "consProduto?faces-redirect=true";
    }

    public String botaoNovo() {
        this.produto = new Produto();
        this.inicioFormularioCadastro();
        return "cadProduto?faces-redirect=true";
    }

    public void botaoSalvar() {
        try {
            produtoSBean.salvar(produto);
            produto = new Produto();
            UteisJsf.addMensagemInfo("O produto foi salvo com sucesso.", "");
        } catch (Exception ex) {
            UteisJsf.addMensagemErro(ex.getMessage(), "");
        }

    }

    public String botaoEditar() {
        this.inicioFormularioCadastro();
        return "cadProduto?faces-redirect=true";
    }

    public void botaoExcluir() {
        try {
            produtoSBean.excluir(produto);
            produto = new Produto();
            UteisJsf.addMensagemInfo("O produto foi apagado com sucesso.", "");
        } catch (Exception ex) {
            UteisJsf.addMensagemErro(ex.getMessage(), "");
        }
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public String getParametroPesquisa() {
        return parametroPesquisa;
    }

    public void setParametroPesquisa(String parametroPesquisa) {
        this.parametroPesquisa = parametroPesquisa;
    }

    public List getListaProdutos() {
        return listaProdutos;
    }

    public void setListaProdutos(List listaProdutos) {
        this.listaProdutos = listaProdutos;
    }

    public List<UnidadeMedida> getListaUnidadeMedida() {
        return listaUnidadeMedida;
    }

    public void setListaUnidadeMedida(List<UnidadeMedida> listaUnidadeMedida) {
        this.listaUnidadeMedida = listaUnidadeMedida;
    }

    public UnidadeMedidaConverter getUnidadeMedidaConverter() {
        return unidadeMedidaConverter;
    }

    public void setUnidadeMedidaConverter(UnidadeMedidaConverter unidadeMedidaConverter) {
        this.unidadeMedidaConverter = unidadeMedidaConverter;
    }

}
