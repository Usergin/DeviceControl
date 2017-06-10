package com.shiz.model.request.indormation;

import com.shiz.model.data.event.NetworkEvent;
import com.shiz.model.request.BaseRequest;

import java.util.List;

/**
 * Created by oldman on 06.04.17.
 */
public class NetworkStatusRequest extends BaseRequest {
    private List<NetworkEvent> data;

    public NetworkStatusRequest(List<NetworkEvent> data, String imei, int device) {
        super(imei, device);
        this.data = data;
    }

    public List<NetworkEvent> getData() {
        return data;
    }

    public void setData(List<NetworkEvent> data) {
        this.data = data;
    }
}