// bestands locatie: http://vmsubversion.vanmeijel.nl/svn/metacom/MetacomCucumberTest/Trunk
/* 	-The *GIVEN* part describes the state of the world before you begin the behavior you're 
specifying in this scenario. You can think of it as the pre-conditions to the test.
	-The *WHEN* section is that behavior that you're specifying.
	-Finally the *THEN* section describes the changes you expect due to the specified behavior.
*/
package stepdefs;

import java.io.File;
import java.util.List;
import org.sikuli.basics.Debug;
import org.sikuli.basics.Settings;
import org.sikuli.script.*;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import cucumber.api.java.en.Then;
import main.CucumberConstants;
import main.ImageInteraction;
import main.KeyMaps;
import main.SearchFileStructure;

public class StepDefinitions {

	// Je maakt een File aan die gevuld word met de resourcesFolder die in de CucumberConstants word aangegeven. Deze file word gebruikt in de methodes hieronder.
	File file = new File(CucumberConstants.resourcesFolder);
	// je maakt een screen object die je vervolgens in de methoden aanroept en activeert door bijvoorbeeld: screen.type te doen.
	Screen screen = new Screen();

	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	// vanaf hier de basis sikuli methodes (commando's)

	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	// Methode om meerdere keren afbeeldingen te klikken d.m.v. list van Strings.
	@Given("^Wait for image, then \"((?i)[^\\\"]*)\" on images: (.*)")
	public void waitForListOfImagesAndClick(String click, List<String> images) throws Exception {

		String imagePath = null;

		for (String image : images) {
			// Gaat naar SearchFileStructure.java
			imagePath = SearchFileStructure.searchImage(file, image);
			Pattern dummyPattern = new Pattern(imagePath);

			try {
				// Gaat naar ImageInteraction.java
				ImageInteraction.actionOnImageMap(click, dummyPattern);
			} catch (Exception e) {
				throw new Exception("Couldn't find " + image);
			}
		}
	}
	@Given("^Wait for image, then \"((?i)[^\\\"]*)\" on \"((?i)[^\"]*)\"$")
	public void waitForImageAndClick(String click, String image) throws Exception {
		// Gaat naar SearchFileStructure.java
		String imagePath = SearchFileStructure.searchImage(file, image);
		Pattern dummyPattern = new Pattern(imagePath);

		try {
			// Gaat naar ImageInteraction.java
			ImageInteraction.actionOnImageMap(click, dummyPattern);
		} catch (Exception e) {
			Debug.user("couldn't find: " + image);
			throw new Exception("Couldn't find " + image);
		}
	}

	// Click|doubleclick|highlight op image met een similarity % die je zelf kan instellen
	@Given("^Wait for image, then \"((?i)[^\\\"]*)\" with similarity of [\\-\\+]?[0-9]*(\\.[0-9]+)? on images: (.*)")
	public void waitForImagesAndClickWithSimilarity(String click, float similarity, List<String> images)
			throws Exception {

		String imagePath = null;

		for (String image : images) {
			// Gaat naar SearchFileStructure.java
			imagePath = SearchFileStructure.searchImage(file, image);
			Pattern dummyPattern = new Pattern(imagePath);

			try {
				// Gaat naar ImageInteraction.java
				ImageInteraction.actionOnImageMapWithSimularity(click, dummyPattern, similarity);
			} catch (Exception e) {
				throw new Exception("Couldn't find: " + image);
			}
		}
	}

	// Methode om toets in te drukken d.m.v. KeyEvent. Het is mogelijk zelf nieuwe toetsen toe te voegen.
	@When("^Use key: \"([^\"]*)\"$")
	public void useKey(String key) throws Exception {

		int iKey = 0;
		try {
			// Gaat naar KeyMaps.java
			iKey = KeyMaps.keyMap(key);
			if (iKey != 0)
				screen.keyDown(iKey);
			screen.keyUp(iKey);

		} catch (Exception e) {
			throw new Exception("could not find " + key + "as a listed key!");
		}
	}

	/* methode om toetsencombinaties te gebruiken. screen.type("b",
	 * KeyModifier.CTRL); = deze gebruiken met een key modifer en een gewone letter (ctrl + B)*/
	@Then("^Use key combination: \"((?i)[^\\\"]*)\" and \"([^\"]*)\"$")
	public void useKeyCombination(String specialKey, String key) throws Exception {

		// Gaat naar KeyMaps.java
		int keyModifier = KeyMaps.keyModifiersMap(specialKey);

		try {
			screen.type(key, keyModifier);
		} catch (Exception e) {
			throw new Exception("could not find " + key + "as a listed key combination!");
		}
	}

