package com.shiz.model.respose;

import com.shiz.model.data.Settings;

/**
 * Created by oldman on 05.04.17.
 */
public class PeriodicalResponse extends BaseResponse {
    private Settings data;

    public PeriodicalResponse(int code, Settings data) {
        super(code);
        this.data = data;
    }

    public Settings getData() {
        return data;
    }

    public void setData(Settings data) {
        this.data = data;
    }
}
