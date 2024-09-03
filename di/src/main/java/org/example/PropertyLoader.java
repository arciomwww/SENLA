package org.example;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PropertyLoader {
    private static final Logger logger = Logger.getLogger(PropertyLoader.class.getName());

    public static Map<String, String> loadProperties(String fileName) {
        Properties properties = new Properties();
        Map<String, String> propertiesMap = new HashMap<>();
        try (FileInputStream fis = new FileInputStream(fileName)) {
            properties.load(fis);
            for (String name : properties.stringPropertyNames()) {
                propertiesMap.put(name, properties.getProperty(name));
            }
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Failed to load properties from file: " + fileName, e);
        }
        return propertiesMap;
    }
}
