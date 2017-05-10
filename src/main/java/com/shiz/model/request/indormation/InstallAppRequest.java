package com.shiz.model.request.indormation;

import com.shiz.model.data.event.Call;
import com.shiz.model.data.event.InstallApp;
import com.shiz.model.request.BaseRequest;

import java.util.Collection;
import java.util.List;

/**
 * Created by oldman on 06.04.17.
 */
public class InstallAppRequest extends BaseRequest {
    private List<InstallApp> data;

    public InstallAppRequest( List<InstallApp> data, String imei, int device) {
        super(imei, device);
        this.data = data;
    }

    public  List<InstallApp> getData() {
        return data;
    }

    public void setData( List<InstallApp> data) {
        this.data = data;
    }
}