package io.github.mehmetozanguven.fireholdownloader.defaultConfiguration;

import io.github.mehmetozanguven.fireholdownloader.IpUtility;
import com.google.common.net.InetAddresses;
import io.github.mehmetozanguven.fireholdownloader.data.FireholSubnetUtils;
import org.apache.commons.net.util.SubnetUtils;

import java.util.Objects;

public class DefaultIpUtilityConfiguration implements IpUtility {

    @Override
    public boolean isValidIpAddress(String ipAddress) {
        if (isIPv6Address(ipAddress)) {
            throw new UnsupportedOperationException("Does not support ip v6");
        }
        try {
            InetAddresses.forString(ipAddress);
            return true;
        } catch (Exception ex) {
            throw new RuntimeException("Invalid ip address", ex);
        }
    }

    private static boolean isIPv6Address(String ip) {
        return Objects.nonNull(ip) && ip.contains(":");
    }

    @Override
    public long ipToBase256Long(String ip) {
        long result = 0L;
        String[] ipBlocks = ip.split("\\.");
        int indx = ipBlocks.length;
        for (int i = 0; i < ipBlocks.length; i++) {
            result += Long.parseLong(ipBlocks[indx - i - 1]) * (long) Math.pow(256, i);
        }
        return result;
    }

    @Override
    public FireholSubnetUtils newSubnetUtils(String cidrNotation) {
        SubnetUtils subnetUtils = new SubnetUtils(cidrNotation);
        subnetUtils.setInclusiveHostCount(true);

        FireholSubnetUtils fireholSubnetUtils = new FireholSubnetUtils();
        fireholSubnetUtils.setIpAddress(subnetUtils.getInfo().getAddress());
        fireholSubnetUtils.setLowIpAddress(subnetUtils.getInfo().getLowAddress());
        fireholSubnetUtils.setLowIpAddressInDecimalFormat(String.valueOf(ipToBase256Long(subnetUtils.getInfo().getLowAddress())));
        fireholSubnetUtils.setHighIpAddress(subnetUtils.getInfo().getHighAddress());
        fireholSubnetUtils.setHighIpAddressInDecimalFormat(String.valueOf(ipToBase256Long(subnetUtils.getInfo().getHighAddress())));
        fireholSubnetUtils.setNetmask(subnetUtils.getInfo().getNetmask());
        fireholSubnetUtils.setCidrNotation(subnetUtils.getInfo().getCidrSignature());
        return fireholSubnetUtils;
    }
}
