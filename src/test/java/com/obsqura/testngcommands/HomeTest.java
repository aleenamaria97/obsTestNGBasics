package com.obsqura.testngcommands;

import org.testng.annotations.Test;

public class HomeTest {
    @Test(priority = 1,enabled = true)
    public void verifyHomePageTitle(){
        System.out.println("this a home page title");
    }
    @Test(priority = 2,enabled = true)
    public void verifyHomePageUserMenu(){
        System.out.println("homepage user menu");
    }
}
