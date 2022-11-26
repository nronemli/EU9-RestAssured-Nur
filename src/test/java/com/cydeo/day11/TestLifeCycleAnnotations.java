package com.cydeo.day11;

import org.junit.jupiter.api.*;

public class TestLifeCycleAnnotations {

    //has to be static to only run once before all
    @BeforeAll
    public static void inti(){
        System.out.println("Before all is running");
    }

    @BeforeEach
    public void initEach(){
        System.out.println("This is before each is running");
    }

    @AfterEach
    public void closeEach(){
        System.out.println("After each is running");
    }

    @Test
    public void test1() {
        System.out.println("TEST 1 IS RUNNING");
    }

    @Test
    public void test2() {
        System.out.println("TEST 2 IS RUNNING");
    }

    @AfterAll
    public static void close(){
        System.out.println("After all is running");
    }








}
