package framework.utils;

import org.json.simple.JSONObject;

public class JsonDataUtils {

    public static String getUrl(String key){
        return (String) ConfigDataUtils.readConfigJsonFile().get(key);
    }

    public static String getConfigStringValueByKey(String key) {
        return getUrl(key);
    }

    public static String getStringValueByKey(String key) {
        return (String) TestDataUtils.readTestJsonFile().get(key);
    }

    public static int getIntValueByKey(String key) {
        return (int)(long) TestDataUtils.readTestJsonFile().get(key);
    }

    public static int getUsersIdByKey(String key) {
        return (int)(long) TestDataUtils.readUserJsonFile().get(key);
    }

    public static int getPostsIdByKey(String key) {
        return (int)(long) TestDataUtils.readPostJsonFile().get(key);
    }

    public static JSONObject getObjectValueByKey(String key){
        return (JSONObject) TestDataUtils.readTestJsonFile().get(key);
    }

}
