package com.mehmetozanguven.fireholdownloader;

import com.mehmetozanguven.fireholdownloader.defaultConfiguration.DefaultFireholLevelSetInfo;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.time.Duration;

public class TestUtils {
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
