package com.wiley.firewatch.core;

import com.wiley.firewatch.core.exceptions.FirewatchConnectionUnavailableException;
import lombok.Getter;
import lombok.experimental.Accessors;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import net.lightbody.bmp.BrowserMobProxy;
import net.lightbody.bmp.BrowserMobProxyServer;

import static java.util.UUID.randomUUID;
import static net.lightbody.bmp.proxy.CaptureType.*;

/**
 * Created by itatsiy on 4/18/2018.
 */
@Slf4j
@UtilityClass
@Accessors(fluent = true)
public class FirewatchConnection {
    @Getter
    private static BrowserMobProxy proxyServer;

    public static boolean create() {
        return !isAvailable() && connect();
    }

    public static boolean isAvailable() {
        return proxyServer != null && proxyServer.isStarted();
    }

    public static void newHar() {
        if (isAvailable()) {
            if (proxyServer().getHar() != null) {
                log.info("End har.");
                proxyServer().endHar();
            }
            String initialPageRefName = randomUUID().toString();
            log.info("New har '{}'.", initialPageRefName);
            proxyServer().newHar(initialPageRefName);
        } else {
            throw new FirewatchConnectionUnavailableException();
        }
    }

    private static boolean connect() {
        try {
            log.info("Creating proxy connection...");
            BrowserMobProxy proxyServer = new BrowserMobProxyServer();
            proxyServer.setTrustAllServers(true);
            proxyServer.enableHarCaptureTypes(REQUEST_HEADERS, REQUEST_CONTENT, REQUEST_BINARY_CONTENT, RESPONSE_HEADERS, RESPONSE_CONTENT, RESPONSE_BINARY_CONTENT);
            proxyServer.start();
            FirewatchConnection.proxyServer = proxyServer;
            return true;
        } catch (Exception e) {
            log.error("Proxy Connection have not created. See log for details.", e);
        }
        return false;
    }
}
