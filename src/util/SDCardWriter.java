package util;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import android.util.Log;

/**
 * SD card writer tool. removes spaces from your stuff, makes the directories you need, and does other things in
 * addition to writing a string to a file for you
 * 
 * @author jack
 * 
 */
public class SDCardWriter {
	
	// TODO: this needs to be cleaned up
	//write the file to the SD card, throw an exception if it fails
	public static void writeFile(String filePath, String fileName, String content) {
		try {
			fileName = fileName.replaceAll("[^a-zA-Z0-9./-]", "_");
			fileName = fileName.replaceAll("/", "_");
			fileName = fileName.replaceAll(" ", "_");
			File myFile = new File(filePath);
			myFile.mkdirs();
			myFile = new File(filePath + fileName);
			FileOutputStream fOut = new FileOutputStream(myFile);
			OutputStreamWriter myOutWriter = new OutputStreamWriter(fOut);
			myOutWriter.append(content);
			myOutWriter.flush();
			myOutWriter.close();
			fOut.close();
		} catch (Exception e1) {
			Log.e("SDCardWriter", "failed writing SD file" + e1.getMessage());
		}
	}
	//read a file from the SD card, throw an exception if it fails
	public static String readFile(String file) {
		String aBuffer = "";
		try {
			File myFile = new File(file);
			FileInputStream fIn = new FileInputStream(myFile);
			BufferedReader myReader = new BufferedReader(new InputStreamReader(fIn));
			String aDataRow = "";
			while ((aDataRow = myReader.readLine()) != null) {
				aBuffer += aDataRow + "\n";
				// aBuffer += aDataRow;
			}
			fIn.close();
			myReader.close();
		} catch (Exception e) {
			Log.e("SDCardWriter", "Failed reading SD file" + e.getMessage());
		}

		// Log.e("stuff", aBuffer);
		return aBuffer;
	}
	//read a file from an input stream, throw exception if it fails
	public static String readFile(InputStream fIn) {
		String aBuffer = "";
		try {
			// File myFile = new File(file);
			// FileInputStream fIn = new FileInputStream(myFile);
			BufferedReader myReader = new BufferedReader(new InputStreamReader(fIn));
			String aDataRow = "";
			while ((aDataRow = myReader.readLine()) != null) {
				// aBuffer += aDataRow + "\n";
				aBuffer += aDataRow;
			}
			fIn.close();
			myReader.close();
		} catch (Exception e) {
			Log.e("SDCardWriter", "Failed reading SD file" + e.getMessage());
		}
		return aBuffer;
	}
}