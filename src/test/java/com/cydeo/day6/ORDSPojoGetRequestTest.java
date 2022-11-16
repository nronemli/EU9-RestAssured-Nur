package com.cydeo.day6;

import com.cydeo.pojo.Regions;
import com.cydeo.utilities.HRTestBase;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;

public class ORDSPojoGetRequestTest extends HRTestBase {

    @Test
    public void regionTest(){

        JsonPath jsonPath = get("/regions").then().statusCode(200).
                log().body().extract().jsonPath();

        Regions region1 = jsonPath.getObject("items[0]", Regions.class);

        System.out.println(region1);

        System.out.println("region1.getRegion_id() = " + region1.getRegionId());
        System.out.println("region1.getRegion_name() = " + region1.getR_name());
       // System.out.println("region1.getLinks().get(0).getHref() = " + region1.getLinks().get(0).getHref());
    }
    }


