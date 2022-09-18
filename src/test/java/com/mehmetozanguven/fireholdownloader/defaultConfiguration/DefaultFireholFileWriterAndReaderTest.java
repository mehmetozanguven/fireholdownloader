package com.mehmetozanguven.fireholdownloader.defaultConfiguration;

import com.mehmetozanguven.fireholdownloader.FireholDirectory;
import com.mehmetozanguven.fireholdownloader.FireholFileWriterAndReader;
import com.mehmetozanguven.fireholdownloader.data.FireholIpData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
}