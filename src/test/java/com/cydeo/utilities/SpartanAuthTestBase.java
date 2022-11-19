package com.cydeo.utilities;

import org.junit.jupiter.api.BeforeAll;
import static io.restassured.RestAssured.baseURI;
public abstract class SpartanAuthTestBase {


    @BeforeAll
    public static void init(){

        baseURI = "http://54.208.34.57:7000";
        String dbUrl =  "jdbc:oracle:thin:@54.208.34.57:1521:XE";
        String dbUsername = "SP";
        String dbPassword = "SP";
        // DBUtils.createConnection(dbUrl,dbUsername,dbPassword);


    }


}
