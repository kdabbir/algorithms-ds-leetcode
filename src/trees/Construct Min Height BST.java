// Construct Min Height BST
// Medium

// Write a function that takes in a non-empty sorted array of distinct integers,
// constructs a BST from the integers, and returns the root of the BST.

// The function should minimize the height of the BST.

// Sample Input
// arr = [1, 2, 5, 7, 10, 13, 14, 15, 22]

// Sample output:
// 10
// /     \
// 2      14
// /   \   /   \
// 1     5 13   15
//    \       \
//     7      22
import java.util.*;

class Program {
  public static BST minHeightBst(List<Integer> array) {
    if(array == null || array.size() == 0) return null;
		return constructMinHeightBST(array, null, 0, array.size() - 1);
  }
	
	public static BST constructMinHeightBST(List<Integer> array, BST bst, int startIdx, int endIdx) {
		if(startIdx > endIdx) return null;
		int midIdx = startIdx + (endIdx - startIdx) / 2;
		int valToInsert = array.get(midIdx);
		if(bst == null) {
			bst = new BST(valToInsert);
		} else {
			bst.insert(valToInsert);
		}
		constructMinHeightBST(array, bst, startIdx, midIdx - 1);
		constructMinHeightBST(array, bst, midIdx + 1, endIdx);
		return bst;
	}
	
  static class BST {
    public int value;
    public BST left;
    public BST right;

    public BST(int value) {
      this.value = value;
      left = null;
      right = null;
    }

    public void insert(int value) {
      if (value < this.value) {
        if (left == null) {
          left = new BST(value);
        } else {
          left.insert(value);
        }
      } else {
        if (right == null) {
          right = new BST(value);
        } else {
          right.insert(value);
        }
      }
    }
  }
}


