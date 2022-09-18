package com.mehmetozanguven.fireholdownloader;

import com.mehmetozanguven.fireholdownloader.defaultConfiguration.DefaultFireholLevelSetInfo;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.time.Duration;

public class TestUtils {
    public static final String FIREHOL_BLOCKED_IP = "0.0.0.0";
    public static FireholHttpBuilder customizedRequestTimeout(Duration timeout) {
        return new FireholHttpBuilder() {
            @Override
            public HttpClient.Builder getHttpClientBuilder() {
                return HttpClient.newBuilder();
            }

            @Override
            public HttpRequest.Builder getHttpRequestBuilder() {
                return HttpRequest.newBuilder().timeout(timeout).GET();
            }
        };
    }

    public static FireholDirectory fireholDirectory(String directoryName) {
        return new FireholDirectory() {
            @Override
            public boolean createDirectoryIfNotExists() {
                return true;
            }

            @Override
            public String getDirectory() {
                return directoryName;
            }
        };
    }

    public static FireholAvailableLevelSets levelSetsWithoutLevel1(boolean isLazyLoad) {
        return new FireholAvailableLevelSets() {
            @Override
            public FireholLevelSetInfo getLevel1SetInfo() {
                return null;
            }

            @Override
            public FireholLevelSetInfo getLevel2SetInfo() {
                DefaultFireholLevelSetInfo level = new DefaultFireholLevelSetInfo();
                level.setUrl("https://raw.githubusercontent.com/ktsaou/blocklist-ipsets/master/firehol_level2.netset");
                level.setLazyLoad(isLazyLoad);
                level.setFileName("firehol_level2.fll");
                return level;
            }

            @Override
            public FireholLevelSetInfo getLevel3SetInfo() {
                DefaultFireholLevelSetInfo level = new DefaultFireholLevelSetInfo();
                level.setUrl("https://raw.githubusercontent.com/ktsaou/blocklist-ipsets/master/firehol_level3.netset");
                level.setLazyLoad(isLazyLoad);
                level.setFileName("firehol_level3.fll");
                return level;
            }

            @Override
            public FireholLevelSetInfo getLevel4SetInfo() {
                DefaultFireholLevelSetInfo level = new DefaultFireholLevelSetInfo();
                level.setUrl("https://raw.githubusercontent.com/ktsaou/blocklist-ipsets/master/firehol_level4.netset");
                level.setLazyLoad(isLazyLoad);
                level.setFileName("firehol_level4.fll");
                return level;
            }
        };
    }

    public static FireholAvailableLevelSets allLevelSetWithLazyLoad(boolean isLazyLoad) {
        return new FireholAvailableLevelSets() {
            @Override
            public FireholLevelSetInfo getLevel1SetInfo() {
                DefaultFireholLevelSetInfo level = new DefaultFireholLevelSetInfo();
                level.setUrl("https://raw.githubusercontent.com/ktsaou/blocklist-ipsets/master/firehol_level1.netset");
                level.setLazyLoad(isLazyLoad);
                level.setFileName("firehol_level1.fll");
                return level;
            }

            @Override
            public FireholLevelSetInfo getLevel2SetInfo() {
                DefaultFireholLevelSetInfo level = new DefaultFireholLevelSetInfo();
                level.setUrl("https://raw.githubusercontent.com/ktsaou/blocklist-ipsets/master/firehol_level2.netset");
                level.setLazyLoad(isLazyLoad);
                level.setFileName("firehol_level2.fll");
                return level;
            }

            @Override
            public FireholLevelSetInfo getLevel3SetInfo() {
                DefaultFireholLevelSetInfo level = new DefaultFireholLevelSetInfo();
                level.setUrl("https://raw.githubusercontent.com/ktsaou/blocklist-ipsets/master/firehol_level3.netset");
                level.setLazyLoad(isLazyLoad);
                level.setFileName("firehol_level3.fll");
                return level;
            }

            @Override
            public FireholLevelSetInfo getLevel4SetInfo() {
                DefaultFireholLevelSetInfo level = new DefaultFireholLevelSetInfo();
                level.setUrl("https://raw.githubusercontent.com/ktsaou/blocklist-ipsets/master/firehol_level4.netset");
                level.setLazyLoad(isLazyLoad);
                level.setFileName("firehol_level4.fll");
                return level;
            }
        };
    }

    public static FireholAvailableLevelSets emptyLevelSet() {
        return new FireholAvailableLevelSets() {
            @Override
            public FireholLevelSetInfo getLevel1SetInfo() {
                return null;
            }

            @Override
            public FireholLevelSetInfo getLevel2SetInfo() {
                return null;
            }

            @Override
            public FireholLevelSetInfo getLevel3SetInfo() {
                DefaultFireholLevelSetInfo level = new DefaultFireholLevelSetInfo();
                level.setUrl("https://raw.githubusercontent.com/ktsaou/blocklist-ipsets/master/firehol_level3.netset");
                level.setLazyLoad(false);
                level.setFileName("");
                return level;
            }

            @Override
            public FireholLevelSetInfo getLevel4SetInfo() {
                DefaultFireholLevelSetInfo level = new DefaultFireholLevelSetInfo();
                level.setUrl("");
                level.setLazyLoad(false);
                level.setFileName("firehol_level4.fll");
                return level;
            }
        };
    }
}
