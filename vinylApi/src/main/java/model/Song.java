package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
//import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name ="song")
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
}
