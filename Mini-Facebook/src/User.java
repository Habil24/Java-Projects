import java.text.*;
import java.util.*;

public class User {
	SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
	Date dateOfBirth = new Date();
	Date lastLoginDate = new Date();
	
	private String relationship_status = "";
	public String getRelationship_status() {
		return relationship_status;
	}

	public void setRelationship_status(Object object) {
		this.relationship_status = (String) object;
	}

	
	public ArrayList<User> getFriendList() {
		return friendList;
	}

	public void setFriendList(ArrayList<User> friendList) {
		this.friendList = friendList;
	}


	private String name;
	private String userName;
	private String password;
	private String graduatedSchool;
	private boolean SignIn = false;
	private ArrayList<User> friendList = new ArrayList<User>();
	private ArrayList<User> blockedUsers = new ArrayList<User>();
	private ArrayList<Post> Collection_post = new ArrayList<Post>();
	
	
	public ArrayList<User> getBlockedUsers() {
		return blockedUsers;
	}

	public void setBlockedUsers(ArrayList<User> blockedUsers) {
		this.blockedUsers = blockedUsers;
	}

	/**
	 * @param name
	 * Name
	 * @param username
	 * Username
	 * @param password
	 * Password
	 * @param dateOfBirth
	 * Date of Birth
	 * @param graduatedSchool
	 * School Info
	 * @throws ParseException
	 * throws exception
	 * User Constructor
	 */
	public User(String name,String username,String password,String dateOfBirth,String graduatedSchool,String relaationshipStatus) throws ParseException{
		this.name = name;
		this.userName = username;
		this.password = password;
		this.dateOfBirth = dateFormat.parse(dateOfBirth);
		this.graduatedSchool = graduatedSchool;
		this.relationship_status = relaationshipStatus;
		
	}
	
	public ArrayList<Post> getCollection_post() {
		return Collection_post;
	}

	public void setCollection_post(ArrayList<Post> collection_post) {
		Collection_post = collection_post;
	}
	
	/**
	 * @param textContent
	 * text
	 * @param longtitude
	 * Longtitude
	 * @param latitude
	 * Latitude
	 * @param taggedFriends
	 * Tagged Friends
	 * Add Text Post
	 */
	public void addTextPost(String textContent,double longtitude,double latitude,String taggedFriends){
		String[] tagged_Friends = taggedFriends.split(":");
		TextPost tp = new TextPost(textContent, latitude, longtitude);
		if(this.SignIn==true){
			for(int j=0;j<tagged_Friends.length;j++){
				for(int i = 0;i<this.friendList.size();i++){
					if(tagged_Friends[j].equals(this.friendList.get(i).getUserName())){
						tp.getTaggedFriends().add(this.friendList.get(i));
					}
				}
			}
			ArrayList<String> checkIfFriend = new ArrayList<String>();
			for(int i = 0;i<this.friendList.size();i++){
				checkIfFriend.add(this.friendList.get(i).getUserName());
			}
			for(int i = 0;i<tagged_Friends.length;i++){
				if(checkIfFriend.contains(tagged_Friends[i]) == false){
					System.out.println("Username " + tagged_Friends[i] + " is not your friend, and will not be tagged!");
				}
			}
			this.Collection_post.add(tp);
			System.out.println("The post has been successfully added.");
		}else{
			System.out.println("Error: Please sign in and try again.");
		}
	}
	
	/**
	 * @param textContent
	 * text
	 * @param longtitude
	 * Longtitude
	 * @param latitude
	 * Latitude
	 * @param taggedFriends
	 * tagged friends
	 * @param fileNameOrPath
	 * File Name
	 * @param resolution
	 * Image resolution
	 * Add Image Post
	 */
	public void addImagePost(String textContent,double longtitude,double latitude,String taggedFriends,String fileNameOrPath,String resolution){
		String[] tagged_Friends = taggedFriends.split(":");
		ImagePost ip = new ImagePost(textContent, latitude, longtitude,fileNameOrPath,resolution);
		if(this.SignIn==true){
			for(int j=0;j<tagged_Friends.length;j++){
				for(int i = 0;i<this.friendList.size();i++){
					if(tagged_Friends[j].equals(this.friendList.get(i).getUserName())){
						ip.getTaggedFriends().add(this.friendList.get(i));
					}
				}
			}
			ArrayList<String> checkIfFriend = new ArrayList<String>();
			for(int i = 0;i<this.friendList.size();i++){
				checkIfFriend.add(this.friendList.get(i).getUserName());
			}
			for(int i = 0;i<tagged_Friends.length;i++){
				if(checkIfFriend.contains(tagged_Friends[i]) == false){
					System.out.println("Username " + tagged_Friends[i] + " is not your friend, and will not be tagged!");
				}
			}
			this.Collection_post.add(ip);
			System.out.println("The post has been successfully added.");
		}else{
			System.out.println("Error: Please sign in and try again.");
		}
	}
	
