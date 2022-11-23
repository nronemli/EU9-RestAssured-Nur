package com.cydeo.day10;

import com.cydeo.utilities.SpartanAuthTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class ResponseTimeTest extends SpartanAuthTestBase {


    @Test
    public void test1() {
        Response response = given().auth().basic("admin", "admin")
                .accept(ContentType.JSON)
                .when()
                .get("/api/spartans").then()
                //both and method: how to verify response time: time method(matchers), with hamcrest both+and method
                        .time(both(greaterThan(500L)).and(lessThanOrEqualTo(1500L)))
                                .extract().response();

        //checking response time, not performance testing
        System.out.println("response.getTime() = " + response.getTime());

    }


}
