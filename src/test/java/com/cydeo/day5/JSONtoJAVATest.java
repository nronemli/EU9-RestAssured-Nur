package com.cydeo.day5;


import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


public class JSONtoJAVATest extends SpartanHamcrestTest {
    @DisplayName("GET one Spartan and deserialize to MAP")
    @Test
    public void oneSpartanToMap() {
        Response response = given().pathParams("id", 15)
                .when().get("/api/spartans/{id}")
                .then().statusCode(200).extract().response();

        //get the json convert it to map
        //
        Map<String,Object> jsonMap= response.as(Map.class);

        System.out.println(jsonMap.toString());
        //we can use hamcrest or junit assertion to do assertion
        String actualName = (String) jsonMap.get("name");
        assertThat(actualName,is("Meta"));

    }

    @DisplayName("GET all Spartan to Java")
    @Test
    public void getAllSpartan() {
        Response response = get("/api/spartans").then().statusCode(200)
                .extract().response();

        //we need to convert it to json to java deserialize
        List<Map<String,Object>>jsonList = response.as(List.class);
        //                  get the 3st of spartan.get the name only
        System.out.println("jsonList.get(1).get(\"name\") = " + jsonList.get(1).get("name"));

        //get 3rd spartan from the list
        Map<String,Object> spartan3 = jsonList.get(2);
        System.out.println("spartan3 = " + spartan3);

    }










}
