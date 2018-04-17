package utilities;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import main.CucumberConstants;
import main.EndingOfScenario;

public class Hooks {

	private Scenario scenario;
	EndingOfScenario EndScenario = new EndingOfScenario();

	// Hier zou je de dateformat stijl aan kunnen passen
	DateFormat df = new SimpleDateFormat("yyyy-MM-dd--HH-mm-ss");
	String dateFormat = df.format(Calendar.getInstance().getTime());

	// Dit wordt voor ieder scenario uitgevoerd
	@Before
	public void beforeScenario(Scenario scenario) {
		
		this.scenario = scenario;
	}

	// Dit word na ieder scenario uitgevoerd (screenshot opgeslagen als date/time in de map die aangegeven is)
	@After
	public void afterScenario() throws Exception {
		
		// gaat naar EndingScenario.java - createPrintscreen methode
//		EndScenario.createPrintscreen();
		// De tekst die hieronder staat beschreven is in het HTML report terug te vinden (index.html) na ieder gemaakte printscreen (einde van ieder scenario).
		scenario.write("Printscreen opgeslagen als:" + CucumberConstants.testResultPrintscreens + dateFormat);
		// gaat naar EndingScenario.java - returnToMainScreen methode
		EndScenario.returnToMainScreen();
	}
}
