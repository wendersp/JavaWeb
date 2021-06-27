package sessionbean;

import entidade.Usuario;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author wender
 */
@Stateless
public class UsuarioSBean {

    @PersistenceContext(unitName = "JavaWebPU")
    private EntityManager em;

    
    public void salvar(Usuario usuario) throws Exception {
        try {
            em.merge(usuario);
        } catch (Exception ex) {
            throw new Exception("Ouve um error ao Salvar o Usuario");
        }
    }

    
    public void excluir(Usuario usuario) throws Exception {
        try {
            em.remove(em.find(Usuario.class, usuario.getId()));
        } catch (Exception ex) {
            throw new Exception("Ouve um erro ao excluir o Usuario.");
        }
    }

    
    public Usuario pesquisar(Long id) throws Exception {
        try {
            return em.find(Usuario.class, id);
        } catch (Exception ex) {
            throw new Exception("Ouve um erro ao pesquisar Usuario.");
        }
    }

    
    public List<Usuario> pesquisar(String valorPesquisa) throws Exception {
        try {
            List<Usuario> listaUsuario;
            Query consulta = em.createNamedQuery("Usuario.findByNome");
            consulta.setParameter("nome", valorPesquisa + "%");
            listaUsuario = consulta.getResultList();
            return listaUsuario;
        } catch (Exception ex) {
            throw new Exception("Ouve um erro ao pesquisar o Usuario.");
        }
    }
    
     public Usuario login(String userName, String senha) throws Exception {
        try {
            Query consulta = em.createNamedQuery("Usuario.login");
            consulta.setParameter("userName", userName);
            consulta.setParameter("senha", senha);
            return (Usuario)consulta.getSingleResult();  
        } catch(NoResultException ex) {
            throw new Exception("Usuario ou senha invalido.");
        } catch (Exception ex) {
            throw new Exception("Erro ao fazer login - " + ex.getMessage());
        }
    }
}
