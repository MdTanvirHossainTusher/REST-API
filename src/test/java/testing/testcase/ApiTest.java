package testing.testcase;

import framework.utils.ApiUtils;
import framework.utils.JsonDataUtils;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.hc.core5.http.HttpStatus;
import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.Test;
import testing.model.PostDetails;
import testing.model.User;
import testing.utils.CheckUserDataUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ApiTest {
    @Test
    public void apiTest(){
        Response response = ApiUtils.sendGetReqForAllPosts();
        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_OK, "Status code is not 200");
        Assert.assertEquals(response.getContentType(), ContentType.JSON.withCharset
                (JsonDataUtils.getStringValueByKey("characterSet")), "The list in response body is not in json.");


        String postId = "id";
        List<Integer> postIds = response.then().extract().path(postId);
        response.then().body(postId, Matchers.hasItems(postIds.toArray()));
        List<Integer> sortedIds = new ArrayList<>(postIds);
        Collections.sort(sortedIds);
        Assert.assertEquals(postIds, sortedIds, "Posts are not sorted in ascending order by id");


        int POST_ID_NO_99 = (int)(long) JsonDataUtils.getPostsIdByKey("correctPostInfoForPostId");
        int USER_ID_NO_10 = (int)(long) JsonDataUtils.getUsersIdByKey("correctPostInfoUserId");

        response = ApiUtils.sendGetReqForSpecificPostId(POST_ID_NO_99);
        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_OK, "Status code is not 200");
        PostDetails postDetails = ApiUtils.postsDetails(response);
        boolean result = postDetails.getUserId() == USER_ID_NO_10 || postDetails.getId() == POST_ID_NO_99
                || !postDetails.getTitle().isEmpty() || !postDetails.getBody().isEmpty();
        Assert.assertTrue(result, "Post information is not correct");


        int POST_ID_NO_150 = (int)(long) JsonDataUtils.getPostsIdByKey("emptyPostId");
        response = ApiUtils.sendGetReqForSpecificPostId(POST_ID_NO_150);
        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_NOT_FOUND, "Status code is not 404");
        Assert.assertEquals(response.getBody().asString(), "{}", "Response body is not empty.");


        int USER_ID_NO_1 = (int)(long) JsonDataUtils.getUsersIdByKey("createPostForUserId");
        response = ApiUtils.sendPostReqForSpecificUserId(USER_ID_NO_1);
        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_CREATED, "Status code is not 201");
        postDetails = ApiUtils.postsDetails(response);
        result = postDetails.getUserId() == USER_ID_NO_1 && postDetails.getId() > 0
                && !postDetails.getTitle().isEmpty() && !postDetails.getBody().isEmpty();
        Assert.assertTrue(result, "Post information is not correct.");


        response = ApiUtils.sendGetReqForAllUsers();
        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_OK, "Status code is not 200");
        Assert.assertEquals(response.getContentType(), ContentType.JSON.withCharset("utf-8"), "The list in response body is not in json.");
        ArrayList<User> users5 = ApiUtils.checkUserData();
        Assert.assertTrue(CheckUserDataUtils.checkUserData(),"User data are not equals to the given data");


        int USER_ID_NO_5 = (int)(long) JsonDataUtils.getUsersIdByKey("userInfoForUserId");
        response = ApiUtils.sendGetReqForSpecificUserId(USER_ID_NO_5);
        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_OK, "Status code is not 200");
        ArrayList<User> usersId5 = ApiUtils.checkUserData(USER_ID_NO_5);
        Assert.assertTrue(users5.equals(usersId5),"User data doesn't match with user data in the previous step");

    }
}
