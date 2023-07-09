package music.store.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import music.store.entity.Artist;

public interface ArtistDao extends JpaRepository<Artist, Long> {

}
