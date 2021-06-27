package sessionbean;

import entidade.Fornecedor;
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
public class FornecedorSBean {

    @PersistenceContext(unitName = "JavaWebPU")
    private EntityManager em;

    public void salvar(Fornecedor fornecedor) throws Exception {
        em.merge(fornecedor);
    }

    public void excluir(Fornecedor fornecedor) throws Exception {
        em.remove(em.find(Fornecedor.class, fornecedor.getId()));
    }

    public Fornecedor pesquisar(Long id) throws Exception {
        try {
            return em.find(Fornecedor.class, id);
        } catch (Exception ex) {
            throw new Exception("Erro ao Pesquisar Fornecedor Por Id");
        }
    }

    public Fornecedor pesquisarPorCnpj(String cnpj) throws Exception {
        try {
            Query consulta = em.createNamedQuery("Forncedor.findByCnpj");
            consulta.setParameter("cnpj", cnpj);
            return (Fornecedor) consulta.getSingleResult();
        } catch (Exception ex) {
            throw new Exception("Erro ao pesquisar o fornecedor por CNPJ.");
        }
    }

    public List<Fornecedor> pesquisar(String nome) throws Exception {
        try {
            Query consulta = em.createNamedQuery("Forncedor.findByNome");
            consulta.setParameter("nome", nome + "%");
            return consulta.getResultList();
        } catch (Exception ex) {
            throw new Exception("Erro ao pesquisar o fornecedor por nome.");
        }
    }

   

}
