package com.mehmetozanguven.fireholdownloader;

import com.mehmetozanguven.fireholdownloader.data.FireholIpData;

import java.util.List;

public interface FireholResponsePreparer {

    List<FireholIpData> prepareFireholLevelSetFor(String fireholHttpResponse, IpUtility ipUtility);
}
