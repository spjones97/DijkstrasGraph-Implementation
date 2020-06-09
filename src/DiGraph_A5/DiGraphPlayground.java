package DiGraph_A5;

public class DiGraphPlayground {
	
	public static void main (String[] args) {
//		exTest();
		shortTest0();
		shortTest1();
		shortTest2();
		shortTest3();
//		AddEdgeTest4();
	}
	
	public static void shortTest0() {
		DiGraph d = new DiGraph();
		d.addNode(0, "a");
		d.addNode(1, "b");
		d.addNode(2, "c");
		d.addEdge(0, "a", "b", 3, null);
		d.addEdge(1, "b", "c", 4, null);
		d.addEdge(2, "a", "c", 5, null);
		ShortestPathInfo[] s = d.shortestPath("a");
		for (ShortestPathInfo shortestPathInfo : s) {
			System.out.println(shortestPathInfo.toString());
		}
		System.out.println("");
	}
	
	public static void shortTest1() {
		DiGraph d = new DiGraph();
		d.addNode(0, "a");
		d.addNode(1, "b");
		d.addNode(2, "c");
		d.addEdge(0, "a", "b", 3, null);
		d.addEdge(1, "b", "c", 4, null);
		d.addEdge(2, "a", "c", 10, null);
		ShortestPathInfo[] s = d.shortestPath("a");
		for (ShortestPathInfo shortestPathInfo : s) {
			System.out.println(shortestPathInfo.toString());
		}
		System.out.println("");
	}
	
	public static void shortTest2() {
		DiGraph d = new DiGraph();
		d.addNode(0, "a");
		d.addNode(1, "b");
		d.addNode(2, "c");
		d.addNode(3, "d");
		d.addEdge(0, "a", "b", 1, null);
		d.addEdge(1, "b", "c", 2, null);
		d.addEdge(2, "c", "a", 3, null);
		d.addEdge(3, "c", "d", 2, null);
		d.addEdge(4, "d", "b", 1, null);
		ShortestPathInfo[] s = d.shortestPath("a");
		for (ShortestPathInfo shortestPathInfo : s) {
			System.out.println(shortestPathInfo.toString());
		}
		System.out.println("");
	}
	
	public static void shortTest3() {
		DiGraph d = new DiGraph();
		d.addNode(1, "p");
		d.addNode(4, "a");
		d.addNode(3, "g");
		d.addNode(2, "e");
		d.addEdge(0, "p", "a", 10, null);
		d.addEdge(1, "a", "g", 12, null);
		d.addEdge(2, "g", "e", 1, null);
		d.addEdge(3, "e", "p", 6, null);
		d.addEdge(4, "p", "g", 4, null);
		d.addEdge(5, "a", "e", 100, null);
		d.addEdge(6, "a", "p", 9, null);
		d.addEdge(7, "e", "a", 3, null);
		d.addEdge(8, "g", "a", 15, null);
		d.addEdge(9, "p", "e", 8, null);
		d.addEdge(10, "g", "p", 2, null);
		ShortestPathInfo[] s = d.shortestPath("p");
		for (ShortestPathInfo shortestPathInfo : s) {
			System.out.println(shortestPathInfo.toString());
		}
		System.out.println("");
	}
	
	public static void AddEdgeTest4() {
		DiGraph d = new DiGraph();

		d.addNode(0, "0");
		d.addNode(1, "1");
		d.addNode(2, "2");
		d.addNode(3, "3");
		d.addNode(4, "4");
		d.addNode(5, "5");
		d.addNode(6, "6");

		d.addEdge(0, "0", "1", 8, null);
		d.addEdge(1, "0", "2", 11, null);
		d.addEdge(2, "1", "3", 3, null);
		d.addEdge(3, "1", "4", 8, null);
		d.addEdge(4, "1", "2", 7, null);
		d.addEdge(5, "2", "4", 9, null);
		d.addEdge(6, "3", "4", 5, null);
		d.addEdge(7, "3", "5", 2, null);
		d.addEdge(8, "4", "6", 6, null);
		d.addEdge(9, "5", "4", 1, null);
		d.addEdge(10, "5", "6", 8, null);

		d.DijkstraShortPath("0", "6");
	}
	
	public static void exTest() {
	  DiGraph d = new DiGraph();
	  d.addNode(1, "f");
	  d.addNode(3, "s");
	  d.addNode(7, "t");
	  d.addNode(0, "fo");
	  d.addNode(4, "fi");
	  d.addNode(6, "si");
	  d.addEdge(0, "f", "s", 0, null);
	  d.addEdge(1, "f", "si", 0, null);
	  d.addEdge(2, "s", "t", 0, null);
	  d.addEdge(3, "fo", "fi", 0, null);
	  d.addEdge(4, "fi", "si", 0, null);
	  d.delEdge("f", "s");
	  System.out.println("numEdges: "+d.numEdges());
	  System.out.println("numNodes: "+d.numNodes());
	}
}
