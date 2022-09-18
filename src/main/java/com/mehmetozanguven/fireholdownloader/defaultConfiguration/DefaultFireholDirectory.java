package com.mehmetozanguven.fireholdownloader.defaultConfiguration;

import com.mehmetozanguven.fireholdownloader.FireholDirectory;

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
