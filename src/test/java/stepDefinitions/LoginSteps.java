package stepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.en.*;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;
import utilities.BaseClass;

import java.time.Duration;
import java.util.Scanner;

public class LoginSteps extends BaseClass {

    WebDriverWait wait;

    @Given("the user is on the login page")
    public void the_user_is_on_the_login_page() {
        setup();
        driver.get(config.getProperty("baseURL"));
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @When("the user enters valid username and password")
    public void the_user_enters_valid_username_and_password() {
        driver.findElement(By.id("username")).sendKeys("admin");
        driver.findElement(By.id("password")).sendKeys("admin#123");
    }

    @When("the user enters invalid username and password")
    public void the_user_enters_invalid_username_and_password() {
        driver.findElement(By.id("username")).sendKeys("invalidUser");
        driver.findElement(By.id("password")).sendKeys("wrongPass");
    }

    @When("the user enters valid username")
    public void the_user_enters_valid_username() {
        driver.findElement(By.id("username")).sendKeys("admin");
    }

    @When("solves the captcha")
    public void solves_the_captcha() {
        System.out.println("⚠️ Please solve the CAPTCHA manually and press ENTER...");
        new Scanner(System.in).nextLine();
    }

    @When("clicks the login button")
    public void clicks_the_login_button() {
        driver.findElement(By.id("submit")).click();
    }

    @When("clicks the login button without captcha")
    public void clicks_the_login_button_without_captcha() {
        driver.findElement(By.id("submit")).click();
    }

    @When("enters invalid captcha")
    public void enters_invalid_captcha() {
        driver.findElement(By.id("captcha")).sendKeys("999");
    }

    @Then("the user should be redirected to the SmartUniversity dashboard")
    public void the_user_should_be_redirected_to_the_dashboard() {
        try {
            Alert alert = driver.switchTo().alert();
            System.out.println("✅ Alert: " + alert.getText());
            alert.accept();
        } catch (NoAlertPresentException ignored) {}

        Assert.assertEquals(driver.getTitle(), "SmartUniversity");
    }

    @Then("an alert with message containing {string} should appear")
    public void alert_with_message_should_appear(String keyword) {
        try {
            Alert alert = wait.until(ExpectedConditions.alertIsPresent());
            String alertText = alert.getText();
            System.out.println("✅ Alert Text Found: " + alertText);
            Assert.assertTrue(alertText.toLowerCase().contains(keyword.toLowerCase()));
            alert.accept();
        } catch (TimeoutException e) {
            System.out.println("⚠️ No alert appeared. Checking inline error messages...");

            boolean matchFound = false;
            String[] errorIds = { "usernameError", "passwordError", "captchaError" };

            for (String id : errorIds) {
                try {
                    WebElement error = driver.findElement(By.id(id));
                    String msg = error.getText().toLowerCase();

                    if (msg.contains(keyword.toLowerCase())
                            || msg.contains("invalid")
                            || msg.contains("incorrect")
                            || msg.contains("wrong")
                            || msg.contains("failed")) {

                        System.out.println("✅ Inline Error Found in <" + id + ">: " + msg);
                        matchFound = true;
                        break;
                    }
                } catch (NoSuchElementException ignored) {}
            }

            if (!matchFound) {
                Assert.fail("❌ Neither alert nor inline error with keyword '" + keyword + "' or similar was shown.");
            }
        }
    }

    @Then("a password required error should be displayed")
    public void a_password_required_error_should_be_displayed() {
        WebElement error = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("passwordError")));
        Assert.assertTrue(error.getText().contains("Password cannot be empty"));
    }

    @Then("a captcha required error should be displayed")
    public void a_captcha_required_error_should_be_displayed() {
        WebElement error = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("captchaError")));
        Assert.assertTrue(error.getText().contains("Captcha cannot be empty"));
    }

    @Then("an error message for invalid captcha should be displayed")
    public void error_message_for_invalid_captcha_should_be_displayed() {
        WebElement error = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("captchaError")));
        Assert.assertTrue(error.getText().toLowerCase().contains("invalid captcha"));
    }

    @Then("all input field labels should be properly displayed")
    public void all_labels_should_be_displayed() {
        Assert.assertTrue(driver.findElement(By.xpath("//label[contains(text(),'Username')]")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//label[contains(text(),'Password')]")).isDisplayed());
        System.out.println("✅ All labels are visible");
    }

    @After
    public void tearDownTest() {
        tearDown();
    }
}
