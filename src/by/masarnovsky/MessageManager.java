package by.masarnovsky;

import java.util.ResourceBundle;

public class MessageManager {
    private final static ResourceBundle resourseBundle = ResourceBundle.getBundle("by.masarnovsky.properties.messages");

    private MessageManager(){}

    public static String getProperty(String key){
        return resourseBundle.getString(key);
    }
}