	/**
	 * @param textContent
	 * text
	 * @param longtitude
	 * Longtitude
	 * @param latitude
	 * Latitude
	 * @param taggedFriends
	 * tagged friends
	 * @param fileNameOrPath
	 * filename
	 * @param videoDuration
	 * Video Durations
	 * Add Video Post
	 */
	public void addVideoPost(String textContent,double longtitude,double latitude,String taggedFriends,String fileNameOrPath,int videoDuration){
		String[] tagged_Friends = taggedFriends.split(":");
		VideoPost ip = new VideoPost(textContent, latitude, longtitude,fileNameOrPath,videoDuration);
		if(this.SignIn==true){
				for(int j=0;j<tagged_Friends.length;j++){
					for(int i = 0;i<this.friendList.size();i++){
						if(tagged_Friends[j].equals(this.friendList.get(i).getUserName())){
							ip.getTaggedFriends().add(this.friendList.get(i));
						}
					}
				}
				ArrayList<String> checkIfFriend = new ArrayList<String>();
				for(int i = 0;i<this.friendList.size();i++){
					checkIfFriend.add(this.friendList.get(i).getUserName());
				}
				for(int i = 0;i<tagged_Friends.length;i++){
					if(checkIfFriend.contains(tagged_Friends[i]) == false){
						System.out.println("Username " + tagged_Friends[i] + " is not your friend, and will not be tagged!");
					}
				}
				this.Collection_post.add(ip);		  
				System.out.println("The post has been successfully added.");
		}else{
			System.out.println("Error: Please sign in and try again.");
		}
	}
	
	/**
	 * Remove Last Post
	 */
	public void removeLastPost(){
		this.Collection_post.remove(this.Collection_post.size()-1);
		System.out.println("Your last post has been successfully removed.");
	}
	
	/**
	 * @param name
	 * name
	 * @param dateOfBirth
	 * Date of Birth
	 * @param schoolGraduated
	 * SchoolInfo
	 * @throws ParseException
	 * Parse Exception
	 * Update Profile Info
	 */
	public void UpdateProfileInfo(String name,String dateOfBirth,String schoolGraduated) throws ParseException{
		if(this.SignIn==true){
			this.setName(name);
			this.setDateOfBirth(dateFormat.parse(dateOfBirth));
			this.setGraduatedSchool(schoolGraduated);
			System.out.println("Your user profile has been successfully updated.");
		}else{
			System.out.println("Error: Please sign in and try again.");
		}
	}

	/**
	 * @param oldPass
	 * @param newpass
	 * Change Password
	 */
	public void ChangePassword(String oldPass,String newpass){
		if(this.SignIn==true){
			if(this.password.equals(oldPass)){
				this.password = newpass;
			}else{
				System.out.println("Password mismatch! Please, try again.");
			}
			
		}else{
			System.out.println("Error: Please sign in and try again.");
		}
	}
	
	/**
	 * @param userName
	 * Add new Friend
	 */
	public void addFriend(String userName){
		int checkIfExists = 0;
		int checkIfInFriendList = 0;
		for(int i=0;i<UserCollection.getUser_objects().size();i++){
			if(UserCollection.getUser_objects().get(i).getUserName().equals(userName)){
				checkIfExists++;
			}
		}
		for(int x = 0;x<this.friendList.size();x++){
				if(this.friendList.get(x).getUserName().equals(userName)){
					checkIfInFriendList++;
				}
		}
		if(this.SignIn == true){
			if(checkIfExists != 0){
				if(checkIfInFriendList == 0){
					for(int i=0;i<UserCollection.getUser_objects().size();i++){
						if(UserCollection.getUser_objects().get(i).getUserName().equals(userName)){
							this.friendList.add(UserCollection.getUser_objects().get(i));
							System.out.println(userName +" has been successfully added to your friend list.");
						}
					}
				}else{
					System.out.println("This user is already in your friend list!");
				}
			}else{
				System.out.println("No such user!");
			}
		}else{
			System.out.println("Error: Please sign in and try again.");
		}
	}

	/**
	 * @param userName
	 * Remove friend
	 */
	public void removeFriend(String userName){
		int friendIndex = 0;
		int checkIfInFriendList = 0;
		for(int x = 0;x<this.friendList.size();x++){
				if(this.friendList.get(x).getUserName().equals(userName)){
					checkIfInFriendList++;
					friendIndex = x;
				}
		}
		if(this.SignIn == true){
			if(checkIfInFriendList!=0){
				this.friendList.remove(friendIndex);
				System.out.println(userName + " has been successfully removed from your friend list.");
			}else{
				System.out.println("No such friend!");
			}
		}else{
			System.out.println("Error: Please sign in and try again.");
		}
		
		
	}

