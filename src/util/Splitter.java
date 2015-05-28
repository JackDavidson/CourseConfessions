package util;

import java.util.ArrayList;

import android.util.Log;

public class Splitter {
	public static ArrayList<String> splitStringArray(String jsonResult)
	{
		
		String[] result = jsonResult.split("\",\"");
		Log.e("", ""+result.length);
		ArrayList<String> splitResult = new ArrayList<String>();
		for(int i = 0; i < result.length; i ++)
			splitResult.add(result[i]);
		//String pattern = "\\d";
		//splitResult = new ArrayList<String>(Arrays.asList(jsonResult.split("\",\"")));
		return splitResult;		
	}
}
