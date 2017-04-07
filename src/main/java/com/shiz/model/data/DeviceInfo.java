package com.shiz.model.data;

/**
 * Created by oldman on 05.04.17.
 */
public class DeviceInfo extends BaseInfo{
    private String imei;
    private String model;
    private String serial_num;
    private String version_os;
    private String brand;
    private String imsi;
    private String manufactured;
    private String product;
    private String sdk;
    private String screen_size;
    private NetworkInfo network;

    public DeviceInfo() {
    }

    private DeviceInfo(Builder builder) {
        setImei(builder.imei);
        setModel(builder.model);
        setSerialNum(builder.serial_num);
        setVersionOS(builder.version_os);
        setBrand(builder.brand);
        setImsi(builder.imsi);
        setManufactured(builder.manufactured);
        setProduct(builder.product);
        setSdk(builder.sdk);
        setScreenSize(builder.screen_size);
        setNetwork(builder.network);
    }

    public static Builder newBuilder() {
        return new Builder();
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

    public String getSerialNum() {
        return serial_num;
    }

    public void setSerialNum(String serial_num) {
        this.serial_num = serial_num;
    }

    public String getVersionOS() {
        return version_os;
    }

    public void setVersionOS(String version_os) {
        this.version_os = version_os;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getImsi() {
        return imsi;
    }

    public void setImsi(String imsi) {
        this.imsi = imsi;
    }

    public String getManufactured() {
        return manufactured;
    }

    public void setManufactured(String manufactured) {
        this.manufactured = manufactured;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getSdk() {
        return sdk;
    }

    public void setSdk(String sdk) {
        this.sdk = sdk;
    }


    public String getScreenSize() {
        return screen_size;
    }

    public void setScreenSize(String screen_size) {
        this.screen_size = screen_size;
    }

    public NetworkInfo getNetwork() {
        return network;
    }

    public void setNetwork(NetworkInfo network) {
        this.network = network;
    }

    public static final class Builder {
        private String imei;
        private String model;
        private String serial_num;
        private String version_os;
        private String brand;
        private String imsi;
        private String manufactured;
        private String product;
        private String sdk;
        private String screen_size;
        private NetworkInfo network;


        private Builder() {
        }

        public Builder imei(String val) {
            imei = val;
            return this;
        }

        public Builder model(String val) {
            model = val;
            return this;
        }

        public Builder serialNum(String val) {
            serial_num = val;
            return this;
        }

        public Builder versionOS(String val) {
            version_os = val;
            return this;
        }

        public Builder brand(String val) {
            brand = val;
            return this;
        }

        public Builder imsi(String val) {
            imsi = val;
            return this;
        }

        public Builder manufactured(String val) {
            manufactured = val;
            return this;
        }

        public Builder product(String val) {
            product = val;
            return this;
        }

        public Builder sdk(String val) {
            sdk = val;
            return this;
        }

        public Builder network(NetworkInfo val) {
            network = val;
            return this;
        }

        public Builder screenSize(String val) {
            screen_size = val;
            return this;
        }

        public DeviceInfo build() {
            return new DeviceInfo(this);
        }
    }
}