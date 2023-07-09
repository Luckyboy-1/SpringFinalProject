package music.store.controller.model;

import java.util.HashSet;
import java.util.Set;


import music.store.entity.Track;
import lombok.Data;
import lombok.NoArgsConstructor;
import music.store.entity.Album;
import music.store.entity.Artist;

@Data
@NoArgsConstructor
public class MusicStoreData {

    private Long artistId;
    private String artistName;
    private String bio;
    private String genre;
    private Set<MusicStoreAlbum> albums = new HashSet<>();

    public MusicStoreData(Artist artist) {
        this.artistId = artist.getArtistId();
        this.artistName = artist.getArtistName();
        this.bio = artist.getBio();
        this.genre = artist.getGenre();

        // Set albums
        for (Album album : artist.getAlbums()) {
            MusicStoreAlbum musicStoreAlbum = new MusicStoreAlbum(album);
            albums.add(musicStoreAlbum);
        }
    }

    @Data
    @NoArgsConstructor
    public static class MusicStoreAlbum {
        private Long albumId;
        private String albumTitle;
        private String releaseDate;
        private Set<MusicStoreTrack> tracks = new HashSet<>();

        public MusicStoreAlbum(Album album) {
            this.albumId = album.getAlbumId();
            this.albumTitle = album.getAlbumTitle();
            this.releaseDate = album.getReleaseDate();

            // Set tracks
            for (Track track : album.getTracks()) {
                MusicStoreTrack musicStoreTrack = new MusicStoreTrack(track);
                tracks.add(musicStoreTrack);
            }
        }
    }

    @Data
    @NoArgsConstructor
    public static class MusicStoreTrack {
        private Long trackId;
        private String trackTitle;
        private int duration;

        public MusicStoreTrack(Track track) {
            this.trackId = track.getTrackId();
            this.trackTitle = track.getTrackTitle();
            this.duration = track.getDuration();
        }
    }

}
