package com.cydeo.day4;

import com.cydeo.utilities.HRTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class ORDSApiWiTHJsonPath extends HRTestBase {

    @DisplayName("GET request to Counties")
    @Test
    public void test1() {
        Response response = given().accept(ContentType.JSON)
                .get("/countries");

        //create jsonPath object
        JsonPath jsonPath = response.jsonPath();

        //get the second country name with JsonPath
        String secondCountryName= jsonPath.getString("items[1].country_name");
        System.out.println("secondCountryName = " + secondCountryName);

        //get all country id
        List<Object> allCountryIds = jsonPath.getList("items.country_id");
        System.out.println("allCountryIds = " + allCountryIds);


        //get all country names where their region id is equal to 2
                                                                        //if statement  //condition         value
        List<Object> countryNameWithRegionsId2 = jsonPath.getList("items.findAll {it.region_id==2}.country_name");
        System.out.println("countryNameWithRegionsId2 = " + countryNameWithRegionsId2);

    }
    @DisplayName("GET request to /employees with query param")
    @Test
    public void test2() {
        Response response = given().accept(ContentType.JSON)
                //see all employees >limit query param
                .and().queryParam("limit","107")
                .get("/employees");

        //get me all email of employees who is working as it_prog

        //create jsonPath object
        JsonPath jsonPath = response.jsonPath();

        //get me all email of employees who is working as IT_PROG
        List<Object> employeeITProgrammer = jsonPath.getList("items.findAll {it.job_id==\"IT_PROG\"}.email");
        System.out.println("employeeITProgrammer = " + employeeITProgrammer);

        //get me first nae of employees who is making more than 10000
        List<Object> nameSalaryOver10 = jsonPath.getList("items.findAll{it.salary>10000}.first_name");
        System.out.println("nameSalaryOver10 = " + nameSalaryOver10);

        //get the max salary first_name
        //also works with response.path()
        String kingFirstName= jsonPath.getString("items.max{itsalary}.first_name");
        System.out.println("kingFirstName = " + kingFirstName);

        //there are more just google rest groovypath!

    }
}
