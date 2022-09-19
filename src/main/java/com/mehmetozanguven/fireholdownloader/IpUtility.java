package com.mehmetozanguven.fireholdownloader;

import com.mehmetozanguven.fireholdownloader.data.FireholSubnetUtils;
import com.mehmetozanguven.fireholdownloader.defaultConfiguration.DefaultIpUtilityConfiguration;

/**
 * @see DefaultIpUtilityConfiguration for the default implementation
 */
public interface IpUtility {

    boolean isValidIpAddress(String ipAddress);

    long ipToBase256Long(String ip);

    FireholSubnetUtils newSubnetUtils(String cidrNotation);
}
