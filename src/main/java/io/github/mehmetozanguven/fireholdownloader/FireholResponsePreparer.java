package io.github.mehmetozanguven.fireholdownloader;

import io.github.mehmetozanguven.fireholdownloader.data.FireholIpData;
import io.github.mehmetozanguven.fireholdownloader.defaultConfiguration.DefaultFireholResponsePreparer;

import java.util.List;

/**
 * @see DefaultFireholResponsePreparer for the default implementation
 */
public interface FireholResponsePreparer {

    List<FireholIpData> prepareFireholLevelSetFor(String fireholHttpResponse, IpUtility ipUtility);
}
