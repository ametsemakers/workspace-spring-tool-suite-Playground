package model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
// import com.fasterxml.jackson.annotation.JsonView;


@Entity
public class Vinyl {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "artist", length = 100, nullable = false)
	private String artist;
	
	@Column(name = "title_album", length = 100, nullable = false)
	private String titleAlbum;
	
	@Column(name = "label", length = 50, nullable = false)
	private String label;
	
	@Column(name = "country", length = 50, nullable = false)
	private String country;
	
	@Column(name = "cat_nb", length = 50, nullable = false)
	private String catNb;
	
	@Column(name = "year_original", length = 4, nullable = false)
	private String yearOriginal;
	
	@Column(name = "year_edition", length = 4, nullable = false)
	private String yearEdition;
	
	@OneToOne(fetch = FetchType.LAZY,
				cascade = CascadeType.ALL,
				mappedBy = "vinyl",
				optional = true)
	@JsonIgnoreProperties({"vinyl"})
	private Image image;
	
	@OneToMany(fetch = FetchType.LAZY,
				cascade = CascadeType.REMOVE,
				mappedBy = "vinyl")
	@JsonIgnoreProperties({"vinyl", "artist", "titleAlbum"})
	private List<Song> songs;

	public Vinyl() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public String getTitleAlbum() {
		return titleAlbum;
	}

	public void setTitleAlbum(String titleAlbum) {
		this.titleAlbum = titleAlbum;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCatNb() {
		return catNb;
	}

	public void setCatNb(String catNb) {
		this.catNb = catNb;
	}

	public String getYearOriginal() {
		return yearOriginal;
	}

	public void setYearOriginal(String yearOriginal) {
		this.yearOriginal = yearOriginal;
	}

	public String getYearEdition() {
		return yearEdition;
	}

	public void setYearEdition(String yearEdition) {
		this.yearEdition = yearEdition;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}
	
	public List<Song> getSongs() {
		return songs;
	}

	public void setSongs(List<Song> songs) {
		this.songs = songs;
	}
}
