package Runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "/Users/md.imranhassan/eclipse-workspace-newversion/qaproject/src/test/resources/features", glue = {
		"Stepdefinitions" }, plugin = { "pretty",
				"html:target/cucumber-reports.html" }, monochrome = true, tags = "@e2epurchase")

public class Runner extends AbstractTestNGCucumberTests {

}
