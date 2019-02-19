
public class VideoPost extends TextPost implements PostInterface{
	private String VideoFileName;
	private int videoDuration;
	private static final int maxVideoLength = 10;
	
	/**
	 * @return
	 * Get Max video length
	 */
	public static int getMaxvideolength() {
		return maxVideoLength;
	}

	/**
	 * @param textContent
	 * text
	 * @param latitude
	 * Latitude
	 * @param longtitude
	 * Longtitude
	 * @param VideoFileName
	 * Video Name
	 * @param videoDuration
	 * Video duration in minutes
	 * VideoPost Constructor
	 */
	public VideoPost(String textContent,double latitude,double longtitude,String VideoFileName,int videoDuration){
		super(textContent,latitude,longtitude);
		super.setLoc(new Location(latitude,longtitude));
		this.videoDuration = videoDuration;
		this.VideoFileName = VideoFileName;
	}

	public String getVideoFileName() {
		return VideoFileName;
	}

	/**
	 * @param videoFileName
	 * Set videoFileName
	 */
	public void setVideoFileName(String videoFileName) {
		VideoFileName = videoFileName;
	}

	/**
	 * @return
	 * Get Duration
	 */
	public int getVideoDuration() {
		return videoDuration;
	}

	/**
	 * @param videoDuration
	 * Set duration
	 */
	public void setVideoDuration(int videoDuration) {
		this.videoDuration = videoDuration;
	}
	
	
	/* (non-Javadoc)
	 * @see TextPost#toString()
	 */
	public String toString(){
		return super.getTextContent() + "\nDate: " + dateFormat.format(date) + "\n" +  super.showPostLocation() + "\nVideo: " + this.VideoFileName + "\nVideo duration: " + this.videoDuration + " minutes\n" + super.showTaggedUsers();
	}
	
	
}

