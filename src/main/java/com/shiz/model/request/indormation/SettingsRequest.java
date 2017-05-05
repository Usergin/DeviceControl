package com.shiz.model.request.indormation;

import com.shiz.model.data.Settings;
import com.shiz.model.request.BaseRequest;

/**
 * Created by oldman on 04.05.17.
 */
public class SettingsRequest extends BaseRequest {
    private Settings settings;
    public SettingsRequest(Settings settings, String imei, int device) {
        super(imei, device);
    }

    public Settings getSettings() {
        return settings;
    }

    public void setSettings(Settings settings) {
        this.settings = settings;
    }
}
