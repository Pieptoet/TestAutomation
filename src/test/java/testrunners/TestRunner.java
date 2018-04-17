package testrunners;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = "src/test/resources/features"
		, plugin = { "pretty", "html:target/cucumber-html-report", "json:target/cucumber-json-report.json" }
		, glue = { "stepdefs", "utilities" }
// Doel is om Testen van toepassing te hebben en uit te sluiten (.feature files)
// tags van toepasssing: gebruik @..... vervolgens 'or' om meerdere van
// toepassing te hebben.
// tags niet van toepassing: gebruik @.... vervolgens 'and not' om meerdere uit
// te sluiten.
// vb:, tags = {("@reg_menu or @test..."),("not @Test1 and not @Test2")}
)
public class TestRunner {
}