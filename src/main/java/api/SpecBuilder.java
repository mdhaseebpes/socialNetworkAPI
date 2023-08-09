package api;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hamcrest.Matchers;

import java.util.concurrent.TimeUnit;

import static utils.Constants.BASE_URI;


public class SpecBuilder {

    private static Logger logger = LogManager.getLogger(SpecBuilder.class);

    /**
     * @return
     * @author
     */
    public static RequestSpecification getRequestSpec() {
        return new RequestSpecBuilder()
                .setBaseUri(BASE_URI)
                .setContentType(ContentType.JSON)
                .addFilter(new AllureRestAssured())
                .log(LogDetail.ALL)
                .build();
    }

    /**
     * @return
     * @author
     */
    public static ResponseSpecification getResponseSpec() {
        return new ResponseSpecBuilder()
                .log(LogDetail.ALL)
                .build();

    }


    /**
     * This method is used to capture response time of API and perform validation
     * @param response : actual response
     * @param responseTime : long ex: 12
     * @return : boolean true or false
     * @author : Mohammed Haseeb
     */
    public static  boolean responseTimeValidation(Response response , String responseTime){
        long expectedResponse  = Long.parseLong(responseTime);
        boolean flag = false;
        try {
            ValidatableResponse actualResponseTime = response.then();
            long millSeconds  = TimeUnit.SECONDS.toMillis(expectedResponse);
            actualResponseTime.time(Matchers.lessThan(millSeconds));
            logger.info("Response time in seconds : " + actualResponseTime.extract().timeIn(TimeUnit.SECONDS));
            flag = true;
        }catch (Exception ex){
            flag =false;
            logger.info("response time is more than "+responseTime+" seconds");
        }
        return flag;
    }

}
