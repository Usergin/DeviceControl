package com.shiz.model.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by oldman on 05.04.17.
 */
public class DeviceInfo extends BaseInfo {
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
    private String is_dual_sim;
    private String imei_sim1;
    private String imei_sim2;
    private String mcc;
    private String mnc;
    private String phone_type;
    private String network_type;
    private String network;
    private String operator_name;
    private boolean root;


    private DeviceInfo(Builder builder) {
        setImei(builder.imei);
        setModel(builder.model);
        setSerial_num(builder.serial_num);
        setVersion_os(builder.version_os);
        setBrand(builder.brand);
        setImsi(builder.imsi);
        setManufactured(builder.manufactured);
        setProduct(builder.product);
        setSdk(builder.sdk);
        setScreen_size(builder.screen_size);
        setIs_dual_sim(builder.is_dual_sim);
        setImei_sim1(builder.imei_sim1);
        setImei_sim2(builder.imei_sim2);
        setMcc(builder.mcc);
        setMnc(builder.mnc);
        setPhone_type(builder.phone_type);
        setNetwork_type(builder.network_type);
        setNetwork(builder.network);
        setOperator_name(builder.operator_name);
        setRoot(builder.is_root);
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public boolean isRoot() {
        return root;
    }

    public void setRoot(boolean is_root) {
        this.root = is_root;
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

    public String getVersion_os() {
        return version_os;
    }

    public void setVersion_os(String version_os) {
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


    public String getScreen_size() {
        return screen_size;
    }

    public void setScreen_size(String screen_size) {
        this.screen_size = screen_size;
    }

    public String getNetwork() {
        return network;
    }

    public void setNetwork(String network) {
        this.network = network;
    }


    public String getIs_dual_sim() {
        return is_dual_sim;
    }

    public void setIs_dual_sim(String is_dual_sim) {
        this.is_dual_sim = is_dual_sim;
    }

    public String getImei_sim1() {
        return imei_sim1;
    }

    public void setImei_sim1(String imei_sim1) {
        this.imei_sim1 = imei_sim1;
    }

    public String getImei_sim2() {
        return imei_sim2;
    }

    public void setImei_sim2(String imei_sim2) {
        this.imei_sim2 = imei_sim2;
    }

    public String getMcc() {
        return mcc;
    }

    public void setMcc(String mcc) {
        this.mcc = mcc;
    }

    public String getMnc() {
        return mnc;
    }

    public void setMnc(String mnc) {
        this.mnc = mnc;
    }

    public String getPhone_type() {
        return phone_type;
    }

    public void setPhone_type(String phone_type) {
        this.phone_type = phone_type;
    }

    public String getNetwork_type() {
        return network_type;
    }

    public void setNetwork_type(String network_type) {
        this.network_type = network_type;
    }

    public String getOperator_name() {
        return operator_name;
    }

    public void setOperator_name(String operator_name) {
        this.operator_name = operator_name;
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
        private String is_dual_sim;
        private String imei_sim1;
        private String imei_sim2;
        private String mcc;
        private String mnc;
        private String phone_type;
        private String network_type;
        private String network;
        private String operator_name;
        private boolean is_root;

        private Builder() {
        }

        public Builder isRoot(boolean val) {
            is_root = val;
            return this;
        }

        public Builder isDualSim(String val) {
            is_dual_sim = val;
            return this;
        }

        public Builder imeiSim1(String val) {
            imei_sim1 = val;
            return this;
        }

        public Builder imeiSim2(String val) {
            imei_sim2 = val;
            return this;
        }

        public Builder mcc(String val) {
            mcc = val;
            return this;
        }

        public Builder mnc(String val) {
            mnc = val;
            return this;
        }

        public Builder phoneType(String val) {
            phone_type = val;
            return this;
        }

        public Builder networkType(String val) {
            network_type = val;
            return this;
        }

        public Builder operatorName(String val) {
            operator_name = val;
            return this;
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

        public Builder network(String val) {
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