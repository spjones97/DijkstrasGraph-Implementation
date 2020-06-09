package DiGraph_A5;

public class Edge {
    private Node source;
    private Node destination;
    private long weight;
    private long id;
    private String label;

    public Edge(long id, Node s, Node d, long weight, String label) {
        this.id = id;
        this.source = s;
        this.destination = d;
        this.weight = weight;
        this.label = label;
    }

    public Node getSource() {
        return source;
    }

    public Node getDestination() {
        return destination;
    }

    public long getWeight() {
        return weight;
    }

    public long getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }
}