	/* methode om speciale toetsencombinaties te gebruiken. screen.type(Key.F4,
	 * Key.ALT); screen.keyUp(); = gebruiken wanneer er twee keymodifer gebruikt
	 * worden (ALT - F4)*/
	@Then("^Use special key combination: \"((?i)[^\\\"]*)\" and \"([^\"]*)\"$")
	public void useSpecialKeyCombination(String key, String key2) throws Exception {

		// Gaat naar KeyMaps.java
		int firstKey = KeyMaps.keyMap(key);
		int secondKey = KeyMaps.keyMap(key2);

		try {
			screen.keyDown(firstKey);
			screen.keyDown(secondKey);
			screen.keyUp();
		} catch (Exception e) {
			throw new Exception("could not find " + key + "as a listed key combination!");
		}
	}

	// Methode om tekst te laten typen
	@Then("^Type in: \"([^\"]*)\"$")
	public void typeText(String text) throws Exception {

		screen.type(text);
	}

	// Methode om op de image te klikken en daar tekst in te typen
	@Then("^Click on \"((?i)[^\\\"]*)\" then type: \"((?i)[^\\\"]*)\"$")
	public void clickImageAndType(String image, String text) throws Exception {

		// Gaat naar SearchFileStructure.java
		String imagePath = SearchFileStructure.searchImage(file, image);
		Pattern dummyPattern = new Pattern(imagePath);

		try {
			screen.type(dummyPattern.similar(CucumberConstants.overallSimilarity), text);
		} catch (Exception e) {
			Debug.user("couldn't find: " + image);
			throw new Exception("Couldn't find: " + image);
		}
	}

	// Methode om het scherm tijd te geven te laden (in seconden)
	@Then("^Wait for: (\\d+) seconds$")
	public void waitForSeconds(int seconds) throws Exception {

		try {
			screen.wait((float) seconds * CucumberConstants.factor);
		} catch (Exception e) {
		}
	}

	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	/* vanaf hier de sikuli methodes in verschillende combinaties bijv: meerdere
	 afbeeldingen, toets+typen combo, met de muis een gebied selecteren op basis van pixellocatie,*/

	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	// Methode om toets in te drukken en vervolgens tekst te typen
	@Then("^Use key: \"((?i)[^\\\"]*)\" and type: \"((?i)[^\\\"]*)\"$")
	public void useKeyAndType(String key, String text) throws Exception {

		int iKey = 0;
		try {
			// Gaat naar KeyMaps.java
			iKey = KeyMaps.keyMap(key);

			if (iKey != 0)
				screen.keyDown(iKey);
			screen.keyUp(iKey);
		} catch (Exception e) {
			throw new Exception("couldn't find " + key + "as a listed key!");
		}
		screen.type(text);
	}

	// Methode om meerdere validaties als list door te geven op een regel in feature
	// file.
	@Then("^Try to find images: (.*)")
	public void validateListOfImagesOnScreen(List<String> images) throws Exception {

		String imagePath = null;

		for (String image : images) {
			// Gaat naar SearchFileStructure.java
			imagePath = SearchFileStructure.searchImage(file, image);
			Pattern dummyPattern = new Pattern(imagePath);

			try {
				screen.exists(dummyPattern.similar(CucumberConstants.validationSimilarity), CucumberConstants.waitTime);
			} catch (Exception e) {
				Debug.user("couldn't find" + image);
				throw new Exception("Couldn't find: " + image);
			}
		}
	}
	
	// Methode om te kijken of een Image op het scherm aanwezig is (doet verder niets), kan gebruikt worden als validatie.
	@Then("^Try to find: \"((?i)[^\\\"]*)\"$")
	public void validateImageOnScreen(String image) throws Exception {

		// Gaat naar SearchFileStructure.java
		String imagePath = SearchFileStructure.searchImage(file, image);
		Pattern dummyPattern = new Pattern(imagePath);

		try {
			screen.exists(dummyPattern.similar(CucumberConstants.validationSimilarity), CucumberConstants.waitTime);
		} catch (Exception e) {
			Debug.user("couldn't find" + image);
			throw new Exception("Couldn't find: " + image);
		}
	}
	
	// methode om een bepaald gebied te selecteren door de muis van beginlocatie tot eindlocatie met de knop ingedrukt te laten slepen
	@Given("^start location: (\\d+) and (\\d+) , end location: (\\d+) and (\\d+)$")
	public void selectArea(int loc1, int loc2, int loc3, int loc4) throws Exception {

		Location startLocation = new Location(loc1, loc2);
		Location endLocation = new Location(loc3, loc4);

		screen.mouseMove(startLocation);
		screen.mouseDown(Button.LEFT);
		screen.mouseMove(endLocation);
		screen.mouseUp(Button.LEFT);
	}
}
