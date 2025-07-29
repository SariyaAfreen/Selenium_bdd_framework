package stepDefinitions;

import io.cucumber.java.en.*;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import utilities.CSVReaderUtil;
import utilities.DriverSetup;

import java.util.List;

public class ProfessorRegistrationSteps {

    WebDriver driver;

    @Given("the user is on the professor registration page")
    public void the_user_is_on_the_professor_registration_page() {
        driver = DriverSetup.getDriver();
        driver.get("https://webapps.tekstac.com/SeleniumApp1/SmartUniversity/add_prof.html");
    }

    @When("the user selects {string} from the country dropdown")
    public void the_user_selects_country_from_the_country_dropdown(String country) {
        Select countrySelect = new Select(driver.findElement(By.id("country")));
        countrySelect.selectByVisibleText(country);
    }

    @Then("the state dropdown should be populated")
    public void the_state_dropdown_should_be_populated() {
        Select stateSelect = new Select(driver.findElement(By.id("state")));
        List<WebElement> options = stateSelect.getOptions();
        Assert.assertTrue(options.size() > 1, "State dropdown is not populated.");
    }

    @Then("the city dropdown should be populated")
    public void the_city_dropdown_should_be_populated() {
        Select citySelect = new Select(driver.findElement(By.id("city")));
        List<WebElement> options = citySelect.getOptions();
        Assert.assertTrue(options.size() > 1, "City dropdown is not populated.");
        driver.quit();
    }
}
