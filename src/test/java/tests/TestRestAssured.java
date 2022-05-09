package tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static tests.spec.Spec.*;

public class TestRestAssured extends TestBase {

    @DisplayName("Вызов пользователя")
    @Test
    void singlUserTest(){
        given()
                .spec(request)
                .when()
                .get("/users/2")
                .then()
                .spec(responseSpec200)
                .body("data.first_name", is("Janet"))
                .log().body();
    }


    @DisplayName("Создание юзера, надо вынести данные в отдельный файл")
    @Test
    void createTest(){
        String inputDataCreate = "{\"name\": \"morpheus\", " +
                "\"job\": \"jobleader\"}";
        given()
                .spec(request)
                .body(inputDataCreate)
                .when()
                .post("/users")
                .then()
                .spec(responseSpec201)
                .body("name", is("morpheus"))
                .log().body();;
    }


    @DisplayName("Изменение пользователя, надо вынести данные в отдельный файл")
    @Test
    void updateTest(){
        String inputDataUpdate = "{\"name\": \"morpheus\", " +
                "\"job\": \"zion resident\"}";
        given()
                .spec(request)
                .body(inputDataUpdate)
                .when()
                .put("/users")
                .then()
                .spec(responseSpec200)
                .body("job", is("zion resident"))
                .log().body();
    }


    @DisplayName("Удаление пользователя")
    @Test
    void deleteTest(){
        given()
                .spec(request)
                .when()
                .delete("/users/2")
                .then()
                .spec(responseSpec204)
                .log().body();
    }



    @DisplayName("Регистрация пользователя, надо вынести данные в отдельный файл")
    @Test
    void registerSuccessfulTest(){
        String inputDataRegister = "{\"email\": \"eve.holt@reqres.in\", " +
                "\"password\": \"pistol\"}";
        given()
                .spec(request)
                .body(inputDataRegister)
                .when()
                .post("/register")
                .then()
                .spec(responseSpec200)
                .body("token", is("QpwL5tke4Pnpja7X4"))
                .log().body();
    }
}
