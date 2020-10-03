// 118. Pascal's Triangle
// Easy

// Given a non-negative integer numRows, generate the first numRows of Pascal's triangle.


// In Pascal's triangle, each number is the sum of the two numbers directly above it.

// Example:

// Input: 5
// Output:
// [
//      [1],
//     [1,1],
//    [1,2,1],
//   [1,3,3,1],
//  [1,4,6,4,1]
// ]


class Solution {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> triangle = new ArrayList<List<Integer>>();
        if(numRows == 0) return triangle;
        
        triangle.add(new ArrayList<Integer>(
            Arrays.asList(1)
        ));
        
        for(int currRow = 1; currRow < numRows; currRow++) {
            List<Integer> row = new ArrayList<>();
            List<Integer> prevRow = triangle.get(currRow - 1);
            
            row.add(1);
            
            for(int idx = 1; idx < currRow; idx++) {
                row.add(prevRow.get(idx - 1) + prevRow.get(idx));
            }
            row.add(1);
            triangle.add(row);
        }
        return triangle;
    }
}

//Time: O(rowNumb ^ 2)
//Space: O(rowNumb ^ 2)