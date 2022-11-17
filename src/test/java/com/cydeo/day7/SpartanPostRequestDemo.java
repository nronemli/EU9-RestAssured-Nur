package com.cydeo.day7;

import static io.restassured.RestAssured.*;

import com.cydeo.pojo.Spartan;
import com.cydeo.utilities.SpartanTestBase;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class SpartanPostRequestDemo extends SpartanTestBase {

     /*
    Given accept type and Content type is JSON
    And request json body is:
    {
      "gender":"Male",
      "name":"Severus",
      "phone":8877445596
   }
    When user sends POST request to '/api/spartans'
    Then status code 201
    And content type should be application/json
    And json payload/response/body should contain:
    "A Spartan is Born!" message
    and same data what is posted
 */

    @Test
    public void postMethod1() {
        String requestJsonBody = "{\n" +
                "      \"gender\":\"Male\",\n" +
                "      \"name\":\"Severus\",\n" +
                "      \"phone\":8877445596\n" +
                "   }";

        Response response = given()
                //what we accept from api as a response json
                .accept(ContentType.JSON)
                .and()
                //what we send to api, we are sending json
                .contentType(ContentType.JSON)
                .body(requestJsonBody)
                .when()
                .post("/api/spartans");

        //verify status code
        assertThat(response.statusCode(), is(201));
        assertThat(response.contentType(), is("application/json"));
        String expectedResponseMessage = "A Spartan is Born!";
        assertThat(response.path("success"), is(expectedResponseMessage));
        assertThat(response.path("data.name"), is("Severus"));
        assertThat(response.path("data.gender"), is("Male"));
    }

    @DisplayName("POST with Map(java) to JSON")
    @Test
    public void postMethod2() {

        //create a map to keep request json body information
        Map<String, Object> requestJsonMap = new LinkedHashMap<>();
        requestJsonMap.put("name", "Severus");
        requestJsonMap.put("gender", "Male");
        requestJsonMap.put("phone", "8877445596L");


        String requestJsonBody = "{\n" +
                "      \"gender\":\"Male\",\n" +
                "      \"name\":\"Severus\",\n" +
                "      \"phone\":8877445596\n" +
                "   }";

        Response response = given()
                //what we accept from api as a response json
                .accept(ContentType.JSON)
                .and()
                //what we send to api, we are sending json
                .contentType(ContentType.JSON)
                .body(requestJsonMap).log().all()
                .when()
                .post("/api/spartans");

        //verify status code
        assertThat(response.statusCode(), is(201));
        assertThat(response.contentType(), is("application/json"));
        String expectedResponseMessage = "A Spartan is Born!";
        assertThat(response.path("success"), is(expectedResponseMessage));
        assertThat(response.path("data.name"), is("Severus"));
        assertThat(response.path("data.gender"), is("Male"));

        response.prettyPrint();
    }

    @DisplayName("POST with Map(java) to Spartan Class")
    @Test
    public void postMethod3() {


        //create one object from your pojo, send it as a JSON
        Spartan spartan = new Spartan();
        spartan.setName("Severus");
        spartan.setGender("Male");
        spartan.setPhone(8877445596L);

        System.out.println("spartan = " + spartan);

        String requestJsonBody = "{\n" +
                "      \"gender\":\"Male\",\n" +
                "      \"name\":\"Severus\",\n" +
                "      \"phone\":8877445596\n" +
                "   }";

        //jackson annotation for deserialization and not for serialization
        //@JsonIgnoreProperties(value = "id", allowSetters= true), this will not include id in the request body
        //you can also create a new class to send a request with 3 fields, instead of 4.
        Response response = given()
                //what we accept from api as a response json
                .accept(ContentType.JSON)
                .and()
                //what we send to api, we are sending json
                .contentType(ContentType.JSON)
                .body(spartan).log().all()
                .when()
                .post("/api/spartans");

        //verify status code
        assertThat(response.statusCode(), is(201));
        assertThat(response.contentType(), is("application/json"));
        String expectedResponseMessage = "A Spartan is Born!";
        assertThat(response.path("success"), is(expectedResponseMessage));
        assertThat(response.path("data.name"), is("Severus"));
        assertThat(response.path("data.gender"), is("Male"));

        response.prettyPrint();
    }


    //Create one SpartanUtil class
    //create a static method that returns Map<String,Object>
    //use faker library(add as a depedency) to assign each time different information
    //for name,gender,phone number
    //then use your method for creating spartan as a map,dynamically.


    @DisplayName("POST with Map(java) to Spartan Class")
    @Test
    public void postMethod4() {
        //this example we implement serialization with creating spartan object sending as request body
        //also implemented deserialization getting the id,sending get request and saving that body as a response

        //create one object from your pojo, send it as a JSON
        Spartan spartan = new Spartan();
        spartan.setName("Severus");
        spartan.setGender("Male");
        spartan.setPhone(8877445596L);

        String expectedResponseMessage = "A Spartan is Born!";

        int idFromPost = given()
                //what we accept from api as a response json
                .accept(ContentType.JSON)
                .and()
                //what we send to api, we are sending json
                .contentType(ContentType.JSON)
                .body(spartan).log().all()
                .when()
                .post("/api/spartans")
                .then()
                .statusCode(201)
                .contentType("application/json")
                .body("success", is(expectedResponseMessage))
                .extract().jsonPath().getInt("data.id");

        System.out.println("idFromPost = " + idFromPost);

        //deserialization
        //send a get request id
        Spartan spartanPosted = given().accept(ContentType.JSON)
                .and().pathParams("id", idFromPost)
                .when().get("api/spartans/{id}")
                .then()
                .statusCode(200).extract().as(Spartan.class);


        assertThat(spartanPosted.getName(),is(spartan.getName()));
        assertThat(spartanPosted.getGender(),is(spartan.getGender()));
        assertThat(spartanPosted.getPhone(),is(spartan.getPhone()));
    }


}
