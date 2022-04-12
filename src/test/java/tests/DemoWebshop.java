package tests;

import org.junit.jupiter.api.Test;
import static org.hamcrest.Matchers.is;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;

public class DemoWebshop {

    @Test
    void addToWishListTest() {
        given()
                .contentType("application/x-www-form-urlencoded; charset=UTF-8")
                .body("addtocart_51.EnteredQuantity=1")
                .when()
                .post("http://demowebshop.tricentis.com/addproducttocart/details/51/2")
                .then()
                .log().all()
                .statusCode(200)
                .body("success", is(true))
                .body("message", is("The product has been added to your <a href=\"/wishlist\">wishlist</a>"))
//                .body("updatetopcartsectionhtml", is("\"(1)\""));
                .body("updatetopwishlistsectionhtml", is(notNullValue()));
    }
}
