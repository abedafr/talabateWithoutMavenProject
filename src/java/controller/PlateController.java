package controller;

import bean.Cuisine;
import bean.Menu;
import bean.Plate;
import controller.util.JsfUtil;
import controller.util.JsfUtil.PersistAction;
import service.PlateFacade;

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
import service.CuisineFacade;
import service.MenuFacade;

@Named("plateController")
@SessionScoped
public class PlateController implements Serializable {

    @EJB
    private service.PlateFacade ejbFacade;
    @EJB
    private MenuFacade menuFacade;
    @EJB
    private CuisineFacade cuisineFacade;
    private List<Plate> items = null;
    private Plate selected;

    public PlateController() {
    }

    public Plate getSelected() {
        if (selected == null) {
            selected = new Plate();
        }
        return selected;
    }

    public void setSelected(Plate selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private PlateFacade getFacade() {
        return ejbFacade;
    }

    public Plate prepareCreate() {
        selected = new Plate();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("PlateCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("PlateUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("PlateDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Plate> getItems() {
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
                    Cuisine cuisine = selected.getCuisine();
                    Menu menu = selected.getMenu();
                    if (!cuisine.getMenus().contains(menu)) {
                        cuisine.getMenus().add(menu);
                    }
                    if (!menu.getCuisines().contains(cuisine)) {
                        menu.getCuisines().add(cuisine);
                    }
                    cuisineFacade.edit(cuisine);
                    menuFacade.edit(menu);
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

    public Plate getPlate(java.lang.Long id) {
        return getFacade().find(id);
    }

    public List<Plate> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Plate> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Plate.class)
    public static class PlateControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            PlateController controller = (PlateController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "plateController");
            return controller.getPlate(getKey(value));
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
            if (object instanceof Plate) {
                Plate o = (Plate) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Plate.class.getName()});
                return null;
            }
        }

    }

}
