package Runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src/test/resources/features", glue = { "Stepdefinitions" }, plugin = { "pretty",
		"html:target/cucumber-reports.html" }, monochrome = true, tags = "@e2e or @navigation or @negative")

public class Runner extends AbstractTestNGCucumberTests {

}
