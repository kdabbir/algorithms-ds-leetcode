// Run-Length Encoding
// Write a function that takes in a non-empty string and returns its run-length
// encoding.

// Input:  = "AAAAAAAAAAAAABBCCCCDD"
// Output: = "9A4A2B4C2D"

import java.util.*;

class Program {
  public String runLengthEncoding(String string) {
    StringBuilder encodedString = new StringBuilder();
		int currRunLen = 1;
		
		for(int i = 1; i < string.length(); i++) {
			Character prevChar = string.charAt(i - 1);
			Character currChar = string.charAt(i);
			
			if(prevChar != currChar || currRunLen == 9) {
				encodedString.append(Integer.toString(currRunLen));
				encodedString.append(prevChar);
				currRunLen = 0;
			}
			currRunLen += 1;
		}
		
		encodedString.append(Integer.toString(currRunLen));
		encodedString.append(string.charAt(string.length() - 1));

		
    return encodedString.toString();
  }
}
