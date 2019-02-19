import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class UserCollection {
	
	/**
	 * @param filename
	 * file name
	 * @return
	 * @throws IOException
	 * Counts the lines of the text file and returns number of it
	 */
	public static int countLines(String filename) throws IOException {
	    LineNumberReader reader  = new LineNumberReader(new FileReader(filename));
		int cnt = 0;
		String lineRead = "";
		while ((lineRead = reader.readLine()) != null) {}
		cnt = reader.getLineNumber(); 
		reader.close();
		return cnt;
	}
	
	/**
	 * @param filePath
	 * file name
	 * @param rownum
	 * row number
	 * @return
	 * @throws FileNotFoundException
	 * throws exception
	 * Convert to 2D List
	 */
	public static  ArrayList<ArrayList<String>> to2DList(String filePath,int rownum) throws FileNotFoundException{
		ArrayList<ArrayList<String>> final_arraylist = new ArrayList<ArrayList<String>>();	
		Scanner scanner = new Scanner(new File(filePath));
		while(scanner.hasNextLine()){
			 String line = scanner.nextLine();
			 String[] line_array = line.trim().split("\t");
			 final_arraylist.add(new ArrayList<String>(Arrays.asList(line_array)));
			 
		}
		scanner.close();
		return final_arraylist;
	}
	
	public static ArrayList<ArrayList<String>> user_arraylist = new ArrayList<ArrayList<String>>();
	private static ArrayList<User> user_objects = new ArrayList<User>();
	
	/**
	 * @param args
	 * command line argument
	 * @throws FileNotFoundException
	 * file not found exception
	 * @throws IOException
	 * input output exception
	 * Fill Users to arraylist
	 */
	public static void fill_user_arraylist(String[] args) throws FileNotFoundException, IOException{
		user_arraylist = to2DList(args[0], countLines(args[0]));
	}
	
	/**
	 * @throws ParseException
	 * Add initial users to new ArrayList as objects
	 */
	public static void addUserfromTxtFile() throws ParseException{
		for(int i=0;i<user_arraylist.size();i++){
			String obj_name = UserCollection.user_arraylist.get(i).get(0);
			User user = new User(UserCollection.user_arraylist.get(i).get(0), UserCollection.user_arraylist.get(i).get(1), UserCollection.user_arraylist.get(i).get(2), UserCollection.user_arraylist.get(i).get(3), UserCollection.user_arraylist.get(i).get(4),UserCollection.user_arraylist.get(i).get(5));
			user_objects.add(user);	
		}
	}
	
	/**
	 * @return
	 * get User Objects
	 */
	public static ArrayList<User> getUser_objects() {
		return user_objects;
	}

	/**
	 * @return
	 * get User ArrayList
	 */
	public static ArrayList<ArrayList<String>> getUser_arraylist() {
		return user_arraylist;
	}
	
	/**
	 * @param name
	 * @param username
	 * @param password
	 * @param DateOfbirth
	 * @param graduatedSchool
	 * @throws ParseException
	 * Add new User
	 */
	public static void addNewUser(String name,String username,String password,String DateOfbirth,String graduatedSchool,String relationshipStatus) throws ParseException{
		User user = new User(name,username,password,DateOfbirth,graduatedSchool,relationshipStatus);
		UserCollection.getUser_objects().add(user);
		System.out.println(name + " has been successfully added.");
	}
	
	public static void removeUser(String userName){
		
	}
	
	/**
	 * @param username
	 * Username
	 * @param password
	 * Password
	 * User Sign In
	 */
	public static void UserSignIn(String username,String password){
		int checkUser = 0;
		int checkUserPassMatch = 0;
		for(int i=0;i<UserCollection.user_objects.size();i++){
			if(UserCollection.user_objects.get(i).getUserName().equals(username)){
				checkUser++;
			}
			if(UserCollection.user_objects.get(i).getUserName().equals(username) && UserCollection.user_objects.get(i).getPassword().equals(password)){
				checkUserPassMatch++;
			}
		}
		if(checkUser != 0){
			if(checkUserPassMatch !=0){
				for(int i=0;i<UserCollection.user_objects.size();i++){
					if(UserCollection.user_objects.get(i).getUserName().equals(username) && UserCollection.user_objects.get(i).getPassword().equals(password)){
						UserCollection.user_objects.get(i).setSignInTrue();
						System.out.println("You have successfully signed in.");
					}
				}
			}else{
				System.out.println("Invalid username or password! Please try again.");
			}
		}else{
			System.out.println("No such user!");
		}
	}
	
}
