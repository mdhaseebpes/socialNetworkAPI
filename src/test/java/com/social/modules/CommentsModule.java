package com.social.modules;

import com.social.pojo.MainComments;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hamcrest.Matchers;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


public class CommentsModule {

    private static Logger logger = LogManager.getLogger(CommentsModule.class);

    /**
     * This method is used to verify Single comment details by using a comment id
     * @param response : Response in json format
     * @param commentId : comment id  int format ex :10
     * @author : Mohammed Haseeb
     */
    public void verifySingleCommentDetails(Response response, int commentId){
        //Deserialize concept using pojo
        MainComments comments =response.as(MainComments.class);
        logger.info("verifying details of single comment");
        assertThat(comments.getId(), equalTo(commentId));
        assertThat(comments.getPostId(), Matchers.instanceOf(Integer.class));
        assertThat(comments.getName(), not(emptyString()));
        assertThat(comments.getEmail(), not(emptyString()));
        assertThat(comments.getBody(), not(emptyString()));
    }
}
