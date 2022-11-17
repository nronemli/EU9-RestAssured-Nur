package com.cydeo.day6;

import com.cydeo.pojo.Employee;
import com.cydeo.pojo.Region;
import com.cydeo.pojo.Regions;
import com.cydeo.utilities.HRTestBase;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import static io.restassured.RestAssured.*;

public class ORDSPojoGetRequestTest extends HRTestBase {

    @Test
    public void regionTest() {

        JsonPath jsonPath = get("/regions").then().statusCode(200).
                log().body().extract().jsonPath();

        Region region1 = jsonPath.getObject("items[0]", Region.class);

        System.out.println(region1);

        System.out.println("region1.getRegion_id() = " + region1.getRegionId());
        System.out.println("region1.getRegion_name() = " + region1.getR_name());
        // System.out.println("region1.getLinks().get(0).getHref() = " + region1.getLinks().get(0).getHref());
    }

    @DisplayName("GET request first 4 values for verification and ignore rest")
    @Test
    public void testEmployee() {
        Employee emp1 = get("/employees").then().statusCode(200)
                .extract().jsonPath().getObject("items[0]", Employee.class);

        System.out.println(emp1);

    }

    //send a get request to regions
    //verify that regions ids are 1,2,3,4
    //verify that regions names Europe,Americas, Asia, Middle east, and africa
    //ignore non-used fields

    @DisplayName("GET Request  to region only some fields test ")
    @Test
    public void testRegions() {

        //send a get request and save everything inside the regions object
        //since we prepare pojo also for the all properties we dont need to use any path as method is enough
        Regions regions = get("/regions").then().statusCode(200)
                .extract().response().as(Regions.class);

        assertThat(regions.getCount(),is(4));

        //create empty list to store values
        List<String> regionNames= new ArrayList<>();
        List<Integer> regionsIds= new ArrayList<>();

        //loop through each of the region, save their ids and names to empty list  that we prepare
        for (Region region :  regions.getItems()) {
            regionsIds.add(region.getRegionId());
            regionNames.add(region.getR_name());
        }

        System.out.println(regionsIds);
        System.out.println(regionNames);

        //prepare expected result
        List<Integer> expectedRegionIds= Arrays.asList(1,2,3,4);
        List<String> expectedRegionName= Arrays.asList("Europe","Americas","Asia","Middle East and Africa");

        //compare two results
        assertThat(regionsIds,is(expectedRegionIds));
        assertThat(regionNames,is(equalTo(expectedRegionName)));




    }





}


