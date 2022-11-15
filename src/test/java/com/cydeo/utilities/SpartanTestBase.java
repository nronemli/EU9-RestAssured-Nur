package com.cydeo.utilities;

import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

public abstract class SpartanTestBase {

    @BeforeAll
    public static void init() {
        RestAssured.baseURI = "http://54.208.34.57:8000";
        String dbUrl =  "jdbc:oracle:thin:@54.208.34.57:1521:XE";
        String dbUsername = "SP";
        String dbPassword = "SP";
       // DBUtils.createConnection(dbUrl,dbUsername,dbPassword);
    }
    @AfterAll
    public static void tearDown(){
      //  DBUtils.destroy();
    }



}
