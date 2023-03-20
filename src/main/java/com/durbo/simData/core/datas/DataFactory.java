package com.durbo.simData.core.datas;

import com.durbo.simData.Track.Track;
import com.durbo.simData.core.TYPE;
import com.durbo.simData.core.datas.object.ObjectDataFactory;
import com.durbo.simData.layout.Layout;
import org.springframework.stereotype.Service;

@Service
public class DataFactory {

    public static SimData typeToSimData(TYPE type) {
        //TODO: Maybe find a better way to do this
        return switch (type) {
            case DOUBLE -> new DoubleData();
            case STRING -> new StringData();
            case INTEGER -> new IntegerData();
            case TRACK -> new ObjectDataFactory<Track>().create(new Track());
            case LAYOUT -> new ObjectDataFactory<Layout>().create(new Layout());
            default -> null;
        };
    }


    public SimData create(TYPE type) {
        return typeToSimData(type);
    }

    public SimData create(TYPE type, Object value) {
        SimData data = typeToSimData(type);
        data.setValue(value);
        return data;
    }
}
