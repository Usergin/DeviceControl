package com.shiz.model.request;

import com.shiz.model.data.Settings;

/**
 * Created by oldman on 05.04.17.
 */
public class PeriodicalRequest extends BaseRequest {


    private Settings settings;

    public PeriodicalRequest(Settings settings, String imei, String device) {
        super(imei, device);
        this.settings = settings;
    }

    public Settings getSettings() {
        return settings;
    }

    public void setSettings(Settings settings) {
        this.settings = settings;
    }
}
