package utils;

public interface Status {

    int SUCCESS_200 = 200;

    int CREATED_201 = 201;
    String INTERNAL_SERVER_ERROR = "500: Internal Server Error";
    int INTERNAL_SERVER_STATUS = 500;

    String BAD_REQUEST_ERROR = "BAD_USER_INPUT";
    int BAD_CLIENT_STATUS = 400;

    int NOT_FOUND_ERROR = 404;

    String UNAUTHORIZED_401_ERROR = "Request failed with status code 401";
}
