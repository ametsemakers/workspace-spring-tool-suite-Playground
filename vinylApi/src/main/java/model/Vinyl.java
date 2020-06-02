package model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
// import com.fasterxml.jackson.annotation.JsonView;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "vinyl")
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

	@ManyToMany(fetch = FetchType.LAZY,
				cascade = CascadeType.ALL,
				mappedBy = "vinyl")
	@JsonIgnoreProperties({"vinyl"})
	private List<Category> categories;
}
