package DiGraph_A5;

public interface DiGraphInterface {
	public boolean addNode(long idNum, String label);
	public boolean addEdge(long idNum, String sLabel, String dLabel, long weight, String eLabel);
	public boolean delNode(String label);
	public boolean delEdge(String sLabel, String dLabel);
	public long numNodes();
	public long numEdges();
	ShortestPathInfo[] shortestPath(String sLabel);
}
