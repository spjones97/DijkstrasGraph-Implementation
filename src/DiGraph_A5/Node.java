package DiGraph_A5;

import java.util.*;

public class Node {
    private long id;
    private String label;
    private LinkedList<Edge> edges;
    private boolean visited;

    public Node(long id, String label) {
        this.id = id;
        this.label = label;
        edges = new LinkedList<>();
        visited = false;
    }

    public void addEdge (Edge e) {
        edges.add(e);
    }

    public void removeEdge (Edge e) {
        edges.remove(e);
    }

    public LinkedList<Edge> getEdges() {
        return edges;
    }

    public String getLabel() {
        return label;
    }

    public long getId() {
        return id;
    }

    public boolean isVisited() {
        return visited;
    }

    public void visit() {
        visited = true;
    }

    public void unvisit() {
        visited = false;
    }
}
