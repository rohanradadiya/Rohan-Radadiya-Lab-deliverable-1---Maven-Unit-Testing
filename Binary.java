package com.ontariotechu.sofe3980U;

import org.junit.Test;

/**
 * Unsigned integer Binary variable
 *
 */

public class Binary
{
	private String number = "0";
	/**
	 * A constructor that generates a binary object.
	 *
	 * @param number a String of the binary values. It should conatins only zeros or ones with any length and order. otherwise, the value of "0" will be stored.   Trailing zeros will be excluded and empty string will be considered as zero.
	 */

	public Binary(String number) {
		for (int i = 0; i < number.length(); i++) {
			char ch = number.charAt(i);
			if (ch != '0' && ch != '1') {
				this.number = "0";
				return;
			}
		}
		// remove any trailing zeros
		int beg;
		for (beg = 0; beg < number.length(); beg++) {
			if (number.charAt(beg) != '0') {
				break;
			}
			//beg has the index of the first non zero digit in the number
		}
		this.number = number.substring(beg); // exclude the trailing zeros if any
		// uncomment the following code
		if (this.number.equals("")) { // replace empty strings with a single zero
			this.number = "0";
		}
	}

	/**
	 * Return the binary value of the variable
	 *
	 * @return the binary value in a string format.
	 */
	public String getValue() {
		return this.number;
	}
	/**
	 * Adding two binary variables. For more information, visit <a href="https://www.wikihow.com/Add-Binary-Numbers"> Add-Binary-Numbers </a>.
	 *
	 * @param num1 The first addend object
	 * @param num2 The second addend object
	 * @return A binary variable with a value of <i>num1+num2</i>.
	 */
	public static Binary add(Binary num1, Binary num2) {
		// the index of the first digit of each number
		int ind1 = num1.number.length() - 1;
		int ind2 = num2.number.length() - 1;
		//initial variable
		int carry = 0;
		StringBuilder num3 = new StringBuilder();  // the binary value of the sum

		while (ind1 >= 0 || ind2 >= 0 || carry != 0) // loop until all digits are processed
		{
			int sum = carry; // previous carry
			if (ind1 >= 0) { // if num1 has a digit to add
				sum += (num1.number.charAt(ind1) == '1') ? 1 : 0; // convert the digit to int and add it to sum
				ind1--;
			}
			if (ind2 >= 0) { // if num2 has a digit to add
				sum += (num2.number.charAt(ind2) == '1') ? 1 : 0; // convert the digit to int and add it to sum
				ind2--; //update ind2
			}
			carry = sum / 2; // the new carry
			sum = sum % 2; // the resultant digit
			num3.insert(0, (sum == 0) ? "0" : "1"); //convert sum to string and append it to num3
		}
		return new Binary(num3.toString()); // create a binary object with the calculated value.
		//return result;
	}

	/**
	 * Conducting the logical "OR" operation with two binary variables. For more information, visit <a href="https://www.wikihow.com/Add-Binary-Numbers"> Add-Binary-Numbers </a>.
	 * By using an logical OR table, we understand that: 0 OR 0 = 0; 0 OR 1 = 1; 1 OR 0 = 1; 1 OR 1 = 1
	 *
	 * @param num1 The first object
	 * @param num2 The second object
	 * @return A binary variable with a value of <i>num1 OR num2</i>.
	 */
	public static Binary or(Binary num1, Binary num2) {
		int binary1length = num1.number.length();
		int binary2length = num2.number.length();
		int lengthMaximum = Math.max(binary1length, binary2length);
		StringBuilder result = new StringBuilder();
		//Allows to modify the content of the string

		for (int i = 0; i < lengthMaximum; i++) {
			char firstPositionNumber = (i < binary1length) ? num1.number.charAt(binary1length - 1 - i) : '0';
			//Utilizes a ternary operator and satisfies as an either/or, for the first binary variable
			char secondPositionNumber = (i < binary2length) ? num2.number.charAt(binary2length - 1 - i) : '0';
			//Utilizes a ternary operator and satisfies as an either/or, for the second binary variable
			char orResult = (firstPositionNumber == '1' || secondPositionNumber == '1') ? '1' : '0';
			result.insert(0, orResult);
		}
		return new Binary(result.toString());
		//returns result
	}

	/**
	 * Conducting the logical "AND" operation with two binary variables. For more information, visit <a href="https://www.wikihow.com/Add-Binary-Numbers"> Add-Binary-Numbers </a>.
	 * By using an logical AND table, we understand that: 0 AND 0 = 0; 0 AND 1 = 0; 1 AND 0 = 0; 1 AND 1 = 1
	 *
	 * @param num1 The first object
	 * @param num2 The second object
	 * @return A binary variable with a value of <i>num1 ^ num2</i>.
	 */
	public static Binary and(Binary num1, Binary num2) {
		int binary1length = num1.number.length();
		int binary2length = num2.number.length();
		int lengthMaximum = Math.min(binary1length, binary2length);
		StringBuilder result = new StringBuilder();

		for (int i = 0; i < lengthMaximum; i++) {
			char firstPositionNumber = num1.number.charAt(binary1length - 1 - i);
			//Utilizes the first binary variable
			char secondPositionNumber = num2.number.charAt(binary2length - 1 - i);
			//Utilizes the second binary variable
			char andResult = (firstPositionNumber == '1' && secondPositionNumber == '1') ? '1' : '0';
			result.insert(0, andResult);
		}
		return new Binary(result.toString());
	}

	/**
	 * Conducting the logical "MULTIPLY" operation with two binary variables. For more information, visit <a href="https://www.wikihow.com/Add-Binary-Numbers"> Add-Binary-Numbers </a>.
	 * By using an logical AND table, we understand that: 0 * 0 = 0; 0 * 1 = 0; 1 * 0 = 0; 1 * 1 = 1
	 *
	 * @param num1 The first object
	 * @param num2 The second object
	 * @return A binary variable with a value of <i>num1 ^ num2</i>.
	 */
	public static Binary multiply2(Binary num1, Binary num2) {
		Binary result = new Binary("0");
		Binary temp = new Binary(num1.number);
		while (!num2.getValue().equals("0")) {
			if (num2.number.charAt(num2.number.length() - 1) == '1') {
				result = add(result, temp);
			}
			temp = add(temp, temp);
			//Used as a temporary variable
			num2 = new Binary(num2.number.substring(0, num2.number.length() - 1));
		}

		return result;
	}
	//Code used above as a placeholder, code below is more user-friendly.


	public static Binary multiply(Binary num1, Binary num2)
	{
		int num1Variable = Integer.parseInt(num1.getValue(),2);
		int num2Variable = Integer.parseInt(num2.getValue(),2);
		//parseInt takes the arguments and returns the integers
		int finalProduct = num1Variable * num2Variable;
		return new Binary(Integer.toBinaryString(finalProduct));
	}
}