package testing.utils;

import framework.utils.JsonDataUtils;

import java.util.Random;

public class RandomUtils {

    public static String randomString(int length){

        String CHARACTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(CHARACTERS.length());
            char randomChar = CHARACTERS.charAt(index);
            sb.append(randomChar);
        }
        return sb.toString();
    }

    public static String generateRandomTitle() {
        return randomString(JsonDataUtils.getIntValueByKey("titleLength"));
    }

    public static String generateRandomBody() {
        return randomString(JsonDataUtils.getIntValueByKey("bodyLength"));
    }

}
