package com.obsqura.testngcommands;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.ArrayList;
import java.util.List;

public class SeleniumTestNG {
    WebDriver driver;

    public void testInitialize(String browser) {
        if (browser.equalsIgnoreCase("chrome")) {
            System.setProperty("webdriver.chrome.driver", "C:\\Selenium\\driverfiles\\chromedriver.exe");
            driver = new ChromeDriver();
        } else if (browser.equalsIgnoreCase("edge")) {
            System.setProperty("webdriver.edge.driver", "C:\\Selenium\\driverfiles\\msedgedriver.exe");
            driver = new EdgeDriver();
        } else if (browser.equalsIgnoreCase("firefox")) {
            System.setProperty("webdriver.gecko.driver", "C:\\Selenium\\driverfiles\\geckodriver-v0.30.0-win32\\geckodriver.exe");
            driver = new FirefoxDriver();
        }
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        // driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
    }
    @BeforeMethod
    @Parameters({"browser","url"})
    public void setup(String browserName,String baseUrl) {
        testInitialize(browserName);
        //driver.get(baseUrl);
    }

    @AfterMethod
    public void tearDown() {

        driver.close();
        // driver.quit();
    }
    @Test(enabled = true,priority = 1,dataProvider = "loginData")
    public void verifyUserLogin(String uName,String pWord) {
        WebElement loginMenu = driver.findElement(By.className("ico-login"));
        loginMenu.click();
        WebElement email = driver.findElement(By.id("Email"));
        email.sendKeys(uName);
        WebElement password = driver.findElement(By.id("Password"));
        password.sendKeys(pWord);
        WebElement submit = driver.findElement(By.xpath("//input[@class='button-1 login-button']"));
        submit.click();
        WebElement userName = driver.findElement(By.xpath("//div[@class='header-links']//a[@class='account']"));
        String actualUserName = userName.getText();
        String expectedUserName = uName;
        Assert.assertEquals(actualUserName, expectedUserName, "log-in failed");
    }
    @DataProvider(name="loginData")
    public Object[][] userCreditionals(){
        Object[][] data=new Object[2][2];
        data[0][0]="aleenamariya97@gmail.com";
        data[0][1]="aleena97";
        data[1][0]="navyanaveenam@gmail.com";
        data[1][1]="Rithul@12";
        return data;
    }

    @Test(enabled = false,priority = 2)
    public void verifyTitle() {
        String expectedTitle = "Demo Web Shop";
        String actualTitle = driver.getTitle();
        Assert.assertEquals(actualTitle, expectedTitle, "Title Mismatch");
    }
    @Test()
    public void verificationOfTable(){
        driver.get("www.w3schools.com/html/html_tables.asp");
        List<WebElement> rowElement= driver.findElements(By.xpath("//table[@id='customers']//tr"));
        List<WebElement>coloumElement= driver.findElements(By.xpath("//table[@id='customers']//tr/td"));
        List<ArrayList<String>> getGridData=TableUtility.getGridData( rowElement,coloumElement);
        System.out.println(getGridData);
    }
}

