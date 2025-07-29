package stepDefinitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;

import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;
import java.util.Map;

import static org.testng.Assert.*;

public class DobValidRange {

    WebDriver driver = Hooks.driver;
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

    @Given("the user is on the Add Professor page")
    public void the_user_is_on_the_add_professor_page() {
        driver.get("https://webapps.tekstac.com/SeleniumApp1/SmartUniversity/add_prof.html");
        try {
            // ✅ Wait for a stable field like 'firstName', not <h1>
            wait.until(ExpectedConditions.presenceOfElementLocated(By.id("firstName")));
            System.out.println("✅ Add Professor form loaded successfully.");
        } catch (TimeoutException e) {
            System.out.println("❌ Failed to load Add Professor form: " + e.getMessage());
            throw e;
        }
    }

    @When("the user fills the form with details:")
    public void the_user_fills_the_form_with_details(DataTable dataTable) {
        Map<String, String> data = dataTable.asMap(String.class, String.class);

        try {
            driver.findElement(By.id("firstName")).clear();
            driver.findElement(By.id("firstName")).sendKeys(data.get("First Name"));

            driver.findElement(By.id("lastName")).clear();
            driver.findElement(By.id("lastName")).sendKeys(data.get("Last Name"));

            driver.findElement(By.id("phoneNo")).clear();
            driver.findElement(By.id("phoneNo")).sendKeys(data.get("Phone"));

            driver.findElement(By.id("emaiId")).clear();
            driver.findElement(By.id("emaiId")).sendKeys(data.get("Email"));

            driver.findElement(By.id("dob")).clear();
            driver.findElement(By.id("dob")).sendKeys(data.get("DOB"));

            // Gender radio buttons
            if (data.get("Gender").equalsIgnoreCase("male")) {
                driver.findElement(By.id("male")).click();
            } else if (data.get("Gender").equalsIgnoreCase("female")) {
                driver.findElement(By.id("female")).click();
            }

            // Department dropdown
            new Select(driver.findElement(By.id("department")))
                    .selectByVisibleText(data.get("Department"));

            // Qualification dropdown
            wait.until(ExpectedConditions.presenceOfElementLocated(By.id("qualification")));
            new Select(driver.findElement(By.id("qualification")))
                    .selectByVisibleText(data.get("Qualification"));

            // Country dropdown
            new Select(driver.findElement(By.id("country")))
                    .selectByVisibleText(data.get("Country"));

            // State dropdown
            wait.until(ExpectedConditions.presenceOfElementLocated(By.id("state")));
            new Select(driver.findElement(By.id("state")))
                    .selectByVisibleText(data.get("State"));

            // City dropdown
            wait.until(ExpectedConditions.presenceOfElementLocated(By.id("city")));
            new Select(driver.findElement(By.id("city")))
                    .selectByVisibleText(data.get("City"));

            LogManager.getLogger(getClass()).info("All form fields filled successfully.");

        } catch (NoSuchElementException e) {
            LogManager.getLogger(getClass()).error("Element not found: " + e.getMessage());
            Assert.fail("Failed to locate one of the form fields: " + e.getMessage());
        } catch (Exception e) {
            LogManager.getLogger(getClass()).error("Error during form fill: " + e.getMessage());
            Assert.fail("Error while filling the form: " + e.getMessage());
        }
    }



    @And("the user submits the form")
    public void the_user_submits_the_form() {
        driver.findElement(By.id("submit")).click();
        System.out.println("📤 Form submitted.");
    }

    @Then("the form should not be submitted due to invalid DOB")
    public void the_form_should_not_be_submitted_due_to_invalid_dob() {
        try {
            WebElement dobError = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("dobError")));
            String errorText = dobError.getText().toLowerCase();
            assertTrue(errorText.contains("invalid") || errorText.contains("date"), "❌ DOB error message not as expected.");
            System.out.println("✅ DOB validation error displayed: " + errorText);
        } catch (TimeoutException | NoSuchElementException e) {
            fail("❌ DOB error message not found.");
        }
    }
}
