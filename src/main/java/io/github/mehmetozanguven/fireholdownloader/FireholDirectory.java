package io.github.mehmetozanguven.fireholdownloader;

import io.github.mehmetozanguven.fireholdownloader.defaultConfiguration.DefaultFireholDirectory;

/**
 * @see DefaultFireholDirectory for the default implementation
 */
public interface FireholDirectory {
    /**
     * If createDirectoryIfNotExists returns false and if there is no directory, RuntimeException will be thrown
     */
    boolean createDirectoryIfNotExists();

    /**
     * Specify firehol directory
     * @return
     */
    String getDirectory();
}