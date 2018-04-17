package stepdefs;

import cucumber.api.java.en.*;

public class StepDefStartingPoints {

	//Je gebruikt de StepDefinitions class als object om zijn methodes aan te roepen in onderstaande methoden door step. te gebruiken
	StepDefinitions step = new StepDefinitions();
	
//	@Given("^Open Metacom")
//	public void openMetacom() throws Exception {
//	}
		
//		step.waitForImageAndClick("click", "voorbeeld.PNG");
//		step.waitForImageAndClickWithSimilarity ("click", "nummer1.PNG", (float) 0.9);
//		step.waitForSeconds(1);
//		step.clickImageAndType("voorbeeld.png", "voorbeeldtekst hier");
//		step.typeText("voorbeeldtekst hier");
//		step.useKey("ENTER");
//		step.validateImageOnScreen("voorbeeld.PNG");
		

	@Given("^Go to VMA001-details")
	public void goToVMA001 () throws Exception {

		step.waitForSeconds(3);
		step.waitForImageAndClick("doubleclick", "menupadSysteem.PNG");
		step.waitForImageAndClick("doubleclick", "menupadSysteemStandaarden.PNG");
		step.waitForImageAndClick("doubleclick", "menupadSysteemStandaardenMenu.PNG");
		step.waitForSeconds(3);
		step.clickImageAndType("ohMenuZoek.PNG", "VMA001");
		step.validateImageOnScreen("ohMenuValidateVMA001.PNG");
		step.waitForImageAndClick("doubleclick", "ohMenuFavorietDetails.PNG");
	}
	}
