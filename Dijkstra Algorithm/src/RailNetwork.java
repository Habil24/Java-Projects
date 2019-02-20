import java.util.*;
import java.util.Map.Entry;
import java.io.*;
import java.lang.invoke.SwitchPoint;


@SuppressWarnings("unused")
public class RailNetwork {
	
	//RailNetwork Class Instances
	private static final String newLine = System.getProperty("line.separator");
	// HashMap of Intersections 
	private static HashMap<String, Intersection> intersections;
	// Edge/Rail LIst
	private static ArrayList<Rail> railList;
	
	//RailNetwork Constructor
	public RailNetwork(){
		RailNetwork.intersections = new HashMap<String, Intersection>();
		RailNetwork.railList = new ArrayList<Rail>();
	}
	
	public ArrayList<Rail> getRailList() {
		return railList;
	}

	public static HashMap<String, Intersection> getIntersections() {
		return intersections;
	}

	// The private class Rail describes EDGE
	public static  class Rail{	
		//Class Instances
		private Intersection src;
		private Intersection dest;
		boolean isActiveSwitch;
		boolean isBroken;
		double distance;
		
		//Class Constructor
		public Rail(Intersection src,Intersection dest,double distance){
			this.src = src;
			this.dest = dest;
			this.distance = distance;
			isBroken = false;
			isActiveSwitch = false;
			src.getNeigbours().add(dest);
			dest.getNeigbours().add(src);
			
		}

		public boolean isActiveSwitch() {
			return isActiveSwitch();
		}

		public void setActiveSwitch(boolean isActive) {
			this.isActiveSwitch = isActive;
		}

		public boolean isBroken() {
			return isBroken;
		}

		public void setBroken(boolean isBroken) {
			this.isBroken = isBroken;
		}

		public double getDistance() {
			return distance;
		}

		public void setDistance(double distance) {
			this.distance = distance;
		}

		public Intersection getSrc() {
			return src;
		}

		public Intersection getDest() {
			return dest;
		}
		
		@Override
		public String toString(){
			return this.getSrc().toString() + "-" + this.getDest().toString() + " " + this.getDistance();
		}
	}
	
	// The private class Intersection describes VERTEX
	public static class Intersection implements Comparable<Intersection>{
		
		//Class Instances
		private String label;
		private boolean underMaintenance;
		// This part is for Dijkstra's
		public static int id=0;
		private boolean isVisited;
		public double distanceFromSource = Double.POSITIVE_INFINITY;
		public Intersection previous;
		public int totalSwitch;
		public LinkedList<Intersection> switches;
		public LinkedList<Intersection> path;
		////////////////////////////////////////////////////////////
		private int totalPasses;
		private ArrayList<Intersection> neigbours;
		private List<Intersection> shortestPath = new LinkedList<>();
		
		public List<Intersection> getShortestPath() {
			return shortestPath;
		}

		public void setShortestPath(List<Intersection> shortestPath) {
			this.shortestPath = shortestPath;
		}

		//Class Constructor
		public Intersection(String label){
			switches = new LinkedList<RailNetwork.Intersection>();
			this.label = label;
			this.underMaintenance = false;
			neigbours = new ArrayList<Intersection>();
			isVisited = false;
			path = new LinkedList<RailNetwork.Intersection>();
			
		}
		
		public double getDistanceFromSource() {
			return distanceFromSource;
		}

		public void setDistanceFromSource(double minDistance) {
			this.distanceFromSource = minDistance;
		}

		public ArrayList<Intersection> getNeigbours() {
			return neigbours;
		}

		private boolean isNeighbour(String check){
			boolean result;
			for(int i=0;i<this.neigbours.size();i++){
				if(this.neigbours.get(i).label.equals(check)){
					result = true;
				}
			}
			result = false;
			return result;
		}

		public boolean isUnderMaintenance() {
			return underMaintenance;
		}

		public void setUnderMaintenance(boolean underMaintenance) {
			this.underMaintenance = underMaintenance;
		}

