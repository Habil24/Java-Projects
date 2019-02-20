import java.io.*;
import java.text.*;
import java.util.*;

public class Assignment3 {
	public static String[] EvectorA;
    public static String[] EvectorB;
	
    static class Edge implements Comparable<Edge> {
        Node start;
        Node end;
        double weight;

        Edge(Node start, Node end, double weight) {
            this.start = start;
            this.end = end;
            this.weight = weight;
        }
        
        
        public int compareTo(Edge right) {
        	if(this.weight<right.weight)
                return -1;
        	else if(right.weight<this.weight)
                return 1;
        	return 0;
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


		public String toString() {
            return start.toString() + " " + end.toString() + " " + weight;
        }
    }

    static class Node {
        String label;
        Vector<Edge> edges;
        LinkedList<Node> neighbors;

        Node(String label) {
        	neighbors = new LinkedList<Node>();
            this.label = label;
            edges = new Vector<Edge>();
        }

        public String getLabel() {
			return label;
		}

		public Vector<Edge> getEdges() {
			return edges;
		}

		public String toString() {
            return label;
        }
    }

    static class Graph {
        public static HashMap<String, Node> nodes;

        Graph() {
            nodes = new HashMap<String, Node>();
        }

        void addEdge(String start, String end, double weight) {
            if (!nodes.containsKey(start)) {
                nodes.put(start, new Node(start));
            }

            if (!nodes.containsKey(end)) {
                nodes.put(end, new Node(end));
            }

            Node s = nodes.get(start);
            Node e = nodes.get(end);

            s.edges.add(new Edge(s, e, weight));
        }

        /* Returns the Node corresponding to label, or null if no such Node exists */
        Node getNode(String label) {
            return nodes.get(label);
        }
    }

    /* returns true if the edge was needed */
    static boolean mergeSet(Node start, Node end, Vector<Vector<Node>> sets) {
        int startvec = -1;
        int endvec = -1;

        for (int i = 0; i < sets.size(); i++) {
            if (sets.get(i).contains(start)) {
                startvec = i;
            }
            if (sets.get(i).contains(end)) {
                endvec = i;
            }
        }

        if (startvec == endvec && startvec != -1) {
            return false;
        } else if (startvec != -1 && endvec == -1) {
            sets.get(startvec).add(end);
            return true;
        } else if (endvec != -1 && startvec == -1) {
            sets.get(endvec).add(start);
            return true;
        } else if (startvec == endvec && startvec == -1) {
            Vector<Node> v = new Vector<Node>();
            v.add(start);
            v.add(end);
            sets.add(v);

            return true;
        } else if (startvec != -1 && endvec != -1 && startvec != endvec) {
            Vector<Node> v = sets.get(endvec);
            sets.get(startvec).addAll(v);
            sets.remove(endvec);

            return true;
        } else {
            System.err.println("How'd we get here?");
            return true;
        }
    }
    
 // Function for Cosine  Similarity
 	public static double cosineSimilarity(String[] vectorA, String[] vectorB) {
 	    double dotProduct = 0.0;
 	    double normA = 0.0;
 	    double normB = 0.0;
 	    for (int i = 0; i < vectorA.length; i++) {
 	        dotProduct += Double.parseDouble(vectorA[i]) * Double.parseDouble(vectorB[i]);
 	        normA += Math.pow(Double.parseDouble(vectorA[i]), 2);
 	        normB += Math.pow(Double.parseDouble(vectorB[i]), 2);
 	    }
 	    double result = dotProduct / (Math.sqrt(normA) * Math.sqrt(normB));
 	    if(result<0){
 	    	result = (1 - result)/2;
 	    }
 	    return result;
 	    
 	}
 	
