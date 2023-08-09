package com.social.modules;

import com.social.pojo.users.User;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hamcrest.Matchers;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class UserModule {
    private static Logger logger = LogManager.getLogger(UserModule.class);

    /**
     * This method is used to verify all user details that are retrieving  from get call of users
     * @param response : Response in Json format
     * @author : Mohammed Haseeb
     */
    public void verifyAllUserList(Response response){
        //Deserialize concept using pojo
        User[] users =response.as(User[].class);

        int userList = users.length;
        logger.info("Total number of users - " + userList);
        for(int i=0;i<userList;i++){
            logger.info("user number - " + i );
            assertThat(users[i].getId(), Matchers.instanceOf(Integer.class));
            assertThat(users[i].getName(), not(emptyString()));
            assertThat(users[i].getUsername(), not(emptyString()));
            assertThat(users[i].getEmail(), not(emptyString()));
            assertThat(users[i].getAddress().getStreet(), not(emptyString()));
            assertThat(users[i].getAddress().getGeo().getLat(), not(emptyString()));
            assertThat(users[i].getCompany().getName(), not(emptyString()));
        }
    }

    /**
     * This method is used to verify detail of Single user using user id of User
     * @param response : Response in Json format
     * @param userId : int ex: 10
     * @author : Mohammed Haseeb
     */
    public void verifySingleUserDetails(Response response, int userId){
        //Deserialize concept using pojo
        User user =response.as(User.class);
        logger.info("verifying details of single user");
        assertThat(user.getId(), equalTo(userId));
        assertThat(user.getName(), not(emptyString()));
        assertThat(user.getUsername(), not(emptyString()));
        assertThat(user.getEmail(), not(emptyString()));
        assertThat(user.getAddress().getStreet(), not(emptyString()));
        assertThat(user.getAddress().getGeo().getLat(), not(emptyString()));
        assertThat(user.getCompany().getName(), not(emptyString()));
    }

}
