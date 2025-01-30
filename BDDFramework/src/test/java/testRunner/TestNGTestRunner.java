package testRunner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = ".\\src\\test\\java\\features",
					glue = {"stepDefinitions"},
					tags = "@AddPlace",
					plugin = {"pretty", "html:target/Reports.html"}
				)
public class TestNGTestRunner extends  AbstractTestNGCucumberTests{

}
