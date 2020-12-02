// 72. Edit Distance: https://leetcode.com/problems/edit-distance/
// HARD

// Given two strings word1 and word2, return the minimum number of operations required to convert word1 to word2.

// You have the following three operations permitted on a word:

// Insert a character
// Delete a character
// Replace a character
 

// Example 1:

// Input: word1 = "horse", word2 = "ros"
// Output: 3
// Explanation: 
// horse -> rorse (replace 'h' with 'r')
// rorse -> rose (remove 'r')
// rose -> ros (remove 'e')
// Example 2:

// Input: word1 = "intention", word2 = "execution"
// Output: 5
// Explanation: 
// intention -> inention (remove 't')
// inention -> enention (replace 'i' with 'e')
// enention -> exention (replace 'n' with 'x')
// exention -> exection (replace 'n' with 'c')
// exection -> execution (insert 'u')
 

// Constraints:

// 0 <= word1.length, word2.length <= 500
// word1 and word2 consist of lowercase English letters.

// Top-Down dynamic programming:

class Solution {
    public int minDistance(String word1, String word2) {
        if(word1.length() * word2.length() == 0) return word2.length() + word1.length() ; 
        
        int[][] dpMemo = new int[word1.length() + 1][word2.length() + 1];
        for (int row = 0; row < word1.length() + 1; row++) {
            dpMemo[row][0] = row;
        }
        for (int col = 0; col < word2.length() + 1; col++) {
            dpMemo[0][col] = col;
        }
        
        for(int row = 1; row < dpMemo.length; row++) {
            for(int col = 1; col < dpMemo[row].length; col++) { 
                    int additionTopVal = dpMemo[row - 1][col] + 1;
                    int removalLeftVal = dpMemo[row][col - 1] + 1;
                    int replaceDiagonalVal = dpMemo[row - 1][col - 1];
                    if(word1.charAt(row - 1) != word2.charAt(col - 1)) {
                        replaceDiagonalVal += 1;
                    }
                    dpMemo[row][col] = Math.min(additionTopVal, Math.min(removalLeftVal, replaceDiagonalVal));
            }
        }
        return dpMemo[word1.length()][word2.length()];
    }
}


// LOGICAL Explaination:


// EXPl1:
// Video: https://www.youtube.com/watch?v=We3YDTzNXEk&ab_channel=TusharRoy-CodingMadeSimple

// Please let me try. Pause the video at 4:52, so you have the reference point to the case I explained below.

// Now look at the scenario where we are computing the count for turning S1 : "abcd" =>  S2 : "azc". Which is the tricky case and hence the confusion. The case where the last two characters are non-identical. 

// At this juncture, Tushar recommends looking at three things to arrive at the best decision possible.

// Step 1:  looking back at the previous row, same column (abcd, az). Which is asking ourselves how much did it cost me to to turn abcd => az -- which is 3 operations. Is this our best sub-problem to further turn ( az (previously abcd) => azc). As that is what we are working towards, to make S1: abcd into S2: azc.

// Step 2: looking back at the previous column, same row (abc, azc). Which is asking ourselves how much did it cost me to turn abc => azc -- which is 1 operation. Now, is this the best sub-problem to further turn (abcd => azc). Because we know abc can be turned to azc in one operation, so considering additional character to S1, that is abc+d, we will only have to remove d.

// Step 3: Looking back at previous row, previous column (abc, az), is this a good sub-problem to further go with abc(turned to az)+d and az+c. That is turning az+d to az+c so both become azc.


// So going with step 2 looks more resonable, whatever has lead to abc turning into azc took only 1 operation. now even by considering abcd, we know just by deleting the additional d, we already have arrived at azc. So the new operation is basically deleting the character d.

// Expl2:

// So the best way to understand how this works is to analyze what the recursive solution would be. 

// Lets say the main recursive function for minimum edits from str1 to str2 is minEdits(str1, str2). This function first compares str1[end] and str2[end] (the last characters of both strings), because if they are the same then it means that the result is equal to the minimum edits required to make the str1 and str2 without str1[end] and str2[end], which is minEdits(str1[0..end-1], str2[0..end-1]). Hence T[i][j] = T[i-1][j-1].

// However if str1[end] and str2[end] are NOT the same, then we have 3 potential ways to go from str1 to str2:
// 1. Removing str1[end] and then going from str1[0..end-1] to str2. (Left)
// 2. Going from str1 to str2[0..end-1] and then adding str2[end]. (Top)
// 3. Going from str1[0..end-1] to str2[0..end-1] and then editing str1[end] to be str2[end]. (Diagonal)

// So the minimum edits for the options above are:
// 1. minEdits(str1[0..end-1], str2)+1 (the +1 comes from the remove operation), hence T[i-1][j]+1. (Left)
// 2. minEdits(str1, str2[0..end-1])+1 (+1 from the add), hence T[i][j-1]+1. (Top)
// 3. minEdits(str1[0..end-1], str2[0..end-1])+1 (+1 from the edit), hence T[i-1][j-1]+1 (Diagonal)     

// Therefore, when str1[end] and str2[end] are not the same, the minEdits(str1, str2) is the minimum between the options above, which is T[i][j] = MIN(T[i-1][j]+1, T[i][j-1]+1, T[i-1][j-1]+1). 

// Probably the main reason why it was confusing at first is because he factored out the +1 from all 3 options above and he added it back in after finding the min between the options, which makes it a lot harder to picture.