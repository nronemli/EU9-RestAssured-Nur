package com.cydeo.day5;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class HamcrestMatchersIntro {

    @DisplayName("Assertion with ")
    @Test
    public void simpleTest1() {

        //actual 5+5
        assertThat(5 + 5, Matchers.is(10));
        assertThat(5 + 5, Matchers.equalTo(10));
        //overloaded method it has

        assertThat(5 + 5, Matchers.is(Matchers.equalTo(10)));
        assertThat(5 + 5, Matchers.is(Matchers.not(Matchers.equalTo(9))));
        assertThat(5 + 5, is(not(equals(9))));

        //number comparison
        //greaterThan()
        //greaterThanOrEqualTo()
        //lessThan()
        //lessThanOrEqualTo()
        assertThat(5 + 5, is(greaterThan(9)));


    }

    @DisplayName("Assertion with String")
    @Test
    public void stringHamcrest() {
        String text = "EU9 is learning Hamcrest";
        //checking for equality same as numbers
        //all do same pick one
        assertThat(text,is("EU9 is learning Hamcrest"));
        assertThat(text, equalTo("EU9 is learning Hamcrest"));
        assertThat(text,is(equalTo("EU9 is learning Hamcrest")));

        //check if this text starts with EU9
        //startsWith
        //ignoreCase
        assertThat(text, startsWith("EU9"));
        assertThat(text, startsWithIgnoringCase("eu9"));
        //ends with
        //ignoring case
        assertThat(text,endsWith("rest"));
        assertThat(text,endsWithIgnoringCase("REST"));
        //containsString
        //containsIgnoreCase
        assertThat(text, containsString("learning"));
        assertThat(text,containsStringIgnoringCase("LEARNING"));

        //is blank with no string
        String str= "  ";
        assertThat(str,blankString());

        //empty
        assertThat(str.trim(),emptyString());

    }

    @DisplayName("Hamcrest for Collection")
    @Test
    public void testCollection() {
        List<Integer> listOfNumbers= Arrays.asList(1,4,5,6,32,54,66,77,45,23);

        //check size of the list
        assertThat(listOfNumbers,hasSize(10));
        //hasItem > checks one item
        assertThat(listOfNumbers, hasItem(77));
        //hasItems >checks multiple items
        assertThat(listOfNumbers, hasItems(77,54,23));

        //check if all numbers greater than 0
        //everyItem
        //contains ,startsWith etc everything can be used
        assertThat(listOfNumbers,everyItem(greaterThan(0)));


    }




}
