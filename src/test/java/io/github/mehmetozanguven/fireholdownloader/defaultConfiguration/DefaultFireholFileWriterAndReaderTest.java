package io.github.mehmetozanguven.fireholdownloader.defaultConfiguration;

import io.github.mehmetozanguven.fireholdownloader.FireholDirectory;
import io.github.mehmetozanguven.fireholdownloader.FireholFileWriterAndReader;
import io.github.mehmetozanguven.fireholdownloader.data.FireholIpData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

class DefaultFireholFileWriterAndReaderTest {
    private FireholFileWriterAndReader fireholFileWriterAndReader;

    @BeforeEach
    public void setup() {
        fireholFileWriterAndReader = new DefaultFireholFileWriterAndReader();
    }

    @Test
    void writeToFile_ShouldThrowException_WhenDirectoryNotExistsAndOptionIsFalse() {
        FireholDirectory fireholDirectory = new FireholDirectory() {
            @Override
            public boolean createDirectoryIfNotExists() {
                return false;
            }

            @Override
            public String getDirectory() {
                return "/tmp";
            }
        };
        DefaultFireholLevelSetInfo ipSetInfo = new DefaultFireholLevelSetInfo();
        ipSetInfo.setFileName(UUID.randomUUID().toString());
        Assertions.assertThrows(RuntimeException.class, () -> fireholFileWriterAndReader.writeToFile(fireholDirectory, ipSetInfo, new ArrayList<>()), "Path must be exists");
    }

    @Test
    void readFromFile_ShouldReturnEmptyList_WhenPathNotExists() throws Exception {
        FireholDirectory defaultFireholDirectory = new DefaultFireholDirectory();
        DefaultFireholLevelSetInfo ipSetInfo = new DefaultFireholLevelSetInfo();
        ipSetInfo.setFileName(UUID.randomUUID().toString());
        List<FireholIpData> response = fireholFileWriterAndReader.readFromFile(defaultFireholDirectory, ipSetInfo);
        Assertions.assertTrue(response.isEmpty());
    }

    @Test
    void readFromFile_ShouldReturnList_WhenFileDateIsNotOutdatedWithUnmodifiableList() throws Exception {
        List<FireholIpData> dummyData = List.of(
                new FireholIpData(new DefaultIpUtilityConfiguration(), "0.0.0.0")
        );
        FireholDirectory fireholDirectory = new DefaultFireholDirectory();
        DefaultFireholLevelSetInfo ipSetInfo = new DefaultFireholLevelSetInfo();
        ipSetInfo.setFileName("test.fll");
        fireholFileWriterAndReader.writeToFile(fireholDirectory, ipSetInfo, dummyData);
        List<FireholIpData> response = fireholFileWriterAndReader.readFromFile(fireholDirectory, ipSetInfo);
        Assertions.assertFalse(response.isEmpty());
        Assertions.assertEquals(dummyData.size(), response.size());
        Assertions.assertEquals("0.0.0.0", response.get(0).getIpAddress());
    }

    @Test
    void readFromFile_ShouldReturnList_WhenFileDateIsNotOutdated() throws Exception {
        List<FireholIpData> dummyData = new ArrayList<>();
        dummyData.add(new FireholIpData(new DefaultIpUtilityConfiguration(), "0.0.0.0"));

        FireholDirectory fireholDirectory = new DefaultFireholDirectory();
        DefaultFireholLevelSetInfo ipSetInfo = new DefaultFireholLevelSetInfo();
        ipSetInfo.setFileName("test.fll");
        fireholFileWriterAndReader.writeToFile(fireholDirectory, ipSetInfo, dummyData);
        List<FireholIpData> response = fireholFileWriterAndReader.readFromFile(fireholDirectory, ipSetInfo);
        Assertions.assertFalse(response.isEmpty());
        Assertions.assertEquals(dummyData.size(), response.size());
        Assertions.assertEquals("0.0.0.0", response.get(0).getIpAddress());
    }

    @Test
    void readFromFile_ShouldReturnEmptyList_WhenFileDateIsOutdated() throws Exception {
        List<FireholIpData> dummyData = new ArrayList<>();
        dummyData.add(new FireholIpData(new DefaultIpUtilityConfiguration(), "0.0.0.0"));
        Duration retentionTime = Duration.of(500, ChronoUnit.MILLIS);

        fireholFileWriterAndReader.setFileRetentionTime(retentionTime);
        FireholDirectory fireholDirectory = new DefaultFireholDirectory();
        DefaultFireholLevelSetInfo ipSetInfo = new DefaultFireholLevelSetInfo();
        ipSetInfo.setFileName("test.fll");

        fireholFileWriterAndReader.writeToFile(fireholDirectory, ipSetInfo, dummyData);
        Thread.sleep(retentionTime.toMillis() + 200);
        List<FireholIpData> response = fireholFileWriterAndReader.readFromFile(fireholDirectory, ipSetInfo);
        Assertions.assertTrue(response.isEmpty());
    }
}