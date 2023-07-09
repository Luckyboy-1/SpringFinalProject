package music.store.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import music.store.controller.model.MusicStoreData;
import music.store.controller.model.MusicStoreData.MusicStoreTrack;
import music.store.dao.AlbumDao;
import music.store.dao.ArtistDao;
import music.store.dao.TrackDao;
import music.store.entity.Album;
import music.store.entity.Artist;
import music.store.entity.Track;

@Service
public class MusicStoreService {
    private final ArtistDao artistDao;
    private final AlbumDao albumDao;
    private final TrackDao trackDao;

    @Autowired
    public MusicStoreService(ArtistDao artistDao, AlbumDao albumDao, TrackDao trackDao) {
        this.artistDao = artistDao;
        this.albumDao = albumDao;
        this.trackDao = trackDao;
    }

    public MusicStoreData saveMusicStore(MusicStoreData musicStoreData) {
        Artist artist = new Artist();
        artist.setArtistName(musicStoreData.getArtistName());
        artist.setBio(musicStoreData.getBio());
        artist.setGenre(musicStoreData.getGenre());

        artistDao.save(artist);

        return musicStoreData;
    }

    public MusicStoreData.MusicStoreAlbum saveAlbum(Long musicStoreId, MusicStoreData.MusicStoreAlbum album) {
        Artist artist = artistDao.findById(musicStoreId)
                .orElseThrow(() -> new NoSuchElementException("Artist with ID " + musicStoreId + " not found"));

        Album newAlbum = new Album();
        newAlbum.setAlbumTitle(album.getAlbumTitle());
        newAlbum.setReleaseDate(album.getReleaseDate());
        newAlbum.setArtist(artist);

        albumDao.save(newAlbum);

        return album;
    }

    

    public List<MusicStoreData> retrieveAllArtists() {
        List<Artist> artists = artistDao.findAll();
        List<MusicStoreData> musicStoreDataList = new ArrayList<>();

        for (Artist artist : artists) {
            MusicStoreData musicStoreData = new MusicStoreData(artist);
            musicStoreDataList.add(musicStoreData);
        }

        return musicStoreDataList;
    }

    public MusicStoreData retrieveArtistById(Long musicStoreId) {
        Optional<Artist> artistOptional = artistDao.findById(musicStoreId);
        if (artistOptional.isPresent()) {
            Artist artist = artistOptional.get();
            return new MusicStoreData(artist);
        } else {
            throw new NoSuchElementException("Artist not found with ID: " + musicStoreId);
        }
    }

    @Transactional
    public void deleteArtistById(Long artistId) {
        Optional<Artist> artistOptional = artistDao.findById(artistId);

        if (artistOptional.isEmpty()) {
            throw new NoSuchElementException("Artist not found with ID: " + artistId);
        }

        Artist artist = artistOptional.get();
        artistDao.delete(artist);
    }

    public MusicStoreData.MusicStoreTrack saveTrack(Long musicStoreId, MusicStoreData.MusicStoreTrack trackData) {
        Optional<Artist> artistOptional = artistDao.findById(musicStoreId);
        if (artistOptional.isPresent()) {
            Artist artist = artistOptional.get();

            // Create a new Track entity
            Track track = new Track();
            track.setTrackTitle(trackData.getTrackTitle());
            track.setDuration(trackData.getDuration());

            // Save the track in the database
            trackDao.save(track);

            // Create a new MusicStoreTrack object from the saved track data
            MusicStoreData.MusicStoreTrack savedTrack = new MusicStoreData.MusicStoreTrack();
            savedTrack.setTrackId(track.getTrackId());
            savedTrack.setTrackTitle(track.getTrackTitle());
            savedTrack.setDuration(track.getDuration());

            // Return the saved track
            return savedTrack;
        } else {
            throw new NoSuchElementException("Artist not found with ID: " + musicStoreId);
        }
    }





	


}
