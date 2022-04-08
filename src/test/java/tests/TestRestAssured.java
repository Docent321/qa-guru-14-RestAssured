package tests;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.is;

public class TestRestAssured {

   @Test
   void singlUserTest(){
        given()
                .when()
                .get("https://reqres.in/api/users/2")
                .then()
                .body("data.first_name", is("Janet"));
   }


    String inputDataCreate = "{\"name\": \"morpheus\", " +
            "\"job\": \"leader\"}";
    @Test
    void createTest(){
        given()
                .body(inputDataCreate)
                .contentType(JSON)
                .when()
                .post("https://reqres.in/api/users")
                .then()
                .body("name", is("morpheus"));
    }



    String inputDataUpdate = "{\"name\": \"morpheus\", " +
            "\"job\": \"zion resident\"}";
    @Test
    void updateTest(){
        given()
                .body(inputDataUpdate)
                .contentType(JSON)
                .when()
                .put("https://reqres.in/api/users")
                .then()
                .body("job", is("zion resident"));
    }

    @Test
    void deleteTest(){
        given()
                .when()
                .delete("https://reqres.in/api/users/2")
                .then()
                .statusCode(204);
    }


    String inputDataRegister = "{\"email\": \"eve.holt@reqres.in\", " +
            "\"password\": \"pistol\"}";
    @Test
    void registerSuccessfulTest(){
        given()
                .body(inputDataRegister)
                .contentType(JSON)
                .when()
                .post("https://reqres.in/api/register")
                .then()
                .body("token", is("QpwL5tke4Pnpja7X4"));
    }



}
