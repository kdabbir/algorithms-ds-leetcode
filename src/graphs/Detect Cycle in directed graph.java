// Detect A Cycle In A Graph (Deadlock Detection): https://backtobackswe.com/platform/content/detect-a-cycle-in-a-graph-deadlock-detection/video
// Medium
// Given a directed graph determine if it contains a cycle.

// Constraints:
// |V| <= 100

 
 /*
    Raw alogorithm using 3 colors:
      White -> Unvisited (we can visit this node, it is not on a pending path & hasn't been processed)
      Gray -> Visiting (currently on the path being traversed)
      Black -> Visited (do not traverse again)
  */

static class GraphVertex {
    enum Color {WHITE, GREY, BLACK};
    Color color;
    List<GraphVertex> adjacents;
}

class Solution {
    public boolean isCyclic(List<GraphVertex> graph) {
           /*
            Since the graph my not be connected, we must initiate a DFS from each
            node so that we still investigate disjoint regions
            */
        for(GraphVertex node: graph){
            if(node.color == GraphVertex.Color.WHITE && hasCycleFromVertex(node)) {
                return true;
            }
        }
        return false;
    }

    public boolean hasCycleFromVertex(GraphVertex node) {

            /*
            If this vertex is currently being visited and we just returned to
            it that means that there is a cycle
            */
        if(node.color == GraphVertex.Color.GREY) {
            return true;
        }


            /*
            Perform work - our work here is marking the vertex as being visited.
            We will undo this once we search all paths reachable from this vertex.
            */
        node.color = GraphVertex.Color.GREY;

            /*
            Process adjacents - only search from the node if it is either:
                - White: The node is unprocessed
                - Gray: The cycle will be caught in the next call and 'false' will bubble
                up the call stack
            */

        for(GraphVertex adjacentNode: node.adjacents) {
            if(adjacentNode.color != GraphVertex.Color.BLACK && hasCycleFromVertex(adjacentNode)) {
                return true;
            }
        }
            // If we get here then all paths were explored and no path was found - mark this vertex as finished

        node.color = GraphVertex.Color.BLACK;

            // No cycle found from this vertex

        return false;
    }
}