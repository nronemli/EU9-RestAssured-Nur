package com.cydeo.day6;
import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import com.cydeo.pojo.Search;
import com.cydeo.pojo.Spartan;
import com.cydeo.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

public class SpartanPojoGetRequestTest extends SpartanTestBase {

    @DisplayName("GET one spartan and convert it to Spartan Object")
    @Test
    public void oneSpartanPojo(){
        Response response = given().accept(ContentType.JSON)
                .and().pathParams("id", 15)
                .when().get("api/spartans/{id}")
                .then().statusCode(200)
                .log().all()
                .extract().response();

        //deserialise --> JSON to Java POJO

        //2 options to do this
        //1.using as() method
        //we convert json response to spartan object with the help of jackson
        //as method uses jason to deserialise (json to java class)
        //check>> Spartan(pojo class linked to this test)
        Spartan spartanID15 = response.as(Spartan.class);
        System.out.println(spartanID15);

        //pojo.Spartan class is used to get these
        System.out.println("spartanID15.getId() = " + spartanID15.getId());
        System.out.println("spartanID15.getGender() = " + spartanID15.getGender());

        //2.way of deserialize json to java
        //2.using JsonPath to deserialise to custom class
        JsonPath jsoPath= response.jsonPath();

        Spartan s15 = jsoPath.getObject("", Spartan.class);
        System.out.println(s15);
        System.out.println("s15.getName() = " + s15.getName());
        System.out.println("s15.getPhone() = " + s15.getPhone());


    }

    @DisplayName("GET one spartan from search endpoint result and use POJO")
    @Test
    public void spartanSearchWithPojo(){

        //spartans/search?nameContains=a&gender=Male
        //send get request to above endpoint and save first object with type SpartanPOJO

        JsonPath jsonPath = given().accept(ContentType.JSON)
                .and().queryParams("nameContains", "a",
                        "gender", "Male")
                .when().get("/api/spartans/search")
                .then().statusCode(200)
                .extract().jsonPath();

        //get the first spartan from content list and put inside the spartan object
        Spartan s1 = jsonPath.getObject("content[0]", Spartan.class);
        System.out.println("s1 = " + s1);
        System.out.println("s1.getName() = " + s1.getName());
        System.out.println("s1.getGender() = " + s1.getGender());
        //  Spartan s2 = jsonPath.getObject("content[1]", Spartan.class);


    }

    @Test
    public void spartanSearchWithPojo2() {

        //spartans/search?nameContains=a&gender=Male
        //send get request to above endpoint and save first object with type SpartanPOJO

        Response response= given().accept(ContentType.JSON)
                .and().queryParams("nameContains", "a",
                        "gender", "Male")
                .when().get("/api/spartans/search")
                .then().statusCode(200)
                .extract().response();

        Search searchResult = response.as(Search.class);
        System.out.println("searchResult.getContent().get(0).getName() = " + searchResult.getContent().get(0).getName());
    }

    @DisplayName("GET /spartans/search and save List<Spartan>")
    @Test
    public void spartanSearchWithPojo3() {

        //save all as list of spartan
        List<Spartan> spartanList = given()
                .accept(ContentType.JSON)
                .and()
                .queryParams("nameContains", "a",
                        "gender", "Male")
                .when()
                .get("/api/spartans/search")
                .then()
                .statusCode(200)
                .extract()
                .response().jsonPath().getList("content", Spartan.class);

        System.out.println("spartanList.get(0).getName() = " + spartanList.get(1).getName());
        System.out.println("spartanList.get(0).getGender() = " + spartanList.get(1).getGender());
    }
}




