/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import bean.Adress;
import bean.Quartier;
import bean.Ville;

import controller.util.JsfUtil;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import service.AdressFacade;
import service.QuartierFacade;
import service.VilleFacade;

/**
 *
 * @author hp
 */
@Named(value = "adressController")
@SessionScoped
public class AdressController implements Serializable {

    @EJB
    private AdressFacade ejbFacade;
    private List<Adress> items;
    private Adress selected;

    private Ville ville;
    private Quartier quartier;
    private List<Quartier> quartiers;
    @EJB
    private VilleFacade villeFacade;
    @EJB
    private QuartierFacade quartierFacade;

    public Ville getVille() {
        return ville;
    }

    public void setVille(Ville ville) {
        this.ville = ville;
    }

    public Quartier getQuartier() {
        return quartier;
    }

    public void setQuartier(Quartier quartier) {
        this.quartier = quartier;
    }

    public List<Quartier> getQuartiers() {
        return quartiers;
    }

    public void setQuartiers(List<Quartier> quartiers) {
        this.quartiers = quartiers;
    }

    public VilleFacade getVilleFacade() {
        return villeFacade;
    }

    public void setVilleFacade(VilleFacade villeFacade) {
        this.villeFacade = villeFacade;
    }

    public QuartierFacade getQuartierFacade() {
        return quartierFacade;
    }

    public void setQuartierFacade(QuartierFacade quartierFacade) {
        this.quartierFacade = quartierFacade;
    }
    

    /**
     * Creates a new instance of AdressController
     */
    public AdressController() {
    }

    public AdressFacade getEjbFacade() {
        return ejbFacade;
    }

    public void setEjbFacade(AdressFacade ejbFacade) {
        this.ejbFacade = ejbFacade;
    }

    public List<Adress> getItems() {
        if (items == null) {
            items = getEjbFacade().findAll();
        }
        return items;
    }

    public void setItems(List<Adress> items) {
        this.items = items;
    }

    public Adress getSelected() {
        return selected;
    }

    public void setSelected(Adress selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    public Adress prepareCreate() {
        selected = new Adress();
        initializeEmbeddableKey();
        return selected;

    }

    public void findByVille() {
        quartiers = quartierFacade.findQuartierByVille(ville);
        ville.setQuartiers(quartiers);
    }

    public void create() {
        persist(JsfUtil.PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("ADressCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(JsfUtil.PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("AdressUpdated"));
    }

    public void destroy() {
        persist(JsfUtil.PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("AdressDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    private void persist(JsfUtil.PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != JsfUtil.PersistAction.DELETE) {
                    getEjbFacade().edit(selected);
                } else {
                    getEjbFacade().remove(selected);
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

    public Adress getAdress(java.lang.Long id) {
        return getEjbFacade().find(id);
    }

    public List<Adress> getItemsAvailableSelectMany() {
        return getEjbFacade().findAll();
    }

    public List<Adress> getItemsAvailableSelectOne() {
        return getEjbFacade().findAll();
    }

    @FacesConverter(forClass = Adress.class)
    public static class AdressControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            AdressController controller = (AdressController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "adressController");
            return controller.getAdress(getKey(value));
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
            if (object instanceof Adress) {
                Adress o = (Adress) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Adress.class.getName()});
                return null;
            }
        }

    }

}
