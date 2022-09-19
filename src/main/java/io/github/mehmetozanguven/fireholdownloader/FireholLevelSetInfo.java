package io.github.mehmetozanguven.fireholdownloader;

import io.github.mehmetozanguven.fireholdownloader.defaultConfiguration.DefaultFireholLevelSetInfo;

/**
 * @see DefaultFireholLevelSetInfo for the default implementation
 */
public interface FireholLevelSetInfo {

    String getFileName();

    String getUrl();

    boolean isLazyLoad();
}