 	// Find the Cosine similarity of 2 words
 	public static double cosSimilarityEDGE(String w1, String w2, String vectorsFile) throws IOException{
 		File file = new File(vectorsFile);
         FileReader fr = new FileReader(file);
         BufferedReader br = new BufferedReader(fr);
         String line;
         String [] tokens;
         // Start adding vertices
         while( (line = br.readLine()) != null ){
         	tokens = line.split(" ");
         	if(tokens[0].substring(1, tokens[0].length()-1).equals(w1)){
         		EvectorA = new String[tokens.length-1];
         		System.arraycopy(tokens, 1, EvectorA, 0, tokens.length-1);
         	}else if(tokens[0].substring(1, tokens[0].length()-1).equals(w2)){
         		EvectorB = new String[tokens.length-1];
         		System.arraycopy(tokens, 1, EvectorB, 0, tokens.length-1);
         	}
         }
         double cosSimilarity = cosineSimilarity(EvectorA, EvectorB);
         br.close();
         //System.out.println("The cosine similarity of "+ w1 +  " and " + w2 + ":\t" + cosSimilarity);
 		 return cosSimilarity;
 		
 	}
 	
 	public static void readWordsToArrayList(String fileName,List<String> wordList){
		Scanner scan = null;
		String inputLine = "";
		// Main part of the function
		try{
			scan = new Scanner(new BufferedReader(new FileReader(fileName)));
			while(scan.hasNextLine()){
				inputLine = scan.nextLine();
				String[] temp = inputLine.split("-");
				wordList.add(temp[0]);
				wordList.add(temp[1]);
			}
		}catch(Exception e){
			System.out.println("There was a problem while setting up the array!");
		}
	}

 	public static void createCompleteGraph(Graph g,List<String> wordList,String vectorsFile) throws IOException{
		// Add the particular edges to each vertex so that it will be completed
		for(int n=0;n<wordList.size();n++){
			for(int e=0;e<wordList.size();e++){
				if(!(wordList.get(n).equals(wordList.get(e)))){
					g.addEdge(wordList.get(n), wordList.get(e), cosSimilarityEDGE(wordList.get(n), wordList.get(e), vectorsFile));
				}
			}
		}
	}
 	
 	public static void DFSUtil(ClusterGraph.Node v){
 		v.setVisited(true);
 		System.out.print(v.getLabel() + " ");
 		for(ClusterGraph.Node u : v.getNeighbors()){
 			if(u.isVisited() == false){
 				DFSUtil(u);
 			}
 		}
 	}
 	
 	@SuppressWarnings("static-access")
	public static void clustering(Vector<Edge> tree, ClusterGraph clus, int k){
 		// Delete the k-1 edges
 		for(int i=0;i<k;i++){
 			tree.remove(i);
 		}
 		
 		// Add MST edges to New ClusterGraph without k-1 edges
 		for(int i=0;i<tree.size();i++){
 			clus.addEdge(tree.get(i).getStart().label, tree.get(i).getEnd().label);
 		}
 		String newLine = System.getProperty("line.separator");
 		// For every Vertex-Node v-n
 		for(ClusterGraph.Node v: ClusterGraph.nodesFinal.values()){
 			if(v.isVisited() == false){
 				DFSUtil(v);
 				System.out.println(newLine);
 			}
 		}
 		
 	}
 	
 	
    public static void main(String[] args) throws IOException {
    	//Start time
    	long startTime = System.currentTimeMillis();
        String vectorsFile = args[0]; // "/home/habil/Desktop/Assignments/Assignment3/wordVec.txt"
        String wordsFile = args[1]; //"/home/habil/Desktop/Assignments/Assignment3/sample1_word_pairs.txt"
    	Graph g = new Graph();
        List<String> wordList = new ArrayList<String>();
        readWordsToArrayList(wordsFile, wordList);
        createCompleteGraph(g, wordList, vectorsFile);
        int numOfClusters = Integer.parseInt(args[2]);
        Vector<Edge> edges = new Vector<Edge>();
        
        for (Node n : g.nodes.values()) {
            for (Edge e : n.edges) {
                edges.add(e);
            }
        }

        Collections.sort(edges);
        Vector<Edge> tree = new Vector<Edge>();
        // Adding edges to tree vector
        Vector<Vector<Node>> sets = new Vector<Vector<Node>>();
        for (Edge e : edges) {
            if (mergeSet(e.start, e.end, sets)) {
                tree.add(e);
            }
        }
        PrintStream out = new PrintStream(new FileOutputStream("Clusters.txt"), false, "UTF-8");
		System.setOut(out);
        // Clustering 
        ClusterGraph clus = new ClusterGraph();
        clustering(tree,clus, numOfClusters);
       
        
    }
}
