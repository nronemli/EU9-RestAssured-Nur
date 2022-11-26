package com.cydeo.day11;

import com.cydeo.utilities.ExcelUtil;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class MethodSourceParametereizedTest {

    @ParameterizedTest
    @MethodSource("getNames")
    public void testPrintNames(String name) {
        //you can get value from anywhere almost anytype and retun to your test
        //db
        //excel
        //other APIs
        System.out.println("name = " + name);
    }

    public static List<String> getNames() {
        List<String> nameList = Arrays.asList("Selim", "Emin", "Nur", "Necip");
        return nameList;
    }

    public static List<Map<String, String>> getExcelData() {
        //getting the file object
        ExcelUtil vytrackFile = new ExcelUtil("src/test/resources/Vytracktestdata.xlsx", "QA3-short");
        //return sheet as a list
        return vytrackFile.getDataList();
    }

    @ParameterizedTest
    @MethodSource("getExcelData")
    public void excelParmTest(Map<String, String> userInfo) {
        System.out.println("userInfo.get(\"firstname\") = " + userInfo.get("firstname"));
        System.out.println("userInfo.get(\"lastname\") = " + userInfo.get("lastname"));
    }
}
