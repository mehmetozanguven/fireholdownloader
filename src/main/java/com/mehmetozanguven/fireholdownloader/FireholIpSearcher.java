package com.mehmetozanguven.fireholdownloader;

import com.mehmetozanguven.fireholdownloader.data.FireholIpData;
import com.mehmetozanguven.fireholdownloader.defaultConfiguration.DefaultFireholIpSearcher;

import java.util.List;

/**
 * Search algorithm for the ip addresses in the firehol data
 * SearchList will be the ip addresses for the specific level set
 * @see DefaultFireholIpSearcher for the default implementation
 */
public interface FireholIpSearcher {

    FireholIpData searchIpInTheList(String ipAddressOrCidrNotation, List<FireholIpData> searchList);
}
