// 151. Reverse Words in a String : https://leetcode.com/problems/reverse-words-in-a-string/
// Medium

// Given an input string s, reverse the order of the words.

// A word is defined as a sequence of non-space characters. The words in s will be separated by at least one space.

// Return a string of the words in reverse order concatenated by a single space.

// Note that s may contain leading or trailing spaces or multiple spaces between two words. The returned string should only have a single space separating the words. Do not include any extra spaces.



// Example 1:

// Input: s = "the sky is blue"
// Output: "blue is sky the"
// Example 2:

// Input: s = "  hello world  "
// Output: "world hello"
// Explanation: Your reversed string should not contain leading or trailing spaces.
// Example 3:

// Input: s = "a good   example"
// Output: "example good a"
// Explanation: You need to reduce multiple spaces between two words to a single space in the reversed string.
// Example 4:

// Input: s = "  Bob    Loves  Alice   "
// Output: "Alice Loves Bob"
// Example 5:

// Input: s = "Alice does not even like bob"
// Output: "bob like even not does Alice"


// Constraints:

// 1 <= s.length <= 104
// s contains English letters (upper-case and lower-case), digits, and spaces ' '.
// There is at least one word in s.


// Follow up: Could you solve it in-place with O(1) extra space?


import java.util.*;

// Reversing entire string and then just reversing word ranges.

class Program {

  public String reverseWordsInString(String string) {
    char[] charArr = string.toCharArray();

		reverseListRange(charArr, 0, charArr.length - 1);
		int startOfWord = 0;

		while(startOfWord < charArr.length) {
			int endOfWord = startOfWord;
			while(endOfWord  < charArr.length && charArr[endOfWord] != ' ') {
				endOfWord++;
			}
			reverseListRange(charArr, startOfWord, endOfWord - 1);
			startOfWord = endOfWord + 1;
		}
		return new String(charArr);
  }

	public void reverseListRange(char[] stringArr, int start, int end) {
		while(start < end) {
			char temp = stringArr[start];
		    stringArr[start] = stringArr[end];
			stringArr[end] = temp;
			start++;
			end--;
		}
	}
}

// Using stack and substring

class Program {

  public String reverseWordsInString(String string) {
    // Write your code here.
		Stack<String> reverseWords = new Stack<>();
		int startOfWord = 0;
		for(int idx = 0; idx < string.length(); idx++) {
			if(string.charAt(idx) == ' ') {
				reverseWords.push(string.substring(startOfWord, idx));
				startOfWord = idx;
			}
			else if(string.charAt(startOfWord) == ' ') {
				reverseWords.push(string.substring(startOfWord, idx));
				startOfWord = idx;
			}
		}
		reverseWords.push(string.substring(startOfWord));
		StringBuilder curr = new StringBuilder();
		while(reverseWords.size() > 0) {
			curr.append(reverseWords.pop());
		}
    return curr.toString();
  }
}

// Using stringBuilder and stack.
class Program {

  public String reverseWordsInString(String string) {
    // Write your code here.
		Stack<String> reverseWords = new Stack<>();
		StringBuilder curr = new StringBuilder();
		for(char currChar: string.toCharArray()){
			if(currChar == ' '){
				if(curr.length() > 0) {
					reverseWords.push(curr.toString());
					curr.setLength(0);
				}
				reverseWords.push(String.valueOf(currChar));
			}
			else {
				curr.append(currChar);
			}
		}
		reverseWords.push(curr.toString());
		curr.setLength(0);
		while(reverseWords.size() > 0) {
			curr.append(reverseWords.pop());
		}

    return curr.toString();
  }
}
