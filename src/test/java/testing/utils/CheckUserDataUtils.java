package testing.utils;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import framework.utils.ApiUtils;
import framework.utils.JsonDataUtils;
import testing.model.User;
import java.util.ArrayList;

public class CheckUserDataUtils {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    private static User createExpectedUserFromJson() {

        String jsonStr = null;
        try {
            jsonStr = objectMapper.writeValueAsString(JsonDataUtils.getObjectValueByKey("userDetails"));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        try {
            return objectMapper.readValue(jsonStr, User.class);
        } catch (JsonMappingException e) {
            e.printStackTrace();
            return null;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static boolean checkUserData() {

        ArrayList<User> users5 = ApiUtils.getUserInfo((int)(long) JsonDataUtils.getUsersIdByKey("userInfoForUserId"));
        User expectedUser = createExpectedUserFromJson();

        ObjectMapper objectMapper = new ObjectMapper();
        String expectedUserJson = null;
        String actualUserJson = null;

        try {
            expectedUserJson = objectMapper.writeValueAsString(expectedUser);
            actualUserJson = objectMapper.writeValueAsString(users5.get(0));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return expectedUserJson.equals(actualUserJson);
    }
}
