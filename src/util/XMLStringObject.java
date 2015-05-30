package util;

import java.util.ArrayList;

import android.util.Log;

public class XMLStringObject {
	String name;
	String value;
	ArrayList<XMLStringObject> items;

	/**
	 * these are the sub-objects in an xml file. think of them like nodes in a
	 * tree
	 * 
	 * @param name
	 * @param value
	 */
	// initialize the name value pairs in the constructor
	public XMLStringObject(String name, String value) {
		this.name = name;
		this.value = value;
		items = new ArrayList<XMLStringObject>(5);
	}

	// another constructor, reads an XML file for the name value pair
	public XMLStringObject(String fileContents, int[] position)
			throws XMLEOFException {
		this("", "");
		// go to the starting "<"
		while (fileContents.charAt(position[0]) != '<') {
			position[0]++;
		}
		position[0]++;
		// everything until ending ">" is part of name
		while (fileContents.charAt(position[0]) != '>') {
			this.name += fileContents.charAt(position[0]);
			position[0]++;
		}
		position[0]++;

		if (this.name.equals(XMLStringParser.XML_END_NAME)) // successful EOF?
			throw new XMLEOFException(XMLEOFException.SUCCESS_STRING);

		// now, everything until the next "<" is part of value
		while (fileContents.charAt(position[0]) != '<') {
			this.value += fileContents.charAt(position[0]);
			position[0]++;
		}
		// finally, lets read in the ending name. it should
		// match. if not, then we need to add a sub-object
		int endNamePos = 1;
		String endName = "";
		while (fileContents.charAt(position[0] + endNamePos) != '>') {
			endName += fileContents.charAt(position[0] + endNamePos);
			endNamePos++;
		}
		// all done
		if (endName.equals("/" + this.name)) {
			// we don't have any children
			position[0] += (endNamePos + 1);
			Log.e("", "no child! name:" + this.name + " value:" + this.value);
		} else {
			Log.e("", "have child! " + this.name + " end: " + endName);
			this.value = ""; // if we have children, we should have no value
			// we have a child! lets read and add it.

			boolean atEnd = false;
			while (!atEnd) {

				XMLStringObject child = new XMLStringObject(fileContents,
						position);
				items.add(child);

				while (!(fileContents.charAt(position[0]) == '<'))
					position[0]++;

				String testEnd = fileContents.substring(position[0]);
				Log.e("", "testEnd: " + testEnd);

				if (testEnd.startsWith("</" + this.name))
					atEnd = true;
			}
		}
	}

	// add an item to the arraylist
	public void addItem(XMLStringObject item) {
		items.add(item);
	}

	public void addItem(String name, String value) {
		addItem(new XMLStringObject(name, value));
	}

	// XMLStringObject's toString() method, depth is the amount of indent (i
	// think)
	public String toString(int depth) {
		// Initialize an empty string
		String asString = "";
		// create necessary indent
		for (int i = 0; i < depth; i++)
			asString += "  ";
		asString += "<" + name + ">";
		asString += value;
		// if we have children, we need to give them some space, and write them
		if (items.size() > 0) {
			asString += "\n";
			for (XMLStringObject item : items) {
				asString += "\n" + item.toString(depth + 1) + "\n";
			}
			asString += "\n";
			for (int i = 0; i < depth; i++)
				asString += "  ";
		}
		// and end the item with its forward slash
		asString += "</" + name + ">";
		return asString;
	}

}
