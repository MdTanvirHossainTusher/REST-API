package framework.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import testing.model.User;
import testing.model.PostDetails;
import testing.utils.RandomUtils;
import java.util.ArrayList;
import java.util.List;

public class ApiUtils {

    private static String title, body;
    private static final String apiUrl = JsonDataUtils.getUrl("apiUrl");
    private static final String userEndPoint = JsonDataUtils.getConfigStringValueByKey("usersEndpoint");
    private static final String postEndPoint = JsonDataUtils.getConfigStringValueByKey("postsEndpoint");

    static {
        RestAssured.baseURI = apiUrl;
    }

    public static PostDetails postsDetails(Response response) {

        ObjectMapper objectMapper = new ObjectMapper();
        String json = response.body().asString();
        PostDetails postDetails = null;

        try {
            postDetails = objectMapper.readValue(json, PostDetails.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return postDetails;
    }

    public static Response sendGetReqForAll(String endPoint){
        return RestAssured.
                given().
                when().
                get(endPoint);
    }

    public static Response sendGetReqForSpecific(String endPoint, int id) {
        return RestAssured.
                given().
                when().
                get(String.format("%s%s",endPoint, id));
    }

    public static Response sendGetReqForAllPosts(){

        return sendGetReqForAll(postEndPoint);
    }

    public static Response sendGetReqForAllUsers(){

        return sendGetReqForAll(userEndPoint);
    }

    public static Response sendGetReqForSpecificPostId(int postId) {

        return sendGetReqForSpecific(postEndPoint, postId);
    }
    public static Response sendGetReqForSpecificUserId(int userId) {

        return sendGetReqForSpecific(userEndPoint, userId);
    }

    public static Response sendPostReqForSpecificUserId(int userId){

        title = RandomUtils.generateRandomTitle();
        body = RandomUtils.generateRandomBody();

        JSONObject request = new JSONObject();

        request.put("userId", userId);
        request.put("title", title);
        request.put("body", body);

        return RestAssured.given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(request.toJSONString())
                .post(postEndPoint);
    }

    public static boolean checkSpecificPostInfo(Response response, int expectedUserId, int expectedId){

        PostDetails postDetails = postsDetails(response);
        return postDetails.getUserId() == expectedUserId || postDetails.getId() == expectedId || !title.isEmpty() || !body.isEmpty();
    }

    public static boolean checkSpecificPostInfo(Response response, int expectedUserId){

        PostDetails postDetails = postsDetails(response);
        return postDetails.getUserId() == expectedUserId && postDetails.getId() > 0 && !title.isEmpty() && !body.isEmpty();
    }

    public static ArrayList<User> getUserInfo(int userId){

        Response response = sendGetReqForAllUsers();

        List<User> users = response.jsonPath().getList("", User.class);
        ArrayList<User> userList = new ArrayList<User>();
        userList.addAll(users);

        ArrayList<User> user5 = new ArrayList<>();
        for (User user : users) {
            if (user.getId() == userId) {
                user5.add(user);
            }
        }
        return user5;
    }

    public static ArrayList<User> checkUserData() {
        return getUserInfo((int)(long) JsonDataUtils.getUsersIdByKey("userInfoForUserId"));
    }

    public static ArrayList<User> checkUserData(int userId) {
        return getUserInfo(userId);
    }

}
