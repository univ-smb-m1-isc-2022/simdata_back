package com.durbo.simData.Track;

import com.durbo.simData.core.Factory;
import com.durbo.simData.core.TYPE;
import com.durbo.simData.core.attributes.Attribute;
import com.durbo.simData.core.datas.DoubleData;
import com.durbo.simData.core.datas.ObjectData;
import com.durbo.simData.core.datas.StringData;

import java.util.List;

public class TrackFactory{

    public ObjectData create(Track track) {
        ObjectData object = new ObjectData(
                TYPE.TRACK,
                List.of(
                        new Attribute("name", TYPE.STRING),
                        new Attribute("country", TYPE.STRING),
                        new Attribute("latitude", TYPE.DOUBLE),
                        new Attribute("longitude", TYPE.DOUBLE)
                )
        );
        object.getAttribute("name").addData(new StringData(track.name));
        object.getAttribute("country").addData(new StringData(track.country));
        object.getAttribute("latitude").addData(new DoubleData(track.latitude));
        object.getAttribute("longitude").addData(new DoubleData(track.longitude));
        return object;
    }
}
