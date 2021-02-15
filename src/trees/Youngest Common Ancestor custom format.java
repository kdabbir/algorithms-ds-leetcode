// Youngest Common Ancestor custom format
// Medium

// You're given three inputs, all of which are instances of an AncestralTree,  class that have an ancestor  property
// pointing to their youngest ancestor. The first input is the top ancestor in an
// ancestral tree (i.e., the only instance that has no ancestor--its ancestor  property points to None/null, ), and the other two inputs are descendants in the ancestral
// tree.

// Write a function that returns the youngest common ancestor to the two
// descendants.

// Note that a descendant is considered its own ancestor. So in the simple
// ancestral tree below, the youngest common ancestor to nodes A and B is node A.

// // The youngest common ancestor to nodes A and B is node A.

// A
// /
// B
// Sample Input
// // The nodes are from the ancestral tree below.
// topAncestor = node A
// descendantOne = node E
// descendantTwo  = node I
// A
// /     \
// B       C
// /   \   /   \
// D     E F     G
// /   \
// H     I

// Sample Output
// node B

import java.util.*;

class Program {
  public static AncestralTree getYoungestCommonAncestor(
      AncestralTree topAncestor, AncestralTree descendantOne, AncestralTree descendantTwo) {
    int oneDepth = getDescendentDepth(descendantOne, topAncestor);
		int twoDepth = getDescendentDepth(descendantTwo, topAncestor);
		if(oneDepth > twoDepth){
			return backTrackToMeetAncestor(descendantOne, descendantTwo, oneDepth - twoDepth);
		} else {
			return backTrackToMeetAncestor(descendantTwo, descendantOne, twoDepth - oneDepth);
		}
  }

	public static int getDescendentDepth(AncestralTree descendent, AncestralTree ancestor) {
		int depth = 0;
		while(descendent != ancestor) {
			depth++;
			descendent = descendent.ancestor;
		}
		return depth;
	}
	
	public static AncestralTree backTrackToMeetAncestor(AncestralTree lowerDescendent, AncestralTree higherDescendent, int depth) {
		while(depth > 0) {
			lowerDescendent = lowerDescendent.ancestor;
			depth--;
		}

		while(lowerDescendent != higherDescendent) {
			lowerDescendent = lowerDescendent.ancestor;
			higherDescendent = higherDescendent.ancestor;
		}
		
		return lowerDescendent;
	}
	
  static class AncestralTree {
    public char name;
    public AncestralTree ancestor;

    AncestralTree(char name) {
      this.name = name;
      this.ancestor = null;
    }

    // This method is for testing only.
    void addAsAncestor(AncestralTree[] descendants) {
      for (AncestralTree descendant : descendants) {
        descendant.ancestor = this;
      }
    }
  }
}

// Note here we have a reference to ancestor rather than descendent. Hence, we find out depth of each node.
// We move deepest node to other node level as per the diff in their depths
// We then backtrack both node pointers to where they meet first as lowest common ancestors