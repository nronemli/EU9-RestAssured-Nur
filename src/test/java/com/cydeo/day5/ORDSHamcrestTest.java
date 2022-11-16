package com.cydeo.day5;

import com.cydeo.utilities.HRTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class ORDSHamcrestTest extends HRTestBase {

    @DisplayName("GET request to Employees IT_PROG endpoint and chaining")
    @Test
    public void employeeTest() {
        //send a get request to employees endpoint with query parameters job_id IT PRGO
        //verify each job_id is IT_PROG
        //verify first name are... (find method to check list against list
        //verify emails without checking order
        List<String> names = Arrays.asList("Alexander", "Bruce", "David", "Valli", "Diana");

        given().accept(ContentType.JSON)
                .and().queryParam("q", "{\"job_id\": \"IT_PROG\"}")
                .when()
                .get("/employees")
                .then()
                .statusCode(200)
                //most important one everyItem > very similar to each in java
                .body("items.job_id", everyItem(equalTo("IT_PROG")))
                .body("items.first_name", containsInRelativeOrder("Alexander", "Bruce", "David", "Valli", "Diana")) //contains with order
                .body("items.email", containsInAnyOrder("VPATABAL", "DAUSTIN", "BERNST", "AHUNOLD", "DLORENTZ")) //contains without order
                .body("items.first_name", equalTo(names)); // equality of lists assertion

    }

    //change and also get response object
    @Test
    public void employeeTest2() {

       JsonPath jsonPath = given().accept(ContentType.JSON)
                .and().queryParam("q", "{\"job_id\": \"IT_PROG\"}")
                .when()
                .get("/employees")
                .then()
                .statusCode(200)
                //most important one everyItem > very similar to each in java
                .body("items.job_id", everyItem(equalTo("IT_PROG")))
                //extract() method that allows us to get response object after we use then method
                .extract().jsonPath();

        //response.prettyPrint();
        //assert that we have only 5 first name


        assertThat(jsonPath.getList("items.first_name"),containsInAnyOrder("\"Alexander\", \"Bruce\", \"David\", \"Valli\", \"Diana\""));
        assertThat(jsonPath.getList("items.first_name"),hasSize(5));

    }
}
