package com.shiz.entity;

import javax.persistence.*;

/**
 * Created by oldman on 19.04.17.
 */
@Entity
@Table(name = "Information", schema = "mydb")
@IdClass(InformationEntityPK.class)
public class InformationEntity {
    private int id;
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
    private String isDualSim;
    private String imeiSim1;
    private String imeiSim2;
    private String mcc;
    private String mnc;
    private String phoneType;
    private String networkType;
    private String network;
    private String operatorName;
    private boolean isRoot;
    private DeviceEntity deviceInfoByDeviceId;
    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "is_root", nullable = false, length = 45)
    public boolean getIsRoot() {
        return isRoot;
    }

    public void setIsRoot(boolean root) {
        this.isRoot = root;
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

    @Basic
    @Column(name = "is_dual_sim", nullable = true, length = 45)
    public String getIsDualSim() {
        return isDualSim;
    }

    public void setIsDualSim(String isDualSim) {
        this.isDualSim = isDualSim;
    }

    @Basic
    @Column(name = "imei_sim1", nullable = true, length = 45)
    public String getImeiSim1() {
        return imeiSim1;
    }

    public void setImeiSim1(String imeiSim1) {
        this.imeiSim1 = imeiSim1;
    }

    @Basic
    @Column(name = "imei_sim2", nullable = true, length = 45)
    public String getImeiSim2() {
        return imeiSim2;
    }

    public void setImeiSim2(String imeiSim2) {
        this.imeiSim2 = imeiSim2;
    }

    @Basic
    @Column(name = "mcc", nullable = true, length = 45)
    public String getMcc() {
        return mcc;
    }

    public void setMcc(String mcc) {
        this.mcc = mcc;
    }

    @Basic
    @Column(name = "mnc", nullable = true, length = 45)
    public String getMnc() {
        return mnc;
    }

    public void setMnc(String mnc) {
        this.mnc = mnc;
    }

    @Basic
    @Column(name = "phone_type", nullable = true, length = 45)
    public String getPhoneType() {
        return phoneType;
    }

    public void setPhoneType(String phoneType) {
        this.phoneType = phoneType;
    }

    @Basic
    @Column(name = "network_type", nullable = true, length = 45)
    public String getNetworkType() {
        return networkType;
    }

    public void setNetworkType(String networkType) {
        this.networkType = networkType;
    }

    @Basic
    @Column(name = "network", nullable = true, length = 45)
    public String getNetwork() {
        return network;
    }

    public void setNetwork(String network) {
        this.network = network;
    }

    @Basic
    @Column(name = "operator_name", nullable = true, length = 45)
    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        InformationEntity that = (InformationEntity) o;

        if (id != that.id) return false;
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
        if (isDualSim != null ? !isDualSim.equals(that.isDualSim) : that.isDualSim != null) return false;
        if (imeiSim1 != null ? !imeiSim1.equals(that.imeiSim1) : that.imeiSim1 != null) return false;
        if (imeiSim2 != null ? !imeiSim2.equals(that.imeiSim2) : that.imeiSim2 != null) return false;
        if (mcc != null ? !mcc.equals(that.mcc) : that.mcc != null) return false;
        if (mnc != null ? !mnc.equals(that.mnc) : that.mnc != null) return false;
        if (phoneType != null ? !phoneType.equals(that.phoneType) : that.phoneType != null) return false;
        if (networkType != null ? !networkType.equals(that.networkType) : that.networkType != null) return false;
        if (network != null ? !network.equals(that.network) : that.network != null) return false;
        if (operatorName != null ? !operatorName.equals(that.operatorName) : that.operatorName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
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
        result = 31 * result + (isDualSim != null ? isDualSim.hashCode() : 0);
        result = 31 * result + (imeiSim1 != null ? imeiSim1.hashCode() : 0);
        result = 31 * result + (imeiSim2 != null ? imeiSim2.hashCode() : 0);
        result = 31 * result + (mcc != null ? mcc.hashCode() : 0);
        result = 31 * result + (mnc != null ? mnc.hashCode() : 0);
        result = 31 * result + (phoneType != null ? phoneType.hashCode() : 0);
        result = 31 * result + (networkType != null ? networkType.hashCode() : 0);
        result = 31 * result + (network != null ? network.hashCode() : 0);
        result = 31 * result + (operatorName != null ? operatorName.hashCode() : 0);
        return result;
    }
    @OneToOne
    @JoinColumn(name = "device_id")
    public DeviceEntity getDeviceInfoByDeviceId() {
        return deviceInfoByDeviceId;
    }

    public void setDeviceInfoByDeviceId(DeviceEntity deviceByDeviceId) {
        this.deviceInfoByDeviceId = deviceByDeviceId;
    }

}
