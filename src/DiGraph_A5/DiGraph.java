package DiGraph_A5;

import java.util.*;

public class DiGraph implements DiGraphInterface {
	private HashMap<String, Node> nodeMap; // Holds all of the nodes with LABEL key
	private List<Node> nodes; // Holds all of the nodes
	private Set<Long> nodeIds; // Holds all the node ID's
	private HashMap<Long, Edge> edges; // Holds all the edges with ID key
	
	public DiGraph() {
		nodes = new ArrayList<>();
		nodeMap = new HashMap<>();
		nodeIds = new HashSet<>();
		edges = new HashMap<>();
	}

	// Reset all the nodes to be unvisited
	public void resetNodesVisited() {
		for (Node node : nodes) {
			node.unvisit();
		}
	}

	private Node closestNode(HashMap<Node, Long> shortestPathMap) {
		// Set shortest distance to max value
		long shortestDistance = Long.MAX_VALUE;

		// Initialize closest node to null
		Node closestReachableNode = null;
		for (Node node : nodes) {
			// If node is visited, continue
			if (!node.isVisited()) {
				// Check to see if current distance is not max value
				long currentDistance = shortestPathMap.get(node);
				if (currentDistance != Long.MAX_VALUE) {
					// Update closest Node if current distance is less than shortest distance
					if (currentDistance < shortestDistance) {
						shortestDistance = currentDistance;
						closestReachableNode = node;
					}
				}
			}
		}
		return closestReachableNode;
	}

	// Add new node to the graph
	@Override
	public boolean addNode(long idNum, String label) {
		if (nodeMap.containsKey(label) || nodeIds.contains(idNum) || label == null) {
			return false;
		} else {
			// Create new node
			Node node = new Node(idNum, label);

			// Place node in HashMap of nodes
			nodeMap.put(label, node);

			// Place node ID in HashSet of node ID's
			nodeIds.add(idNum);

			// Add node to node list
			nodes.add(node);
			return true;
		}
	}

	// Add new edge to the graph
	@Override
	public boolean addEdge(long idNum, String sourceLabel, String destinationLabel, long weight, String edgeLabel) {
		// Check if edge id exists, edge label exists, source node exists, and destination node exists
		if (edges.containsKey(idNum) || !nodeMap.containsKey(sourceLabel) || !nodeMap.containsKey(destinationLabel) || idNum < 0) {
			return false;
		} else {

			// Create source node variable
			Node s = nodeMap.get(sourceLabel);

			// Create destination node variable
			Node d = nodeMap.get(destinationLabel);

			// Check to see if edge between nodes already exists
			for (Edge edge : s.getEdges()) {
				if (edge.getSource() == s && edge.getDestination() == d) {
					return false;
				}
			}

			// Create new edge
			Edge edge = new Edge(idNum, s, d, weight, edgeLabel);

			// Add edge to source
			s.addEdge(edge);

			// Add edge to HashMap of edges
			edges.put(idNum, edge);

			return true;
		}
	}

	@Override
	public boolean delNode(String label) {
		// TODO Auto-generated method stub

		// Check to see if node exists
		if (!nodeMap.containsKey(label)) {
			return false;
		}

		// Initialize node variable
		Node node = nodeMap.get(label);

		// Remove ID from node ID's
		nodeIds.remove(node.getId());

		// Remove node from node map
		nodeMap.remove(label);

		// Remove edges connected to the node
		for (Edge edge : node.getEdges()) {
			delEdge(edge.getSource().getLabel(), edge.getDestination().getLabel());
		}

		// Remove node from nodes list
		nodes.remove(node);

		return true;
	}

	@Override
	public boolean delEdge(String sLabel, String dLabel) {
		// TODO Auto-generated method stub
		// Check to see if nodes exist
		if (!nodeMap.containsKey(sLabel) || !nodeMap.containsKey(dLabel)) {
			return false;
		}

		// Create source variable
		Node s = nodeMap.get(sLabel);

		// Create destination variable
		Node d = nodeMap.get(dLabel);

		// Check to see if edge exists between nodes
		boolean exists = false;
		for (Edge edge : s.getEdges()) {
			if (edge.getSource() == s && edge.getDestination() == d) {
				exists = true;
			}
		}
		if (!exists) {
			return false;
		}

		// Find edge shared between the two
		for (int i = 0; i < s.getEdges().size(); i++) {
			if (s.getEdges().get(i).getDestination() == d) {
				// Initialize edge variable
				Edge e = s.getEdges().get(i);

				// Remove edge from source
				s.removeEdge(e);

				// Remove edge from destination
				d.removeEdge(e);

				// Remove edge from edge HashMap
				edges.remove(e.getId());
			}
		}

		return true;
	}

	@Override
	public long numNodes() {
		// TODO Auto-generated method stub
		return nodes.size();
	}

	@Override
	public long numEdges() {
		// TODO Auto-generated method stub
		return edges.size();
	}

	public ShortestPathInfo[] shortestPath(String sLabel) {
		// TODO Auto-generated method stub

		// Initialize output array
		ShortestPathInfo[] shorts = new ShortestPathInfo[(int) numNodes()];

		// Initialize location access variable
		int i = 0;
		for (Node dest : nodes) {
			shorts[i] = new ShortestPathInfo(dest.getLabel(), DijkstraShortPath(sLabel, dest.getLabel()));
			// Reset nodes visited to be able to run Dijkstra's shortest path algorithm again
			resetNodesVisited();
			i++;
		}
		return shorts;
	}

	public long DijkstraShortPath(String sLabel, String dLabel) {
		// Initialize start and end nodes
		Node start = nodeMap.get(sLabel);
		Node end = nodeMap.get(dLabel);
		if (start == end) {
			return 0;
		}

		// Initialize changedMap to hold all node paths
		HashMap<Node, Node> changedMap = new HashMap<>();
		changedMap.put(start, null);

		// Initialize shortPathMap to hold shortest path
		HashMap<Node, Long> shortPathMap = new HashMap<>();

		for (Node node : nodes) {
			if (node == start)
				shortPathMap.put(start, (long) 0);
			else shortPathMap.put(node, Long.MAX_VALUE);
		}

		// Add edges into shortMap and changedMap
		for (Edge edge : start.getEdges()) {
			shortPathMap.put(edge.getDestination(), edge.getWeight());
			changedMap.put(edge.getDestination(), start);
		}

		// Start node is visited
		start.visit();

		// Run until value is returned
		while (true) {
			Node currentNode = closestNode(shortPathMap);
			if (currentNode == null) {
				// Return -1 when there is no path
				return -1;
			}

			// If node is found
			if (currentNode == end) {
				Node childNode = end;

				// Run until parent node is null
				while (true) {
					Node parentNode = changedMap.get(childNode);
					if (parentNode == null) {
						break;
					}
					childNode = parentNode;
				}
				// Return shortest weight
				return shortPathMap.get(end);
			}

			// Set current node to visited
			currentNode.visit();

			// Set shortest path
			for (Edge edge : currentNode.getEdges()) {
				if (!edge.getDestination().isVisited()) {
					if (shortPathMap.get(currentNode) + edge.getWeight() < shortPathMap.get(edge.getDestination())) {
						shortPathMap.put(edge.getDestination(), shortPathMap.get(currentNode) + edge.getWeight());
						changedMap.put(edge.getDestination(), currentNode);
					}
				}
			}
		}
	}
}
