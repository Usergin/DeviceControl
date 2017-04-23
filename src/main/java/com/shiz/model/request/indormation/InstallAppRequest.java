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
    private Collection<InstallApp> data;

    public InstallAppRequest( Collection<InstallApp> data, String imei, int device) {
        super(imei, device);
        this.data = data;
    }

    public  Collection<InstallApp> getData() {
        return data;
    }

    public void setData( Collection<InstallApp> data) {
        this.data = data;
    }
}