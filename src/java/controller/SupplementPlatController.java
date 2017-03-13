package controller;

import bean.Plate;
import bean.SupplementPlat;
import controller.util.JsfUtil;
import controller.util.JsfUtil.PersistAction;
import service.SupplementPlatFacade;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@Named("supplementPlatController")
@SessionScoped
public class SupplementPlatController implements Serializable {

    @EJB
    private service.PlateFacade plateFacade;
    @EJB
    private service.SupplementPlatFacade ejbFacade;
    private List<SupplementPlat> items = null;
    private SupplementPlat selected;

    public SupplementPlatController() {
    }

    public SupplementPlat getSelected() {
        if (selected == null) {
            selected = new SupplementPlat();
        }
        return selected;
    }

    public void setSelected(SupplementPlat selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private SupplementPlatFacade getFacade() {
        return ejbFacade;
    }

    public SupplementPlat prepareCreate() {
        selected = new SupplementPlat();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("SupplementPlatCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("SupplementPlatUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("SupplementPlatDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<SupplementPlat> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
                    getFacade().edit(selected);
                } else {
                    getFacade().remove(selected);
                }
                JsfUtil.addSuccessMessage(successMessage);
            } catch (EJBException ex) {
                String msg = "";
                Throwable cause = ex.getCause();
                if (cause != null) {
                    msg = cause.getLocalizedMessage();
                }
                if (msg.length() > 0) {
                    JsfUtil.addErrorMessage(msg);
                } else {
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            }
        }
    }

    public List<Plate> costumePlates() {
        return plateFacade.findCostumePlates();
    }

    public SupplementPlat getSupplementPlat(java.lang.Long id) {
        return getFacade().find(id);
    }

    public List<SupplementPlat> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<SupplementPlat> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = SupplementPlat.class)
    public static class SupplementPlatControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            SupplementPlatController controller = (SupplementPlatController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "supplementPlatController");
            return controller.getSupplementPlat(getKey(value));
        }

        java.lang.Long getKey(String value) {
            java.lang.Long key;
            key = Long.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Long value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof SupplementPlat) {
                SupplementPlat o = (SupplementPlat) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), SupplementPlat.class.getName()});
                return null;
            }
        }

    }

}
