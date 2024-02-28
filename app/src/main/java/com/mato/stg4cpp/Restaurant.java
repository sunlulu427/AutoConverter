package com.mato.stg4cpp;


import com.mato.stg4.annotation.FormatJson;
import com.mato.stg4.annotation.STGClass;
import com.mato.stg4.annotation.STGField;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @Author sunlulu.tomato
 * @Date 2023/12/31
 */
@FormatJson
public class Restaurant {

    @STGField
    public String location = "";

    @STGField
    public float score = 0.0f;

    @STGField
    public int comments = 0;

//    @STGField
//    public Menu menu;

    @STGField
    public Integer staffs = 0;

//    @STGClass
//    public static class Menu {
//        @STGField
//        public ArrayList<Food> foods = new ArrayList<>();
//
//        @STGField
//        public int level = 1;
//    }
//
//    @STGClass
//    public static class Food {
//        @STGField
//        public String name;
//        @STGField
//        public float price;
//    }
}
