package main;

public interface CucumberConstants {

	//Waar kan Sikuli de resources (screenshots) vinden in het project (resources).
	public static final String resourcesFolder = "resources";
	
	//Waar moeten de printscreens aan het eind van ieder scenario/falen van scenario's opgeslagen worden.
	public static final String testResultPrintscreens = "C:\\testResultPrintscreens\\";
	
	// De wait for seconds methode maakt gebruik van seconds*factor (dus verander deze int om de wait for seconds te verhogen)
	public static final int factor = 1;
	
	/* Algemene instellingen voor methodes: 
	overallSimilarity = met welke similarity moeten afbeeldingen gevonden worden op scherm (standaard = 0.80 - 0.85)
	validationSimilarity = met welke similarity moeten afbeeldingen gevalideerd worden (standaard = 0.90 - 0.95)
	waitTime = het aantal seconden dat Sikuli er over kan doen om bepaalde afbeelding op scherm te vinden (standaard = 2) */ 
	public static final float overallSimilarity = (float) 0.85;
	public static final float validationSimilarity = (float) 0.97;
	public static final int waitTime = 2;
}