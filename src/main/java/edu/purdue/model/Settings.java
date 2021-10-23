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
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("<settings loaded from " + f.getAbsolutePath() + ">");
        print();
    }

    private void print() {
        System.out.println("difficulty" + " = " + settings.getProperty("difficulty"));
    }

    public String getSetting(String setting) {
        return settings.getProperty(setting);
    }
}
