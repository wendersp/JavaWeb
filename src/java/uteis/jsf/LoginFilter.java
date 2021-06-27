package uteis.jsf;

import entidade.Usuario;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author wender
 */
public class LoginFilter implements Filter {

    
    private final static String FILTER_APPLIED = "_security_filter_applied";
    
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig); 
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest hRequest = (HttpServletRequest)request;
        HttpServletResponse hResponse = (HttpServletResponse)response;
        HttpSession session = hRequest.getSession();
        
        String paginaAtual = new String(hRequest.getRequestURL());
        
        if ((request.getAttribute(FILTER_APPLIED) == null) && (!paginaAtual.endsWith("login.xhtml"))
                && (paginaAtual.endsWith(".xhtml"))) {
                request.setAttribute(FILTER_APPLIED, Boolean.TRUE);
                
                Usuario usuarioLogado = null;
                if (((Usuario)session.getAttribute("usuarioLogado")) != null){
                    usuarioLogado = (Usuario)session.getAttribute("usuarioLogado");                                                            
                } 
                if ((usuarioLogado == null) || (usuarioLogado.getId() == null)) {
                    hResponse.sendRedirect("login.xhtml?faces-redirect=true");
                }                    
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        Filter.super.destroy(); //To change body of generated methods, choose Tools | Templates.
    }
    
}
