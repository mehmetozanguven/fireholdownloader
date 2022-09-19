package io.github.mehmetozanguven.fireholdownloader.data;

import io.github.mehmetozanguven.fireholdownloader.FireholLevelSetInfo;

import java.util.ArrayList;
import java.util.List;

public class FireholLevelSet {
    private FireholLevelSetInfo fireholLevelSetInfo;
    private List<FireholIpData> fireholList;

    public FireholLevelSet() {
        this.fireholList = new ArrayList<>();
    }

    public FireholLevelSetInfo getFireholLevelSetInfo() {
        return fireholLevelSetInfo;
    }

    public void setFireholLevelSetInfo(FireholLevelSetInfo fireholLevelSetInfo) {
        this.fireholLevelSetInfo = fireholLevelSetInfo;
    }

    public List<FireholIpData> getFireholList() {
        return fireholList;
    }

    public void setFireholList(List<FireholIpData> fireholList) {
        this.fireholList = fireholList;
    }
}
