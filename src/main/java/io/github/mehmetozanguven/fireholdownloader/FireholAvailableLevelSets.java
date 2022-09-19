package io.github.mehmetozanguven.fireholdownloader;

import io.github.mehmetozanguven.fireholdownloader.defaultConfiguration.DefaultFireholLevelSets;

/**
 * Interface to set level set for the firehol
 * Return null if you don't want to use and download specific level set
 * @see DefaultFireholLevelSets for the default implementation
 */
public interface FireholAvailableLevelSets {

    FireholLevelSetInfo getLevel1SetInfo();

    FireholLevelSetInfo getLevel2SetInfo();

    FireholLevelSetInfo getLevel3SetInfo();

    FireholLevelSetInfo getLevel4SetInfo();
}
