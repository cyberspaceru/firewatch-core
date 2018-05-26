package com.wiley.firewatch.core.observers.har.request;

import com.wiley.firewatch.core.observers.IObserver;
import com.wiley.firewatch.core.utils.MatchingType;
import com.wiley.firewatch.core.utils.StringMatcher;
import net.lightbody.bmp.core.har.HarRequest;

public class RequestUrlObserver implements IObserver<HarRequest> {
    private final MatchingType type;
    private final String url;

    public RequestUrlObserver(MatchingType type, String url) {
        this.type = type;
        this.url = url;
    }

    @Override
    public boolean observe(HarRequest har) {
        return StringMatcher.match(har.getUrl(), type, url);
    }

    @Override
    public String toString() {
        return "URL(matching='" + type + "', url='" + url + "')";
    }
}
