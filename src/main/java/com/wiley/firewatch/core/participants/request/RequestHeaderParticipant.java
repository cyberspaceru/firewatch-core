package com.wiley.firewatch.core.participants.request;

import com.wiley.firewatch.core.participants.IParticipant;
import com.wiley.firewatch.core.utils.StringMatcher;
import net.lightbody.bmp.core.har.HarRequest;

public class RequestHeaderParticipant implements IParticipant<HarRequest> {
    private final StringMatcher nameMatcher;
    private final StringMatcher valueMatcher;
    private final String replacement;

    public RequestHeaderParticipant(StringMatcher nameMatcher, StringMatcher valueMatcher, String replacement) {
        this.nameMatcher = nameMatcher;
        this.valueMatcher = valueMatcher;
        this.replacement = replacement;
    }

    @Override
    public void participate(HarRequest har) {
    }
}
