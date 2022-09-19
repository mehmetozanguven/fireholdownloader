package io.github.mehmetozanguven.fireholdownloader.defaultConfiguration;

import io.github.mehmetozanguven.fireholdownloader.FireholResponsePreparer;
import io.github.mehmetozanguven.fireholdownloader.IpUtility;
import io.github.mehmetozanguven.fireholdownloader.data.FireholIpData;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DefaultFireholResponsePreparer implements FireholResponsePreparer {

    @Override
    public List<FireholIpData> prepareFireholLevelSetFor(String fireholHttpResponse, IpUtility ipUtility) {
        List<FireholIpData> fireholList = new ArrayList<>();
        if (Objects.isNull(fireholHttpResponse)) {
            return fireholList;
        }

        String[] lines = fireholHttpResponse.split("\n");
        for (String line : lines) {
            if (!isCommentLine(line)) {
                FireholIpData fireholIpData = new FireholIpData(ipUtility, line);
                fireholList.add(fireholIpData);
            }
        }
        return fireholList;
    }

    private boolean isCommentLine(String line) {
        return line.startsWith("#");
    }
}
