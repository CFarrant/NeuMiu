package models;

public class Track {

	private String title;
	private String genere;
	private String artist;
	private long totalTime;
	private String songPath;
	private String artPath;

	public Track() {

	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getGenere() {
		return genere;
	}

	public void setGenere(String genere) {
		this.genere = genere;
	}

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public long getTotalTime() {
		return totalTime;
	}

	public void setTotalTime(long totalTime) {
		this.totalTime = totalTime;
	}

	public String getSongPath() {
		return songPath;
	}

	public void setSongPath(String songPath) {
		this.songPath = songPath;
	}

	public String getArtPath() {
		return artPath;
	}

	public void setArtPath(String artPath) {
		this.artPath = artPath;
	}

}