		public int getTotalPasses() {
			return totalPasses;
		}

		public void setTotalPasses() {
			this.totalPasses = totalPasses + 1;
		}

		public String getLabel() {
			return label;
		}
		
		public Intersection getPrevious() {
			return previous;
		}

		public void setPrevious(Intersection previous) {
			this.previous = previous;
		}

		@Override
		public String toString(){
			return this.label;
		}

		@Override
		public int compareTo(Intersection o) {
			 return Double.compare(distanceFromSource, o.distanceFromSource);
		}

			
	}
	
	// Find if Intersection exists
	private static boolean hasIntersection(String check){
		return intersections.containsKey(check);
	}
	
	//Create Initial Network
	public void addinitialRails(String s,String d,double dist){		
		if(hasIntersection(s) && hasIntersection(d)){
			Intersection src;
			Intersection dst;
			src = intersections.get(s);
			dst = intersections.get(d);
			if(!src.isNeighbour(d)){
				src.neigbours.add(dst);
			}
			if(!dst.isNeighbour(s)){
				dst.neigbours.add(src);
			}
			Rail rail = new Rail(src, dst, dist);
			Rail rail2 = new Rail(dst, src, dist);
			railList.add(rail);
			railList.add(rail2);
			
		}else if(!hasIntersection(s) && !hasIntersection(d)){
			Intersection src = new Intersection(s);
			Intersection dst = new Intersection(d);
			if(!src.isNeighbour(d)){
				src.neigbours.add(dst);
			}
			if(!dst.isNeighbour(s)){
				dst.neigbours.add(src);
			}
			intersections.put(s, src);
			intersections.put(d, dst);
			Rail rail = new Rail(src, dst, dist);
			Rail rail2 = new Rail(dst, src, dist);
			railList.add(rail);
			railList.add(rail2);
			
		}else if(hasIntersection(s) && !hasIntersection(d)){
			Intersection src;
			Intersection dst = new Intersection(d);
			src = intersections.get(s);
			if(!src.isNeighbour(d)){
				src.neigbours.add(dst);
			}
			if(!dst.isNeighbour(s)){
				dst.neigbours.add(src);
			}
			intersections.put(d, dst);
			Rail rail = new Rail(src, dst, dist);
			Rail rail2 = new Rail(dst, src, dist);
			railList.add(rail);
			railList.add(rail2);
			
		}else if(!hasIntersection(s) && hasIntersection(d)){
			Intersection src = new Intersection(s);
			Intersection dst;
			dst = intersections.get(d);
			if(!src.isNeighbour(d)){
				src.neigbours.add(dst);
			}
			if(!dst.isNeighbour(s)){
				dst.neigbours.add(src);
			}
			intersections.put(s, src);
			Rail rail = new Rail(src, dst, dist);
			Rail rail2 = new Rail(dst, src, dist);
			railList.add(rail);
			railList.add(rail2);
		}
	}
	
	// Maintain Intersection
	public static void maintain(String s){
		Intersection i = intersections.get(s);
		if(i.underMaintenance == false){
			i.setUnderMaintenance(true);
			System.out.println("COMMAND IN PROCESS >> MAINTAIN " + i.label);
			System.out.println("\tCommand \"MAINTAIN "+ i.label + "\"  has been executed successfully!");
		}else{
			System.out.println("\tERROR: "+ i.label + " is already in under maintenance.");
		}
		
	}
	
	// Service Intersection
	public static void service(String s){
		Intersection i = intersections.get(s);
		if(i.underMaintenance == true){
			i.setUnderMaintenance(false);
			System.out.println("COMMAND IN PROCESS >> SERVICE " + i.label);
			System.out.println("\tCommand \"SERVICE "+ i.label + "\"  has been executed successfully!");
		}else{
			System.out.println("\tERROR: " + i.label + " is already in service.");
		}
	}
	
