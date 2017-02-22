
import java.util.Enumeration;
import java.util.ResourceBundle;
import javax.faces.context.FacesContext;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Abed
 */
public class BundleExtender extends ResourceBundle {

    public BundleExtender() {
        setParent(getBundle("my.package.resources.MyText", FacesContext.getCurrentInstance()
                .getViewRoot().getLocale()));
    }

    @Override
    public Enumeration<String> getKeys() {
        return parent.getKeys();
    }

    @Override
    protected Object handleGetObject(String key) {
        return key;
        //The code below will try to turn the annoying ???some_key??? 
        //into "some key" (looks better)
        /*try {
            return parent.getObject(key);
        } catch (MissingResourceException e) {
            if (!StringUtils.isEmpty(key)) {
                logger.error("Missing key: " + key + " in the properties", e);
                return key.replace("_", " ");
            } else {
                logger.error("Key was null???", e);
                return "";
            }
        }*/
    }
}