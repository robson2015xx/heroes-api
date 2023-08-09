package br.com.gubee.interview;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/java/br/com/gubee/interview/features",
        glue = "br.com.gubee.interview.glues",
        plugin = {"pretty"}
)
public class RunCucumberTest {
}
