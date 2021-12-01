package com.helper;

import java.util.Locale;
import java.util.ResourceBundle;

public class TranslateManager {
    static Locale locale;
    static ResourceBundle rb;

    static {
        locale = new Locale("en", "US");
        rb = ResourceBundle.getBundle("com/language/language", locale);
    }

    public static Locale getLocale() {
        return locale;
    }

    public static void setLocale(Locale locale) {
        TranslateManager.locale = locale;
        rb = ResourceBundle.getBundle("com/language/language", locale);
    }

    public static ResourceBundle getRb() {
        return rb;
    }

    public static String getKey(String key) {
        return rb.getString(key);
    }
}
