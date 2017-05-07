package controller;

import bean.Restaurant;
import controller.util.JsfUtil;
import controller.util.JsfUtil.PersistAction;
import service.RestaurantFacade;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import org.primefaces.event.map.ReverseGeocodeEvent;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

@Named("restaurantController")
@SessionScoped
public class RestaurantController implements Serializable {

    @EJB
    private service.RestaurantFacade ejbFacade;
    private List<Restaurant> items = null;
    private List<Restaurant> results = null;
    private Restaurant selected;
    private String nom;
// map attributes
    private MapModel emptyModel;
    private MapModel revGeoModel;
    private Marker marker;
    private String centerRevGeoMap = "33.53333, -7.58333";

    
    /// Map //*******///
    
    @PostConstruct
    public void init() {
        emptyModel = new DefaultMapModel();
        revGeoModel = new DefaultMapModel();
    }

    public MapModel getEmptyModel() {
        return emptyModel;
    }

    public void setEmptyModel(MapModel emptyModel) {
        this.emptyModel = emptyModel;
    }

    public MapModel getRevGeoModel() {
        if (revGeoModel == null) {
            revGeoModel = new DefaultMapModel();
        }
        return revGeoModel;
    }

    public Marker getMarker() {
        return marker;
    }

    public String getCenterRevGeoMap() {
        return centerRevGeoMap;
    }

    public void onReverseGeocode(ReverseGeocodeEvent event) {
        System.out.println("hada howa mochkil : " + selected);
        ejbFacade.create(selected);
        List<String> addresses = event.getAddresses();
        LatLng coord = event.getLatlng();
        if (addresses != null && !addresses.isEmpty()) {
            centerRevGeoMap = coord.getLat() + "," + coord.getLng();
            revGeoModel.addOverlay(new Marker(coord, addresses.get(0)));
        }

    }
    
    
    public RestaurantController() {
    }


    public Restaurant getSelected() {
        if (selected == null) {
            selected = new Restaurant();
        }
        return selected;
    }

    public String getNom() {
        return nom;
    }
//    public String getRestauNom() {
//        StringBuilder b=new StringBuilder();
//        for (String s:getNoms()) {
//            if (b.length()>0) {
//                b.append(",");
//            }
//            b.append(s);
//        }
//        return b.toString();
//    }
    public String toRestau(Restaurant restaurant){
        selected= restaurant;
        return "/restaurant/ShowRestau.xhtml";
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public List<Restaurant> getResults() {
        if (results == null) {
            results = ejbFacade.findAll();
        }
        return results;
    }

    public void setResults(List<Restaurant> results) {
        this.results = results;
    }

    public void search() {
        results = ejbFacade.search(nom);
    }

    public void setSelected(Restaurant selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private RestaurantFacade getFacade() {
        return ejbFacade;
    }

    public Restaurant prepareCreate() {
        selected = new Restaurant();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("RestaurantCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("RestaurantUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("RestaurantDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Restaurant> getItems() {
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

    public Restaurant getRestaurant(java.lang.Long id) {
        return getFacade().find(id);
    }

    public List<Restaurant> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Restaurant> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Restaurant.class)
    public static class RestaurantControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            RestaurantController controller = (RestaurantController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "restaurantController");
            return controller.getRestaurant(getKey(value));
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
            if (object instanceof Restaurant) {
                Restaurant o = (Restaurant) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Restaurant.class.getName()});
                return null;
            }
        }

    }

}
