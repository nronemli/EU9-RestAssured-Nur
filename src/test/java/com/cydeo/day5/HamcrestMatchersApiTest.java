package com.cydeo.day5;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;

public class HamcrestMatchersApiTest {
    /*
      given accept type is Json
      And path param id is 15
      When user sends a get request to spartans/{id}
      Then status code is 200
      And content type is Json
      And json data has following
          "id": 15,
          "name": "Meta",
          "gender": "Female",
          "phone": 1938695106
       */
    @DisplayName("One spartan with hamcrest and chaining ")
    @Test
    public void simpleTest1() {
        //request
        given().accept(ContentType.JSON)
                .and()
                .pathParams("id", 15)
                .when()
                //send request
                .get("http://54.208.34.57:8000/api/spartans/{id}")
                //response
                .then()
                .statusCode(200) //check status code
                .and()
                .contentType("application/json") //check content type
                .and()
                .body("id", equalTo(15), //check body
                        "name", is("Meta"),
                        "gender", is("Female"),
                        "phone", is(1938695106));

    }

    @DisplayName("CBTraining teacher with chaining and matchers ")
    @Test
    public void teachersData() {
        //request
        given().accept(ContentType.JSON)
                .and()
                .pathParams("id", 3)
                .get("https://api.training.cydeo.com/teacher/{id}")
                //response
                .then()
                .statusCode(200)
                .and()
                .contentType("application/json;charset=UTF-8")
                .and()
                //.header("Content-Length")
                .and()
                .header("Date", notNullValue())
                .and().assertThat()
                .body("teachers[0].firstName", is("Tet"))
                .body("teachers[0].lastName", is("DS"))
                .body("teachers[0].gender", equalTo("Male"));
    }

    @DisplayName("Get request to teacher/all and chaining ")
    @Test
    public void teachersTest() {
        //request
        //verify Erik,Tory inside the all teachers
        given().accept(ContentType.JSON)
                .when()
                .get("https://api.training.cydeo.com/teacher/all")
        //response
                .then()
                    .statusCode(200)
                .body("teachers.firstName",hasItems("Erik","Tory","Porter"));
    }

}
