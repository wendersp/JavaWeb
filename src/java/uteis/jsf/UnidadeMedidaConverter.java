/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uteis.jsf;

import entidade.UnidadeMedida;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import sessionbean.UnidadeMedidaSBean;

/**
 *
 * @author wender
 */
//@FacesConverter("unidadeMedidaConverter")
public class UnidadeMedidaConverter implements Converter {

    private UnidadeMedidaSBean unidadeMedidaSBean;
    private UnidadeMedida unidadeMedida = null;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {

        if (value != null && value.trim().length() > 0) {
            Long id = Long.parseLong(value);
            unidadeMedida = unidadeMedidaSBean.pesquisar(id);
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
