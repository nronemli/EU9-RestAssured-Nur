package com.cydeo.day10;

import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class FormulaOneXMLTest {

    // <Driver driverId="alonso" code="ALO" url="http://en.wikipedia.org/wiki/Fernando_Alonso">
    @BeforeAll
    public static void setUp(){
        baseURI= "http://ergast.com";
        //http://ergast.com/api/f1/drivers/alonso
        basePath="/api/f1";
    }

    @DisplayName("GET requst")
    @Test
    public void test1(){
        Response response = given().pathParams("driver", "alonso")
                .when().get("/drivers/{driver}").then().statusCode(200)
                .log().all().extract().response();

        XmlPath xmlPath = response.xmlPath();
        //get given name
        String givenName = xmlPath.getString("MRData.DriverTable.Driver.GivenName");
        System.out.println("givenName = " + givenName);
        //get family name
        String familyName = xmlPath.getString("MRData.DriverTable.Driver.FamilyName");
        System.out.println("familyName = " + familyName);
        //get driverId
        String driverIdAttribute = xmlPath.getString("\"MRData.DriverTable.Driver.@driverId");
        System.out.println("driverIdAttribute = " + driverIdAttribute);
        //get code
        String codeAttribute = xmlPath.getString("\"MRData.DriverTable.Driver.@code");
        System.out.println("codeAttribute = " + codeAttribute);
        //get URl
        String URLAttribute = xmlPath.getString("\"MRData.DriverTable.Driver.@url");
        System.out.println("URLAttribute = " + URLAttribute);
    }





}
