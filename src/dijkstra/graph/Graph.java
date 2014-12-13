package dijkstra.graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Created by Alex on 06/11/2014.
 */
public class Graph {

	private int height, width;
	private ArrayList<Node> nodes = new ArrayList<Node>();

    public Graph(int width, int height) {
		this.height = height;
		this.width = width;
	}
    
    /**
     * Ajoute un node au graph
     * @param node
     */
    public void registerNode(Node node) {
    	this.nodes.add(node);
    }
    
    /**
     * Enleve un node au graph
     * @param id
     */
	public void unregisterNode(int id) {
		// TODO inutile ?
	}
	
	public ArrayList<Node> getNodes() {
		return this.nodes;
	}

	public int getHeight(){
		return this.height;
	}

	public int getWidth(){
		return this.width;
	}

	@Override
	public String toString() {
		StringBuilder str = new StringBuilder("Graph [height=" + height + ", width=" + width + ", nodes.size=" + nodes.size() + " nodes{\n");
		for (Node node : nodes) {
			str.append("\t" + node + "\n");
		}
		str.append("}]");
		return str.toString();
	}
	
	/**
	 * Dijkstra
	 * @param source
	 */
	public void computePaths(Node source) {
		PriorityQueue<Node> nodeQueue = new PriorityQueue<>();
		nodeQueue.add(source);
		while (! nodeQueue.isEmpty()) {
			Node current = nodeQueue.poll();
			
			for (Edge edge: current.getEdges()) {
				Node next = edge.getOther();
				//System.out.println(next);
				double weight = edge.getWeight();
				double distance = (current.minDistance == Double.POSITIVE_INFINITY) ? weight : current.minDistance + weight;
				if (distance < next.minDistance) {
					nodeQueue.remove(next);
					next.minDistance = distance;
					next.previous = current;
					nodeQueue.add(next);
				}
			}
			//System.out.println(current);
		}
		source.previous = null;
	}
	
	public List<Node> getShortestPathTo(Node target) {
        List<Node> path = new ArrayList<>();
        Node node = target;
        while (node != null) {
        	//System.out.println(node);
        	path.add(node);
        	node = node.previous;
        } 
        Collections.reverse(path);
        return path;
    }
	
	public Node getDoor() {
		Node door = null;
		for (Node node : nodes) {
			if (node.type.equals(Node.DOOR)) {
				door = node;
			}
		}
		return door;
	}
	
	public Node getCheese() {
		Node cheese = null;
		for (Node node : nodes) {
			if (node.type.equals(Node.CHEESE)) {
				cheese = node;
			}
		}
		return cheese;
	}
}

