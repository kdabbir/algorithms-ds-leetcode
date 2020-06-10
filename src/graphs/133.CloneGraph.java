/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> neighbors;
    
    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }
    
    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }
    
    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}
*/
//Using DFS

class Solution {
    public Node cloneGraph(Node node) {
        if(node == null) return node;
        HashMap<Node,Node> seenList = new HashMap<>();
        return dfs(node, seenList);
    }
    
    public Node dfs(Node node, HashMap<Node,Node> seenList){
        if(seenList.containsKey(node)) {
            return seenList.get(node);
        }
        Node cloneNode = new Node(node.val, new ArrayList());
        seenList.put(node, cloneNode);
        for(Node neighbor: node.neighbors){
            cloneNode.neighbors.add(dfs(neighbor, seenList));
        }
        return cloneNode;
    }
}


//Using BFS
class Solution {
    public Node cloneGraph(Node node) {
        if(node == null) return node;
        Queue<Node> queue = new LinkedList<>();
        HashMap<Node,Node> seenList = new HashMap();
        Node cloneNode = new Node(node.val);
        queue.add(node);
        seenList.put(node, cloneNode);
        while(queue.size() !=0){
            Node curr = queue.poll();
            for(Node neighbor: curr.neighbors){
                if(!seenList.containsKey(neighbor)){
                    seenList.put(neighbor, new Node(neighbor.val, new ArrayList()));
                    queue.add(neighbor);
                }
                seenList.get(curr).neighbors.add(seenList.get(neighbor));
            }
        }
       
       return cloneNode;
    }
}