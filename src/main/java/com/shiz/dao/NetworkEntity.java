package com.shiz.dao;

import javax.persistence.*;

/**
 * Created by oldman on 14.04.17.
 */
@Entity
@Table(name = "Network", schema = "mydb", catalog = "")
public class NetworkEntity {
    private int id;
    private String isDualSim;
    private String imeiSim1;
    private String imeiSim2;
    private String mcc;
    private String mnc;
    private String phoneType;
    private String networkType;
    private String network;
    private String operatorName;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

        NetworkEntity that = (NetworkEntity) o;

        if (id != that.id) return false;
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
}
