package utilities;

/*PLEASE DO NOT MAKE ANY CHANGES OR MOFICATIONS IN THIS PROGRAM */
 
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
 
 
public class DriverSetup {  // DO NOT CHANGE THE CLASS NAME
    private static WebDriver driver;
    public static WebDriver getDriver() {    // DO NOT CHANGE THE METHOD SIGNATURE
	    System.setProperty("webdriver.gecko.driver", "C://Users//Lenovo//Downloads//geckodriver.exe");
		driver = new FirefoxDriver();
	    return driver;
 
	}
}