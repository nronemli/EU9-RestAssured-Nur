package com.cydeo.day4;

import com.cydeo.utilities.HRTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class ORDSApiTestWithPath extends HRTestBase {


    @DisplayName("GET request to counties with path method ")
    @Test
    public void test1() {
        Response response = given().accept(ContentType.JSON)
                .and().queryParam("q","{\"region_id\":2}")
                .log().all()
                .when().get("/countries");

        assertEquals(200,response.statusCode());

        //print limit result
        System.out.println("response.path(\"limit\") = " + response.path("limit"));

        //print hasMpre
        System.out.println("response.path(\"hasMore\") = " + response.path("hasMore"));

        //print first country id
        String firstCountryId= response.path("items[0].country_id");
        System.out.println("firstCountryId = " + firstCountryId);

        //print second country name
        String secondCountryName= response.path("items[1].country_name");
        System.out.println("secondCountrName = " + secondCountryName);

        //print "htt,,,/CA
        String CAhref = response.path("items[2].links[0].href");
        System.out.println("CAhref = " + CAhref);

        //get me all country names

        List<String> countyNames= response.path("items.country_name");
        for (String eachCountryName : countyNames) {
            System.out.println(eachCountryName);
        }

        //assert that all regions ids are qual to 2
        List<Integer> allRegionsIds= response.path("items.region_id");

        for (Integer each : allRegionsIds) {
            System.out.println(each);
            assertEquals(2,each);
        }




    }

    @DisplayName("GET request to /employees with Query params")
    @Test
    public void test2() {

        Response response = given().accept(ContentType.JSON)
                .and().queryParam("q", "{ \"job_id\": \"IT_PROG\"}")
                .when().get("/employees");

        assertEquals(200, response.statusCode());
        assertEquals("application/json", response.contentType());
        assertTrue(response.body().asString().contains("IT_PROG"));

        List<String> allJobIds = response.path("items.job_id");
        for (String eachJobId : allJobIds) {
            System.out.println("eachJobId = " + eachJobId);
            assertEquals("IT_PROG", eachJobId);
        }
        
        
        //print each name of IT_Progs

    //    List<String> eachName=
        
    }


    





    }




