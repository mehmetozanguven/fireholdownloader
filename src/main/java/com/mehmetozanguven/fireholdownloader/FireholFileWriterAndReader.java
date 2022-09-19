package com.mehmetozanguven.fireholdownloader;

import com.mehmetozanguven.fireholdownloader.data.FireholIpData;
import com.mehmetozanguven.fireholdownloader.defaultConfiguration.DefaultFireholFileWriterAndReader;

import java.time.Duration;
import java.util.List;

/**
 * @see DefaultFireholFileWriterAndReader for the default implementation
 */
public interface FireholFileWriterAndReader {

    Duration getFileRetentionTime();

    void setFileRetentionTime(Duration fileRetentionTime);

    void writeToFile(FireholDirectory fireholDirectory, FireholLevelSetInfo fireholIPSetInfo, List<FireholIpData> fireholResponse) throws Exception;

    List<FireholIpData> readFromFile(FireholDirectory fireholDirectory, FireholLevelSetInfo fireholIPSetInfo) throws Exception;

}
