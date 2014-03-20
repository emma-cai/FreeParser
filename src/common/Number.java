package common;

public class Number {
	
	/**
	 * Is an integer?
	 */
	public static boolean isInteger(String value){
		try{
			Integer.parseInt(value);
			return true;
		}catch(NumberFormatException e){
			return false;
		}
	}
	
	/**
	 * Is a double?
	 */
	public static boolean isDouble(String value){
		try{
			Double.parseDouble(value);
			return true;
		}catch(NumberFormatException e){
			return false;
		}
	}
	
	/**
	 * Is a number?
	 */
	public static boolean isNumber(String value){
		return isInteger(value) || isDouble(value);
	}
	
	public static void main(String[] args){
		String value = "1928.9";
		System.out.println(isNumber(value));
	}
}
