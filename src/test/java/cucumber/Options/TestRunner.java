package cucumber.Options;


import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/java/features",
	plugin = "json:target/jsonReports/cucumber-reports.json",
	glue = {"stepDefinations"}//,
	//tags = {"@DeletePlace"}
)

public class TestRunner {

}
