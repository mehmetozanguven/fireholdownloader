package io.github.mehmetozanguven;

import io.github.mehmetozanguven.data.FireholSubnetUtils;
import io.github.mehmetozanguven.defaultConfiguration.DefaultIpUtilityConfiguration;

/**
 * @see DefaultIpUtilityConfiguration for the default implementation
 */
public interface IpUtility {

    boolean isValidIpAddress(String ipAddress);

    long ipToBase256Long(String ip);

    FireholSubnetUtils newSubnetUtils(String cidrNotation);
}
