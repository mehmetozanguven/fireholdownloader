package com.mehmetozanguven.fireholdownloader.defaultConfiguration;

import com.mehmetozanguven.fireholdownloader.FireholLevelSetInfo;

public class DefaultFireholIPSetInfoInfo implements FireholLevelSetInfo {
    private String url;
    private boolean isLazyLoad;
    private String fileName;

    public DefaultFireholIPSetInfoInfo() {
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setLazyLoad(boolean lazyLoad) {
        isLazyLoad = lazyLoad;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public String getUrl() {
        return url;
    }

    @Override
    public boolean isLazyLoad() {
        return isLazyLoad;
    }

    @Override
    public String getFileName() {
        return fileName;
    }
}
