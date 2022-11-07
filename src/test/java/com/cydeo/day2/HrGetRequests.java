package com.cydeo.day2;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class HrGetRequests {
    //annotation executes one time before everything
    @BeforeAll
    public static void init() {
        RestAssured.baseURI = "http://54.208.34.57:1000/ords/hr";
    }

    @DisplayName("GET request to/regions ")
    @Test
    public void test1() {
        Response response = RestAssured.get("/regions");

        //print the status code
        System.out.println("response.statusCode() = " + response.statusCode());
    }

 /*
        Given accept type is application/json
        When user sends get request to /regions/2
        Then response status code must be 200
        and content type equals to application/json
        and response body contains   Americas
     */

    @DisplayName("GET request to/regions/2 ")
    @Test
    public void test2() {
        Response response = RestAssured.get("/regions/2");

        //Then response status code must be 200
        Assertions.assertEquals(200, response.statusCode());
        //And content type equals to application/json
        Assertions.assertEquals("application/json", response.contentType());
        //And response body contains   Americas
        Assertions.assertTrue(response.body().asString().contains("Americas"));
        response.prettyPrint();
    }










}
