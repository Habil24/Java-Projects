import java.util.ArrayList;
import java.util.UUID;

/**
 * 
 */

/**
 * @author HQ
 *
 */
public interface PostInterface {
	
	/**
	 * @return
	 * Get Text Content
	 */
	public String getTextContent();
	
	/**
	 * @param text
	 * Set Text Content
	 */
	public void setTextContent(String text);
	
	/**
	 * @return
	 * return UUID id
	 */
	public UUID getPostID();
	
	/**
	 * @return
	 * get Location
	 */
	public Location getLoc();
	
	
	/**
	 * @param loc
	 * set Location
	 */
	public void setLoc(Location loc);
	
	/**
	 * @return
	 * return tagged friends
	 */
	public ArrayList<User> getTaggedFriends();
	
	/**
	 * @param postID
	 * set post ID
	 */
	public void setPostID(UUID postID);
	
}
