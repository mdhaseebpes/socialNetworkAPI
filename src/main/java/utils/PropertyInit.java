package utils;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.util.Properties;

public class PropertyInit {

    private static Logger logger = LogManager.getLogger(PropertyInit.class);
    public static Properties prop;


    /**
     * This method is used to initialize  the properties
     * @return properties object
     * @author Mohammed Haseeb
     */
    public static Properties initProp(String filePath) {
        prop = new Properties();
        try {
            FileInputStream file = new FileInputStream(filePath);
            logger.info("Loading properties");
            prop.load(file);
        } catch (IOException e) {
            logger.info(e.getMessage());
        }
        return prop;
    }

    /**
     * This method is used to load the properties from the data or config files
     * @param filepath : pass the file path for data to be loaded and initialize
     * @return : Properties instance
     * @author : Mohammed Haseeb
     */
    public static  Properties propertyLoader(String filepath) {
        Properties properties = new Properties();
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(filepath));
            try {
                properties.load(reader);
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException("failed to load properties file " + filepath);
            }
        }catch (FileNotFoundException e) {
             e.printStackTrace();
             throw new RuntimeException("properties file not found at " + filepath);
         }
        return properties;
    }
}
