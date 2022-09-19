package io.github.mehmetozanguven;

import io.github.mehmetozanguven.defaultConfiguration.DefaultFireholLevelSetInfo;

/**
 * @see DefaultFireholLevelSetInfo for the default implementation
 */
public interface FireholLevelSetInfo {

    String getFileName();

    String getUrl();

    boolean isLazyLoad();
}
