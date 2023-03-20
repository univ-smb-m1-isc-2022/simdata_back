package com.durbo.simData.Track;

import com.durbo.simData.core.datas.object.ObjectDataFactory;
import com.durbo.simData.core.datas.object.ObjectDataRepository;
import com.durbo.simData.core.TYPE;
import com.durbo.simData.core.datas.object.ObjectData;
import com.durbo.simData.core.datas.object.ObjectDataService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

public class TrackServiceTest {

    @InjectMocks
    private ObjectDataService<Track> trackService;

    @Mock
    private ObjectDataRepository objectDataRepository;


    ObjectDataFactory<Track> trackFactory = new ObjectDataFactory<>();

    Track track = new Track();


    @BeforeEach
    public void setup(){
        MockitoAnnotations.openMocks(this);


        track.setName("Test Track");
        track.setCountry("Test Country");
        track.setLatitude(0.0);
        track.setLongitude(0.0);
    }

    @Test
    public void testgetAll() {

        ObjectData trackObj = trackFactory.create(track);

        when(objectDataRepository.findByType(TYPE.TRACK)).thenReturn(Optional.ofNullable(trackObj));

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

            ObjectData trackObj = trackFactory.create(track);

            when(objectDataRepository.save(trackObj)).thenReturn(trackObj);

            Track createdTrack = trackService.create(track);

            assert(createdTrack.getName().equals("Test Track"));
            assert(createdTrack.getCountry().equals("Test Country"));
            assert(createdTrack.getLatitude() == 0.0);
            assert(createdTrack.getLongitude() == 0.0);
    }



}
