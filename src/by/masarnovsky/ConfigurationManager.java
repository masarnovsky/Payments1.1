package by.masarnovsky;

import java.util.ResourceBundle;

public class ConfigurationManager {
    private static final ResourceBundle resourseBundle = ResourceBundle.getBundle("config.properties");

    private ConfigurationManager() {}

    public static String getProperty(String key){
        return resourseBundle.getString(key);
    }
}
