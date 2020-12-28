// Caesar Cipher Encryptor : https://www.algoexpert.io/questions/Caesar%20Cipher%20Encryptor

//   Given a non-empty string of lowercase letters and a non-negative integer
//   representing a key, write a function that returns a new string obtained by
//   shifting every letter in the input string by k positions in the alphabet,
//   where k is the key.

//   Sample Input
//   String  = "xyz", key  = 2

//   Sample Output
//   "zab"


import java.util.*;

class Program {
  public static String caesarCypherEncryptor(String str, int key) {
        char[] newLetters = new char[str.length()];
        int hashKey = key % 26;
        
        for(int idx = 0; idx < str.length(); idx++) {
            newLetters[idx] = encryptChar(str.charAt(idx), hashKey);
        }
        
        return new String(newLetters);
  }
	
	public static char encryptChar(char letter, int key) {
        int newLetterCode = letter + key;
        
        // a -> 96, z-> 122. hence, numbers greater than 122 need to be converted to 96 + offset as per 122 modulo number.
		return newLetterCode <= 122 ? (char) newLetterCode: (char) (96 + newLetterCode % 122);
	}
	
}

// O(n) time | O(n) space - where n is the length of the input string