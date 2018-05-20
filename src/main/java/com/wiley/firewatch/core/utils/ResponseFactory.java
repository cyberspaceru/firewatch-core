package com.wiley.firewatch.core.utils;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.handler.codec.http.*;
import lombok.experimental.UtilityClass;

import java.nio.charset.StandardCharsets;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@UtilityClass
public class ResponseFactory {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.RFC_1123_DATE_TIME;

    public static HttpResponse createHttpResponse(String serverName, HttpVersion version, HttpResponseStatus status) {
        return createHttpResponse(serverName, version, status, ContentType.plain().utf8(), status.reasonPhrase());
    }

    public static HttpResponse createHttpResponse(String serverName, HttpVersion version, HttpResponseStatus status, ContentType contentType, String content) {
        // Create content.
        byte[] bytes = content.getBytes(StandardCharsets.UTF_8);
        ByteBuf buffer = Unpooled.wrappedBuffer(bytes);
        // Create default response.
        HttpResponse response = new DefaultFullHttpResponse(version, status, buffer, false);
        // Add headers.
        DefaultHttpHeaders headers = (DefaultHttpHeaders) response.headers();
        headers.set(HttpHeaderNames.SERVER, serverName);
        headers.set(HttpHeaderNames.DATE, ZonedDateTime.now().format(FORMATTER));
        headers.set(HttpHeaderNames.CONTENT_TYPE, contentType.toString());
        headers.set(HttpHeaderNames.CONTENT_LENGTH, Integer.toString(bytes.length));
        headers.set("Access-Control-Allow-Origin", "*");
        return response;
    }
}
