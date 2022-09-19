package io.github.mehmetozanguven.fireholdownloader.defaultConfiguration;

import io.github.mehmetozanguven.fireholdownloader.FireholLevelSetInfo;

public class DefaultFireholLevelSetInfo implements FireholLevelSetInfo {
    private String url;
    private boolean isLazyLoad;
    private String fileName;

    public DefaultFireholLevelSetInfo() {
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
