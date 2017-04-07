package com.shiz.db;

import javax.persistence.*;

/**
 * Created by oldman on 07.04.17.
 */
@Entity
@Table(name = "Information", schema = "mydb", catalog = "")
@IdClass(InformationEntityPK.class)
public class InformationEntity {
    private int id;
    private int deviceId;
    private String imei;
    private String imsi;
    private String serialNum;
    private String model;
    private String brand;
    private String manufactured;
    private String versionOs;
    private String product;
    private String sdk;
    private String screenSize;
    private int networkId;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Id
    @Column(name = "device_id", nullable = false)
    public int getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(int deviceId) {
        this.deviceId = deviceId;
    }

    @Basic
    @Column(name = "imei", nullable = false, length = 45)
    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    @Basic
    @Column(name = "imsi", nullable = true, length = 45)
    public String getImsi() {
        return imsi;
    }

    public void setImsi(String imsi) {
        this.imsi = imsi;
    }

    @Basic
    @Column(name = "serial_num", nullable = true, length = 45)
    public String getSerialNum() {
        return serialNum;
    }

    public void setSerialNum(String serialNum) {
        this.serialNum = serialNum;
    }

    @Basic
    @Column(name = "model", nullable = true, length = 45)
    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Basic
    @Column(name = "brand", nullable = true, length = 45)
    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    @Basic
    @Column(name = "manufactured", nullable = true, length = 45)
    public String getManufactured() {
        return manufactured;
    }

    public void setManufactured(String manufactured) {
        this.manufactured = manufactured;
    }

    @Basic
    @Column(name = "version_os", nullable = true, length = 45)
    public String getVersionOs() {
        return versionOs;
    }

    public void setVersionOs(String versionOs) {
        this.versionOs = versionOs;
    }

    @Basic
    @Column(name = "product", nullable = true, length = 45)
    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    @Basic
    @Column(name = "sdk", nullable = true, length = 45)
    public String getSdk() {
        return sdk;
    }

    public void setSdk(String sdk) {
        this.sdk = sdk;
    }

    @Basic
    @Column(name = "screen_size", nullable = true, length = 45)
    public String getScreenSize() {
        return screenSize;
    }

    public void setScreenSize(String screenSize) {
        this.screenSize = screenSize;
    }

    @Id
    @Column(name = "network_id", nullable = false)
    public int getNetworkId() {
        return networkId;
    }

    public void setNetworkId(int networkId) {
        this.networkId = networkId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        InformationEntity that = (InformationEntity) o;

        if (id != that.id) return false;
        if (deviceId != that.deviceId) return false;
        if (networkId != that.networkId) return false;
        if (imei != null ? !imei.equals(that.imei) : that.imei != null) return false;
        if (imsi != null ? !imsi.equals(that.imsi) : that.imsi != null) return false;
        if (serialNum != null ? !serialNum.equals(that.serialNum) : that.serialNum != null) return false;
        if (model != null ? !model.equals(that.model) : that.model != null) return false;
        if (brand != null ? !brand.equals(that.brand) : that.brand != null) return false;
        if (manufactured != null ? !manufactured.equals(that.manufactured) : that.manufactured != null) return false;
        if (versionOs != null ? !versionOs.equals(that.versionOs) : that.versionOs != null) return false;
        if (product != null ? !product.equals(that.product) : that.product != null) return false;
        if (sdk != null ? !sdk.equals(that.sdk) : that.sdk != null) return false;
        if (screenSize != null ? !screenSize.equals(that.screenSize) : that.screenSize != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + deviceId;
        result = 31 * result + (imei != null ? imei.hashCode() : 0);
        result = 31 * result + (imsi != null ? imsi.hashCode() : 0);
        result = 31 * result + (serialNum != null ? serialNum.hashCode() : 0);
        result = 31 * result + (model != null ? model.hashCode() : 0);
        result = 31 * result + (brand != null ? brand.hashCode() : 0);
        result = 31 * result + (manufactured != null ? manufactured.hashCode() : 0);
        result = 31 * result + (versionOs != null ? versionOs.hashCode() : 0);
        result = 31 * result + (product != null ? product.hashCode() : 0);
        result = 31 * result + (sdk != null ? sdk.hashCode() : 0);
        result = 31 * result + (screenSize != null ? screenSize.hashCode() : 0);
        result = 31 * result + networkId;
        return result;
    }
}
