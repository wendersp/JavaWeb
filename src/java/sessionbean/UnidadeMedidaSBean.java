package sessionbean;

import entidade.UnidadeMedida;
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
public class UnidadeMedidaSBean {

    @PersistenceContext(unitName = "JavaWebPU")
    private EntityManager em;

    public void salvar(UnidadeMedida unidadeMedida) throws Exception {
        try {
            em.merge(unidadeMedida);
        } catch (Exception ex) {
            throw new Exception("Ouve um error ao Salvar Unidade de Mendida");
        }
    }

    public void excluir(UnidadeMedida unidadeMedida) throws Exception {
        try {
            em.remove(em.find(UnidadeMedida.class, unidadeMedida.getId()));
        } catch (Exception ex) {
            throw new Exception("Ouve um erro ao excluir a Unidade de Medidas.");
        }
    }

    public UnidadeMedida pesquisar(Long id) throws Exception {
        try {
            return em.find(UnidadeMedida.class, id);
        } catch (Exception ex) {
            throw new Exception("Ouve um erro ao pesquisar a Unidade de Medidas.");
        }
    }

    public List<UnidadeMedida> pesquisar(String valorPesquisa) throws Exception {
        try {
            List<UnidadeMedida> listaUnidadeMedida;
            Query consulta = em.createNamedQuery("UnidadeMedida.findByNome");
            consulta.setParameter("nome", valorPesquisa + "%");
            listaUnidadeMedida = consulta.getResultList();
            return listaUnidadeMedida;
        } catch (Exception ex) {
            throw new Exception("Ouve um erro ao pesquisar a Unidade de Medidas.");
        }
    }
}
