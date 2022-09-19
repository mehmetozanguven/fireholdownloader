package io.github.mehmetozanguven.defaultConfiguration;

import io.github.mehmetozanguven.FireholDirectory;
import io.github.mehmetozanguven.FireholFileWriterAndReader;
import io.github.mehmetozanguven.data.FireholIpData;
import io.github.mehmetozanguven.FireholLevelSetInfo;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class DefaultFireholFileWriterAndReader implements FireholFileWriterAndReader {
    private Duration fileRetentionTime;

    public DefaultFireholFileWriterAndReader() {
        this.fileRetentionTime = Duration.of(1, ChronoUnit.DAYS);
    }

    private boolean shouldRewriteFile(Path file) throws Exception {
        if (!Files.exists(file)) {
            return true;
        }
        BasicFileAttributes attr = Files.readAttributes(file, BasicFileAttributes.class);
        FileTime fileTime = attr.creationTime();
        LocalDateTime creationDate = LocalDateTime.ofInstant(fileTime.toInstant(), ZoneId.systemDefault());
        Duration duration = Duration.between(creationDate, LocalDateTime.now());
        return duration.toMillis() > fileRetentionTime.toMillis();
    }

    @Override
    public void writeToFile(FireholDirectory fireholDirectory, FireholLevelSetInfo fireholIPSetInfo, List<FireholIpData> fireholResponse) throws Exception {
        Path directoryPath = Path.of(fireholDirectory.getDirectory());
        if (!fireholDirectory.createDirectoryIfNotExists() && Files.exists(directoryPath)) {
            throw new RuntimeException("Path must be exists");
        }
        Files.createDirectories(directoryPath);
        Path filePath = directoryPath.resolve(Path.of(fireholIPSetInfo.getFileName()));
        try (FileOutputStream writeData = new FileOutputStream(filePath.toString())) {
            ObjectOutputStream writeStream = new ObjectOutputStream(writeData);
            writeStream.writeObject(fireholResponse);
            writeStream.flush();
        }
    }

    @Override
    public List<FireholIpData> readFromFile(FireholDirectory fireholDirectory, FireholLevelSetInfo fireholIPSetInfo) throws Exception {
        Path directoryPath = Path.of(fireholDirectory.getDirectory());
        Path filePath = directoryPath.resolve(Path.of(fireholIPSetInfo.getFileName()));

        if (!Files.exists(filePath)) {
            return new ArrayList<>();
        }
        if (shouldRewriteFile(filePath)) {
            return new ArrayList<>();
        }

        try (FileInputStream readData = new FileInputStream(filePath.toString())) {
            ObjectInputStream readStream = new ObjectInputStream(readData);
            return (List<FireholIpData>) readStream.readObject();
        }
    }

    @Override
    public Duration getFileRetentionTime() {
        return this.fileRetentionTime;
    }

    @Override
    public void setFileRetentionTime(Duration fileRetentionTime) {
        this.fileRetentionTime = fileRetentionTime;
    }
}
