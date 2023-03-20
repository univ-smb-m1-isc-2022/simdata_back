package com.durbo.simData.Track;

import com.durbo.simData.core.ObjectDataRepository;
import com.durbo.simData.core.TYPE;
import com.durbo.simData.core.datas.ObjectData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

public class TrackServiceTest {

    @InjectMocks
    private TrackService trackService;

    @Mock
    private ObjectDataRepository objectDataRepository;


    ObjectFactory<Track> trackFactory = new ObjectFactory<Track>();

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

        List<Track> tracks = trackService.getAll();
        assert(tracks.size() == 1);
        assert(tracks.get(0).getName().equals("Test Track"));
        assert(tracks.get(0).getCountry().equals("Test Country"));
        assert(tracks.get(0).getLatitude() == 0.0);
        assert(tracks.get(0).getLongitude() == 0.0);
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
