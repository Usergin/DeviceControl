package com.shiz.model;


import com.shiz.model.data.Contact;
import com.shiz.model.data.DeviceInfo;
import com.shiz.model.data.Settings;
import com.shiz.model.data.event.*;

import java.util.List;

/**
 * Created by oldman on 04.04.17.
 */
public class Device {
    private int id;
    private String imei;
    private String model;
    private String version_os;
    private Double longitude;
    private Double latitude;

    public static DeviceBuilder newBuilder() {
        return new DeviceBuilder();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getVersion_os() {
        return version_os;
    }

    public void setVersion_os(String version_os) {
        this.version_os = version_os;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public static final class DeviceBuilder {
        private int id;
        private String imei;
        private String model;
        private String version_os;
        private Double longitude;
        private Double latitude;

        private DeviceBuilder() {
        }


        public DeviceBuilder id(int id) {
            this.id = id;
            return this;
        }

        public DeviceBuilder imei(String imei) {
            this.imei = imei;
            return this;
        }

        public DeviceBuilder model(String model) {
            this.model = model;
            return this;
        }

        public DeviceBuilder version_os(String version_os) {
            this.version_os = version_os;
            return this;
        }
        public DeviceBuilder longitude(Double longitude) {
            this.longitude = longitude;
            return this;
        }

        public DeviceBuilder latitude(Double latitude) {
            this.latitude = latitude;
            return this;
        }

        public Device build() {
            Device device = new Device();
            device.setId(id);
            device.setImei(imei);
            device.setModel(model);
            device.setVersion_os(version_os);
            device.setLongitude(longitude);
            device.setLatitude(latitude);
            return device;
        }
    }
}
