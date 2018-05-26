package com.wiley.firewatch.core.observers.har.responce;

import com.wiley.firewatch.core.observers.IObserver;
import com.wiley.firewatch.core.observers.har.common.TextObserver;
import net.lightbody.bmp.core.har.HarResponse;

import java.util.function.BiPredicate;

public class ResponseTextContentObserver extends TextObserver implements IObserver<HarResponse> {
    public ResponseTextContentObserver(String expected, BiPredicate<String, String> predicate) {
        super(expected, predicate);
    }

    @Override
    public boolean observe(HarResponse har) {
        return observeText(har.getContent() == null ? null : har.getContent().getText());
    }
}
