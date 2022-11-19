package com.cydeo.day8;

import com.cydeo.utilities.SpartanAuthTestBase;
import com.cydeo.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class SpartanWithAuthTests extends SpartanAuthTestBase {


    @DisplayName("GET /api/spartans as a public user(guest) expected 401")
    @Test
    public void test1() {

        Response response = given().accept(ContentType.JSON).when()
                .get("/api/spartans")
                .then().statusCode(401)
                .log().all().extract().response();

        assertThat(response.path("error"), is("Unauthorized"));
        assertThat(response.path("message"), is("Unauthorized"));

    }


    @DisplayName("GET request /api/spartans as admin user , expect 200")
    @Test
    public void test2() {
        //how to pass admin as a username and password

        given().auth()
                    .basic("admin", "admin")
                .and()
                    .accept(ContentType.JSON)
                .when()
                    .get("/api/spartans")
                .then()
                    .statusCode(200).log().all();

    }


    @DisplayName("DELETE request /api/spartans/{id} as editor , expect 403")
    @Test
    public void test3() {

        Response response = given().auth().basic("editor", "editor")
                .and().accept(ContentType.JSON)
                .and().pathParam("id", 30)
                .when().delete("/api/spartans/{id}")
                .then()
                .statusCode(403).log().all().extract().response();

        assertThat(response.path("error"),is("Forbidden"));

    }




}
