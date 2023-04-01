package com.durbo.simData.core.object;

import com.durbo.simData.Track.Track;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

public class ObjectServiceTest {

    @InjectMocks
    private ObjectDataService<Track> trackService;

    @Mock
    private ObjectDataRepository objectDataRepository;


    ObjectDataFactory<Track> trackFactory = new ObjectDataFactory<>();

    Track track = new Track("Test", "Test Country", 0.0, 0.0, new ArrayList<>());
    ObjectData trackObj = trackFactory.create(track);


    @BeforeEach
    public void setup(){
        MockitoAnnotations.openMocks(this);

        trackObj = trackFactory.create(track);
    }

    @Test
    public void testgetAll() {

        when(objectDataRepository.findByType("Test")).thenReturn(new ArrayList<>(List.of(trackObj)));

        ArrayList<Object> tracks = trackService.getAll("Test");
        assert(tracks.size() == 1);
        Object track = tracks.get(0);
        Track track1 = (Track) track;
        //TODO: add name
        assert(track1.getCountry().equals("Test Country"));
        assert(track1.getLatitude() == 0.0);
        assert(track1.getLongitude() == 0.0);
    }

    @Test
    public void testGetBy(){

        when(objectDataRepository.findBy("Track","name", "Test")).thenReturn(new ArrayList<>(List.of(trackObj)));

        Track track = (Track) trackService.getBy("Track", "name", "Test").get(0);

        assert(track.getName().equals("Test"));
        assert(track.getCountry().equals("Test Country"));
        assert(track.getLatitude() == 0.0);
        assert(track.getLongitude() == 0.0);
    }



}
