package main;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.imageio.ImageIO;

import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;
import org.sikuli.script.ScreenImage;

import stepdefs.StepDefinitions;

public class EndingOfScenario {

	Screen screen = new Screen();
	StepDefinitions step = new StepDefinitions();
	File file = new File(CucumberConstants.resourcesFolder);

	// Hier zou je de dateformat stijl aan kunnen passen
	DateFormat df = new SimpleDateFormat("yyyy-MM-dd--HH-mm-ss");
	String dateFormat = df.format(Calendar.getInstance().getTime());

	// Methode om printscreen te maken aan het einde van ieder scenario
	public void createPrintscreen() throws IOException {

		ScreenImage dummyPrintScreen = screen.capture(screen.getBounds());

		// Hier kan je de plaats waar de printscreen word opgeslagen aanpassen (en de tekst die in het html-report word weergegeven)
		ImageIO.write(dummyPrintScreen.getImage(), "PNG", new File(CucumberConstants.testResultPrintscreens + dateFormat + ".PNG"));
	}

	// methode om naar hoofdscherm Metacom te returnen aan het einde van ieder scenario
	public void returnToMainScreen() throws Exception {

		step.waitForSeconds(1);
		step.useKeyCombination("CTRL", "b");
		step.waitForSeconds(1);

		String image = "jaNeeMelding.PNG";
		String imagePath = SearchFileStructure.searchImage(file, image);
		Pattern check = new Pattern(imagePath);

		if (screen.exists(check.similar(CucumberConstants.validationSimilarity)) != null) {
			// wat als hij afbeelding wel kan vinden
			step.waitForImageAndClick("click", "neeMeldingZonderFocus.PNG");
			step.waitForSeconds(1);
			step.useKey("ENTER");
		} else {
			// wat als hij afbeelding niet kan vinden
			step.useKey("ENTER");
		}
	}
}
