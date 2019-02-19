import java.util.*;
import java.text.*;

public abstract class Post  implements PostInterface  {
	
	UUID postID = UUID.randomUUID();
	private String textContent;
	DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	Date date = new Date();
	private ArrayList<User> taggedFriends = new ArrayList<User>();
	private Location loc;
	
	/**
	 * @param textContent
	 * text Content
	 * @param latitude
	 * Latitude
	 * @param longtitude
	 * Longtitude
	 * Post Constructor
	 */
	public Post(String textContent,double latitude,double longtitude){
		this.textContent = textContent;
		this.loc = new Location(latitude,longtitude);
	}
	
	/* (non-Javadoc)
	 * @see PostInterface#getLoc()
	 * get Location
	 */
	
	
	/* (non-Javadoc)
	 * @see PostInterface#getLoc()
	 */
	public Location getLoc() {
		return loc;
	}

	/* (non-Javadoc)
	 * @see PostInterface#setLoc(Location)
	 * set Location
	 */
	public void setLoc(Location loc) {
		this.loc = loc;
	}

	/**
	 * @return
	 * return tagged Users
	 */
	abstract public String showTaggedUsers();
	
	/**
	 * @return
	 * show Post Location
	 */
	abstract public String showPostLocation();

	/* (non-Javadoc)
	 * @see PostInterface#getTaggedFriends()
	 */
	public ArrayList<User> getTaggedFriends() {
		return taggedFriends;
	}

	/* (non-Javadoc)
	 * @see PostInterface#getPostID()
	 */
	public UUID getPostID() {
		return postID;
	}
	
	/**
	 * @return
	 * get Post Date
	 */
	public String getPostDate(){
		return dateFormat.format(date);
	}
	
	/* (non-Javadoc)
	 * @see PostInterface#setPostID(java.util.UUID)
	 */
	public void setPostID(UUID postID) {
		this.postID = postID;
	}

	/* (non-Javadoc)
	 * @see PostInterface#getTextContent()
	 */
	public String getTextContent() {
		return textContent;
	}

	/* (non-Javadoc)
	 * @see PostInterface#setTextContent(java.lang.String)
	 */
	public void setTextContent(String text) {
		this.textContent = text;
	}
		
}