	// Break Rail/EDGE between 2 intersections
	public static void Break(String Src,String Dest){
		System.out.println("COMMAND IN PROCESS >> BREAK " + Src + ">" + Dest);
		for(int i=0;i<railList.size();i++){
			if(railList.get(i).src.label.equals(Src) && railList.get(i).dest.label.equals(Dest)){
				railList.get(i).setBroken(true);
				System.out.println("\tCommand " + "\"BREAK " + Src + ">" + Dest + "\"  has been executed successfully!");
			}
		}
		
	}
	
	// Repair Rail/EDGE between 2 intersections
	public static void repair(String Src,String Dest){
		System.out.println("COMMAND IN PROCESS >> REPAIR " + Src + ">" + Dest);
		for(int i=0;i<railList.size();i++){
			if(railList.get(i).src.label.equals(Src) && railList.get(i).dest.label.equals(Dest)){
				railList.get(i).setBroken(false);
				System.out.println("\tCommand " + "\"REPAIR " + Src + ">" + Dest + "\"  has been executed successfully!");
			}
		}
	}
	
	// Add intersection to Railway Network
	public void add(String s){
		System.out.println("COMMAND IN PROCESS >> ADD " + s);
		Intersection i = new Intersection(s);
		intersections.put(s, i);
		System.out.println("\tCommand \"ADD " + s + "\"  has been executed successfully!");
		
	}
	
	// LINK TUNEL_SISHANE:KARAKOY-3,TAKSIM-2,YENIKAPI-6>TAKSIM
	public static void link(String wholeLine){
		System.out.println("COMMAND IN PROCESS >> " + wholeLine);
		String[] temp = wholeLine.split(":|\\,|\\>");
		String newIntersection = temp[0].split(" ")[1];
		String switchPoint = temp[temp.length-1];
		Intersection inter = intersections.get(newIntersection);
		// Connect node with others
		for(int n = 1;n<temp.length-1;n++){
			String[] edge = temp[n].split("-");
			Intersection t = intersections.get(edge[0]);
			Rail e1 = new Rail(inter, t, Double.parseDouble(edge[1]));
			Rail e2 = new Rail(t, inter, Double.parseDouble(edge[1]));
			railList.add(e1);
			railList.add(e2);
		}
		// Add also switch
		for(int i=0;i<railList.size();i++){
			if(railList.get(i).getSrc().getLabel().equals(newIntersection) && railList.get(i).getDest().getLabel().equals(switchPoint)){
				railList.get(i).setActiveSwitch(true);	
			}
		}
		// OUTPUT
		System.out.println("\tCommand \"" + wholeLine + "\"  has been executed successfully!");
	}
		
