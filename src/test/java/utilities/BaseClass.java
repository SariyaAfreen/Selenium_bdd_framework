package utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BaseClass {
    public static WebDriver driver;

    // Method to initialize WebDriver
    public static void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    // Method to close WebDriver
    public static void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
