package com.mehmetozanguven.fireholdownloader;

import com.mehmetozanguven.fireholdownloader.defaultConfiguration.DefaultFireholIPSetInfoInfo;

public class TestUtils {

    public static FireholAvailableLevelSets zeroIPSet() {
        return new FireholAvailableLevelSets() {
            @Override
            public FireholLevelSetInfo getLevel1SetInfo() {
                return null;
            }

            @Override
            public FireholLevelSetInfo getLevel2SetInfo() {
                return null;
            }

            @Override
            public FireholLevelSetInfo getLevel3SetInfo() {
                DefaultFireholIPSetInfoInfo level = new DefaultFireholIPSetInfoInfo();
                level.setUrl("https://raw.githubusercontent.com/ktsaou/blocklist-ipsets/master/firehol_level3.netset");
                level.setLazyLoad(false);
                level.setFileName("");
                return level;
            }

            @Override
            public FireholLevelSetInfo getLevel4SetInfo() {
                DefaultFireholIPSetInfoInfo level = new DefaultFireholIPSetInfoInfo();
                level.setUrl("");
                level.setLazyLoad(false);
                level.setFileName("firehol_level4.fll");
                return level;
            }
        };
    }
}
