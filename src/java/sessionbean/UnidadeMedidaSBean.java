/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
public class UnidadeMedidaSBean  {

    @PersistenceContext(unitName = "JavaWebPU")
    private EntityManager em;
    
    public void salvar(UnidadeMedida unidadeMedida) {
        em.merge(unidadeMedida);
    }
    
    public void excluir(UnidadeMedida unidadeMedida) {
        em.remove(em.find(UnidadeMedida.class, unidadeMedida.getId()));
    }
    
    public UnidadeMedida pesquisar(Long id) {
        return em.find(UnidadeMedida.class, id);        
    }
    
    public List<UnidadeMedida> pesquisar(String valorPesquisa) {
        List<UnidadeMedida> listaUnidadeMedida;
        Query consulta = em.createNamedQuery("UnidadeMedida.findByNome");
        consulta.setParameter("nome", valorPesquisa + "%");
        listaUnidadeMedida = consulta.getResultList();
        return listaUnidadeMedida;
    }
}
