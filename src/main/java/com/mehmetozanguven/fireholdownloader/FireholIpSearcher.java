package com.mehmetozanguven.fireholdownloader;

import com.mehmetozanguven.fireholdownloader.data.FireholIpData;

import java.util.List;

public interface FireholIpSearcher {

    FireholIpData searchIpInTheList(String ipAddressOrCidrNotation, List<FireholIpData> searchList);
}
