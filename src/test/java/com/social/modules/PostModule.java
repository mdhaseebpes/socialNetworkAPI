package com.social.modules;

import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.asserts.SoftAssert;
import utils.FakerUtils;

import java.util.ArrayList;
import java.util.LinkedHashMap;


public class PostModule {

    private static Logger logger = LogManager.getLogger(PostModule.class);

    /**
     * This method is used to build post request body using LinkedHashMap
     * @return : LinkedHashMap<String, Object>
     * @author: Mohammed Haseeb
     */
    public LinkedHashMap<String, Object> postContentUsingMap(){
        LinkedHashMap<String,Object> postMap = new LinkedHashMap<>();
        postMap.put("userId",Integer.parseInt(FakerUtils.generateId()));
        postMap.put("title", FakerUtils.generateName());
        postMap.put("body",FakerUtils.generateDescription());
        return postMap;
    }


    /**
     * This method is used to verify all post response body retrieving from get call
     * @param response : Response in Json format
     * @author : Mohammed Haseeb
     */
    public void verifyGetAllPost(Response response){
        SoftAssert softAssert = new SoftAssert();
        ArrayList<String> posts = response.path("");
        logger.info("Total number of posts " + posts.size());
        for(int i=0;i<posts.size();i++){
            softAssert.assertTrue(response.path("[" + i + "].userId") != null);
            softAssert.assertTrue(response.path("[" + i + "].id") != null);
            softAssert.assertTrue(response.path("[" + i + "].title") != null);
            logger.info("Title is not null for post number - " + i);
            softAssert.assertTrue(response.path("[" + i + "].body") != null);
            logger.info("Body is not null for post number - " + i);
        }
        softAssert.assertAll();
    }
}
