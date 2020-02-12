package com.huyibo.springlogstash.config;

import ch.qos.logback.classic.pattern.ClassicConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;
import org.apache.commons.lang3.StringUtils;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by huyibo on 2020/2/11.
 */
public class LogIpConfig extends ClassicConverter {

    private volatile static String appIp;

    @Override
    public String convert(ILoggingEvent iLoggingEvent) {
        if (StringUtils.isBlank(appIp))
            try {
                appIp =  InetAddress.getLocalHost().getHostAddress();
            } catch (UnknownHostException e) {
                appIp = "0.0.0.0";
            }
        return appIp;
    }
}
