package com.cydeo.day10;

import com.cydeo.pojo.Spartan;
import com.cydeo.utilities.SpartanAuthTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class SpartanWithXML extends SpartanAuthTestBase {

    @DisplayName("GET request to /api/spartans and verify xml")
    @Test
    public void getSpartanXml() {

        //ask for xml response
        //assert content type is xml
        //assert status code is 200
        //verify first spartan name is Meade

        given().accept(ContentType.XML)
                .and()
                .auth().basic("admin", "admin")
                .when()
                .get("/api/spartans")
                .then()
                .statusCode(200)
                .contentType("application/xml;charset=UTF-8")
                .body("List.item[0].name", is("Meade"))
                .body("List.item[0].gender", is("Male"))
                .log().all();

    }

    @DisplayName("GET request /api/spartans with xmlPath")
    @Test
    public void testXMLPath(){

        Response response = given().accept(ContentType.XML)
                .and()
                .auth().basic("admin", "admin")
                .when()
                .get("/api/spartans");

        //get response xml body/payload save inside xml path object
        XmlPath xmlPath = response.xmlPath();

        String name = xmlPath.getString("List.item[0].name");
        System.out.println("name = " + name);
        int id = xmlPath.getInt("List.item[2].id");
        System.out.println("id = " + id);

        //how to get all names and save into list of string
        List<Object> names = xmlPath.getList("List.item.name");
        System.out.println("names = " + names);
    }















}
