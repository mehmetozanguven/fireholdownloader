package com.mehmetozanguven.fireholdownloader;


import com.mehmetozanguven.fireholdownloader.data.FireholIpData;

import java.time.Duration;
import java.util.Objects;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws Exception {
        FireholDownloader fireholDownloader = new FireholDownloader
                .Builder()
                .build();
        FireholIpData fireholIpData = fireholDownloader.searchFireholIp("0.0.0.0");
        if (Objects.nonNull(fireholIpData)) {
            System.out.println(fireholIpData);
        }
    }
}
