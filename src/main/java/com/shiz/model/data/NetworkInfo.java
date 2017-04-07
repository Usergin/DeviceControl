package com.shiz.model.data;

/**
 * Created by oldman on 05.04.17.
 */
public class NetworkInfo {
    private String is_dual_sim;
    private String imei_sim1;
    private String imei_sim2;
    private String mcc;
    private String mnc;
    private String phone_type;
    private String network_type;
    private String network;
    private String operator_name;

    public static NetworkInfo.Builder newBuilder() {
        return new NetworkInfo.Builder();
    }
    private NetworkInfo(Builder builder) {
        setIsDualSim(builder.is_dual_sim);
        setImeiSim1(builder.imei_sim1);
        setImeiSim2(builder.imei_sim2);
        setMcc(builder.mcc);
        setMnc(builder.mnc);
        setPhoneType(builder.phone_type);
        setNetworkType(builder.network_type);
        setNetwork(builder.network);
        setOperatorName(builder.operator_name);
    }
    public String getIsDualSim() {
        return is_dual_sim;
    }

    public void setIsDualSim(String is_dual_sim) {
        this.is_dual_sim = is_dual_sim;
    }

    public String getImeiSim1() {
        return imei_sim1;
    }

    public void setImeiSim1(String imei_sim1) {
        this.imei_sim1 = imei_sim1;
    }

    public String getImeiSim2() {
        return imei_sim2;
    }

    public void setImeiSim2(String imei_sim2) {
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

    public String getPhoneType() {
        return phone_type;
    }

    public void setPhoneType(String phone_type) {
        this.phone_type = phone_type;
    }

    public String getNetworkType() {
        return network_type;
    }

    public void setNetworkType(String network_type) {
        this.network_type = network_type;
    }

    public String getNetwork() {
        return network;
    }

    public void setNetwork(String network) {
        this.network = network;
    }

    public String getOperatorName() {
        return operator_name;
    }

    public void setOperatorName(String operator_name) {
        this.operator_name = operator_name;
    }


    public static final class Builder {
        private String is_dual_sim;
        private String imei_sim1;
        private String imei_sim2;
        private String mcc;
        private String mnc;
        private String phone_type;
        private String network_type;
        private String network;
        private String operator_name;

        private Builder() {}

        public NetworkInfo.Builder isDualSim(String val) {
            is_dual_sim = val;
            return this;
        }

        public NetworkInfo.Builder imeiSim1(String val) {
            imei_sim1 = val;
            return this;
        }

        public NetworkInfo.Builder imeiSim2(String val) {
            imei_sim2 = val;
            return this;
        }

        public NetworkInfo.Builder mcc(String val) {
            mcc = val;
            return this;
        }

        public NetworkInfo.Builder mnc(String val) {
            mnc = val;
            return this;
        }

        public NetworkInfo.Builder phoneType(String val) {
            phone_type = val;
            return this;
        }

        public NetworkInfo.Builder networkType(String val) {
            network_type = val;
            return this;
        }

        public NetworkInfo.Builder network(String val) {
            network = val;
            return this;
        }

        public NetworkInfo.Builder operatorName(String val) {
            operator_name = val;
            return this;
        }

        public NetworkInfo build() {
            return new NetworkInfo(this);
        }
    }
}
