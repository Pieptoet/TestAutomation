package main;

import org.sikuli.script.FindFailed;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;

public class ImageInteraction {

	static Screen screen = new Screen();
	// Methode die word uitgevoerd door de waitForListOfImagesAndClick methode uit Stepdefinitions.java
	public static void actionOnImageMap(String click, Pattern dummyPattern) throws FindFailed {

		switch (click) {
		case "click":
			screen.wait(dummyPattern.similar(CucumberConstants.overallSimilarity), CucumberConstants.waitTime).click();
			break;
		case "rightclick":
			screen.wait(dummyPattern.similar(CucumberConstants.overallSimilarity), CucumberConstants.waitTime).rightClick();
			break;
		case "highlight":
			screen.wait(dummyPattern.similar(CucumberConstants.overallSimilarity), CucumberConstants.waitTime).highlight(4);
			break;
		case "doubleclick":
			screen.wait(dummyPattern.similar(CucumberConstants.overallSimilarity), CucumberConstants.waitTime).doubleClick();
			break;
		}
	}
	// Methode die word uitgevoerd door de waitForImagesAndClickWithSimilarity methode uit Stepdefinitions.java
	public static void actionOnImageMapWithSimularity (String click, Pattern dummyPattern, float similarity) throws FindFailed {

	switch (click) {
	case "click":
		screen.wait(dummyPattern.similar((float) similarity), CucumberConstants.waitTime).click();
		break;
	case "highlight":
		screen.wait(dummyPattern.similar((float) similarity), CucumberConstants.waitTime).highlight(4);
		break;
	case "doubleclick":
		screen.wait(dummyPattern.similar((float) similarity), CucumberConstants.waitTime).doubleClick();
		break;
	case "rightclick":
		screen.wait(dummyPattern.similar((float) similarity), CucumberConstants.waitTime).rightClick();
		break;
	}
}
	}
