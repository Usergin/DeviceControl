package com.shiz.model.request.indormation;

import com.shiz.model.data.Contact;
import com.shiz.model.request.BaseRequest;

import java.util.List;

/**
 * Created by oldman on 06.04.17.
 */
public class ContactRequest extends BaseRequest {
    private List<Contact> data;

    public ContactRequest(List<Contact> data, String imei, int device) {
        super(imei, device);
        this.data = data;
    }

    public List<Contact> getData() {
        return data;
    }

    public void setData(List<Contact> data) {
        this.data = data;
    }
}