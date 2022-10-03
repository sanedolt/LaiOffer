package com.laioffer.Algorithm.DFS;

import java.util.*;

public class Flight {
    /*
    466. Reconstruct Itinerary
    Given a list of airline tickets represented by pairs of departure and arrival airports [from, to], reconstruct the itinerary in order. All of the tickets belong to a man who departs from JFK. Thus, the itinerary must begin with JFK.
    Note:
    If there are multiple valid itineraries, you should return the itinerary that has the smallest lexical order when read as a single string. For example, the itinerary ["JFK", "LGA"] has a smaller lexical order than ["JFK", "LGB"].
    All airports are represented by three capital letters (IATA code).
    You may assume all tickets form at least one valid itinerary.
    Example 1:
    tickets = [["MUC", "LHR"], ["JFK", "MUC"], ["SFO", "SJC"], ["LHR", "SFO"]]
    Return ["JFK", "MUC", "LHR", "SFO", "SJC"].
    Example 2:
    tickets = [["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],["ATL","SFO"]]
    Return ["JFK","ATL","JFK","SFO","ATL","SFO"].
    Another possible reconstruction is ["JFK","SFO","ATL","JFK","ATL","SFO"]. But it is larger in lexical order.
     */
    public List<String> findItinerary(String[][] tickets) {
        if (tickets==null || tickets.length==0) {return new ArrayList<String>();}
        // reformat graph
        Map<String,List<String>> fromAirport = new HashMap<>();
        loadGraph(tickets,fromAirport);
        Map<String,boolean[]> visited = createVisited(fromAirport);
        // start to find path
        int len=tickets.length+1;
        List<String> result = new ArrayList<>();
        result.add("JFK");
        if (!findPath(result,len,fromAirport,visited)) {
            return new ArrayList<String>();
        } else {
            return result;
        }
        // Write your solution here
    }
    private boolean findPath (List<String> result, int len, Map<String,List<String>> fromAirport, Map<String,boolean[]> visited) {
        int index=result.size()-1;
        String cur = result.get(index);
        if (index==len-1) {return true;}
        if (fromAirport.get(cur)==null) {return false;}
        List<String> nei = fromAirport.get(cur);
        boolean[] v = visited.get(cur);
        for (int i=0;i<nei.size();i++) {
            if (v[i]) {continue;}
            result.add(nei.get(i));
            v[i]=true;
            if (findPath(result,len,fromAirport,visited)) {return true;}
            result.remove(index+1);
            v[i]=false;
        }
        return false;
    }
    private Map<String,boolean[]> createVisited(Map<String,List<String>> graph) {
        Map<String,boolean[]> visited = new HashMap<>();
        for (Map.Entry<String,List<String>> e : graph.entrySet()) {
            Collections.sort(e.getValue());
            visited.put(e.getKey(), new boolean[e.getValue().size()]);
        }
        return visited;
    }
    private void loadGraph (String[][] tickets, Map<String,List<String>> fromAirport) {
        for (String[] ticket : tickets) {
            String a = ticket[0];
            String b = ticket[1];
            List<String> temp = fromAirport.get(a);
            if (temp==null) {
                temp = new ArrayList<>();
                fromAirport.put(a,temp);
            }
            temp.add(b);
        }
    }
    /*
    660. Flights with Lowest Price With At Most K Stops
    Suppose there are m flights connecting n cities. Flight is represented by an int array int[] where the first element is departure city, the second element is destination city and the third element is the price.
    Given a departure city src, a destination city dst, and most number of stops k, return the lowest price of flights can take you from src to dst with at most k stops. If there is no such a route, then return -1.
    You can assume that there is no duplicated flights.
    Example 1:
    Input: n = 3, flights = [[0,1,100],[0,2,1000], [1,2,200]], src = 0, dst = 2, k = 1
    Output: 300
    Example 2:
    Input: n = 3, flights = [[0,1,100],[0,2,1000], [1,2,200]], src = 0, dst = 2, k = 0
    Output: 1000
     */
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) { // 660
        if (flights==null) {return -1;}
        if (src==dst) {return 0;}
        int[][] dp = new int[2][n];
        for (int[] d : dp) {Arrays.fill(d,-1);}
        int row=0;
        dp[0][src]=dp[1][src]=0;
        for (int i=1;i<k+2;i++) {
            row=1-row;
            for (int[] flight : flights) {
                if (dp[1-row][flight[0]]!=-1) {
                    int curCost = dp[1-row][flight[0]] + flight[2];
                    if (dp[row][flight[1]]==-1 || curCost<dp[row][flight[1]]) {
                        dp[row][flight[1]]=curCost;
                    }
                }
            }
        }
        return dp[row][dst];
        // Write your solution here
    }
    public static void main(String[] args) {
        Flight solution = new Flight();
//      String[][] tickets = new String[][]{{"JFK","SFO"},{"JFK","ATL"},{"SFO","ATL"},{"ATL","JFK"},{"ATL","SFO"}};
        String[][] tickets = new String[][]{{"JFK","SFO"},{"SFO","ATL"},{"ATL","JFK"},{"JFK","BEJ"}};
        System.out.println(solution.findItinerary(tickets));
    }
}