	// Find Route using dijkstra's shortest Path algorithm(NOTE!!! MAINTENANCE AND REPAIR STATUS OF RAILS AND INTERSECTIONS)
	public void route(String wholeLine,double switchTime){
		System.out.println("COMMAND IN PROCESS >> " + wholeLine);
		String[] temp = wholeLine.split(" |\\>");
		int vel = Integer.parseInt(temp[3]);
		double totalpath = 0;
		int numOfSwitches = 0;
		// My Source and Destination Nodes
		Intersection src = intersections.get(temp[1]);
		Intersection dest = intersections.get(temp[2]);
		// Start the operation
		Set<Intersection> visitedInters = new HashSet<RailNetwork.Intersection>();
		Set<Intersection> unvisitedInters = new HashSet<RailNetwork.Intersection>();
				src.distanceFromSource = 0;
				PriorityQueue<Intersection> queue = new PriorityQueue<Intersection>();
				queue.add(src);
				
				while(!queue.isEmpty()){
					
					Intersection u = queue.poll();
					
					for(Intersection neighbour: u.neigbours){
						Rail tempRail = null;
						for(int i=0;i<railList.size();i++){
							if(railList.get(i).getSrc().getLabel().equals(u.label) && railList.get(i).getDest().getLabel().equals(neighbour.label)){
								tempRail = railList.get(i);
							}
						}
						
						Double newDist = u.distanceFromSource+tempRail.getDistance();
						
						if(tempRail.getDest().getDistanceFromSource() > newDist && tempRail.getDest().isUnderMaintenance() == false && tempRail.getSrc().isUnderMaintenance()==false){
							if(tempRail.isBroken == false){
								if(tempRail.isActiveSwitch == true){
									queue.remove(tempRail.getDest());
									tempRail.getDest().setDistanceFromSource(newDist);
									
									tempRail.getDest().path = new LinkedList<RailNetwork.Intersection>(u.path);
									tempRail.getDest().path.add(u);
									
									queue.add(tempRail.getDest());
									
									}
							}					
						}
						if(tempRail.getDest().getDistanceFromSource() > newDist + (switchTime/60) * vel && tempRail.getDest().isUnderMaintenance() == false && tempRail.getSrc().isUnderMaintenance()==false){
							if(tempRail.isBroken == false){
								if(tempRail.isActiveSwitch == false){
									queue.remove(tempRail.getDest());
									tempRail.getDest().setDistanceFromSource(newDist + (switchTime/60) * vel);
									
									tempRail.getDest().path = new LinkedList<RailNetwork.Intersection>(u.path);
									tempRail.getDest().path.add(u);
									
									queue.add(tempRail.getDest());
								}
							}					
						}
					}
				}
				ArrayList<Intersection> temp1 = new ArrayList<RailNetwork.Intersection>();
				for(Intersection i : dest.path){
					temp1.add(i);
				}
				temp1.add(dest);
				// Active-Passive Stuff
				int counter = 0;
				for(int i=0;i<temp1.size();i++){
					ArrayList<Rail> tempRail = new ArrayList<RailNetwork.Rail>();
					for(int i1=0;i1<railList.size();i1++){
						// Adding all rails of current Intersection to arraylist
						if(railList.get(i1).getSrc().getLabel().equals(temp1.get(i).getLabel())){
							tempRail.add(railList.get(i1));
						}
					}
					for(Rail rail : tempRail){
							if( i< temp1.size()-1 && rail.dest.label.equals(temp1.get(i+1).label)){
								if(rail.isActiveSwitch == false){
									rail.setActiveSwitch(true);
									counter++;
									
								}
							}else if(i< temp1.size()-1){
								rail.setActiveSwitch(false);
							}
						
						
					}
				}
				///////////////////////////////////
				if(dest.distanceFromSource != Double.POSITIVE_INFINITY){
					System.out.printf("\tTime (in min): %.3f\n", ((dest.getDistanceFromSource()/vel) * 60));
					System.out.println("\tTotal # of switch changes: " + counter);
					System.out.print("\tRoute from " + src.label + " to " + dest.label + ": ");
					for(Intersection i : dest.path){
						System.out.print(i.label + " ");
						i.totalPasses += 1;
					}
					System.out.print(dest.label);
					
				}else{
					System.out.print("\tNo route from " + src.label +  " to " + dest.label + " found currently!");
				}
				
				for(Intersection i: intersections.values()){
					i.path.clear();
					i.distanceFromSource = Double.POSITIVE_INFINITY;
				}
				
				
				System.out.println("\n\tCommand \"" + wholeLine + "\"  has been executed successfully!");
				
				
		
	}
	
	// List routes from particular intersection
	public static void listRoutesFrom(String s){
		System.out.println("COMMAND IN PROCESS >> LISTROUTESFROM " + s);
		ArrayList<String> temp = new ArrayList<String>();
		Intersection i = intersections.get(s);
		for(int n=0;n<railList.size();n++){
			if(railList.get(n).getSrc().getLabel().equals(s)){
				temp.add(railList.get(n).getDest().getLabel());
			}
		}
		temp.sort(String::compareToIgnoreCase);
		System.out.print("\tRoutes from " + s + ": ");
		Collections.sort(temp);
		for (String string : temp) {
			System.out.print(string + " ");
		}
		System.out.println("\n\tCommand \"LISTROUTESFROM " + s + "\"  has been executed successfully!");
	}
	
