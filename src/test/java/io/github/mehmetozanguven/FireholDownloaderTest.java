package io.github.mehmetozanguven;

import io.github.mehmetozanguven.data.FireholIpData;
import io.github.mehmetozanguven.fireholDownloader.FireholDownloader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class FireholDownloaderTest {

    @Test
    void fireholDownloader_ShouldFindFireholIp_WithDefaultSetup() throws Exception {
        FireholDownloader fireholDownloader = new FireholDownloader.Builder().build();
        fireholDownloader.initializeDownloader();
        FireholIpData fireholIpData = fireholDownloader.searchMaliciousIp(TestUtils.FIREHOL_BLOCKED_IP);
        Assertions.assertNotNull(fireholIpData);
    }

    @Test
    void searchFireholIp_ShouldReturnNull_WhenIpNotFoundInFirehol() throws Exception {
        FireholDownloader fireholDownloader = new FireholDownloader.Builder()
                .fireholDirectory(TestUtils.fireholDirectory("/tmp"))
                .fireholAvailableLevelSets(TestUtils.levelSetsWithoutLevel1(true))
                .build();
        fireholDownloader.initializeDownloader();
        FireholIpData fireholIpData = fireholDownloader.searchMaliciousIp(TestUtils.FIREHOL_BLOCKED_IP);
        Assertions.assertNull(fireholIpData);
    }
}