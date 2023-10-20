package saucedemo.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/java/resources/features",
        glue = "saucedemo/stepdefinitions"
)
public class TestRunner {
}
