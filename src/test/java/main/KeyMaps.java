package main;

import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

import org.sikuli.script.KeyModifier;
import org.sikuli.script.Screen;

public class KeyMaps {
	
	static Screen screen = new Screen();
	
	/* Dit is de Hashmap voor de methode om key in te drukken. Hier kan je nieuwe key's toevoegen mocht dit nodig zijn.
	* Deze methode word aangesproken door de volgende methodes: useSpecialKeyCombination, useKey, useKeyAndType in StepDefinitions.java  */
	public static int keyMap(String key) {

		Map<String, Integer> keyMap = new HashMap<String, Integer>();
		keyMap.put("CTRL", KeyEvent.VK_CONTROL);
		keyMap.put("ALT", KeyEvent.VK_ALT);
		keyMap.put("ENTER", KeyEvent.VK_ENTER);
		keyMap.put("SPACE", KeyEvent.VK_SPACE);
		keyMap.put("WINDOWS", KeyEvent.VK_WINDOWS);
		keyMap.put("SHIFT", KeyEvent.VK_SHIFT);
		keyMap.put("TAB", KeyEvent.VK_TAB);
		keyMap.put("END", KeyEvent.VK_END);
		keyMap.put("BACKSPACE", KeyEvent.VK_BACK_SPACE);

		keyMap.put("PLUS", KeyEvent.VK_ADD);
		keyMap.put("MIN", KeyEvent.VK_SUBTRACT);
		keyMap.put("UP", KeyEvent.VK_UP);
		keyMap.put("DOWN", KeyEvent.VK_DOWN);

		keyMap.put("F2", KeyEvent.VK_F2);
		keyMap.put("F3", KeyEvent.VK_F3);
		keyMap.put("F4", KeyEvent.VK_F4);
		keyMap.put("F7", KeyEvent.VK_F7);
		keyMap.put("F8", KeyEvent.VK_F8);
		
		keyMap.put("PAGEUP", KeyEvent.VK_PAGE_UP);
		
		return keyMap.get(key);
	}
	/* Dit is de keymodifier map die gebruikt word voor de useKeyCombination methode in Stepdefinitions.java */
	public static int keyModifiersMap (String key) {
		
		Map<String, Integer> keyModifiers = new HashMap<>();
		keyModifiers.put("CTRL", KeyModifier.CTRL);
		keyModifiers.put("ALT", KeyModifier.ALT);
		keyModifiers.put("SHIFT", KeyModifier.SHIFT);
		
		return keyModifiers.get(key);
	}
}
