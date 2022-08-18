package com.laioffer.copy;
import java.util.*;

class GraphNode {
  public int key;
  public List<GraphNode> neighbors;
  public GraphNode(int key) {
    this.key = key;
    this.neighbors = new ArrayList<GraphNode>();
  }
}
public class UndirectedGraph {
    public List<GraphNode> copy(List<GraphNode> graph) { // 132
        if (graph==null) {return null;}
        Map<GraphNode,GraphNode> map = new HashMap<GraphNode,GraphNode>();
        for (GraphNode node : graph) {
            if (!map.containsKey(node)) {
                map.put(node,new GraphNode(node.key));
                dfs(node,map);
            }
        }
        return new ArrayList<>(map.values());
    }
    private void dfs(GraphNode seed, Map<GraphNode,GraphNode> map) {
        GraphNode copy = map.get(seed);
        for (GraphNode nei : seed.neighbors) {
            if (!map.containsKey(nei)) {
                map.put(nei,new GraphNode(nei.key));
                dfs(nei,map);
            }
            copy.neighbors.add(map.get(nei));
        }
    }
    public static void main(String[] args) {
        UndirectedGraph solution = new UndirectedGraph();
    }
}
