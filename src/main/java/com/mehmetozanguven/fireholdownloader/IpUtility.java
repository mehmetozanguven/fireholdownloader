package com.mehmetozanguven.fireholdownloader;

import com.mehmetozanguven.fireholdownloader.data.FireholSubnetUtils;

public interface IpUtility {

    boolean isValidIpAddress(String ipAddress);

    long ipToBase256Long(String ip);

    FireholSubnetUtils newSubnetUtils(String cidrNotation);
}
