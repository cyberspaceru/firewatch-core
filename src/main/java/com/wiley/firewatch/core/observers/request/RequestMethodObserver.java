package com.wiley.firewatch.core.observers.request;

import com.wiley.firewatch.core.observers.IObserver;
import io.netty.handler.codec.http.HttpMethod;
import net.lightbody.bmp.core.har.HarRequest;

public class RequestMethodObserver implements IObserver<HarRequest> {
    private HttpMethod method;

    public RequestMethodObserver(HttpMethod method) {
        this.method = method;
    }

    @Override
    public boolean observe(HarRequest har) {
        return har.getMethod().equalsIgnoreCase(method.name());
    }

    @Override
    public String toString() {
        return "Method(" + method.name() + ")";
    }
}
