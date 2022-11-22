package com.cydeo.day10;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class GovXMLTest {


    @Test
    public void test1() {
        //send a request to
        //https://data.ct.gov/api/views/qm34-pq7e/rows.xml
        //get all the years
        //get all uknows
        //get 206 other
        //get 2007 address

        //path doesnt WORK
        Response response = get("https://data.ct.gov/api/views/qm34-pq7e/rows.xml").then().statusCode(200)
                .extract().response();
        XmlPath xmlPath = response.xmlPath();

        List<Object> listOfYear =  xmlPath.getList("response.row.row.year");
        System.out.println("listOfYear = " + listOfYear);

        List<Object> unkowns =  xmlPath.getList("response.row.row.unknown");
        System.out.println("unkowns = " + unkowns);

        int other = xmlPath.getInt("response.row.row[2].other");
        System.out.println("other = " + other);

        String address = xmlPath.getString("response.row.row[4].@_address");
        System.out.println("address = " + address);

    }
}
