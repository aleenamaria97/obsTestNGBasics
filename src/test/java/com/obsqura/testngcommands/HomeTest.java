package com.obsqura.testngcommands;

import org.testng.annotations.*;

public class HomeTest {
    @BeforeSuite
    public void beforeSuit(){
        System.out.println("this is before suit");
    }
    @BeforeTest
    public void beforeTest(){
        System.out.println("this is before test");
    }
    @BeforeMethod
    public void beforeMethod(){
        System.out.println("This is a before method");
    }
    @AfterMethod
    public void afterMethod(){
        System.out.println("This is after method");
    }
   @AfterTest
    public void afterTest(){
        System.out.println("this is after test");
    }
    @AfterSuite
    public void afterSuit(){
        System.out.println("this is after suitS");
    }
    @Test(priority = 1,enabled = false,invocationCount = 1)
    public void verifyHomePageTitle(){
        System.out.println("this a home page title");
    }
    @Test(priority = 2,enabled = false)
    public void verifyHomePageUserMenu(){
        System.out.println("homepage user menu");
    }
}
