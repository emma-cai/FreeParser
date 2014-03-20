package common;

import java.util.ArrayList;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Operator {
	public static void main(String[] args){
	//	SenToList("A B C D E F",3);
	//	System.out.println(replaceRegularExp("''(02133fdadfda^%", "['(1^%]", "A"));
		
//		System.out.println(addSpace("(ABC) is a good person,ma? haha, ", ","));
//		System.out.println(addSpace("(ABC) is a good person,ma? haha, ", "\\)"));
		
//		System.out.println(getMinimum(2,-3));
		
		for(int i=0; i<20; i++){
			System.out.println(getRandomBoolean(0.5));
		}
	}
	
	/**
	 * Convert array to list
	 */
	public static ArrayList<String> ArrToList(String[] arr){
		ArrayList<String> list = new ArrayList<String>();
		int len = arr.length;
		for(int i=0; i<len; i++){
			list.add(arr[i]);
		}
		return list;
	}
	
	/**
	 * Convert sentence to list, given window-size
	 */
	public static ArrayList<String> SenToList(String sen, int winSize){
		ArrayList<String> list = new ArrayList<String>();
		String[] arr = sen.split(" ");
		int len = arr.length;
		
		boolean flag = true;
		for(int i=0; i<len && flag==true; i++){
			String sub = "";
			for(int j=i; j<i+winSize && j<len; j++){
				sub = sub+" "+arr[j];
		//		System.out.println(i+"\t"+j+"\t"+sub);
				sub = sub.trim();
				if(!sub.equals("") && !sub.equals(" "))
					list.add(sub);
			}
		}
		return list;
	}
	
	/**
	 * Convert sentence to list, separated by label
	 */
	public static ArrayList<String> SenToList(String sen, String label){
		ArrayList<String> list = new ArrayList<String>();
		String[] arr = sen.split(label);
		int len = arr.length;
		
		for(int i=0; i<len; i++){
			list.add(arr[i]);
		}
		
		return list;
	}
	
	/**
	 * String + oldList = newList
	 */
	public static ArrayList<String> addSL(String str, ArrayList<String> oldList){
		ArrayList<String> newList = new ArrayList<String>();
		newList.add(str);
		for(String oldS : oldList){
			newList.add(oldS);
		}
		return newList;
	}
	
	/**
	 * Check unnecessary symbols
	 */
	public static boolean checkRegularExp(String snippet, String match){
		String regex1 = "\\b"+match+"\\b";
        Pattern p1 = Pattern.compile(regex1, Pattern.CASE_INSENSITIVE);
        Matcher m1 = p1.matcher(snippet);
        if(m1.find()){
        	return true;
        }
        
        String regex2 = match;
        Pattern p2 = Pattern.compile(regex2, Pattern.CASE_INSENSITIVE);
        Matcher m2 = p2.matcher(snippet);
        if(m2.find()){
        	return true;
        }
        return false;
    }
	
	/**
	 * replaceAllRegularExp
	 */
	public static String replaceAllRegularExp(String str)
	{
		if(str==null || str.equals(""))
			return str;
		
		String regEx="[Ä±Ãªâåäã©¡ð¸ð•£ñ¾²°ìí`~!.@#$%^&'+=:(){}\"\\[\\]]";
		Pattern p=Pattern.compile(regEx);     
		Matcher m=p.matcher(str);     
		if(m.find()){
			return m.replaceAll(" ");
		}
		return str;
	}
	
	public static String replaceAllRegularExp(String str, String regEx, String replacedEx){
		if(str==null || str.equals(""))
			return str;
		
		Pattern p=Pattern.compile(regEx);     
		Matcher m=p.matcher(str);     
		if(m.find()){
			return m.replaceAll(replacedEx);
		}
		return str;
	}
	
	
	/**
	 * Replace unnecessary symbols
	 */
	public static String replaceRegularExp(String str, String match, String replacement){
		if(str==null || str.equals(""))
			return null;
		String regex = match;  
        Pattern p = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(str);
        if(m.find()){
        	return m.replaceAll(replacement);
        }
        return str;
	}
	
	/**
	 * Add " " before or after the given symbol
	 */
	public static String addSpace(String str, String sym){
		String res = str;
		res = res.replaceAll(sym, " "+sym+" ");
		res = res.replaceAll("  ", " ");
		res = res.trim();
		return res;
	}
	
	/**
	 * return the minimum
	 */
	public static int getMinimum(int a, int b){
		return Math.min(a, b);
	}
	
	/**
	 * get random boolean value
	 */
	private static Random random = new Random();
	public static boolean getRandomBoolean(double d){
		return random.nextFloat() < d;
	}
}
