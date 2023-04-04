package com.durbo.simData.core.simdata;

import com.durbo.simData.Location.Coordinates;
import com.durbo.simData.Location.Location;
import com.durbo.simData.Track.Track;
import com.durbo.simData.core.subdata.DoubleData;
import com.durbo.simData.core.subdata.IntegerData;
import com.durbo.simData.core.subdata.StringData;
import com.durbo.simData.core.object.ObjectDataFactory;
import com.durbo.simData.Track.Layout;
import org.springframework.stereotype.Service;

@Service
public class SimDataFactory {

    public SimData create(String type, Object value) {
        return switch (type) {
            case "double" -> new DoubleData((double) value);
            case "String" -> new StringData((String) value);
            case "Integer" -> new IntegerData((Integer) value);
            case "Track" -> new ObjectDataFactory<Track>().create((Track) value);
            case "Layout" -> new ObjectDataFactory<Layout>().create((Layout) value);
            case "Location" -> new ObjectDataFactory<Location>().create((Location) value);
            case "Coordinates" -> new ObjectDataFactory<Coordinates>().create((Coordinates) value);
            default -> null;
        };
    }


}
