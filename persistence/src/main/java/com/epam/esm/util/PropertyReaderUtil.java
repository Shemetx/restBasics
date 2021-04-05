package com.epam.esm.util;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class PropertyReaderUtil {

    private static final Properties properties = new Properties();

    private PropertyReaderUtil() {
    }
    public static Properties getInstance() {
        return properties;
    }

    public static void loadProperties() throws IOException {
        final String propertiesFileName = "C:/mjc/module #2. REST API Basics/SpringAppWeb/web/src/main/resources/application.properties";
        try (InputStream inputStream = new FileInputStream(propertiesFileName)) {
            properties.load(inputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static String url() { return properties.getProperty("url");}
    public static String dbName() {return  properties.getProperty("dbname");}
    public static String user() { return properties.getProperty("user");}
    public static String password() {return properties.getProperty("password");}
    public static String availableConnections() {return properties.getProperty("availableConnections");}
}