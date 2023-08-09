package com.social.tests;


import api.RestResource;
import api.SpecBuilder;
import com.social.modules.CommentsModule;
import com.social.pojo.MainComments;

import io.qameta.allure.*;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;
import utils.Constants;
import utils.Status;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class CommentTest extends BaseTest {

    @Epic("Social Network Comment")
    @Story("Comment Module:SN-1801")
    @Link("https://socialnetworkcloud.atlassian.net/browse/SN-1801")
    @Description("verifying the user is able to comment")
    @Owner("Mohammed Haseeb")
    @Test
    public void verifyUserAbleToCommentTest(){
        MainComments mainComments = new MainComments(Integer.parseInt(properties.getProperty("postId")),
                properties.getProperty("name"),
                properties.getProperty("email"), properties.getProperty("body"));
       Response response = RestResource.post(mainComments, Constants.COMMENTS_ENDPOINT);

        //Deserialize concept using pojo classes
        MainComments actualResponse = response.as(MainComments.class);
        assertThat(response.getStatusCode(),equalTo(Status.CREATED_201));
        //verifying the response time of api
        assertThat(SpecBuilder.responseTimeValidation(response, properties.getProperty("response")), Matchers.is(true));

        assertThat(actualResponse.getEmail(), equalTo( properties.getProperty("email")));
        assertThat(actualResponse.getName(), equalTo(properties.getProperty("name")));
        assertThat(actualResponse.getPostId(), is(Integer.parseInt(properties.getProperty("postId"))));
        assertThat(actualResponse.getId(), is(notNullValue()));
    }

    @Epic("Social Network Comment")
    @Story("Comment Module:SN-1802")
    @Link("https://socialnetworkcloud.atlassian.net/browse/SN-1802")
    @Description("verifying the user is able to verify details for single comment")
    @Owner("Mohammed Haseeb")
    @Test
    public void verifySingleCommentTest(){
        Response response = RestResource.get(Constants.COMMENTS_ENDPOINT + "/"+ properties.getProperty("commentId"));

        CommentsModule commentsModule = new CommentsModule();
        assertThat(response.getStatusCode(), equalTo(Status.SUCCESS_200));
        //verifying the response time of api
        assertThat(SpecBuilder.responseTimeValidation(response, properties.getProperty("response")), Matchers.is(true));

        commentsModule.verifySingleCommentDetails(response,Integer.parseInt(properties.getProperty("commentId")));

    }


    @Epic("Social Network Comment")
    @Story("Comment Module:SN-1802")
    @Link("https://socialnetworkcloud.atlassian.net/browse/SN-1802")
    @Description("verifying negative scenario no comment with invalid end point")
    @Owner("Mohammed Haseeb")
    @Test
    public void verifyNoCommentAddedTest(){
        //serialize using pojo  classes
        MainComments mainComments = new MainComments(Integer.parseInt(properties.getProperty("postId")),
                properties.getProperty("name"),
                properties.getProperty("email"), properties.getProperty("body"));
        Response response = RestResource.post(mainComments, properties.getProperty("basePathInvalid"));

        assertThat(response.getStatusCode(), equalTo(Status.NOT_FOUND_ERROR));
    }
}
