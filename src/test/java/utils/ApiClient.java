package utils;

import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.apache.http.HttpStatus.*;
import static org.hamcrest.Matchers.*;

public class ApiClient {
    public static final String BASE_URL = "https://stellarburgers.nomoreparties.site";

    @Step("Создать пользователя через API")
    public static String createUser(UserData u) {
        Response r = given()
                .when()
                .contentType(ContentType.JSON)
                .body(u)
                .post(BASE_URL + "/api/auth/register");
        return r.then()
                .statusCode(SC_OK)
                .body("accessToken", notNullValue())
                .extract()
                .path("accessToken");
    }
    @Step("Залогинить пользователя через API")
    public static String loginUser(UserData u) {
        Response r = given()
                .when()
                .contentType(ContentType.JSON)
                .body(Map.of("email",u.getEmail(),"password",u.getPassword()))
                .post(BASE_URL + "/api/auth/login");
        return r.then()
                .statusCode(SC_OK)
                .body("accessToken", notNullValue())
                .extract()
                .path("accessToken");
    }
    @Step("Удалить пользователя через API")
    public static void deleteUser(String accessToken) {
                given()
                .header("Authorization", accessToken)
                .when()
                .delete(BASE_URL + "/api/auth/user/")
                .then()
                .statusCode(SC_ACCEPTED);
    }
}