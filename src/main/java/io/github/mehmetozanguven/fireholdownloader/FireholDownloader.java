package io.github.mehmetozanguven.fireholdownloader;

import io.github.mehmetozanguven.fireholdownloader.data.FireholLevelSet;
import io.github.mehmetozanguven.fireholdownloader.data.FireholIpData;
import io.github.mehmetozanguven.fireholdownloader.defaultConfiguration.*;

import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class FireholDownloader {
    private final FireholAvailableLevelSets fireholAvailableLevelSets;
    private final FireholDirectory fireholDirectory;
    private final boolean isAlwaysLoadFromInternet;
    private final FireholHttpBuilder fireholHttpBuilder;
    private final IpUtility ipUtility;
    private final FireholResponsePreparer fireholResponsePreparer;
    private final FireholFileWriterAndReader fireholFileWriterAndReader;
    private final FireholIpSearcher fireholIpSearcher;

    private List<FireholLevelSet> availableFireholSets;


    private FireholDownloader(FireholAvailableLevelSets fireholAvailableLevelSets,
                              FireholDirectory fireholDirectory,
                              boolean isAlwaysLoadFromInternet,
                              FireholHttpBuilder fireholHttpBuilder,
                              IpUtility ipUtility,
                              FireholResponsePreparer fireholResponsePreparer,
                              FireholFileWriterAndReader fireholFileWriterAndReader,
                              FireholIpSearcher fireholIpSearcher) throws Exception {
        this.isAlwaysLoadFromInternet = isAlwaysLoadFromInternet;
        this.fireholAvailableLevelSets = fireholAvailableLevelSets;
        this.fireholDirectory = fireholDirectory;
        this.fireholHttpBuilder = fireholHttpBuilder;
        this.ipUtility = ipUtility;
        this.fireholResponsePreparer = fireholResponsePreparer;
        this.fireholFileWriterAndReader = fireholFileWriterAndReader;
        this.fireholIpSearcher = fireholIpSearcher;

        setAvailableFireholSets();
    }

    private void setAvailableFireholSets() {
        this.availableFireholSets = new ArrayList<>();
        if (isFireholNotEmpty(fireholAvailableLevelSets.getLevel1SetInfo())) {
            FireholLevelSet fireholLevelSet = new FireholLevelSet();
            fireholLevelSet.setFireholLevelSetInfo(fireholAvailableLevelSets.getLevel1SetInfo());
            this.availableFireholSets.add(fireholLevelSet);
        }

        if (isFireholNotEmpty(fireholAvailableLevelSets.getLevel2SetInfo())) {
            FireholLevelSet fireholLevelSet = new FireholLevelSet();
            fireholLevelSet.setFireholLevelSetInfo(fireholAvailableLevelSets.getLevel2SetInfo());
            this.availableFireholSets.add(fireholLevelSet);
        }

        if (isFireholNotEmpty(fireholAvailableLevelSets.getLevel3SetInfo())) {
            FireholLevelSet fireholLevelSet = new FireholLevelSet();
            fireholLevelSet.setFireholLevelSetInfo(fireholAvailableLevelSets.getLevel3SetInfo());
            this.availableFireholSets.add(fireholLevelSet);
        }

        if (isFireholNotEmpty(fireholAvailableLevelSets.getLevel4SetInfo())) {
            FireholLevelSet fireholLevelSet = new FireholLevelSet();
            fireholLevelSet.setFireholLevelSetInfo(fireholAvailableLevelSets.getLevel4SetInfo());
            this.availableFireholSets.add(fireholLevelSet);
        }
    }

    private boolean isFireholNotEmpty(FireholLevelSetInfo fireholIPSetInfo) {
        return !Objects.isNull(fireholIPSetInfo) &&
                !Objects.isNull(fireholIPSetInfo.getUrl()) &&
                !fireholIPSetInfo.getUrl().isBlank() &&
                !Objects.isNull(fireholIPSetInfo.getFileName()) &&
                !fireholIPSetInfo.getFileName().isBlank();
    }

    private List<FireholIpData> downloadFireholLevel(String url) throws Exception {
        HttpRequest fireholRequest = fireholHttpBuilder.getHttpRequestBuilder().uri(new URI(url)).build();
        HttpResponse<String> response = fireholHttpBuilder.getHttpClientBuilder().build().send(fireholRequest, HttpResponse.BodyHandlers.ofString());
        return fireholResponsePreparer.prepareFireholLevelSetFor(response.body(), ipUtility);
    }

    public void initializeDownloader() throws Exception {
        if (isAlwaysLoadFromInternet) {
            for (FireholLevelSet each : availableFireholSets) {
                List<FireholIpData> fireholList = downloadFireholLevel(each.getFireholLevelSetInfo().getUrl());
                each.setFireholList(fireholList);
            }
            return;
        }

        for (FireholLevelSet each : availableFireholSets) {
            List<FireholIpData> fireholResponse = fireholFileWriterAndReader.readFromFile(fireholDirectory, each.getFireholLevelSetInfo());
            if (fireholResponse.isEmpty()) {
                List<FireholIpData> fireholList = downloadFireholLevel(each.getFireholLevelSetInfo().getUrl());
                fireholFileWriterAndReader.writeToFile(fireholDirectory, each.getFireholLevelSetInfo(), fireholList);
                fireholResponse = fireholList;
            }
            if (!each.getFireholLevelSetInfo().isLazyLoad()) {
                each.setFireholList(fireholResponse);
            }
        }
    }

    public FireholIpData searchMaliciousIp(String ipAddressOrCidrNotation) throws Exception {
        for (FireholLevelSet each : availableFireholSets) {
            FireholIpData response;
            if (each.getFireholLevelSetInfo().isLazyLoad()) {
                List<FireholIpData> fireholResponse = fireholFileWriterAndReader.readFromFile(fireholDirectory, each.getFireholLevelSetInfo());
                response = fireholIpSearcher.searchIpInTheList(ipAddressOrCidrNotation, fireholResponse);
            } else {
                response = fireholIpSearcher.searchIpInTheList(ipAddressOrCidrNotation, each.getFireholList());
            }
            if (Objects.nonNull(response)) {
                return response;
            }
        }
        return null;
    }

    public FireholAvailableLevelSets getFireholIPSetUrl() {
        return fireholAvailableLevelSets;
    }

    public FireholDirectory getFireholDirectory() {
        return fireholDirectory;
    }

    public boolean isAlwaysLoadFromInternet() {
        return isAlwaysLoadFromInternet;
    }

    public FireholHttpBuilder getFireholHttpBuilder() {
        return fireholHttpBuilder;
    }

    public IpUtility getIpUtility() {
        return ipUtility;
    }

    public FireholResponsePreparer getFireholResponsePreparer() {
        return fireholResponsePreparer;
    }

    public FireholFileWriterAndReader getFireholFileWriterAndReader() {
        return fireholFileWriterAndReader;
    }

    public FireholIpSearcher getFireholIpSearcher() {
        return fireholIpSearcher;
    }

    public List<FireholLevelSet> getAvailableFireholSets() {
        return availableFireholSets;
    }

    public static class Builder {
        private FireholAvailableLevelSets fireholAvailableLevelSets;
        private FireholDirectory fireholDirectory;
        private boolean isAlwaysLoadFromInternet;
        private FireholHttpBuilder fireholHttpBuilder;
        private IpUtility ipUtility;
        private FireholResponsePreparer fireholResponsePreparer;
        private FireholFileWriterAndReader fireholFileWriterAndReader;
        private FireholIpSearcher fireholIpSearcher;

        public Builder() {
            this.fireholAvailableLevelSets = new DefaultFireholLevelSets();
            this.fireholDirectory = new DefaultFireholDirectory();
            this.isAlwaysLoadFromInternet = false;
            this.fireholHttpBuilder = new DefaultFireholHttpBuilder();
            this.ipUtility = new DefaultIpUtilityConfiguration();
            this.fireholResponsePreparer = new DefaultFireholResponsePreparer();
            this.fireholFileWriterAndReader = new DefaultFireholFileWriterAndReader();
            this.fireholIpSearcher = new DefaultFireholIpSearcher(ipUtility);
        }

        public Builder fireholAvailableLevelSets(FireholAvailableLevelSets fireholAvailableLevelSets) {
            this.fireholAvailableLevelSets = fireholAvailableLevelSets;
            return this;
        }

        public Builder fireholDirectory(FireholDirectory fireholDirectory) {
            this.fireholDirectory = fireholDirectory;
            return this;
        }

        public Builder alwaysLoadFromInternet(boolean isAlwaysLoadFromInternet) {
            this.isAlwaysLoadFromInternet = isAlwaysLoadFromInternet;
            return this;
        }

        public Builder httpBuilder(FireholHttpBuilder customHttpBuilder) {
            this.fireholHttpBuilder = customHttpBuilder;
            return this;
        }

        public Builder ipUtility(IpUtility customUtility) {
            this.ipUtility = customUtility;
            return this;
        }

        public Builder fireholResponsePreparer(FireholResponsePreparer customResponsePreparer) {
            this.fireholResponsePreparer = customResponsePreparer;
            return this;
        }

        public Builder fireholFileWriterAndReader(FireholFileWriterAndReader customFileWriterAndReader) {
            this.fireholFileWriterAndReader = customFileWriterAndReader;
            return this;
        }

        public Builder fileRetentionTime(Duration retentionTime) {
            this.fireholFileWriterAndReader.setFileRetentionTime(retentionTime);
            return this;
        }

        public Builder fireholIpSearchAlgorithm(FireholIpSearcher customIpSearcher) {
            this.fireholIpSearcher = customIpSearcher;
            return this;
        }

        public FireholDownloader build() throws Exception {
            return new FireholDownloader(fireholAvailableLevelSets,
                    fireholDirectory,
                    isAlwaysLoadFromInternet,
                    fireholHttpBuilder,
                    ipUtility,
                    fireholResponsePreparer,
                    fireholFileWriterAndReader,
                    fireholIpSearcher);
        }
    }
}
