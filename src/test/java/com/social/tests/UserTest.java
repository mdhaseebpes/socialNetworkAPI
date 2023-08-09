package com.social.tests;

import api.RestResource;
import api.SpecBuilder;
import com.social.modules.UserModule;
import io.qameta.allure.*;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;
import utils.Constants;
import utils.Status;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class UserTest extends  BaseTest{

    @Epic("Social Network User")
    @Story("User Module:SN-1806")
    @Link("https://socialnetworkcloud.atlassian.net/browse/SN-1806")
    @Description("verifying user is able to retrieve all details of users and validate it ")
    @Owner("Mohammed Haseeb")
    @Test
    public void verifyUserAbleToGetAllUsers(){
       Response response = RestResource.get(Constants.USERS_ENDPOINT);

        assertThat(response.getStatusCode(), equalTo(Status.SUCCESS_200));
        //verifying the response time of api
        assertThat(SpecBuilder.responseTimeValidation(response, properties.getProperty("response")), Matchers.is(true));

        //Deserialize  concept using pojo
        UserModule userModule = new UserModule();
        userModule.verifyAllUserList(response);
    }

    @Epic("Social Network User")
    @Story("User Module:SN-1807")
    @Link("https://socialnetworkcloud.atlassian.net/browse/SN-1807")
    @Description("verifying user is able to retrieve single user details and validate it")
    @Owner("Mohammed Haseeb")
    @Test
    public void verifySingleUserDetailTest(){
       Response response = RestResource.get(Constants.USERS_ENDPOINT+"/"+properties.getProperty("userId"));

        assertThat(response.getStatusCode(), equalTo(Status.SUCCESS_200));
        //verifying the response time of api
        assertThat(SpecBuilder.responseTimeValidation(response, properties.getProperty("response")), Matchers.is(true));

        //Deserialize  concept using pojo
        UserModule userModule = new UserModule();
        userModule.verifySingleUserDetails(response,Integer.parseInt(properties.getProperty("userId")));
    }

    @Epic("Social Network User")
    @Story("User Module:SN-1807")
    @Link("https://socialnetworkcloud.atlassian.net/browse/SN-1807")
    @Description("verifying negative scenario - providing invalid user id and validate status code")
    @Owner("Mohammed Haseeb")
    @Test
    public void verifyInvalidUserTest(){
        Response  response = RestResource.get(Constants.USERS_ENDPOINT+"/10001");

        assertThat(response.getStatusCode(), equalTo(Status.NOT_FOUND_ERROR));
    }
}