	//List all Intersections that are in maintenance
	public static void listMaintains(){
		ArrayList<String> temp = new ArrayList<String>();
		System.out.println("COMMAND IN PROCESS >> LISTMAINTAINS");
		for(Intersection i : intersections.values()){
			if(i.underMaintenance == true){
				temp.add(i.label);
			}
		}
		Collections.sort(temp);
		if(temp.size() > 0){
			System.out.print("\tIntersections under maintenance: ");
			for(int i = 0;i<temp.size();i++){
				System.out.print(temp.get(i) + " ");
			}
		}
		System.out.println("\n\tCommand \"LISTMAINTAINS\"  has been executed successfully!");
	}
	
	// List Active Rails
	public static void listActiveRails(){
		ArrayList<String> temp = new ArrayList<String>();
		System.out.println("COMMAND IN PROCESS >> LISTACTIVERAILS");
		System.out.print("\tActive Rails: ");
		for(int i=0;i<railList.size();i++){
			if(railList.get(i).isBroken == false && railList.get(i).isActiveSwitch == true){
				temp.add(railList.get(i).getSrc().getLabel() + ">" + railList.get(i).getDest().getLabel());
			}
		}
		Collections.sort(temp);
		for(int i = 0;i<temp.size();i++){
			System.out.print(temp.get(i) + " ");
		}
		System.out.println("\n\tCommand \"LISTACTIVERAILS\"  has been executed successfully!");
	}
	
	// List broken Rails
	public static void listBrokenRails(){
		ArrayList<String> temp = new ArrayList<String>();
		System.out.println("COMMAND IN PROCESS >> LISTBROKENRAILS");
		System.out.print("\tBroken rails: ");
		for(int i=0;i<railList.size();i++){
			if(railList.get(i).isBroken == true){
				temp.add(railList.get(i).getSrc().getLabel() + ">" + railList.get(i).getDest().getLabel());
			}
		}
		Collections.sort(temp);
		for(int i = 0;i<temp.size();i++){
			System.out.print(temp.get(i) + " ");
		}
		System.out.println("\n\tCommand \"LISTBROKENRAILS\"  has been executed successfully!");
	}
	
	// List Cross times of all Intersections
	public static void listCrossTimes(){
		ArrayList<String> temp = new ArrayList<String>();
		System.out.println("COMMAND IN PROCESS >> LISTCROSSTIMES");
		for(Intersection i : intersections.values()){
			if(i.getTotalPasses()>0){
				temp.add(i.label + ":" + i.getTotalPasses());
			}
		}
		Collections.sort(temp);
		System.out.print("\t# of cross times: " );
		for(int i = 0;i<temp.size();i++){
			System.out.print(temp.get(i) + " ");
		}
		System.out.print("\n\tCommand \"LISTCROSSTIMES\"  has been executed successfully!");
		
	}
	
	//Total Number of Juctions/Intersections
	public static void totalNumberOfJunctions(){
		System.out.println("\nCOMMAND IN PROCESS >> TOTALNUMBEROFJUNCTIONS");
		System.out.println("\tTotal # of junctions: " + intersections.values().size());
		System.out.println("\tCommand \"TOTALNUMBEROFJUNCTIONS\"  has been executed successfully!");
		
	}
	
	//Total number of Rails	
	public static void totalNumberOfRails(){
		System.out.println("COMMAND IN PROCESS >> TOTALNUMBEROFRAILS");
		System.out.println("\tTotal # of rails: " + railList.size());
		System.out.println("\tCommand \"TOTALNUMBEROFRAILS\"  has been executed successfully!");
	}
	
	// Dummy Command Output(int the end else{})
	public static void dummyCommand(String s){
		System.out.println("COMMAND IN PROCESS >> " + s);
		System.out.println("\tUnrecognized command \"" + s + "\"!");
	}
	
	
}
