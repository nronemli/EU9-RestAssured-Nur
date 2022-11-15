package com.cydeo.day5;

import com.cydeo.utilities.DBUtils;
import com.cydeo.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import java.util.Map;
import java.util.ResourceBundle;

public class SpartanApiVsDB extends SpartanTestBase {
    @DisplayName("GET one spartan from api and database")
    @Test
    public void testDB1() {
        //get id, name, gender, phone from database
        // and get same information from api
        // and compare
        String query = "select SPARTAN_ID, name, gender, phone from SPARTANS\n" +
                "where SPARTAN_ID = 15";

        //save data inside the map
        Map<String, Object> dbMap = DBUtils.getRowMap(query);
        //print query
        System.out.println("dbMap = " + dbMap);

        //get info from api
        Response response = given().accept(ContentType.JSON)
                .pathParams("id", 15)
                .when().get("/api/spartans/{id}")
                .then()
                .statusCode(200)
                .and().contentType("application/json")
                .extract().response();

        Map<String, Object> apiMap = response.as(Map.class);
        System.out.println(apiMap);

        //compare
        assertThat(apiMap.get("id").toString(), is(dbMap.get("SPARTAN_ID").toString()));
        assertThat(apiMap.get("name"), is(dbMap.get("NAME")));
        assertThat(apiMap.get("gender"), is(dbMap.get("GENDER")));
        assertThat(apiMap.get("phone").toString(), is(dbMap.get("PHONE").toString()));
    }








}
