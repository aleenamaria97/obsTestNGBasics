package com.obsqura.testngcommands;

import com.relevantcodes.extentreports.ExtentReports;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class ExtentReportLog {
    WebDriver driver;
    public ExtentReports report;
    static ExtentTest test;

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
    @BeforeTest
    public void setReport() {
        report = new ExtentReports(System.getProperty("user.dir") + "//test-output//Extent.html", true);
        test = report.startTest("ExtentReport");
    }
    @BeforeMethod
    @Parameters({"browser", "url"})
    public void setup(String browserName, String baseUrl) {
        testInitialize(browserName);
        driver.get(baseUrl);
        test.log(LogStatus.PASS, "Navigated to the  URL");
    }

    @AfterMethod
    public void tearDown() {
        driver.close();
        test.log(LogStatus.PASS, " driver closed");
        // driver.quit();
    }
    @AfterTest
    public void endReport() {
        report.endTest(test);
        report.flush();
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
        test.log(LogStatus.PASS, "login success");
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

    @Test(priority = 2,enabled = true)
    public void verifyTitle() {
        String expectedTitle = "Demo Web Shop";
        String actualTitle = driver.getTitle();
        Assert.assertEquals(actualTitle, expectedTitle, "Values not matching");
        test.log(LogStatus.PASS, "Successfully Asserted");
    }
}
