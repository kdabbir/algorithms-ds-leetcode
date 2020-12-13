// 207. Course Schedule: https://leetcode.com/problems/course-schedule/
// Medium

// There are a total of numCourses courses you have to take, labeled from 0 to numCourses-1.

// Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]

// Given the total number of courses and a list of prerequisite pairs, is it possible for you to finish all courses?

// Example 1:

// Input: numCourses = 2, prerequisites = [[1,0]]
// Output: true
// Explanation: There are a total of 2 courses to take. 
//              To take course 1 you should have finished course 0. So it is possible.
// Example 2:

// Input: numCourses = 2, prerequisites = [[1,0],[0,1]]
// Output: false
// Explanation: There are a total of 2 courses to take. 
//              To take course 1 you should have finished course 0, and to take course 0 you should
//              also have finished course 1. So it is impossible.
 

// Constraints:

// The input prerequisites is a graph represented by a list of edges, not adjacency matrices. Read more about how a graph is represented.
// You may assume that there are no duplicate edges in the input prerequisites.
// 1 <= numCourses <= 10^5


// Using topological sort.

class GNode{
    public Integer inDegree = 0;
    public List<Integer> outNodes = new LinkedList<Integer>();
}
class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        if(prerequisites == null || prerequisites.length == 0 || numCourses == 0 || prerequisites[0].length == 0) 
            return true;
        HashMap<Integer, GNode> preReqOrder = new HashMap<>();
        for(int[] row: prerequisites) {
            GNode prevNode = createGNode(preReqOrder, row[1]);
            GNode nextNode = createGNode(preReqOrder, row[0]);
            prevNode.outNodes.add(row[0]);
            nextNode.inDegree++;
        }
        
        LinkedList<Integer> zeroReqNodes = new LinkedList<>();
        for (Integer courseNum : preReqOrder.keySet()) {
            if(preReqOrder.get(courseNum).inDegree == 0)
                zeroReqNodes.add(courseNum);
        }
        
        Integer removedNodes = 0;
        while(zeroReqNodes.size() > 0){
            Integer curr = zeroReqNodes.pop();
            for(Integer currNumCourse: preReqOrder.get(curr).outNodes){
                GNode outNodes = preReqOrder.get(currNumCourse);
                outNodes.inDegree--;
                removedNodes++;
                if(outNodes.inDegree == 0)
                    zeroReqNodes.add(currNumCourse);
            }
        }
        if(removedNodes == prerequisites.length)
            return true;
        else
            return false;
    }
    
    public GNode createGNode(HashMap<Integer,GNode> hashList, Integer courseNumber){
        if(hashList.containsKey(courseNumber)){
            return hashList.get(courseNumber);
        }
        GNode gnode = new GNode();
        hashList.put(courseNumber, gnode);
        return gnode;
    }
}

// Using backtracking.

class Solution {
  public boolean canFinish(int numCourses, int[][] prerequisites) {
      HashMap<Integer, List<Integer>> courseGraph = new HashMap();
      for(int[] currCourse: prerequisites){
         if(courseGraph.containsKey(currCourse[1])){
             courseGraph.get(currCourse[1]).add(currCourse[0]);
         } else{
             List<Integer> neighbors = new LinkedList();
             neighbors.add(currCourse[0]);
             courseGraph.put(currCourse[1], neighbors);
         }
      }
      
      boolean[] path = new boolean[numCourses];
      
      for(int curr = 0; curr < numCourses; curr++) {
        if(isCyclic(curr, path, courseGraph)) {
            return false;
        }
      }
     
      return true;
  }
    
    public boolean isCyclic(int course, boolean[] path, HashMap<Integer, List<Integer>> graphDict){
        if(path[course] == true) 
            // come across a previously visited node, i.e. detect the cycle. Base case for backtracking.
            return true;

        // no following courses, no loop. Another base case, but for ending case.
        if(!graphDict.containsKey(course))
            return false;
        
        // before backtracking, mark the node in the path
        path[course] = true;

        // backtracking
        boolean ret = false;
        for(int depCourse: graphDict.get(course)){
            ret = isCyclic(depCourse, path, graphDict);
            if(ret) break;
        }
        // after backtracking, remove the node from the path
        path[course] = false;
        
        return ret;
    }
}