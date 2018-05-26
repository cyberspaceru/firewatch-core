package com.wiley.firewatch.core.participants.filter.request;

import com.wiley.firewatch.core.participants.filter.IParticipant;
import com.wiley.firewatch.core.utils.StringMatcher;
import com.wiley.firewatch.core.utils.StringReplacer;
import io.netty.handler.codec.http.HttpRequest;
import net.lightbody.bmp.util.HttpMessageContents;
import net.lightbody.bmp.util.HttpMessageInfo;

public class RequestHeaderParticipant implements IParticipant<HttpRequest> {
    private final StringMatcher nameMatcher;
    private final StringReplacer valueReplacer;

    public RequestHeaderParticipant(StringMatcher nameMatcher, StringReplacer valueReplacer) {
        this.nameMatcher = nameMatcher;
        this.valueReplacer = valueReplacer;
    }

    public RequestHeaderParticipant(StringMatcher nameMatcher, StringMatcher valueMatcher, String replacement) {
        this.nameMatcher = nameMatcher;
        this.valueReplacer = StringReplacer.create(valueMatcher, replacement);
    }

    @Override
    public void participate(HttpRequest request, HttpMessageContents contents, HttpMessageInfo info) {
    }
}
