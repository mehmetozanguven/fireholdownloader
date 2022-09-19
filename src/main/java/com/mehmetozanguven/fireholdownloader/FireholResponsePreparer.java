package com.mehmetozanguven.fireholdownloader;

import com.mehmetozanguven.fireholdownloader.data.FireholIpData;
import com.mehmetozanguven.fireholdownloader.defaultConfiguration.DefaultFireholResponsePreparer;

import java.util.List;

/**
 * @see DefaultFireholResponsePreparer for the default implementation
 */
public interface FireholResponsePreparer {

    List<FireholIpData> prepareFireholLevelSetFor(String fireholHttpResponse, IpUtility ipUtility);
}
