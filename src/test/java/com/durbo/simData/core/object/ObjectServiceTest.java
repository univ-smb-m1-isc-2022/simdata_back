package com.durbo.simData.core.object;

import com.durbo.simData.Track.Track;
import com.durbo.simData.core.TYPE;
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

    Track track = new Track();
    ObjectData trackObj = trackFactory.create(track);


    @BeforeEach
    public void setup(){
        MockitoAnnotations.openMocks(this);


        track.setName("Test Track");
        track.setCountry("Test Country");
        track.setLatitude(0.0);
        track.setLongitude(0.0);

        trackObj = trackFactory.create(track);


    }

    @Test
    public void testgetAll() {

        when(objectDataRepository.findByType(TYPE.TRACK)).thenReturn(new ArrayList<>(List.of(trackObj)));

        ArrayList<Object> tracks = trackService.getAll(TYPE.TRACK);
        assert(tracks.size() == 1);
        Object track = tracks.get(0);
        Track track1 = (Track) track;
        //TODO: add name
        assert(track1.getCountry().equals("Test Country"));
        assert(track1.getLatitude() == 0.0);
        assert(track1.getLongitude() == 0.0);
    }

    @Test
    public void testCreate() {

            when(objectDataRepository.save(trackObj)).thenReturn(trackObj);

            Track createdTrack = trackService.create(track);

            assert(createdTrack.getName().equals("Test Track"));
            assert(createdTrack.getCountry().equals("Test Country"));
            assert(createdTrack.getLatitude() == 0.0);
            assert(createdTrack.getLongitude() == 0.0);
    }

    @Test
    public void testGetBy(){

        when(objectDataRepository.findBy(TYPE.TRACK,"name", "Test Track")).thenReturn(new ArrayList<>(List.of(trackObj)));

        Track track = (Track) trackService.getBy(TYPE.TRACK, "name", "Test Track").get(0);

        assert(track.getName().equals("Test Track"));
        assert(track.getCountry().equals("Test Country"));
        assert(track.getLatitude() == 0.0);
        assert(track.getLongitude() == 0.0);
    }



}
