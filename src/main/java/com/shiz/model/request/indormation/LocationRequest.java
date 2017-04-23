package com.shiz.model.request.indormation;

import com.shiz.model.Location;
import com.shiz.model.request.BaseRequest;

import java.util.List;

/**
 * Created by oldman on 06.04.17.
 */
public class LocationRequest extends BaseRequest {
    private List<Location> data;

    public LocationRequest(List<Location> data, String imei, int device) {
        super(imei, device);
        this.data = data;
    }

    public  List<Location> getData() {
        return data;
    }

    public void setData( List<Location> data) {
        this.data = data;
    }
}