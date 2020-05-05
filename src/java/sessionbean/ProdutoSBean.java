/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
    
    public void salvar(Produto produto) {        
        em.merge(produto);
    }
    
    public void excluir(Produto produto) {
        em.remove(em.find(Produto.class, produto.getId()));
    }
    
    public Produto pesquisar(Long id) {
        return em.find(Produto.class, id);        
    }
    
    public List<Produto> pesquisar(String valorPesquisa) {
        List<Produto> listaProduto;
        Query consulta = em.createNamedQuery("Produto.findByNome");
        consulta.setParameter("nome", valorPesquisa + "%");
        listaProduto = consulta.getResultList();
        return listaProduto;
    }
}
