package music.store.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import music.store.entity.Album;

public interface AlbumDao extends JpaRepository<Album, Long> {

}
