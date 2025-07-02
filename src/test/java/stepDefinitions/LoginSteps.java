package stepDefinitions;

import com.aventstack.extentreports.*;
import utilities.ExtentManager;
import io.cucumber.java.en.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utilities.BaseClass;

public class LoginSteps {

    WebDriver driver;
    ExtentReports extent = ExtentManager.getInstance();
    ExtentTest test;

    @Given("User launches the browser")
    public void user_launches_browser() {
        test = extent.createTest("Login Feature - Launch Browser");
        BaseClass.setup();
        driver = BaseClass.driver;
        test.pass("Browser launched successfully");
    }

    @When("User opens the login page")
    public void user_opens_login_page() {
        driver.get("https://webapps.tekstac.com/SeleniumApp1/SmartUniversity/login.html");
        test.pass("Navigated to Smart University login page");
    }

    @Then("User enters valid credentials and clicks login")
    public void user_enters_valid_credentials_and_clicks_login() {
        driver.findElement(By.id("username")).sendKeys("admin");
        driver.findElement(By.id("password")).sendKeys("admin#123");
        test.info("Entered username and password");

        System.out.println("⚠️ Please solve the CAPTCHA in the browser manually, then press ENTER in console to continue...");
        new java.util.Scanner(System.in).nextLine();
        test.info("CAPTCHA solved manually by user");

        driver.findElement(By.id("submit")).click();
        test.pass("Clicked on login button");
    }

    @Then("User should be logged in successfully")
    public void user_should_be_logged_in_successfully() {
        String expectedTitle = "Smart University Dashboard";
        String actualTitle = driver.getTitle();

        if (actualTitle.equals(expectedTitle)) {
            test.pass("Login successful. Title matched: " + actualTitle);
            System.out.println("✅ Login successful — Test Passed");
        } else {
            test.fail("Login failed. Expected title: " + expectedTitle + ", but got: " + actualTitle);
            System.out.println("❌ Login failed — Test Failed");
        }

        BaseClass.tearDown();
        test.info("Browser closed successfully");

        // Flush ExtentReport after test is done
        extent.flush();
    }
}
