package io.github.mehmetozanguven.data;

import io.github.mehmetozanguven.IpUtility;

import java.io.Serializable;

public class FireholIpData implements Serializable {
    private String rawFormatOfFireholResponse;
    private String ipAddress;

    private String lowIpAddress;
    private String lowIpAddressInDecimalFormat;

    private String highIpAddress;
    private String highIpAddressInDecimalFormat;

    private String netmask;
    private String cidrNotation;

    public FireholIpData(IpUtility ipUtility, String fireholIp) {
        setRawFormatOfFireholResponse(fireholIp);
        setLowAndHighIpAddress(ipUtility, fireholIp);
    }

    private void setLowAndHighIpAddress(IpUtility ipUtility, String fireholIp) {
        String removedSpaceFireholIp = fireholIp.trim();
        String[] splittedRawData = removedSpaceFireholIp.split("/");
        String ipAddress = splittedRawData[0].trim();
        if (!ipUtility.isValidIpAddress(ipAddress)) {
            return;
        }

        setIpAddress(ipAddress);

        if (splittedRawData.length == 1)
        {
            setLowIpAddress(ipAddress);
            setLowIpAddressInDecimalFormat(String.valueOf(ipUtility.ipToBase256Long(ipAddress)));
            setHighIpAddress(ipAddress);
            setHighIpAddressInDecimalFormat(String.valueOf(ipUtility.ipToBase256Long(ipAddress)));
            setNetmask("");
            setCidrNotation("");
        }
        else if (splittedRawData.length == 2)
        {
            FireholSubnetUtils ipCalculatedSubnet = ipUtility.newSubnetUtils(removedSpaceFireholIp);

            setIpAddress(ipCalculatedSubnet.getIpAddress());
            setLowIpAddress(ipCalculatedSubnet.getLowIpAddress());
            setLowIpAddressInDecimalFormat(ipCalculatedSubnet.getLowIpAddressInDecimalFormat());
            setHighIpAddress(ipCalculatedSubnet.getHighIpAddress());
            setHighIpAddressInDecimalFormat(ipCalculatedSubnet.getHighIpAddressInDecimalFormat());
            setNetmask(ipCalculatedSubnet.getNetmask());
            setCidrNotation(ipCalculatedSubnet.getCidrNotation());

        }
    }


    public String getRawFormatOfFireholResponse() {
        return rawFormatOfFireholResponse;
    }

    public void setRawFormatOfFireholResponse(String rawFormatOfFireholResponse) {
        this.rawFormatOfFireholResponse = rawFormatOfFireholResponse;
    }

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

    @Override
    public String toString() {
        return "FireholIpData{" +
                "rawFormatOfFireholResponse='" + rawFormatOfFireholResponse + '\'' +
                ", ipAddress='" + ipAddress + '\'' +
                ", lowIpAddress='" + lowIpAddress + '\'' +
                ", lowIpAddressInDecimalFormat='" + lowIpAddressInDecimalFormat + '\'' +
                ", highIpAddress='" + highIpAddress + '\'' +
                ", highIpAddressInDecimalFormat='" + highIpAddressInDecimalFormat + '\'' +
                ", netmask='" + netmask + '\'' +
                ", cidrNotation='" + cidrNotation + '\'' +
                '}';
    }
}
