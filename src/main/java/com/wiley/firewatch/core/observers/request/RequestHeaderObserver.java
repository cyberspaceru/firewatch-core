package com.wiley.firewatch.core.observers.request;

import com.wiley.firewatch.core.observers.IObserver;
import com.wiley.firewatch.core.utils.MatchingType;
import com.wiley.firewatch.core.utils.StringMatcher;
import net.lightbody.bmp.core.har.HarNameValuePair;
import net.lightbody.bmp.core.har.HarRequest;

public class RequestHeaderObserver implements IObserver<HarRequest> {
    private final StringMatcher nameMatcher;
    private final StringMatcher valueMatcher;

    public RequestHeaderObserver(MatchingType nameMatchingType, String name, MatchingType valueMatchingType, String value) {
        this.nameMatcher = StringMatcher.create(nameMatchingType, name);
        this.valueMatcher = StringMatcher.create(valueMatchingType, value);
    }

    @Override
    public boolean observe(HarRequest har) {
        String actual = null;
        for (HarNameValuePair pair : har.getHeaders()) {
            if (nameMatcher.match(pair.getName())) {
                actual = pair.getValue();
                break;
            }
        }
        return valueMatcher.match(actual);
    }

    @Override
    public String toString() {
        return String.format("Header(nameMatcher='%s', valueMatcher='%s')", nameMatcher, valueMatcher);
    }
}
