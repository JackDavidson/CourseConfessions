package util;

import java.util.ArrayList;

import android.util.Log;

public class Splitter {
	public static ArrayList<String> splitStringArray(String jsonResult)
	{
		ArrayList<String> splitResult = new ArrayList<String>();
		String[] result = jsonResult.split("\",\"");
		if(result.length > 0){
			if (result[0].length() == 0) {
				return splitResult;
			}
			result[0] = result[0].substring(2);
			result[result.length - 1] = result[result.length - 1].substring(0, result[result.length - 1].length() - 3);
		}
		Log.e("", ""+result.length);
		for(int i = 0; i < result.length; i ++)
			splitResult.add(result[i]);
		return splitResult;		
	}
}