	/**
	 * @param username
	 * Block User
	 */
	public void blockUser(String username){
		int checkIfExists = 0;
		for(int i=0;i<UserCollection.getUser_objects().size();i++){
			if(UserCollection.getUser_objects().get(i).getUserName().equals(username)){
				checkIfExists++;
			}
		}
		if(checkIfExists != 0){
			if(this.SignIn==true){
				for(int i=0;i<UserCollection.getUser_objects().size();i++){
					if(UserCollection.getUser_objects().get(i).getUserName().equals(username)){
						this.blockedUsers.add(UserCollection.getUser_objects().get(i));
						System.out.println(username + " has been succesfully blocked.");
					}
				}
			}else{
				System.out.println("Error: Please sign in and try again.");
			}
		}else{
			System.out.println("No such user!");
		}
	}
	
	/**
	 * @param username
	 * Unblock User
	 */
	public void unblockUser(String username){
		int friendIndex = 0;
		int checkIfInBlockedFriendList = 0;
		for(int x = 0;x<this.blockedUsers.size();x++){
				if(this.blockedUsers.get(x).getUserName().equals(username)){
					checkIfInBlockedFriendList++;
					friendIndex = x;
				}
		}
		if(this.SignIn == true){
			if(checkIfInBlockedFriendList!=0){
				this.blockedUsers.remove(friendIndex);
				System.out.println(username + " has been successfully unblocked.");
			}else{
				System.out.println("No such user in your blocked users list!");
			}
		}else{
			System.out.println("Error: Please sign in and try again.");
		}
		
	}
	
	/**
	 * List existing friends
	 */
	public void listFriends(){
		if(friendList.size()!=0){
			if(this.SignIn==true){
				for(int i=0;i<this.friendList.size();i++){
					System.out.println(this.friendList.get(i).toString());
					System.out.println("---------------------------");
				}
			}else{
				System.out.println("Error: Please sign in and try again.");
			}
		}else{
			System.out.println("You haven't added any friends yet!");
		}
	}
	
	/**
	 * List All Users
	 */
	public void listUsers(){
		if(this.SignIn == true){
			for(int i =0;i<UserCollection.getUser_objects().size();i++){
				System.out.println(UserCollection.getUser_objects().get(i).toString());
				System.out.println("---------------------------");
			}
		}else{
			System.out.println("Error: Please sign in and try again.");
		}
	}
	
	/**
	 * Show Blocked Friends
	 */
	public void showBlockedFriends(){
		if(this.SignIn == true){
			if(this.blockedUsers.size() != 0){
				for(int i=0;i<this.blockedUsers.size();i++){
					for(int j =0;j<this.friendList.size();j++){
						if(this.blockedUsers.get(i).equals(this.friendList.get(j))){
							System.out.println(this.blockedUsers.get(i).toString());
							System.out.println("---------------------------");
						}
					}
				}
			}else{
				System.out.println("You have not blocked any friends yet!");
			}
		}else{
			System.out.println("Error: Please sign in and try again.");
		}
	}
	
	/**
	 * Show Blocked Users
	 */
	public void showBlockedUsers(){
		if(this.SignIn == true){
			if(this.blockedUsers.size() != 0){
				for(int i=0;i<this.blockedUsers.size();i++){
					System.out.println(this.blockedUsers.get(i).toString());
					System.out.println("---------------------------");
				}
			}else{
				System.out.println("You have not blocked any users yet!");
			}
		}else{
			System.out.println("Error: Please sign in and try again.");
		}
	}
	
	/**
	 * Sign Out
	 */
	public void SignOut(){
		SignIn = false;			     
		System.out.println("You have successfully signed out.");
	}
	
	/**
	 * @return
	 * Check the user's sign status
	 */
	public boolean isSignIn() {
		return SignIn;
	}

	/**
	 * Sign In
	 */
	public void setSignInTrue() {
		SignIn = true;
	}
	
	public void setSignInFalse(){
		SignIn = false;
	}
	
	/**
	 * @return
	 * return Date of Birth
	 */
	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	/**
	 * @param dateOfBirth
	 * Set date of birth
	 */
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	/**
	 * @return
	 * get name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 * Set name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return
	 * Get username
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName
	 * Set Username
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return password
	 * get password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 * Set Password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return
	 * get graduated school info
	 */
	public String getGraduatedSchool() {
		return graduatedSchool;
	}

	/**
	 * @param graduatedSchool
	 * Set graduated School
	 */
	public void setGraduatedSchool(String graduatedSchool) {
		this.graduatedSchool = graduatedSchool;
	}
	
	/**
	 * @param date
	 * date
	 * @return
	 * Change the order of Month and day
	 */
	public String final_date(String date){
	      String[] date_array  = date.split("/");
	      String new_date = "";
	      new_date+=date_array[1];
	      new_date+="/";
	      new_date+=date_array[0];
	      new_date+="/";
	      new_date+=date_array[2];
	      return new_date;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString(){
		return "Name: " + this.name + "\nUsername: " + this.userName + "\nDate of Birth: " + this.final_date(dateFormat.format(this.dateOfBirth)) + "\nSchool: " + this.graduatedSchool + "\nRelationship Status: " + this.relationship_status; 
	}

	
	
}
