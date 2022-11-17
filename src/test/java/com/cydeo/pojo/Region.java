package com.cydeo.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString

public class Region {
    /*
     "items": [
        {
            "region_id": 1,
            "region_name": "Europe",
            "links": [
                {
                    "rel": "self",
                    "href": "http://54.208.34.57:1000/ords/hr/regions/1"
                }
            ]
        }
     */
    //use JsonProperty to create a new variable name that does not match the JsonKey
    //use JsonProperty annotation that comes from jackson
    @JsonProperty("region_id")
    private int regionId;
    @JsonProperty("region_name")
    private String r_name;
    private List<Link> links;






}

