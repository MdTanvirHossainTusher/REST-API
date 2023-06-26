package framework.utils;

import aquality.selenium.core.logging.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import testing.utils.FileManager;
import java.io.FileReader;

public class TestDataUtils {
    private static JSONObject jsonData;

    private TestDataUtils(){}

    public static JSONObject readJsonFile(String filename){
        try {
            JSONParser parser = new JSONParser();
            FileReader fileReader = new FileReader(filename);
            Object obj = parser.parse(fileReader);
            jsonData = (JSONObject) obj;
            fileReader.close();
        } catch (Exception e) {
            Logger.getInstance().error(e.getMessage());
            e.printStackTrace();
        }
        return jsonData;
    }

    public static JSONObject readTestJsonFile() {
        return readJsonFile(FileManager.TEST_DATA_FILE_PATH);
    }

    public static JSONObject readUserJsonFile() {
        return readJsonFile(FileManager.USERS_TEST_DATA_FILE_PATH);
    }

    public static JSONObject readPostJsonFile() {
        return readJsonFile(FileManager.POSTS_TEST_DATA_FILE_PATH);
    }
}
