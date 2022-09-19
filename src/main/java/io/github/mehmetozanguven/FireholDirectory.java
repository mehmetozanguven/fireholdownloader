package io.github.mehmetozanguven;

import io.github.mehmetozanguven.defaultConfiguration.DefaultFireholDirectory;

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
