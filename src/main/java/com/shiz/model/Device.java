package com.shiz.model;


import com.shiz.model.data.Contact;
import com.shiz.model.data.DeviceInfo;
import com.shiz.model.data.event.*;
import com.shiz.model.data.event.Message;

import java.util.List;

/**
 * Created by oldman on 04.04.17.
 */
public class Device {
    private int id;
    private String imei;
    private String model;
    private String serial_num;
    private List<Location> location;
    private List<Call> call_list;
    private List<Message> sms_list;
    private List<InstallApp> install_apps;
    private List<Contact> contact_list;
    private List<BatteryEvent> battery;
    private List<DeviceEvent> device_status;
    private List<NetworkEvent> network_status;
    private Settings settings;
    private DeviceInfo device_info;

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

    public String getSerial_num() {
        return serial_num;
    }

    public void setSerial_num(String serial_num) {
        this.serial_num = serial_num;
    }

    public List<Location> getLocation() {
        return location;
    }

    public void setLocation(List<Location> location) {
        this.location = location;
    }

    public List<Call> getCall_list() {
        return call_list;
    }

    public void setCall_list(List<Call> call_list) {
        this.call_list = call_list;
    }

    public List<Message> getSms_list() {
        return sms_list;
    }

    public void setSms_list(List<Message> sms_list) {
        this.sms_list = sms_list;
    }

    public List<InstallApp> getInstall_apps() {
        return install_apps;
    }

    public void setInstall_apps(List<InstallApp> install_apps) {
        this.install_apps = install_apps;
    }

    public List<Contact> getContact_list() {
        return contact_list;
    }

    public void setContact_list(List<Contact> contact_list) {
        this.contact_list = contact_list;
    }

    public List<BatteryEvent> getBattery() {
        return battery;
    }

    public void setBattery(List<BatteryEvent> battery) {
        this.battery = battery;
    }

    public List<DeviceEvent> getDevice_status() {
        return device_status;
    }

    public void setDevice_status(List<DeviceEvent> device_status) {
        this.device_status = device_status;
    }

    public List<NetworkEvent> getNetwork_status() {
        return network_status;
    }

    public void setNetwork_status(List<NetworkEvent> network_status) {
        this.network_status = network_status;
    }

    public Settings getSettings() {
        return settings;
    }

    public void setSettings(Settings settings) {
        this.settings = settings;
    }

    public DeviceInfo getDevice_info() {
        return device_info;
    }

    public void setDevice_info(DeviceInfo device_info) {
        this.device_info = device_info;
    }

    public static DeviceBuilder newBuilder() {
        return new DeviceBuilder();
    }

    public static final class DeviceBuilder {
        private int id;
        private String imei;
        private String model;
        private String serial_num;
        private List<Location> location;
        private List<Call> call_list;
        private List<Message> sms_list;
        private List<InstallApp> install_apps;
        private List<Contact> contact_list;
        private List<BatteryEvent> battery;
        private List<DeviceEvent> device_status;
        private List<NetworkEvent> network_status;
        private Settings settings;
        private DeviceInfo device_info;

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

        public DeviceBuilder serial_num(String serial_num) {
            this.serial_num = serial_num;
            return this;
        }

        public DeviceBuilder location(List<Location> location) {
            this.location = location;
            return this;
        }

        public DeviceBuilder call_list(List<Call> call_list) {
            this.call_list = call_list;
            return this;
        }

        public DeviceBuilder sms_list(List<Message> sms_list) {
            this.sms_list = sms_list;
            return this;
        }

        public DeviceBuilder install_apps(List<InstallApp> install_apps) {
            this.install_apps = install_apps;
            return this;
        }

        public DeviceBuilder contact_list(List<Contact> contact_list) {
            this.contact_list = contact_list;
            return this;
        }

        public DeviceBuilder battery(List<BatteryEvent> battery) {
            this.battery = battery;
            return this;
        }

        public DeviceBuilder device_status(List<DeviceEvent> device_status) {
            this.device_status = device_status;
            return this;
        }

        public DeviceBuilder network_status(List<NetworkEvent> network_status) {
            this.network_status = network_status;
            return this;
        }

        public DeviceBuilder settings(Settings settings) {
            this.settings = settings;
            return this;
        }

        public DeviceBuilder device_info(DeviceInfo device_info) {
            this.device_info = device_info;
            return this;
        }

        public Device build() {
            Device device = new Device();
            device.setId(id);
            device.setImei(imei);
            device.setModel(model);
            device.setSerial_num(serial_num);
            device.setLocation(location);
            device.setCall_list(call_list);
            device.setSms_list(sms_list);
            device.setInstall_apps(install_apps);
            device.setContact_list(contact_list);
            device.setBattery(battery);
            device.setDevice_status(device_status);
            device.setNetwork_status(network_status);
            device.setSettings(settings);
            device.setDevice_info(device_info);
            return device;
        }
    }
}
