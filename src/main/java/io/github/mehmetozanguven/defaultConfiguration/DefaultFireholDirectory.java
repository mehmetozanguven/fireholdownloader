package io.github.mehmetozanguven.defaultConfiguration;

import io.github.mehmetozanguven.FireholDirectory;

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
