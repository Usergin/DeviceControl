package com.shiz.model.request;

import com.shiz.model.data.Settings;

/**
 * Created by oldman on 05.04.17.
 */
public class SyncRequest extends BaseRequest {


//    private Settings settings;

    public SyncRequest(String imei, int device) {
        super(imei, device);
        }

//    public Settings getSettingsEntity() {
//        return settings;
//    }
//
//    public void setSettings(Settings settings) {
//        this.settings = settings;
//    }
}
