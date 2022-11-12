package com.cydeo.day3;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;


public class SpartantTestsWithPath {

    @BeforeAll
    public static void init() {
        RestAssured.baseURI = "http://54.208.34.57:8000";
    }

    /*
   Given accept type is json
   And path param id is 10
   When user sends a get request to "api/spartans/{id}"
   Then status code is 200
   And content-type is "application/json"
   And response payload values match the following:
        id is 10,
        name is "Lorenza",
        gender is "Female",
        phone is 3312820936
 */
    @DisplayName("GET one spartan with path METHOD")
    @Test
    public void test1() {

        Response response= given().accept(ContentType.JSON)
                .and().pathParams("id",10)
                .when().get("api/spartans/{id}");

        assertEquals(200,response.statusCode());
        assertEquals("application/json",response.contentType());

        //verify each jason has specific value PATH METHOD
        System.out.println(response.path("id").toString());
        System.out.println(response.path("name").toString());
        System.out.println(response.path("gender").toString());
        System.out.println(response.path("phone").toString());

        int id = response.path("id");
        String name= response.path("name");
        String gender= response.path("gender");
        long phone = response.path("phone");


        //assertions        expected actual
        assertEquals(10,id);
        assertEquals("Lorenza",name);
        assertEquals("Female",gender);
       // assertEquals(3312820936l),phone;

    }

    @DisplayName("GET all spartan and navigate with Path()")
    @Test
    public void test3(){

        Response response= given().accept(ContentType.JSON)
                .when().get("/api/spartans");

       // response.prettyPrint();

        int firstId= response.path("id[0]");
        System.out.println(firstId);

        //first will be index 0
        String name= response.path("name[0]");
        System.out.println(name);

        //last one will be index -1
        String lastFirstName= response.path("name[-1]");
        System.out.println(lastFirstName);

        //store each names in  a list
        List<String> names= response.path("name");
        System.out.println(names);

        //print each name one by one
        for (String eachName : names) {
            System.out.println(eachName);
        }




    }








}
