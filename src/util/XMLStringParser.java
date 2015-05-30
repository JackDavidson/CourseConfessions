package util;

import java.util.ArrayList;

import android.util.Log;

public class XMLStringParser {
	public static String XML_BEGIN = "<xml>\n";
	public static String XML_END = "\n</xml>";
	public static String XML_END_NAME = "/xml";
	ArrayList<XMLStringObject> children;

	/**
	 * this is the root of the XML document. the root doesn't need a special
	 * name
	 */
	// one of the ctors for the class, initializes arraylist items to size 5.
	public XMLStringParser() {
		children = new ArrayList<XMLStringObject>(5);
	}

	// another ctor for the class, initializes arraylist items to size 5,
	// and fills the items arraylist with the xml name value pairs.
	public XMLStringParser(String input) throws XMLEOFException {
		// TODO: handle loading up username/pass
		children = new ArrayList<XMLStringObject>(5);

		// i needs to be an array because we need to pass by reference
		// sorry for the hack
		int i[] = { 0 };
		// ignore the XML_BEGIN
		if (input.startsWith(XML_BEGIN))
			i[0] += XML_BEGIN.length();
		// read character by character, and load up
		while (i[0] < input.length()) {
			try {
				children.add(new XMLStringObject(input, i));
			} catch (XMLEOFException | StringIndexOutOfBoundsException e) {
				if (!e.getMessage().equals(XMLEOFException.SUCCESS_STRING))
					throw e;
			}
			i[0]++;
		}
	}

	// add an item to the arraylist
	public void addItem(XMLStringObject item) {
		children.add(item);
	}

	// return the value of the item with a certain name
	public String getChildsValue(String name) {
		for (XMLStringObject item : children) {
			if (item.name.equals(name))
				return item.value;
		}
		return "";
	}

	public XMLStringObject getChild(String name) {
		for (XMLStringObject item : children) {
			if (item.name.equals(name))
				return item;
		}
		return null;
	}

	// toString method for the xml string parser.
	public String toString() {
		String asString = "";
		asString += XML_BEGIN;
		for (XMLStringObject item : children) {
			asString += "\n" + item.toString(1) + "\n";
		}
		asString += XML_END;

		Log.e("XML result: ", asString);
		return asString;
	}

}
