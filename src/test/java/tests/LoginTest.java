package tests;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;
import org.testng.annotations.*;
import utilities.BaseClass;
import java.time.Duration;
import java.util.Scanner;

public class LoginTest extends BaseClass {

    @BeforeMethod
    public void openBrowser() {
        setup();
        driver.get(config.getProperty("baseURL"));
    }

    @Test
    public void loginWithValidCredentials() throws InterruptedException {
        driver.findElement(By.id("username")).sendKeys("admin");
        driver.findElement(By.id("password")).sendKeys("admin#123");

        System.out.println("⚠️ Please solve the CAPTCHA manually and press ENTER...");
        new Scanner(System.in).nextLine();

        driver.findElement(By.id("submit")).click();

        try {
            Alert alert = driver.switchTo().alert();
            String alertText = alert.getText();
            System.out.println("✅ Alert appeared: " + alertText);
            alert.accept();
        } catch (NoAlertPresentException e) {
            System.out.println("⚠️ No alert appeared after login.");
        }

        Thread.sleep(2000);

        String expectedTitle = "SmartUniversity";
        String actualTitle = driver.getTitle();
        System.out.println("🔍 Page title after login: " + actualTitle);
        Assert.assertEquals(actualTitle, expectedTitle, "❌ Title mismatch after login");
        System.out.println("✅ Login test passed successfully");
    }

    @Test
    public void loginWithInvalidCredentials() {
        driver.findElement(By.id("username")).sendKeys("invalidUser");
        driver.findElement(By.id("password")).sendKeys("wrongPass");

        System.out.println("⚠️ Solve the CAPTCHA manually and press ENTER...");
        new Scanner(System.in).nextLine();

        driver.findElement(By.id("submit")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        try {
            // If alert appears
            Alert alert = wait.until(ExpectedConditions.alertIsPresent());
            String alertText = alert.getText();
            System.out.println("✅ Alert appeared: " + alertText);
            Assert.assertTrue(alertText.toLowerCase().contains("invalid"));
            alert.accept();
        } catch (TimeoutException e) {
            // If no alert, check for error message on page containing 'not correct'
            try {
                WebElement errorElement = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//*[contains(text(),'not correct')]")
                ));
                String errorMsg = errorElement.getText();
                System.out.println("✅ Error message displayed: " + errorMsg);
                Assert.assertTrue(errorMsg.toLowerCase().contains("not correct"));
            } catch (TimeoutException ex) {
                Assert.fail("❌ Neither alert nor error message appeared for invalid login.");
            }
        }
    }
    @Test
    public void loginWithoutPassword() {
        driver.findElement(By.id("username")).sendKeys("admin");

        System.out.println("⚠️ Solve the CAPTCHA manually and press ENTER...");
        new java.util.Scanner(System.in).nextLine();

        driver.findElement(By.id("submit")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        try {
            WebElement errorMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("passwordError"))); // Check exact ID
            String msg = errorMsg.getText();
            System.out.println("✅ Error message displayed: " + msg);
            Assert.assertTrue(msg.contains("Password cannot be empty"));
        } catch (TimeoutException e) {
            Assert.fail("❌ Password error message not displayed.");
        }
    }

    @Test
    public void loginWithoutCaptcha() {
        driver.findElement(By.id("username")).sendKeys("admin");
        driver.findElement(By.id("password")).sendKeys("admin#123");

        // Leave CAPTCHA field blank intentionally
        driver.findElement(By.id("submit")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        try {
            WebElement captchaError = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("captchaError"))); // Adjust id if needed
            String actualError = captchaError.getText();
            System.out.println("✅ Error message displayed: " + actualError);

            // Fix: Match exact message or partial string check
            Assert.assertTrue(actualError.contains("Captcha cannot be empty"), "❌ Unexpected error message.");
        } catch (TimeoutException e) {
            Assert.fail("❌ Captcha error message not displayed.");
        }
    }


    @Test
    public void loginWithIncorrectCaptcha() throws InterruptedException {
        driver.findElement(By.id("username")).sendKeys("admin");
        driver.findElement(By.id("password")).sendKeys("admin#123");

        // Enter incorrect CAPTCHA value
        driver.findElement(By.id("captcha")).sendKeys("999"); // replace 'captcha' with actual input's id attribute

        driver.findElement(By.id("submit")).click();

        // Wait for error message
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement errorMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("captchaError"))); // use actual error message element ID

        String errorText = errorMsg.getText();
        System.out.println("Actual error message: " + errorText);

        // Case-insensitive validation
        Assert.assertTrue(errorText.toLowerCase().contains("invalid captcha".toLowerCase()), "❌ Incorrect error message displayed.");
        System.out.println("✅ Captcha validation test passed.");
    }


    @Test
    public void checkUILabelsAndAlignment() {
        driver.get("https://webapps.tekstac.com/SeleniumApp1/SmartUniversity/login.html");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement usernameLabel = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[contains(text(),'Username')]")));
        Assert.assertTrue(usernameLabel.isDisplayed(), "Username label missing");

        WebElement passwordLabel = driver.findElement(By.xpath("//label[contains(text(),'Password')]"));
        Assert.assertTrue(passwordLabel.isDisplayed(), "Password label missing");

        // No Captcha label exists on this page — removed
        System.out.println("✅ Username and Password labels are present.");
    }


    @AfterMethod
    public void closeBrowser() {
        tearDown();
    }
}
