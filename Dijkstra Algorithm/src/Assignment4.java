import java.util.*;
import java.io.*;
import java.nio.*;
import java.lang.*;
import java.text.*;

@SuppressWarnings("unused")
public class Assignment4 {
	
	// Function to get number of Lines
	public static int numOfLines(String fileName){
	    File file = new File(fileName);
	    try{
	    FileReader fr = new FileReader(file);
	    LineNumberReader lnr = new LineNumberReader(fr);
	    int linenumber = 0;
	    while (lnr.readLine() != null){
	    	  linenumber++;
	    }
	    lnr.close();
	    return linenumber;
		}catch(IOException e){
			System.out.println("There was an error while reading the lines");
			return 0;
		}
	}
	
	public static void createDirectedGrap(String fileName,RailNetwork railways){
		Scanner scan = null;
		String inputLine = "";
		// Main part of the function
		try{
			scan = new Scanner(new BufferedReader(new FileReader(fileName)));
			while(scan.hasNextLine()){
				inputLine = scan.nextLine();
				String[] temp = inputLine.split("-|\\ ");
				railways.addinitialRails(temp[0], temp[1], Double.parseDouble(temp[2]));
			}
			scan.close();
		}catch(Exception e){
			System.out.println("There was an error while creating the graph!");
		}
		
	}
	// Adjust Graph into Topology including switches
	public static void adjustTopology(RailNetwork railways,String fileName){
		Scanner scan = null;
		String inputLine = "";
		// Main part of the function
		try{
			scan = new Scanner(new BufferedReader(new FileReader(fileName)));
			while(scan.hasNextLine()){
				inputLine = scan.nextLine();
				String[] temp = inputLine.split(":|\\>");
				String mainIntersection = temp[0];
				String switchPoint = temp[temp.length-1];
				for(int i=0;i<railways.getRailList().size();i++){
					if(railways.getRailList().get(i).getSrc().getLabel().equals(mainIntersection) && railways.getRailList().get(i).getDest().getLabel().equals(switchPoint)){
						railways.getRailList().get(i).setActiveSwitch(true);
					}
				}
			}
			scan.close();
		}catch(Exception e){
			System.out.println("There was an error while adjusting graph into topology!");
		}
		
	}
	
	@SuppressWarnings("static-access")
	public static void operate(String commandsFile,RailNetwork railways,double switchTime) throws FileNotFoundException{
		Scanner scan = null;
		String inputLine = "";
		// Main part of the function
		
			scan = new Scanner(new BufferedReader(new FileReader(commandsFile)));
			while(scan.hasNextLine()){
				inputLine = scan.nextLine();
				String[] temp = inputLine.split(" ");
				if(temp[0].equals("MAINTAIN")){
					railways.maintain(temp[1]);
				}else if(temp[0].equals("BREAK")){
					String[] temp1 = inputLine.split(" |\\>");
					railways.Break(temp1[1], temp1[2]);
				}
				else if(temp[0].equals("ROUTE")){
					railways.route(inputLine, switchTime);
				}
				else if(temp[0].equals("REPAIR")){
					String[] temp1 = inputLine.split(" |\\>");
					railways.repair(temp1[1], temp1[2]);
				}else if(temp[0].equals("SERVICE")){
					railways.service(temp[1]);
				}else if(temp[0].equals("ADD")){
					railways.add(temp[1]);
				}else if(temp[0].equals("LISTROUTESFROM")){
					railways.listRoutesFrom(temp[1]);
				}else if(temp[0].equals("LINK")){
					railways.link(inputLine);
				}else if(temp[0].equals("LISTMAINTAINS")){
					railways.listMaintains();
				}
				else if(temp[0].equals("LISTACTIVERAILS")){
					railways.listActiveRails();
				}else if(temp[0].equals("LISTBROKENRAILS")){
					railways.listBrokenRails();
				}else if(temp[0].equals("LISTCROSSTIMES")){
					railways.listCrossTimes();
				}else if(temp[0].equals("TOTALNUMBEROFJUNCTIONS")){
					railways.totalNumberOfJunctions();
				}else if(temp[0].equals("TOTALNUMBEROFRAILS")){
					railways.totalNumberOfRails();
				}else{
					railways.dummyCommand(inputLine);
				}
				
			}
			scan.close();
		
	}
	
	
	public static void main(String[] args) throws FileNotFoundException {
		// Files
		String distanceFile = args[1];
		String topologyFile = args[0];
		String commandsFile = args[2];
		double switchTime = Integer.parseInt(args[3]);
		
		// Create initial Graph
		RailNetwork railWays = new RailNetwork();
		createDirectedGrap(distanceFile, railWays);
		
		// Adapt graph into topology
		adjustTopology(railWays, topologyFile);
		
		// Operate function
		operate(commandsFile, railWays, switchTime);
		
		
		
		
	}

}
