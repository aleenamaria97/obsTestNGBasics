package com.obsqura.testngcommands;

import org.testng.annotations.Test;

public class LoginTest {
    @Test(priority = 3,enabled = true,groups = {"Sanity","Regression"})
    public void verifyLoginPageTitle(){
        System.out.println("this a login page title");
    }
    @Test(priority = 4,enabled = true,groups = {"Regression"})
    public void verifyValidLogin(){
        System.out.println(" user login");
    }
}
