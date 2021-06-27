package sessionbean;

import entidade.PerfilUsuario;
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
public class PerfilUsuarioSBean {

    @PersistenceContext(unitName = "JavaWebPU")
    private EntityManager em;

    public void salvar(PerfilUsuario perfilUsuario) throws Exception {
        try {
            em.merge(perfilUsuario);
        } catch (Exception ex) {
            throw new Exception("Ouve um error ao Salvar o Peril do Usuario.");
        }
    }

    public void excluir(PerfilUsuario perfilUsuario) throws Exception {
        try {
            em.remove(em.find(PerfilUsuario.class, perfilUsuario.getId()));
        } catch (Exception ex) {
            throw new Exception("Ouve um erro ao excluir o Peril do Usuario.");
        }
    }

    public PerfilUsuario pesquisar(Long id) throws Exception {
        try {
            return em.find(PerfilUsuario.class, id);
        } catch (Exception ex) {
            throw new Exception("Ouve um erro ao pesquisar o Peril do Usuario.");
        }
    }

    public List<PerfilUsuario> pesquisar(String valorPesquisa) throws Exception {
        try {
            List<PerfilUsuario> listaPerfilUsuario;
            Query consulta = em.createNamedQuery("PerfilUsuario.findByNome");
            consulta.setParameter("nome", valorPesquisa + "%");
            listaPerfilUsuario = consulta.getResultList();
            return listaPerfilUsuario;
        } catch (Exception ex) {
            throw new Exception("Ouve um erro ao pesquisar o Peril do Usuario.");
        }
    }
}
