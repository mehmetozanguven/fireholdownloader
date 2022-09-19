package io.github.mehmetozanguven.fireholdownloader.defaultConfiguration;

import io.github.mehmetozanguven.fireholdownloader.FireholIpSearcher;
import io.github.mehmetozanguven.fireholdownloader.IpUtility;
import io.github.mehmetozanguven.fireholdownloader.data.FireholIpData;
import io.github.mehmetozanguven.fireholdownloader.data.FireholSubnetUtils;

import java.util.List;

public class DefaultFireholIpSearcher implements FireholIpSearcher {
    private final IpUtility ipUtility;

    public DefaultFireholIpSearcher(IpUtility ipUtility) {
        this.ipUtility = ipUtility;
    }

    @Override
    public FireholIpData searchIpInTheList(String ipAddressOrCidrNotation, List<FireholIpData> searchList) {
        String removedSpaceFireholIp = ipAddressOrCidrNotation.trim();
        String[] splittedRawData = removedSpaceFireholIp.split("/");
        String ipAddress = splittedRawData[0].trim();

        if (!ipUtility.isValidIpAddress(ipAddress)) {
            return null;
        }
        if (splittedRawData.length == 1) {
            long ipInDecimalFormat = ipUtility.ipToBase256Long(ipAddress);
            return searchList.stream().filter(elem -> ipInDecimalFormat >= Long.parseLong(elem.getLowIpAddressInDecimalFormat()) && ipInDecimalFormat <= Long.parseLong(elem.getHighIpAddressInDecimalFormat()))
                    .findFirst().orElse(null);
        }
        else if (splittedRawData.length == 2) {
            FireholSubnetUtils ipCalculatedSubnet = ipUtility.newSubnetUtils(removedSpaceFireholIp);
            long lowAddressInDecimal = Long.parseLong(ipCalculatedSubnet.getLowIpAddressInDecimalFormat());
            long highAddressInDecimal = Long.parseLong(ipCalculatedSubnet.getHighIpAddressInDecimalFormat());
            return searchList.stream().filter(elem -> lowAddressInDecimal >= Long.parseLong(elem.getLowIpAddressInDecimalFormat()) && highAddressInDecimal <= Long.parseLong(elem.getHighIpAddressInDecimalFormat()))
                    .findFirst().orElse(null);
        } else{
            return null;
        }
    }
}
