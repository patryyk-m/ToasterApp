package internationalization;

import java.util.Locale;
import java.util.ResourceBundle;
import logging.AssignmentLogger;

public class Internationalization {
    private ResourceBundle res;
    private Locale currentLocale;

    public Internationalization(String language, String country) {
        AssignmentLogger.logConstructor(this);
        this.currentLocale = new Locale(language, country);
        loadResourceBundle();
    }

    //load resource bundle
    private void loadResourceBundle() {
        AssignmentLogger.logMethodEntry(this);
        res = ResourceBundle.getBundle("Locale", currentLocale);
        AssignmentLogger.logMethodExit(this);
    }

    //method to set the locale to a new language
    public void setLocale(String language, String country) {
        AssignmentLogger.logMethodEntry(this);
        this.currentLocale = new Locale(language, country);
        loadResourceBundle();
        AssignmentLogger.logMethodExit(this);
    }

    //method to retrieve a string
    public String getString(String key) {
        return res.getString(key);
    }

    public Locale getCurrentLocale() {
        return currentLocale;
    }
}
