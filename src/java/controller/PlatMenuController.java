package controller;

import bean.PlatMenu;
import bean.Plate;
import bean.Supplement;
import bean.SupplementPlat;
import controller.util.JsfUtil;
import controller.util.JsfUtil.PersistAction;
import service.PlatMenuFacade;

import java.io.Serializable;
import java.util.ArrayList;
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

@Named("platMenuController")
@SessionScoped
public class PlatMenuController implements Serializable {

    @EJB
    private service.PlatMenuFacade ejbFacade;
    private List<PlatMenu> items = null;
    private List<Supplement> supplements = null;
    private PlatMenu selected;

    public PlatMenuController() {
    }

    public PlatMenu getSelected() {
        return selected;
    }

    public void setSelected(PlatMenu selected) {
        this.selected = selected;
    }

    public void saveSupplements() {
        System.out.println("Sups +" + supplements);
        if (supplements != null) {
            List<SupplementPlat> list = new ArrayList();
            for (Supplement item : supplements) {
                SupplementPlat supplementPlat = new SupplementPlat(0D, selected, item);
                list.add(supplementPlat);
            }
            System.out.println("SupPlats " + list);
            selected.setSupplementPlats(list);
        }
    }

    public List<Supplement> getSupplements() {
        if (supplements == null) {
            supplements = new ArrayList<>();
        }
        return supplements;
    }

    public void setSupplements(List<Supplement> supplements) {
        this.supplements = supplements;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private PlatMenuFacade getFacade() {
        return ejbFacade;
    }

    public List<Plate> plateByCuisine() {
        if (selected != null && selected.getCuisine() != null) {
            return ejbFacade.getPlateByCuisine(selected.getCuisine());
        } else {
            return new ArrayList<>();
        }
    }

    public PlatMenu prepareCreate() {
        selected = new PlatMenu();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("PlatMenuCreated"));
        if (!JsfUtil.isValidationFailed()) {
            supplements = null;
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("PlatMenuUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("PlatMenuDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<PlatMenu> getItems() {
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
                    System.out.println("1//////////");
                    System.out.println("2///"+ selected.getMenu());
                    getFacade().addPlateMenuCuisine(selected.getMenu(), selected.getCuisine());
                    saveSupplements();
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
                if (msg == null) {
                    msg = "";
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

    public PlatMenu getPlatMenu(java.lang.Long id) {
        return getFacade().find(id);
    }

    public List<PlatMenu> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<PlatMenu> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = PlatMenu.class)
    public static class PlatMenuControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            PlatMenuController controller = (PlatMenuController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "platMenuController");
            return controller.getPlatMenu(getKey(value));
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
            if (object instanceof PlatMenu) {
                PlatMenu o = (PlatMenu) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), PlatMenu.class.getName()});
                return null;
            }
        }

    }

}
