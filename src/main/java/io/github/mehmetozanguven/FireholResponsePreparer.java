package io.github.mehmetozanguven;

import io.github.mehmetozanguven.data.FireholIpData;
import io.github.mehmetozanguven.defaultConfiguration.DefaultFireholResponsePreparer;

import java.util.List;

/**
 * @see DefaultFireholResponsePreparer for the default implementation
 */
public interface FireholResponsePreparer {

    List<FireholIpData> prepareFireholLevelSetFor(String fireholHttpResponse, IpUtility ipUtility);
}
