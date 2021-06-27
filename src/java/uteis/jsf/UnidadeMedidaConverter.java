package uteis.jsf;

import entidade.UnidadeMedida;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import sessionbean.UnidadeMedidaSBean;

/**
 *
 * @author wender
 */
@FacesConverter("unidadeMedidaConverter")
public class UnidadeMedidaConverter implements Converter {

    private UnidadeMedidaSBean unidadeMedidaSBean;
    private UnidadeMedida unidadeMedida = null;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        
        if (value != null && value.trim().length() > 0) {
            Long id = Long.parseLong(value);
            try {
            unidadeMedida = unidadeMedidaSBean.pesquisar(id);
            } catch(Exception ex){}
        }
        return unidadeMedida;
    }

    @Override
    public String getAsString(FacesContext arg0, UIComponent arg1, Object value) {
        if (value != null) {
            unidadeMedida = (UnidadeMedida) value;
            return unidadeMedida.getId().toString();
        }
        return null;
    }

    public UnidadeMedidaSBean getUnidadeMedidaSBeam() {
        return unidadeMedidaSBean;
    }

    public void setUnidadeMedidaSBean(UnidadeMedidaSBean unidadeMedidaSBean) {
        this.unidadeMedidaSBean = unidadeMedidaSBean;
    }

}
