package com.jini.zendesk.util;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;

public class Utils {
    public static File getFileFromResource(String fileName) throws URISyntaxException {
        ClassLoader classLoader = Utils.class.getClassLoader();
        URL resource = classLoader.getResource(fileName);
        if (resource == null) {
            System.out.println("Filename ::"+fileName+":: was not found. Please check if the file name is correct");
            throw new IllegalArgumentException("file not found! " + fileName);
        } else {
            return new File(resource.toURI());
        }
    }
}
