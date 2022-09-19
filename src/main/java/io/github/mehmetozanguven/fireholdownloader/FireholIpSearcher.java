package io.github.mehmetozanguven.fireholdownloader;

import io.github.mehmetozanguven.fireholdownloader.data.FireholIpData;
import io.github.mehmetozanguven.fireholdownloader.defaultConfiguration.DefaultFireholIpSearcher;

import java.util.List;

/**
 * Search algorithm for the ip addresses in the firehol data
 * SearchList will be the ip addresses for the specific level set
 * @see DefaultFireholIpSearcher for the default implementation
 */
public interface FireholIpSearcher {

    FireholIpData searchIpInTheList(String ipAddressOrCidrNotation, List<FireholIpData> searchList);
}
