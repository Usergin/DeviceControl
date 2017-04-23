package com.shiz.model.request.indormation;

import com.shiz.model.data.event.NetworkEvent;
import com.shiz.model.request.BaseRequest;

/**
 * Created by oldman on 06.04.17.
 */
public class NetworkStatusRequest extends BaseRequest {
    private NetworkEvent data;

    public NetworkStatusRequest(NetworkEvent data, String imei, int device) {
        super(imei, device);
        this.data = data;
    }

    public NetworkEvent getData() {
        return data;
    }

    public void setData( NetworkEvent data) {
        this.data = data;
    }
}