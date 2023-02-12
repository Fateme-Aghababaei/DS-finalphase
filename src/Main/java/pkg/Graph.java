package pkg;

import java.util.*;

public class Graph {
    // We use Hashmap to store the edges in the graph
    private Map<User, List<User>> map = new HashMap<>();

    // This function adds a new vertex to the graph
    public void addVertex(User s)
    {
        map.put(s, new LinkedList<User>());
    }

    // This function adds the edge
    // between source to destination
    public void addEdge(User source,
                        User destination,
                        boolean bidirectional)
    {

        if (!map.containsKey(source))
            addVertex(source);

        if (!map.containsKey(destination))
            addVertex(destination);

        map.get(source).add(destination);
        if (bidirectional) {
            map.get(destination).add(source);
        }
    }

    // This function gives the count of vertices
    public void getVertexCount()
    {
        System.out.println("The graph has "
                + map.keySet().size()
                + " vertex");
    }

    // This function gives the count of edges
    public void getEdgesCount(boolean bidirectional)
    {
        int count = 0;
        for (User v : map.keySet()) {
            count += map.get(v).size();
        }
        if (bidirectional) {
            count = count / 2;
        }
        System.out.println("The graph has "
                + count
                + " edges.");
    }

    // This function gives whether
    // a vertex is present or not.
    public void hasVertex(User s)
    {
        if (map.containsKey(s)) {
            System.out.println("The graph contains "
                    + s + " as a vertex.");
        }
        else {
            System.out.println("The graph does not contain "
                    + s + " as a vertex.");
        }
    }

    // This function gives whether an edge is present or not.
    public void hasEdge(User s, User d)
    {
        if (map.get(s).contains(d)) {
            System.out.println("The graph has an edge between "
                    + s + " and " + d + ".");
        }
        else {
            System.out.println("The graph has no edge between "
                    + s + " and " + d + ".");
        }
    }

//    public LinkedList<User> findShortestPath(User start, User end) {
//        LinkedList<User> path = new LinkedList<>();
//        path.add(start);
//        if (start == end) return path;
//        LinkedList<User> shortest = new LinkedList<>();
//
//    }

    public Set<User> suggestionCandidates(User source) {
        Set<User> candidates = new HashSet<User>();
        return new HashSet<>();
    }

    // Prints the adjacency list of each vertex.
    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();

        for (User v : map.keySet()) {
            builder.append(v.toString()).append(": ");
            for (User w : map.get(v)) {
                builder.append(w.toString()).append(" ");
            }
            builder.append("\n");
        }

        return (builder.toString());
    }
}
