// Suffix Trie Construction

// Write a SuffixTrie  class for a Suffix-Trie-like data structure.
// The class should have a  property set to be the root node of
// the trie and should support:

// - Creating the trie from a string; this will be done by calling the populateSuffixTrieFrom  method upon class instantiation, which
// should populate the root  of the class.
  
// -- Searching for strings in the trie.

// Note that every string added to the trie should end with the special endSymbol  character: *

// Sample Input (for creation)
// = "babc"

// Sample Output (for creation)

// The structure below is the root of the trie.

// {
//     "c": {"*": true},
//     "b": {
//       "c": {"*": true},
//       "a": {"b": {"c": {"*": true}}},
//     },
//     "a": {"b": {"c": {"*": true}}},
//   }


//   Sample Input (for searching in the suffix trie above)
//   = "abc"

//   Sample Output (for searching in the suffix trie above)
//   true

import java.util.*;

// Approach 1: Using Array.: https://www.geeksforgeeks.org/trie-insert-and-search/
// Here we are building only string level tree also. EG: abcde can involve a->b->c->d->e->


public class Trie {
    static final int ALPHABET_SIZE = 26;
    static TrieNode root;

    static class TrieNode {
        TrieNode[] children = new TrieNode[ALPHABET_SIZE];
        
        // isEndOfWord is true if the node represents 
        // end of a word 
        boolean isEndOfWord;
        TrieNode() {
            isEndOfWord = false;
            for(int idx = 0; idx < ALPHABET_SIZE; idx++) {
                children[idx] = null;
            }
        }
    }

    static void insertTrie(String str) {
        TrieNode node = root;

        for(int idx = 0; idx < str.length(); idx++) {
            char letter = str.charAt(idx);
            int indexToSearch = letter - 'a';
            if(node.children[indexToSearch] == null) {
                TrieNode newNode = new TrieNode();
                node.children[indexToSearch] = letter;
            }
            node = node.children[indexToSearch];
        }
        node.isEndOfWord = true;
    } 


    static boolean search(String key) {
        TrieNode node = root;
        for(int idx = 0; idx < key.length(); idx++) {
            char letterToSearch = key.charAt(idx);
            int indexToSearch  = letterToSearch - 'a';
            if(node.children[indexToSearch] == null)
                return false;
            node = node.children[indexToSearch];    
        }
        return node != null && node.isEndOfWord == true;
    }
}

// Approach 2: Using hashmap.
// Here we are building prefixes also. EG: abcde can involve a, ab, abc, abcd, abcde etc.


class Program {
  static class TrieNode {
    Map<Character, TrieNode> children = new HashMap<Character, TrieNode>();
  }

  static class SuffixTrie {
    TrieNode root = new TrieNode();
    char endSymbol = '*';

    public SuffixTrie(String str) {
      populateSuffixTrieFrom(str);
    }
		
    // Time: O(N^2) & Space: O(N ^2)
    public void populateSuffixTrieFrom(String str) {
      for(int idx = 0; idx < str.length(); idx++){
				generateTrieFromIndex(idx, str);
			}
    }
		
    public void generateTrieFromIndex(int index, String str) {
        TrieNode node = root;
        
        for(int idx = index; idx < str.length(); idx++) {
            char entry = str.charAt(idx);
            
            if(!node.children.containsKey(entry)) {
                TrieNode curr = new TrieNode();
                node.children.put(entry, curr);
            }
            node = node.children.get(entry);
        }
        node.children.put(endSymbol, null);
    }

    // Time: O(N) Space: O(1)
    public boolean contains(String str) {
      TrieNode node = root;
			for(int idx = 0; idx < str.length(); idx++) {
				char entry = str.charAt(idx);
				if(!node.children.containsKey(entry))
					return false;
				node = node.children.get(entry);
			}
      return node.children.containsKey(endSymbol);
    }
  }
}
