import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Vector;


public class ClusterGraph {
	
	
	
	static class Edge{
		Node start;
        Node end;
        double weight;

        Edge(Node start, Node end, double weight) {
            this.start = start;
            this.end = end;
            this.weight = weight;
        }

		public Node getStart() {
			return start;
		}

		public Node getEnd() {
			return end;
		}

		public double getWeight() {
			return weight;
		}
        
        
        
	}
	
	static class Node{
		String label;
		ArrayList<Node> neighbours;
		boolean visited;
		
		public Node(String label) {
	           this.label = label;
	           this.visited = false;
	           neighbours = new ArrayList<Node>();
	    }
		
		public void addNeighbour(Node u){
	        this.neighbours.add(u);
	    }
		
		
        public String getLabel() {
			return label;
		}


		public ArrayList<Node> getNeighbors() {
			return neighbours;
		}

		public boolean isVisited() {
			return visited;
		}

		public void setVisited(boolean visited) {
			this.visited = visited;
		}
	}
	
	// Class Instances
	public static ArrayList<Edge> edgeList;
	public static HashMap<String, Node> nodesFinal;
	
	public ClusterGraph() {
		nodesFinal = new HashMap<String, Node>();
		edgeList = new ArrayList<Edge>();
		
	}
	public static boolean hasVertex(String key){
        return nodesFinal.containsKey(key);
    }
	
	public static void getNeighbouredNodes(){
		for(Node n: nodesFinal.values()){
			System.out.print(n.label + "----->  ");
			for(int i=0;i<n.neighbours.size();i++){
				System.out.print(n.neighbours.get(i).label + " ");
			}
			System.out.println("\n");
		}
	}
	
	// Add new edge
	public static void addEdge(String start,String end){
		if(hasVertex(start) && hasVertex(end)){
			Node s;
			Node e;
			s = nodesFinal.get(start);
			e = nodesFinal.get(end);
			s.addNeighbour(e);
			e.addNeighbour(s);
		}else if(!hasVertex(start) && !hasVertex(end)){
			Node s = new Node(start);
			Node e = new Node(end);
			s.addNeighbour(e);
			e.addNeighbour(s);
			nodesFinal.put(start, s);
			nodesFinal.put(end, e);
		}else if(hasVertex(start) && !hasVertex(end)){
			Node s;
			Node e = new Node(end);
			s = nodesFinal.get(start);
			s.addNeighbour(e);
			e.addNeighbour(s);
			nodesFinal.put(end, e);
		}else if(!hasVertex(start) && hasVertex(end)){
			Node s = new Node(start);
			Node e;
			e = nodesFinal.get(end);
			s.addNeighbour(e);
			e.addNeighbour(s);
			nodesFinal.put(start, s);
		}
	}
	
}

