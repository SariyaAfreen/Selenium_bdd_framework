package runners;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

// Run with JUnit
@RunWith(Cucumber.class)
@CucumberOptions(
    // Path to feature files
    features = "src/test/resources/features",

    // Path to step definition packages
    glue = "stepDefinitions",

    // To generate readable console output and reports
    plugin = {
        "pretty",
        "html:target/cucumber-reports.html",
        "json:target/cucumber-reports.json"
    },

    // Optional settings
    monochrome = true
)
public class TestRunner {
}
