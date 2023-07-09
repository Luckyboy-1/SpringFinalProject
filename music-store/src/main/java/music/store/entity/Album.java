package music.store.entity;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
public class Album {
		@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long albumId;

	    private String albumTitle;

	    private String releaseDate;

	    @ManyToOne
	    @JoinColumn(name = "artist_id")
	    private Artist artist;

	    @ManyToMany
	    @JoinTable(
	        name = "Album_Track",
	        joinColumns = @JoinColumn(name = "album_id"),
	        inverseJoinColumns = @JoinColumn(name = "track_id")
	    )
	    private Set<Track> tracks = new HashSet<>();
}
