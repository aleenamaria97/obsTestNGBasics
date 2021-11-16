package com.obsqura.testngcommands;

import org.testng.annotations.Test;

public class Grouping {
    @Test(groups = "Regression")
    public void test1(){
    System.out.println("Regression group");
    }
    @Test(groups = {"Regression","Sanity"})
    public void test2(){
        System.out.println("Regression group,Sanity group");
    }
    @Test(groups = "Regression")
    public void test3(){
        System.out.println("Regression group");
    }
    @Test(groups = {"Regression","Sanity"})
    public void test4() {
        System.out.println("Regression group,Sanity Group");
    }
}
