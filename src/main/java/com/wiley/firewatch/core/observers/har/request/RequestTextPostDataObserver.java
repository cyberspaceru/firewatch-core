package com.wiley.firewatch.core.observers.har.request;

import com.wiley.firewatch.core.observers.IObserver;
import com.wiley.firewatch.core.observers.har.common.TextObserver;
import net.lightbody.bmp.core.har.HarRequest;

import java.util.function.BiPredicate;

public class RequestTextPostDataObserver extends TextObserver implements IObserver<HarRequest> {
    public RequestTextPostDataObserver(String expected, BiPredicate<String, String> predicate) {
        super(expected, predicate);
    }

    @Override
    public boolean observe(HarRequest har) {
        return observeText(har.getPostData() == null ? null : har.getPostData().getText());
    }
}
