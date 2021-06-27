package sessionbean;

import entidade.Produto;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author wender
 */
@Stateless
public class ProdutoSBean {

    @PersistenceContext(unitName = "JavaWebPU")
    private EntityManager em;

    public void salvar(Produto produto) throws Exception {
        try {
            em.merge(produto);
        } catch (Exception ex) {
            throw new Exception("Ouve um erro ao salvar o Produto.");
        }
    }

    public void excluir(Produto produto) throws Exception {
        try {
            em.remove(em.find(Produto.class, produto.getId()));
        } catch (Exception ex) {
            throw new Exception("Ouve um erro ao apagar o Produto.");
        }
    }

    public Produto pesquisar(Long id) throws Exception {
        try {
            return em.find(Produto.class, id);
        } catch (Exception ex) {
            throw new Exception("Ouve um erro ao pesquisar o Produto.");
        }
    }

    public List<Produto> pesquisar(String valorPesquisa) throws Exception {
        try {
            List<Produto> listaProduto;
            Query consulta = em.createNamedQuery("Produto.findByNome");
            consulta.setParameter("nome", valorPesquisa + "%");
            listaProduto = consulta.getResultList();
            return listaProduto;
        } catch (Exception ex) {
            throw new Exception("Ouve um erro ao pesquisar o Produto.");
        }
    }
}
