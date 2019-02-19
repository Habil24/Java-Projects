
public class ImagePost extends TextPost implements PostInterface{
	
	private String imageFileName;
	private String imgResolution;
	
	
	/**
	 * @param textContent
	 * text Content
	 * @param latitude
	 * Latitude
	 * @param longtitude
	 * Longtitude
	 * @param imageFileName
	 * Image File Name
	 * @param imgReslution
	 * Image's Resolution
	 * ImagePost Constructor
	 */
	public ImagePost(String textContent,double latitude,double longtitude,String imageFileName,String imgReslution){
		super(textContent,latitude,longtitude);
		super.setLoc(new Location(latitude,longtitude));
		this.imageFileName = imageFileName;
		this.imgResolution = imgReslution;
	}
	
	
	/* (non-Javadoc)
	 * @see TextPost#toString()
	 */
	public String toString(){
		return super.getTextContent() + "\nDate: " + dateFormat.format(date) + "\n" +  super.showPostLocation() + "\nImage: " + this.imageFileName + "\nImage resolution: " + this.imgResolution + "\n" + super.showTaggedUsers();
	}
}
