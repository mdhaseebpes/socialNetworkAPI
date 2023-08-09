package api;

import io.restassured.response.Response;

import static api.SpecBuilder.getRequestSpec;
import static api.SpecBuilder.getResponseSpec;
import static io.restassured.RestAssured.given;

public class RestResource {

    /**
     * This method is used to call POST API and return the response by creating new resource
     * @param requestPayLoad : Payload in Json format
     * @param basePath : endpoint of the resource
     * @return : Response
     * @author : Mohammed Haseeb
     */

    public static Response post(Object requestPayLoad, String basePath) {
        return given(getRequestSpec()).
                body(requestPayLoad)
                .when()
                .post(basePath)
                .then().spec(getResponseSpec()).extract().response();
    }


    /**
     * This method is used to call GET API and return the response by fetching details from resource
     * @param basePath : endPoint of the resource
     * @return : Response
     * @author : Mohammed Haseeb
     */
    public static Response get(String basePath) {
        return given(getRequestSpec())
                .when()
                .get(basePath)
                .then().spec(getResponseSpec()).extract().response();
    }

    /**
     * This method is used to call Update API and return the response by updating the resource
     * @param requestPayLoad : Payload in Json format
     * @param basePath :endPoint of the resource
     * @return : Response
     * @author : Mohammed Haseeb
     */
    public static Response update(Object requestPayLoad, String basePath) {
        return given(getRequestSpec()).
                body(requestPayLoad)
                .when()
                .put(basePath)
                .then().spec(getResponseSpec()).extract().response();
    }


    /**
     * This method is used to call delete API and return the response by deleting the resource
     * @param basePath : endPoint of resource that's should be deleted
     * @return : Response
     * @author :  Mohammed Haseeb
     */
    public static Response delete( String basePath) {
        return given(getRequestSpec())
                .when()
                .delete(basePath)
                .then().spec(getResponseSpec()).extract().response();
    }


}
