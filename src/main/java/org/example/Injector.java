package org.example;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Properties;

/***
 * A class that performs dependency injection on any object containing fields marked with the @AutoInjectable annotation.
 */
public class Injector {
    /***
     * method for getting properties from a properties file with the specified name.
     * @param fileName properties file name.
     * @return - names of the required interface implementations
     * @throws IOException throws an I/O exception if the file is not found
     */
    private static Properties getProperties(String fileName) throws IOException {
        FileInputStream input;
        try {
            input = new FileInputStream("src/main/resources/" + fileName + ".properties");

            Properties properties = new Properties();
            properties.load(input);
            return properties;
        }
        catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /***
     * The method takes an arbitrary object, examines the fields that exist in it, and looks to see if they are
     * annotated with the required @AutoIjectable annotation. If so, then it looks at the type of this field and
     * looks for the implementation in the properties file. After that, it creates an instance of the desired class
     * and writes a link to this instance in the required field.
     * @param object arbitrary object
     * @return the same object, with the fields changed
     * @param <T> custom class template
     */
    public <T> T inject(T object, String fileName) {
        try {
            Properties properties = getProperties(fileName);

            Class<?> objClass = object.getClass();
            for (Field field : objClass.getDeclaredFields()) {
                if (field.isAnnotationPresent(AutoInjectable.class)) {
                    Class<?> fieldType = field.getType();
                    String interfaceName = fieldType.getName();

                    String implName = properties.getProperty(interfaceName);
                    if (implName != null) {
                        Object implInstance = Class.forName(implName).newInstance();
                        field.setAccessible(true);
                        field.set(object, implInstance);
                    }
                }
            }

            return object;

        } catch (IOException | IllegalAccessException | InstantiationException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

}
