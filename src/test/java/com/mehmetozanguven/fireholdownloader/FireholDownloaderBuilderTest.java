package com.mehmetozanguven.fireholdownloader;

import com.mehmetozanguven.fireholdownloader.defaultConfiguration.DefaultFireholHttpBuilder;
import com.mehmetozanguven.fireholdownloader.defaultConfiguration.DefaultFireholLevelSetInfo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.net.URI;
import java.net.http.HttpRequest;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.ThreadLocalRandom;


class FireholDownloaderBuilderTest {
    @Test
    public void builder_checkDefaultBuilderValues() throws Exception {
        FireholDownloader fireholDownloader = new FireholDownloader
                .Builder()
                .build();
        Assertions.assertEquals(4, fireholDownloader.getAvailableFireholSets().size());
        Assertions.assertFalse(fireholDownloader.isAlwaysLoadFromInternet());
        Assertions.assertTrue(fireholDownloader.getFireholDirectory().createDirectoryIfNotExists());
        Assertions.assertEquals(Duration.of(1, ChronoUnit.DAYS), fireholDownloader.getFireholFileWriterAndReader().getFileRetentionTime());

        HttpRequest.Builder requestBuilder = fireholDownloader.getFireholHttpBuilder().getHttpRequestBuilder();
        requestBuilder.uri(new URI("https://raw.githubusercontent.com/ktsaou/blocklist-ipsets/master/firehol_level2.netset"));

        Assertions.assertNotNull(requestBuilder.build().timeout());
        Duration timeout = requestBuilder.build().timeout().get();
        Assertions.assertEquals(DefaultFireholHttpBuilder.TIMEOUT, timeout);
    }

    @Test
    public void builder_ShouldMatchWithLevelSet_WhenItIsCustomized() throws Exception {
        FireholDownloader fireholDownloader = new FireholDownloader
                .Builder()
                .fireholIpSetUrlAddress(TestUtils.emptyLevelSet())
                .build();
        Assertions.assertTrue(fireholDownloader.getAvailableFireholSets().isEmpty());
    }

    @Test
    public void builder_ShouldMatchWithFields_WhenFieldsAreChanged() throws Exception {
        boolean randomBoolean = ThreadLocalRandom.current().nextBoolean();
        String directory = "/test";
        FireholDirectory customizedDirectory = new FireholDirectory() {
            @Override
            public boolean createDirectoryIfNotExists() {
                return randomBoolean;
            }

            @Override
            public String getDirectory() {
                return directory;
            }
        };

        FireholAvailableLevelSets customizedIpSetUrls = new FireholAvailableLevelSets() {
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
                return null;
            }

            @Override
            public FireholLevelSetInfo getLevel4SetInfo() {
                DefaultFireholLevelSetInfo level = new DefaultFireholLevelSetInfo();
                level.setUrl("https://raw.githubusercontent.com/ktsaou/blocklist-ipsets/master/firehol_level4.netset");
                level.setLazyLoad(false);
                level.setFileName("firehol_level4.fll");
                return level;
            }
        };

        Duration fileRetentionTime = Duration.of(3, ChronoUnit.DAYS);
        Duration customizedTimeout = Duration.of(25, ChronoUnit.SECONDS);
        FireholDownloader fireholDownloader = new FireholDownloader
                .Builder()
                .alwaysLoadFromInternet(randomBoolean)
                .fileRetentionTime(fileRetentionTime)
                .fireholDirectory(customizedDirectory)
                .fireholIpSetUrlAddress(customizedIpSetUrls)
                .httpClient(TestUtils.customizedRequestTimeout(customizedTimeout))
                .build();

        Assertions.assertEquals(randomBoolean, fireholDownloader.isAlwaysLoadFromInternet());
        Assertions.assertEquals(fileRetentionTime, fireholDownloader.getFireholFileWriterAndReader().getFileRetentionTime());
        Assertions.assertEquals(directory, fireholDownloader.getFireholDirectory().getDirectory());
        Assertions.assertEquals(randomBoolean, fireholDownloader.getFireholDirectory().createDirectoryIfNotExists());
        Assertions.assertEquals(1, fireholDownloader.getAvailableFireholSets().size());

        HttpRequest.Builder requestBuilder = fireholDownloader.getFireholHttpBuilder().getHttpRequestBuilder();
        requestBuilder.uri(new URI("https://raw.githubusercontent.com/ktsaou/blocklist-ipsets/master/firehol_level2.netset"));

        Duration timeout = requestBuilder.build().timeout().get();
        Assertions.assertEquals(customizedTimeout, timeout);
    }
}
