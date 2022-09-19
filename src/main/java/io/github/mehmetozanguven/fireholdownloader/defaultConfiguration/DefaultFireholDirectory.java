package io.github.mehmetozanguven.fireholdownloader.defaultConfiguration;

import io.github.mehmetozanguven.fireholdownloader.FireholDirectory;

public class DefaultFireholDirectory implements FireholDirectory {

    @Override
    public boolean createDirectoryIfNotExists() {
        return true;
    }

    @Override
    public String getDirectory() {
        return "/tmp/firehol";
    }
}
