package util;

import java.util.ArrayList;

import android.util.Log;

public class XMLStringParser {
	public static String XML_BEGIN = "<xml>\n";
	public static String XML_END = "\n</xml>";
	public static String XML_END_NAME = "/xml";
	ArrayList<XMLStringObject> items;

	/**
	 * this is the root of the XML document. the root doesn't need a special
	 * name
	 */
	public XMLStringParser() {
		items = new ArrayList<XMLStringObject>(5);
	}

	public XMLStringParser(String input) throws XMLEOFException {
		// TODO: handle loading up username/pass
		items = new ArrayList<XMLStringObject>(5);

		String currentName = "";
		String currentValue = "";

		// i needs to be an array because we need to pass by reference
		// sorry for the hack
		int i[] = { 0 };
		// ignore the XML_BEGIN
		if (input.startsWith(XML_BEGIN))
			i[0] += XML_BEGIN.length();
		// read character by character, and load up
		while (i[0] < input.length()) {
			try {
				items.add(new XMLStringObject(input, i));
			} catch (XMLEOFException | StringIndexOutOfBoundsException e) {
				if (!e.getMessage().equals(XMLEOFException.SUCCESS_STRING))
					throw e;
			}
			i[0]++;
		}
	}

	public void addItem(XMLStringObject item) {
		items.add(item);
	}
	
	public String getChildsValue(String name){
		for (XMLStringObject item : items) {
			if(item.name.equals(name))
				return item.value;
		}
		return "";
	}

	public String toString() {
		String asString = "";
		asString += XML_BEGIN;
		for (XMLStringObject item : items) {
			asString += "\n" + item.toString(1) + "\n";
		}
		asString += XML_END;

		Log.e("XML result: ", asString);
		return asString;
	}

}