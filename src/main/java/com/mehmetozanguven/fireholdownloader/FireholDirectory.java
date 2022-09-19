package com.mehmetozanguven.fireholdownloader;

import com.mehmetozanguven.fireholdownloader.defaultConfiguration.DefaultFireholDirectory;

/**
 * Each firehol ip level set will be placed directory. To define your own directory, create an implementation for that interface
 * NOTE: If createDirectoryIfNotExists returns false and if there is no directory, RuntimeException will be thrown
 * @see DefaultFireholDirectory for the default implementation
 */
public interface FireholDirectory {
    boolean createDirectoryIfNotExists();

    String getDirectory();
}
