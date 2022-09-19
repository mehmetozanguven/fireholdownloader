package io.github.mehmetozanguven.data;

public class FireholSubnetUtils {
    private String ipAddress;
    private String lowIpAddress;
    private String lowIpAddressInDecimalFormat;

    private String highIpAddress;
    private String highIpAddressInDecimalFormat;

    private String netmask;
    private String cidrNotation;

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getLowIpAddress() {
        return lowIpAddress;
    }

    public void setLowIpAddress(String lowIpAddress) {
        this.lowIpAddress = lowIpAddress;
    }

    public String getLowIpAddressInDecimalFormat() {
        return lowIpAddressInDecimalFormat;
    }

    public void setLowIpAddressInDecimalFormat(String lowIpAddressInDecimalFormat) {
        this.lowIpAddressInDecimalFormat = lowIpAddressInDecimalFormat;
    }

    public String getHighIpAddress() {
        return highIpAddress;
    }

    public void setHighIpAddress(String highIpAddress) {
        this.highIpAddress = highIpAddress;
    }

    public String getHighIpAddressInDecimalFormat() {
        return highIpAddressInDecimalFormat;
    }

    public void setHighIpAddressInDecimalFormat(String highIpAddressInDecimalFormat) {
        this.highIpAddressInDecimalFormat = highIpAddressInDecimalFormat;
    }

    public String getNetmask() {
        return netmask;
    }

    public void setNetmask(String netmask) {
        this.netmask = netmask;
    }

    public String getCidrNotation() {
        return cidrNotation;
    }

    public void setCidrNotation(String cidrNotation) {
        this.cidrNotation = cidrNotation;
    }
}
