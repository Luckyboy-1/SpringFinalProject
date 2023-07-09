package music.store.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import music.store.controller.model.MusicStoreData;
import music.store.service.MusicStoreService;

@RestController
@RequestMapping("/music_store")
@Slf4j
public class MusicStoreController {
    private final MusicStoreService musicStoreService;

    @Autowired
    public MusicStoreController(MusicStoreService musicStoreService) {
        this.musicStoreService = musicStoreService;
    }

    @PostMapping
    public ResponseEntity<MusicStoreData> createMusicStore(@RequestBody MusicStoreData musicStoreData) {
        log.info("Received create music store request: {}", musicStoreData);

        MusicStoreData createdMusicStore = musicStoreService.saveMusicStore(musicStoreData);
        return new ResponseEntity<>(createdMusicStore, HttpStatus.CREATED);
    }

    @PutMapping("/{musicStoreId}")
    public ResponseEntity<MusicStoreData> updateMusicStoreData(@PathVariable Long musicStoreId, @RequestBody MusicStoreData musicStoreData) {
        log.info("Received request to update MusicStore with ID {}: {}", musicStoreId, musicStoreData);
        musicStoreData.setArtistId(musicStoreId);
        MusicStoreData updatedMusicStoreData = musicStoreService.saveMusicStore(musicStoreData);
        return ResponseEntity.ok(updatedMusicStoreData);
    }



    @PostMapping("/{musicStoreId}/album")
    @ResponseStatus(HttpStatus.CREATED)
    public MusicStoreData.MusicStoreAlbum addAlbumToMusicStore(
            @PathVariable Long musicStoreId,
            @RequestBody MusicStoreData.MusicStoreAlbum album
    ) {
        // Log the request
        log.info("Received request to add album to MusicStore with ID {}: {}", musicStoreId, album);

        // Call the saveAlbum method in the music store service
        MusicStoreData.MusicStoreAlbum savedAlbum = musicStoreService.saveAlbum(musicStoreId, album);

        // Return the results
        return savedAlbum;
    }

    @PostMapping("/{musicStoreId}/track")
    public ResponseEntity<MusicStoreData.MusicStoreTrack> addTrackToMusicStore(
            @PathVariable Long musicStoreId,
            @RequestBody MusicStoreData.MusicStoreTrack track) {

        // Log the request
        log.info("Received request to add track to MusicStore with ID {}: {}", musicStoreId, track);

        // Call the saveTrack method in the music store service
        MusicStoreData.MusicStoreTrack savedTrack = musicStoreService.saveTrack(musicStoreId, track);

        // Return the results
        return ResponseEntity.ok(savedTrack);
    }



    
    @GetMapping
    public List<MusicStoreData> retrieveAllArtists() {
        return musicStoreService.retrieveAllArtists();
    }
    
    @GetMapping("/{musicStoreId}")
    public ResponseEntity<MusicStoreData> retrieveArtistById(@PathVariable Long musicStoreId) {
        MusicStoreData artist = musicStoreService.retrieveArtistById(musicStoreId);
        if (artist != null) {
            return ResponseEntity.ok(artist);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    
    @DeleteMapping("/{musicStoreId}")
    public Map<String, String> deleteArtistById(@PathVariable Long musicStoreId) {
        log.info("Received request to delete Artist with ID: {}", musicStoreId);
        musicStoreService.deleteArtistById(musicStoreId);

        Map<String, String> response = new HashMap<>();
        response.put("message", "Artist deleted successfully");
        return response;
    }

}
