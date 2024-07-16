package runnerfiles;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

public class Runner {

	@CucumberOptions(

			features = "src/test/java/featureFile", glue = { "stepdefinations" },

//			,plugin = { "pretty","html:target/cucumber-reports" },
			monochrome = true)

	public class TestNgRunner extends AbstractTestNGCucumberTests {

	}

}
