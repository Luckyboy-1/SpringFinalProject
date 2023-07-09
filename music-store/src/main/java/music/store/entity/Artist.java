package music.store.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.Set;

import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
public class Artist {
	 	@Id
	    @GeneratedValue
	    private Long artistId;

	    private String artistName;
	    private String bio;
	    private String genre;

	    @ToString.Exclude
	    @EqualsAndHashCode.Exclude
	    @OneToMany(mappedBy = "artist", cascade = CascadeType.ALL)
	    private Set<Album> albums;
}
