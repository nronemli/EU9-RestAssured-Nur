package com.cydeo.day2;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SpartanGetRequest {


    String baseUrl = "http://54.208.34.57:8000";

    //Given Accept type application/json
    //When user send GET request to api/spartans
    //Then status code must be 200
    //Then response Content Type must be application/json
    //And response body should include spartan result
    @Test
    public void test1() {

        Response response = RestAssured.given().accept(ContentType.JSON)
                .when()
                .get(baseUrl + "/api/spartans");

        //printing status code frm response object
        System.out.println(response.statusCode());
        //printing response content type from response object
        System.out.println(response.contentType());
        //print whole result body
        response.prettyPrint();
        //how to do API testing then?
        //verify status code is 200
        Assertions.assertEquals(response.statusCode(), 200);
        //verify content type is application json
        Assertions.assertEquals(response.contentType(), "application/json");

    }

    //Given accept is application/jspn
    //when users sends a get request to /api/spartans/3
    //then status code should be 2--
    //and content type should be application/json
    //and json body should contain Fidole

    @DisplayName("GET one spartan /api/spartans/3")
    @Test
    public void test2() {
        Response response = RestAssured.given().accept(ContentType.JSON)
                .when().get(baseUrl + "/api/spartans/3");
        Assertions.assertEquals(200, response.statusCode());
        Assertions.assertEquals("application/json", response.contentType());
        Assertions.assertTrue(response.body().asString().contains("Fidole"));
    }

    /*
            Given no headers provided
            When Users sends GET request to /api/hello
            Then response status code should be 200
            And Content type header should be “text/plain;charset=UTF-8”
            And header should contain date
            And Content-Length should be 17
            And body should be “Hello from Sparta"
            */
    @DisplayName("GET request to /api/hello")
    @Test
    public void test3() {

        Response response = RestAssured.when().get(baseUrl + "/api/hello");

        Assertions.assertEquals(200, response.statusCode());
        Assertions.assertEquals("text/plain;charset=UTF-8", response.contentType());

        //boolean result
        //we use hasHeaderWithName method to verify header exist or not
        Assertions.assertTrue(response.headers().hasHeaderWithName("Date"));

        //we use response.header(String headerName) method to get any header value
        System.out.println("response.headers(\"Content-Length\") = " + response.header("Content-Length"));
        System.out.println("response.header(\"Date\") = " + response.header("Date"));

        //verify content length is 17
        Assertions.assertEquals("17", response.header("Content-Length"));

        Assertions.assertEquals("Hello from Sparta", response.body().asString());

    }

}
