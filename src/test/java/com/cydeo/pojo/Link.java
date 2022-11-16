package com.cydeo.pojo;

public class Link {
    /*
 {
                    "rel": "self",
                    "href": "http://54.208.34.57:1000/ords/hr/regions/1"
                }
 */

    //json key and variable name has to match exactly
    private String rel;
    private String href;

    public String getRel() {
        return rel;
    }

    public void setRel(String rel) {
        this.rel = rel;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    @Override
    public String toString() {
        return "Links{" +
                "rel='" + rel + '\'' +
                ", href='" + href + '\'' +
                '}';
    }
}

