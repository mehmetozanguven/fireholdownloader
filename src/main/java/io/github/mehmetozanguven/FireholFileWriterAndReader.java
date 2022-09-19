package io.github.mehmetozanguven;

import io.github.mehmetozanguven.defaultConfiguration.DefaultFireholFileWriterAndReader;
import io.github.mehmetozanguven.data.FireholIpData;

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
