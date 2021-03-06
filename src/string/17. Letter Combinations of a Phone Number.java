// 17. Letter Combinations of a Phone Number: https://leetcode.com/problems/letter-combinations-of-a-phone-number/

// Medium
// Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number could represent. Return the answer in any order.

// ----- ----- -----
// |     |     |     |
// |  1  |  2  |  3  |
// |     | abc | def |
//  ----- ----- -----
// |     |     |     |
// |  4  |  5  |  6  |
// | ghi | jkl | mno |
//  ----- ----- -----
// |     |     |     |
// |  7  |  8  |  9  |
// | pqrs| tuv | wxyz|
//  ----- ----- -----
//       |     |
//       |  0  |
//       |     |
//        -----

// A mapping of digit to letters (just like on the telephone buttons) is given below. Note that 1 does not map to any letters.

// Example 1:

// Input: digits = "23"
// Output: ["ad","ae","af","bd","be","bf","cd","ce","cf"]
// Example 2:

// Input: digits = ""
// Output: []
// Example 3:

// Input: digits = "2"
// Output: ["a","b","c"]
 

// Constraints:

// 0 <= digits.length <= 4
// digits[i] is a digit in the range ['2', '9'].



class Solution {
    public static Map<Character, String[]> numberMap = new HashMap<Character, String[]>();
   static {
       numberMap.put('0', new String[] {"0"});
       numberMap.put('1', new String[] {"1"});
       numberMap.put('2', new String[] {"a","b","c"});
       numberMap.put('3', new String[] {"d","e","f"});
       numberMap.put('4', new String[] {"g","h","i"});
       numberMap.put('5', new String[] {"j","k","l"});
       numberMap.put('6', new String[] {"m","n","o"});
       numberMap.put('7', new String[] {"p","q","r","s"});
       numberMap.put('8', new String[] {"t","u","v"});
       numberMap.put('9', new String[] {"w","x","y","z"});	
   }
   
   public List<String> letterCombinations(String digits) {
       if(digits.isEmpty()) return new ArrayList<String>();
       List<String> outputStr = new ArrayList<String>();
       generateMnemonics(0, digits, new String[digits.length()], outputStr);
   
   return outputStr;
   }
                                  
   public void generateMnemonics(int idx, String phoneNumber, String[] generatedPneumonic, List<String> output) {
       if(idx == phoneNumber.length()) {
               output.add(String.join("", generatedPneumonic));
       } else {
           char curr = phoneNumber.charAt(idx);
           for(String pneumonic: numberMap.get(curr)) {
                   generatedPneumonic[idx] = pneumonic;
                   generateMnemonics(idx + 1, phoneNumber, generatedPneumonic, output);
           }
       }
   }				
}


// O(4^n * n) time | O(4^n * n) space - where n is len of phone number

//Another approach where we create string on the fly instead of using String[]
class Solution {
    Map<String, String> phone = new HashMap<String, String>() {{
      put("2", "abc");
      put("3", "def");
      put("4", "ghi");
      put("5", "jkl");
      put("6", "mno");
      put("7", "pqrs");
      put("8", "tuv");
      put("9", "wxyz");
    }};
  
    List<String> output = new ArrayList<String>();
  
    public void backtrack(String combination, String next_digits) {
      // if there is no more digits to check
      if (next_digits.length() == 0) {
        // the combination is done
        output.add(combination);
      }
      // if there are still digits to check
      else {
        // iterate over all letters which map 
        // the next available digit
        String digit = next_digits.substring(0, 1);
        String letters = phone.get(digit);
        for (int i = 0; i < letters.length(); i++) {
          String letter = phone.get(digit).substring(i, i + 1);
          // append the current letter to the combination
          // and proceed to the next digits
          backtrack(combination + letter, next_digits.substring(1));
        }
      }
    }
  
    public List<String> letterCombinations(String digits) {
      if (digits.length() != 0)
        backtrack("", digits);
      return output;
    }
  }