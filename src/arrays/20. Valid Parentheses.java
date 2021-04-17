// 20. Valid Parentheses: https://leetcode.com/problems/valid-parentheses/
// Easy


// Given a string s containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.

// An input string is valid if:

// Open brackets must be closed by the same type of brackets.
// Open brackets must be closed in the correct order.


// Example 1:

// Input: s = "()"
// Output: true
// Example 2:

// Input: s = "()[]{}"
// Output: true
// Example 3:

// Input: s = "(]"
// Output: false
// Example 4:

// Input: s = "([)]"
// Output: false
// Example 5:

// Input: s = "{[]}"
// Output: true


// Constraints:

// 1 <= s.length <= 104
// s consists of parentheses only '()[]{}'.



// Using strings

class Solution {
    public boolean isValid(String str) {
        Stack<Character> charStack = new Stack<Character>();
		String openingBracket = "({[";
		String closingBracket = ")}]";
		HashMap<Character, Character> matchingMap = new HashMap<Character, Character>();
		matchingMap.put('}','{');
		matchingMap.put(')','(');
		matchingMap.put(']','[');

		for(int idx = 0; idx < str.length(); idx++){
			char curr = str.charAt(idx);
			if(openingBracket.indexOf(curr) != -1) {
				charStack.push(curr);
			} else if(closingBracket.indexOf(curr) != -1) {
				if(charStack.size() == 0) return false;
				char topOfStack = charStack.peek();
				if(topOfStack == matchingMap.get(curr) ){
					charStack.pop();
				} else {
					return false;
				}
			}
		}
        return charStack.size() == 0;
    }
}

// Time: O(N)

// Shorter version

public boolean isValid(String s) {
    Stack<Character> stack = new Stack<Character>();
    for (char c : s.toCharArray()) {
        if (c == '(')
            stack.push(')');
        else if (c == '{')
            stack.push('}');
        else if (c == '[')
            stack.push(']');
        else if (stack.isEmpty() || stack.pop() != c)
            return false;
    }
    return stack.isEmpty();
}


// Using sets

class Solution {
    public boolean isValid(String str) {
        Stack<Character> charStack = new Stack<Character>();
		Set<Character> openingBracket = new HashSet<>();
        openingBracket.add('(');
        openingBracket.add('[');
        openingBracket.add('{');

        Set<Character> closingBracket = new HashSet<>();
        closingBracket.add(')');
        closingBracket.add('}');
        closingBracket.add(']');

		HashMap<Character, Character> matchingMap = new HashMap<Character, Character>();
		matchingMap.put('}','{');
		matchingMap.put(')','(');
		matchingMap.put(']','[');

		for(int idx = 0; idx < str.length(); idx++){
			char curr = str.charAt(idx);
			if(openingBracket.contains(curr)) {
				charStack.push(curr);
			} else if(closingBracket.contains(curr)) {
				if(charStack.size() == 0) return false;
				char topOfStack = charStack.peek();
				if(topOfStack == matchingMap.get(curr) ){
					charStack.pop();
				} else {
					return false;
				}
			}
		}
    return charStack.size() == 0;
    }
}

// Time: O(N)

