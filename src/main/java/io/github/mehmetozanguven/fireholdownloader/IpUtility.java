package io.github.mehmetozanguven.fireholdownloader;

import io.github.mehmetozanguven.fireholdownloader.data.FireholSubnetUtils;
import io.github.mehmetozanguven.fireholdownloader.defaultConfiguration.DefaultIpUtilityConfiguration;

/**
 * @see DefaultIpUtilityConfiguration for the default implementation
 */
public interface IpUtility {

    boolean isValidIpAddress(String ipAddress);

    long ipToBase256Long(String ip);

    FireholSubnetUtils newSubnetUtils(String cidrNotation);
}
