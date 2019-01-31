package com.prestashop.utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigurationReader {

    private static Properties properties;

    static {
        String path = "configuration.properties";

        try{
            //java cannot read files directly, it needs inputStream to read files.
            //inputStream takes the location of the file as a constructor
            FileInputStream fileInputStream = new FileInputStream(path);

            //Properties is used to read specifically Properties files, files with key value pairs.
            properties = new Properties();

            //file contents are loaded to properties from the inputStream
            properties.load(fileInputStream);

            //all input streams must be closed
            fileInputStream.close();

        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getProperty(String property){
        return properties.getProperty(property);
    }

}
