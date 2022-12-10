package utils;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utils.model.UserLogin;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;

public class UserActions {

    private static final String BASE_URL = "https://stellarburgers.nomoreparties.site/";
    private static final String AUTH_PATH = "api/auth/login";
    private static final String DELETE_PATH = "api/auth/user";


    private static RequestSpecification getSpecs(){
        return new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .setBaseUri(BASE_URL)
                .build();
    }

    public static void deleteUser(String email, String password){
        UserLogin user = new UserLogin(email, password);
        Response response =  given()
                .spec(getSpecs())
                .body(user)
                .when()
                .post(AUTH_PATH);
        if (response.then().extract().body().path("success")){
            String authToken = response.then().extract().body().path("accessToken");
            given().spec(getSpecs())
                    .header("Authorization", authToken)
                    .when().delete(DELETE_PATH);
        }
    }
}
