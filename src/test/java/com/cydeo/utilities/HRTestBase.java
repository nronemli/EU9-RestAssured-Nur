package com.cydeo.utilities;

import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

public abstract class HRTestBase {

    @BeforeAll
    public static void init() {
        RestAssured.baseURI = "http://54.208.34.57:1000/ords/hr";
        String dbUrl =  " ";
        String dbUsername = "HR";
        String dbPassword = "HR";
    }


    @AfterAll
    public void tearDown(){
        DBUtils.destroy();
    }
}
