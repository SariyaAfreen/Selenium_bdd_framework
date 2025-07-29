package stepDefinitions;

import io.cucumber.java.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import utilities.ExtentReportManager;

import java.time.Duration;

public class Hooks {

    public static WebDriver driver;

    @Before
    public void beforeScenario(Scenario scenario) {
        ExtentReportManager.startScenario(scenario.getName());

        // ✅ Set GeckoDriver path explicitly
        System.setProperty("webdriver.gecko.driver", "C://Users//Lenovo//Downloads//geckodriver.exe");

        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("--start-maximized");

        driver = new FirefoxDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();

        System.out.println("🚀 Browser launched for scenario: " + scenario.getName());
    }


    @After
    public void afterScenario(Scenario scenario) {
        // Log scenario status
        if (scenario.isFailed()) {
            ExtentReportManager.logInfo("❌ Scenario Failed: " + scenario.getName());
        } else {
            ExtentReportManager.logInfo("✅ Scenario Passed: " + scenario.getName());
        }

        // Flush Extent report
        ExtentReportManager.flushReports();

        // Quit WebDriver
        if (driver != null) {
            driver.quit();
            System.out.println("🛑 Browser closed for scenario: " + scenario.getName());
        }
    }
}
