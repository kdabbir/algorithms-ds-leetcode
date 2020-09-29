// 142. Linked List Cycle II
// Medium

// Given a linked list, return the node where the cycle begins. If there is no cycle, return null.

// There is a cycle in a linked list if there is some node in the list that can be reached again by continuously following the next pointer. Internally, pos is used to denote the index of the node that tail's next pointer is connected to. Note that pos is not passed as a parameter.

// Notice that you should not modify the linked list.

// Follow up:

// Can you solve it using O(1) (i.e. constant) memory?

 

// Example 1:


// Input: head = [3,2,0,-4], pos = 1
// Output: tail connects to node index 1
// Explanation: There is a cycle in the linked list, where tail connects to the second node.
// Example 2:


// Input: head = [1,2], pos = 0
// Output: tail connects to node index 0
// Explanation: There is a cycle in the linked list, where tail connects to the first node.
// Example 3:


// Input: head = [1], pos = -1
// Output: no cycle
// Explanation: There is no cycle in the linked list.
 

// Constraints:

// The number of the nodes in the list is in the range [0, 104].
// -105 <= Node.val <= 105
// pos is -1 or a valid index in the linked-list.


// Approach 1: Using fast and slow pointer

/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public ListNode detectCycle(ListNode head) {
        if(head == null || head.next == null ) return null;
        
        ListNode slow = head;
        ListNode fast = head;
        while(fast != null && fast.next != null) { 
            slow = slow.next;
            fast = fast.next.next;
            if(slow == fast) break;
        }    
        
        if(slow != fast) return null;
        
        ListNode intersection = slow;
        ListNode headNode = head;
        
        while(intersection != headNode) {
            intersection = intersection.next;
            headNode = headNode.next;
        }
        return intersection;
    }
}



// Time Complexity: O(N)
// Space complexity : O(1)

// Approach 2: Using hashmap.

public class Solution {
    public ListNode detectCycle(ListNode head) {
        Set<ListNode> visited = new HashSet<ListNode>();

        ListNode node = head;
        while (node != null) {
            if (visited.contains(node)) {
                return node;
            }
            visited.add(node);
            node = node.next;
        }

        return null;
    }
}


// Time complexity : O(n)

// For both cyclic and acyclic inputs, the algorithm must visit each node exactly once. This is transparently obvious for acyclic lists because the nnth node points to null, causing the loop to terminate. For cyclic lists, the if condition will cause the function to return after visiting the nnth node, as it points to some node that is already in visited. In both cases, the number of nodes visited is exactly nn, so the runtime is linear in the number of nodes.

// Space complexity : O(n)

// For both cyclic and acyclic inputs, we will need to insert each node into the Set once. The only difference between the two cases is whether we discover that the "last" node points to null or a previously-visited node. Therefore, because the Set will contain nn distinct nodes, the memory footprint is linear in the number of nodes.

