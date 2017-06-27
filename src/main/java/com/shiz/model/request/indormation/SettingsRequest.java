package com.shiz.model.request.indormation;

import com.shiz.model.data.Settings;
import com.shiz.model.request.BaseRequest;

/**
 * Created by oldman on 04.05.17.
 */
public class SettingsRequest extends BaseRequest {
    private Settings data;

    public SettingsRequest(Settings data, String imei, int device) {
        super(imei, device);
        this.data = data;
    }

    public Settings getData() {
        return data;
    }

    public void setData(Settings data) {
        this.data = data;
    }
}
