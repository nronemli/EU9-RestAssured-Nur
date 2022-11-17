package com.cydeo.day7;

import com.cydeo.pojo.Spartan;
import com.cydeo.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;

public class PutAndPatchRequestDemo extends SpartanTestBase {

    @DisplayName("PUT request to one spartan for update ")
    @Test
    public void PUTRequest() {

        //just like post request we have different options to send body, we will go with map

        Map<String, Object> putRequestMap = new LinkedHashMap<>();
        putRequestMap.put("name", "Selim");
        putRequestMap.put("gender", "Male");
        putRequestMap.put("phone", 8877441111L);

        given().contentType(ContentType.JSON)//sending json body
                .body(putRequestMap)//serialized while my request is sending, from java to json structure
                .log().all()
                .and().pathParams("id", 101) //which spartan is needed to update
                .when().put("/api/spartans/{id}") //put request
                .then().statusCode(204);//verify 204, because there is no body, maybe some headers

        //once 204 is verified that is updated maybe the database is not updated so we need to check with GET request and
        //check if the 101 id is updated with above info , assert
        //send a get request after update, make sure updated field changed, or the new info matching with
        //requestBody that we send

        Spartan spartan = given().accept(ContentType.JSON)
                .and().pathParams("id", 101)
                .when().get("/api/spartans/{id}")
                .then().statusCode(200)
                .log().all()
                .extract().as(Spartan.class);

        assertThat(spartan.getName(),is(putRequestMap.get("name")));
        assertThat(spartan.getPhone(),is(putRequestMap.get("phone")));
        assertThat(spartan.getGender(),is(putRequestMap.get("gender")));
    }


    @DisplayName("PATCH request to one spartan for update ")
    @Test
    public void PATCHRequest() {
        Map<String, Object> patchRequestMap = new LinkedHashMap<>();
        patchRequestMap.put("phone", 8111111111L);

        given().contentType(ContentType.JSON)//sending json body
                .body(patchRequestMap)//serialized while my request is sending, from java to json structure
                .log().all()
                .and().pathParams("id", 101) //which spartan is needed to update
                .when().patch("/api/spartans/{id}") //patch request
                .then().statusCode(204);//verify 204, because there is no body, maybe some headers

        Spartan spartan = given().accept(ContentType.JSON)
                .and().pathParams("id", 101)
                .when().get("/api/spartans/{id}")
                .then().statusCode(200)
                .log().all()
                .extract().as(Spartan.class);

        assertThat(spartan.getPhone(),is(patchRequestMap.get("phone")));

    }

    @DisplayName("DELETE request one spartan")
    @Test
    public void test1(){

        Response deleteResponse = given().pathParams("id", 103)
                .when().delete("/api/spartans/{id}")
                .then()
                .statusCode(204)
                .extract().response();


        //get request status code should be 404
                given().accept(ContentType.JSON)
                .and().pathParams("id", 103)
                .when().get("/api/spartans/{id}")
                .then().statusCode(404);



    }






}
