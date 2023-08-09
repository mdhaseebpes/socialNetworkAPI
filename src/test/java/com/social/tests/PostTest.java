package com.social.tests;


import api.RestResource;
import api.SpecBuilder;
import com.social.modules.PostModule;
import com.social.pojo.MainPost;
import io.qameta.allure.*;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;
import utils.Constants;
import utils.Status;

import java.util.LinkedHashMap;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.is;

public class PostTest extends BaseTest{

    @Epic("Social Network Posts")
    @Story("Post Module:SN-1803")
    @Link("https://socialnetworkcloud.atlassian.net/browse/SN-1803")
    @Description("verifying user is able to post and validate the response")
    @Owner("Mohammed Haseeb")
    @Test
    public void verifyUserAbleToPostTest(){
        PostModule postModule = new PostModule();
        LinkedHashMap<String, Object> postContent = postModule.postContentUsingMap();
        Response response = RestResource.post(postContent, Constants.POST_ENDPOINT);

        //verifying the assertion on the response
        assertThat(response.getStatusCode(),equalTo(Status.CREATED_201));
        //verifying the response time of api
        assertThat(SpecBuilder.responseTimeValidation(response, properties.getProperty("response")), Matchers.is(true));

        assertThat(response.path("userId"), Matchers.lessThan(100));
        assertThat(response.path("title"), Matchers.not(Matchers.emptyString()));
        assertThat(response.path("body"),Matchers.not(Matchers.emptyString()));
        assertThat(response.path("id"),Matchers.is(notNullValue()));
    }

    @Epic("Social Network Posts")
    @Story("Post Module:SN-1804")
    @Link("https://socialnetworkcloud.atlassian.net/browse/SN-1804")
    @Description("verifying user is able retrieve all posts and validate")
    @Owner("Mohammed Haseeb")
    @Test
    public void verifyUserAbleToRetrieveAllPostTest(){
       Response response = RestResource.get(Constants.POST_ENDPOINT);

        PostModule postModule = new PostModule();
        assertThat(response.getStatusCode(),equalTo(Status.SUCCESS_200));
        //verifying the response time of api
        assertThat(SpecBuilder.responseTimeValidation(response, properties.getProperty("response")), Matchers.is(true));

        postModule.verifyGetAllPost(response);
    }

    @Epic("Social Network Posts")
    @Story("Post Module:SN-1805")
    @Link("https://socialnetworkcloud.atlassian.net/browse/SN-1805")
    @Description("verifying user is able to retrieve single post and verify the response details")
    @Owner("Mohammed Haseeb")
    @Test
    public void verifyUserAbleTOGetSinglePostTest(){
       Response response = RestResource.get(Constants.POST_ENDPOINT+"/"+properties.getProperty("validPostId"));

        //Deserialize concept using pojo classes
        MainPost singlePostResponse = response.as(MainPost.class);
        assertThat(response.getStatusCode(),equalTo(Status.SUCCESS_200));
        //verifying the response time of api
        assertThat(SpecBuilder.responseTimeValidation(response, properties.getProperty("response")), Matchers.is(true));

        assertThat(singlePostResponse.getUserId(), is(notNullValue()));
        assertThat(singlePostResponse.getTitle(),is(notNullValue()));
        assertThat(singlePostResponse.getId(), equalTo(Integer.parseInt(properties.getProperty("validPostId"))));
    }
}
