package tests;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

import static tests.listeners.CustomAllureListener.withCustomTemplates;

public class TestBase {

    @BeforeAll
    static void beforeAll() {
        RestAssured.filters(withCustomTemplates());
    }

}
