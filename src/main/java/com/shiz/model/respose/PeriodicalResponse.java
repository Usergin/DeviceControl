package com.shiz.model.respose;

import com.shiz.model.data.Settings;

/**
 * Created by oldman on 05.04.17.
 */
public class PeriodicalResponse extends BaseResponse {
    private Settings settings;

    public PeriodicalResponse(int code, Settings settings) {
        super(code);
        this.settings = settings;
    }

    public Settings getSettings() {
        return settings;
    }

    public void setSettings(Settings settings) {
        this.settings = settings;
    }
}
