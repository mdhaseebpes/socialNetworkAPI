package utils;

import com.github.javafaker.Faker;

public class FakerUtils {

    /**
     * This method is used for generating dynamic data for description
     * @return : String
     * @author : Mohammed Haseeb
     */
    public static String generateDescription(){
        Faker faker = new Faker();
        return "Description " + faker.regexify("[ A-Za-z0-9]{25}");
    }

    /**
     * This method is used for generating dynamic data for name
     * @return : String
     * @author: Mohammed Haseeb
     */
    public static String generateName(){
        Faker faker = new Faker();
        return faker.regexify("[a-z]{7}");
    }

    /**
     * This method is used for generating dynamic id
     * @return : String
     * @author : Mohammed Haseeb
     */
    public static String generateId(){
        Faker faker = new Faker();
        return faker.regexify("[0-9]{2}");
    }
}
