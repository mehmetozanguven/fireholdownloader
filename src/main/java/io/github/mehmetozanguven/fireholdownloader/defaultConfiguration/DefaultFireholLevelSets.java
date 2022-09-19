package io.github.mehmetozanguven.fireholdownloader.defaultConfiguration;

import io.github.mehmetozanguven.fireholdownloader.FireholAvailableLevelSets;
import io.github.mehmetozanguven.fireholdownloader.FireholLevelSetInfo;


public class DefaultFireholLevelSets implements FireholAvailableLevelSets {

    @Override
    public FireholLevelSetInfo getLevel1SetInfo() {
        DefaultFireholLevelSetInfo level = new DefaultFireholLevelSetInfo();
        level.setUrl("https://raw.githubusercontent.com/ktsaou/blocklist-ipsets/master/firehol_level1.netset");
        level.setLazyLoad(false);
        level.setFileName("firehol_level1.fll");
        return level;
    }

    @Override
    public FireholLevelSetInfo getLevel2SetInfo() {
        DefaultFireholLevelSetInfo level = new DefaultFireholLevelSetInfo();
        level.setUrl("https://raw.githubusercontent.com/ktsaou/blocklist-ipsets/master/firehol_level2.netset");
        level.setLazyLoad(false);
        level.setFileName("firehol_level2.fll");
        return level;
    }

    @Override
    public FireholLevelSetInfo getLevel3SetInfo() {
        DefaultFireholLevelSetInfo level = new DefaultFireholLevelSetInfo();
        level.setUrl("https://raw.githubusercontent.com/ktsaou/blocklist-ipsets/master/firehol_level3.netset");
        level.setLazyLoad(false);
        level.setFileName("firehol_level3.fll");
        return level;
    }

    @Override
    public FireholLevelSetInfo getLevel4SetInfo() {
        DefaultFireholLevelSetInfo level = new DefaultFireholLevelSetInfo();
        level.setUrl("https://raw.githubusercontent.com/ktsaou/blocklist-ipsets/master/firehol_level4.netset");
        level.setLazyLoad(false);
        level.setFileName("firehol_level4.fll");
        return level;
    }
}
