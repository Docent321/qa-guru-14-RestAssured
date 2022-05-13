package tests;

import io.qameta.allure.Owner;
import models.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static spec.Spec.*;
import static org.assertj.core.api.Assertions.assertThat;

public class RestAssuredTest extends TestBase {

    @Test
    @Tag("api")
    @Owner("AlexDonskov")
    @DisplayName("Вызов пользователя")
    void singlUserTest() {
        given()
                .spec(request)
                .when()
                .get("/users/2")
                .then()
                .spec(responseSpec200)
                .body("data.first_name", is("Janet"))
                .log().body();
    }

    @Test
    @Tag("api")
    @Owner("AlexDonskov")
    @DisplayName("Создание пользователя")
    void createTest() {
        CredentialsCreateModel credentialsCreateModel = new CredentialsCreateModel();
        credentialsCreateModel.setName("morpheus");
        credentialsCreateModel.setJob("jobleader");

        GenerateCreateResponse UserResponse =
                given()
                        .spec(request)
                        .body(credentialsCreateModel)
                        .when()
                        .post("/users")
                        .then()
                        .spec(responseSpec201)
                        .extract().as(GenerateCreateResponse.class);

        assertThat(UserResponse.getName()).isEqualTo("morpheus");
        assertThat(UserResponse.getJob()).isEqualTo("jobleader");
        assertThat(UserResponse.getId()).hasSizeGreaterThan(1);
        assertThat(UserResponse.getCreatedAt()).hasSizeGreaterThan(10);
    }

    @Test
    @Tag("api")
    @Owner("AlexDonskov")
    @DisplayName("Изменение пользователя")
    void updateTest() {
        CredentialsUpdateModel credentialsUpdateModel = new CredentialsUpdateModel();
        credentialsUpdateModel.setName("morpheus");
        credentialsUpdateModel.setJob("zion resident");

        GenerateUpdateResponse UpdateResponse =
                given()
                        .spec(request)
                        .body(credentialsUpdateModel)
                        .when()
                        .put("/users")
                        .then()
                        .spec(responseSpec200)
                        .extract().as(GenerateUpdateResponse.class);

        assertThat(UpdateResponse.getName()).isEqualTo("morpheus");
        assertThat(UpdateResponse.getJob()).isEqualTo("zion resident");
        assertThat(UpdateResponse.getUpdatedAt()).hasSizeGreaterThan(10);
    }

    @Test
    @Tag("api")
    @Owner("AlexDonskov")
    @DisplayName("Удаление пользователя")
    void deleteTest() {
        given()
                .spec(request)
                .when()
                .delete("/users/2")
                .then()
                .spec(responseSpec204)
                .log().body();
    }

    @Test
    @Tag("api")
    @Owner("AlexDonskov")
    @DisplayName("Регистрация пользователя")
    void registerSuccessfulTest() {
        CredentialsRegisterModel credentialsRegisterModel = new CredentialsRegisterModel();
        credentialsRegisterModel.setEmail("eve.holt@reqres.in");
        credentialsRegisterModel.setPassword("pistol");

        GenerateRegisterResponse RegisterResponse =
                given()
                        .spec(request)
                        .body(credentialsRegisterModel)
                        .when()
                        .post("/register")
                        .then()
                        .spec(responseSpec200)
                        .extract().as(GenerateRegisterResponse.class);

        assertThat(RegisterResponse.getId()).isEqualTo(4);
        assertThat(RegisterResponse.getToken()).hasSizeGreaterThan(10);
    }
}
