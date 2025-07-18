package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.DriverSetup;

import java.util.List;

public class ProfessorRegistrationTest {

    WebDriver driver;

    @BeforeClass
    public void setUp() {
        driver = DriverSetup.getDriver();
        driver.manage().window().maximize();
        driver.get("https://webapps.tekstac.com/SeleniumApp1/SmartUniversity/add_prof.html");
    }

    @Test
    public void validateStateAndCityDropdownsForIndia() {

        // Select 'India' from Country dropdown
        WebElement countryDropdown = driver.findElement(By.id("country"));
        Select countrySelect = new Select(countryDropdown);
        countrySelect.selectByVisibleText("India");

        // Get State dropdown options
        WebElement stateDropdown = driver.findElement(By.id("state"));
        Select stateSelect = new Select(stateDropdown);
        List<WebElement> stateOptions = stateSelect.getOptions();

        // Assert states available for India
        Assert.assertTrue(stateOptions.size() > 1, "States should be populated for India");

        System.out.println("Available states:");
        for (WebElement option : stateOptions) {
            System.out.println(option.getText());
        }

        // Get City dropdown options
        WebElement cityDropdown = driver.findElement(By.id("city"));
        Select citySelect = new Select(cityDropdown);
        List<WebElement> cityOptions = citySelect.getOptions();

        // Assert cities available for India
        Assert.assertTrue(cityOptions.size() > 1, "Cities should be populated for India");

        System.out.println("Available cities:");
        for (WebElement option : cityOptions) {
            System.out.println(option.getText());
        }
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
