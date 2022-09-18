package com.mehmetozanguven.fireholdownloader.defaultConfiguration;

import com.mehmetozanguven.fireholdownloader.FireholResponsePreparer;
import com.mehmetozanguven.fireholdownloader.IpUtility;
import com.mehmetozanguven.fireholdownloader.data.FireholIpData;

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
