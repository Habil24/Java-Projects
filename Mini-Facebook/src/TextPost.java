import java.text.*;
import java.util.*;

public class TextPost extends Post implements PostInterface{
	
	DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
	Date date = new Date();
	
	/**
	 * @param textContent
	 * text for post
	 * @param latitude
	 * Latitude
	 * @param longtitude
	 * Longtitude
	 * TextPost Constructor
	 */
	public TextPost(String textContent,double latitude,double longtitude){
		super(textContent,latitude,longtitude);
		super.setLoc(new Location(latitude,longtitude));
	}
	
	/* (non-Javadoc)
	 * @see Post#showTaggedUsers()
	 */
	@Override
	public String showTaggedUsers() {
		String friends = "Friends tagged in this post: ";
		if(super.getTaggedFriends().size() !=0){
			for(int i = 0;i<super.getTaggedFriends().size();i++){
				friends += super.getTaggedFriends().get(i).getName() + ", ";
			}
		}
		if(friends.length()==29)
			return "";
		else
			friends = friends.replaceAll(", $", "");
			return friends.trim();
		
	}

	/* (non-Javadoc)
	 * @see Post#showPostLocation()
	 */
	@Override
	public String showPostLocation() {
		return super.getLoc().toString();
		
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString(){
		return super.getTextContent() + "\nDate: " + dateFormat.format(date) + "\n" +  showPostLocation() + "\n"  + showTaggedUsers();
	}

}
