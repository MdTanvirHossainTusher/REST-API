package framework.utils;

import aquality.selenium.core.logging.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import testing.utils.FileManager;
import java.io.FileReader;

public class ConfigDataUtils {
    private static JSONObject jsonData;

    private ConfigDataUtils(){}

    public static JSONObject readConfigJsonFile() {
        if(jsonData != null){
            return jsonData;
        }
        try {
            JSONParser parser = new JSONParser();
            String path = FileManager.CONFIG_DATA_FILE_PATH;
            FileReader fileReader = new FileReader(path);
            Object obj = parser.parse(fileReader);
            jsonData = (JSONObject) obj;
            fileReader.close();
        } catch (Exception e) {
            Logger.getInstance().error(e.getMessage());
            e.printStackTrace();
        }
        return jsonData;
    }
}
