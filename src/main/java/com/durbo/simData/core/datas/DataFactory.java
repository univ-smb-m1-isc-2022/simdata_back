package com.durbo.simData.core.datas;

import com.durbo.simData.Track.Track;
import com.durbo.simData.core.TYPE;
import com.durbo.simData.core.object.ObjectDataFactory;
import com.durbo.simData.layout.Layout;
import org.springframework.stereotype.Service;

@Service
public class DataFactory {

    public SimData create(TYPE type, Object value) {
        return switch (type) {
            case DOUBLE -> new DoubleData((double) value);
            case STRING -> new StringData((String) value);
            case INTEGER -> new IntegerData((Integer) value);
            case TRACK -> new ObjectDataFactory<Track>().create((Track) value);
            case LAYOUT -> new ObjectDataFactory<Layout>().create((Layout) value);
            default -> null;
        };
    }
}
