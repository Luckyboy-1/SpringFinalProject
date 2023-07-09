package music.store.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import music.store.entity.Track;

public interface TrackDao extends JpaRepository<Track, Long> {

}
