package com.cydeo.day1;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

public class SimpleGetRequest {


    String url= "http://54.208.34.57:8000/api/spartans";

    @Test
    public void test1(){

        //send a get request a save response inside the repsonse object
        //                  restAssured. get method
        Response response = RestAssured.get(url);
        //print response status code
        System.out.println(response.statusCode());

        //print response BODY
        response.prettyPrint();




    }



}
