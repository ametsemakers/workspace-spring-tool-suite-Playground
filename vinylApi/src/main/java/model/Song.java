package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
//import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Song {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "title_song", length = 100, nullable = false)
	private String titleSong;
	
	@Column(name = "artist", length = 100, nullable = false)
	private String artist;
	
	@Column(name = "alternate_info", length = 50, nullable = true)
	private String alternateInfo;
	
	@Column(name = "feat", length = 50, nullable = true)
	private String featuring;
	
	@Column(name = "title_album", length = 100, nullable = false)
	private String titleAlbum;
	
	@Column(name = "side", length = 2, nullable = false)
	private String side;
	
	@Column(name = "position", nullable = false)
	private int position;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnore
	// @JsonIgnoreProperties({"artist", "titleAlbum", "label", "country", "catNb", "yearOriginal", "yearEdition", "image", "songs"})
	private Vinyl vinyl;
	
	public Song() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitleSong() {
		return titleSong;
	}

	public void setTitleSong(String titleSong) {
		this.titleSong = titleSong;
	}

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public String getAlternateInfo() {
		return alternateInfo;
	}

	public void setAlternateInfo(String alternateInfo) {
		this.alternateInfo = alternateInfo;
	}

	public String getFeaturing() {
		return featuring;
	}

	public void setFeaturing(String featuring) {
		this.featuring = featuring;
	}

	public String getTitleAlbum() {
		return titleAlbum;
	}

	public void setTitleAlbum(String titleAlbum) {
		this.titleAlbum = titleAlbum;
	}

	public String getSide() {
		return side;
	}

	public void setSide(String side) {
		this.side = side;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public Vinyl getVinyl() {
		return vinyl;
	}

	public void setVinyl(Vinyl vinyl) {
		this.vinyl = vinyl;
	}

}
