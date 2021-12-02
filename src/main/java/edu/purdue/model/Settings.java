package edu.purdue.model;

import java.io.*;
import java.util.Properties;

public class Settings {

    private Properties settings;

    public Settings() {
        File f = new File("conf/settings.properties");
        try {
            InputStream is = new FileInputStream(f);
            settings = new Properties();
            settings.load(is);
            is.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("<settings loaded from " + f.getAbsolutePath() + ">");
        print();
    }

    private void print() {
        System.out.println("difficulty" + " = " + settings.getProperty("difficulty"));
        System.out.println("map" + " = " + settings.getProperty("map"));
        System.out.println("headColor" + " = " + settings.getProperty("headColor"));
        System.out.println("bodyColor" + " = " + settings.getProperty("bodyColor"));
        System.out.println("headColor2" + " = " + settings.getProperty("headColor2"));
        System.out.println("bodyColor2" + " = " + settings.getProperty("bodyColor2"));
        System.out.println("defaultStyle" + " = " + settings.getProperty("defaultStyle"));
    }

    public void save() {
        try {
            OutputStream os = new FileOutputStream("conf/settings.properties");
            String music = settings.getProperty("music");
            String effects = settings.getProperty("effects");
            String muteMusic = settings.getProperty("muteMusic");
            String muteEffects = settings.getProperty("muteEffects");
            settings.remove("music");
            settings.remove("effects");
            settings.remove("muteMusic");
            settings.remove("muteEffects");
            settings.store(os, null);
            setSetting("music", music);
            setSetting("effects", effects);
            setSetting("muteMusic", muteMusic);
            setSetting("muteEffects", muteEffects);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getSetting(String value) {
        return settings.getProperty(value);
    }

    public void setSetting(String property, String value) {
        settings.setProperty(property, value);
    }
}
