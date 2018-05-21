package com.wiley.firewatch.core.observers.request;

import com.wiley.firewatch.core.observers.IObserver;
import com.wiley.firewatch.core.observers.common.JsonObserver;
import net.lightbody.bmp.core.har.HarRequest;

import java.util.function.BiPredicate;

public class RequestJsonPostDataObserver<K> extends JsonObserver<K> implements IObserver<HarRequest> {
    public RequestJsonPostDataObserver(Class<K> objectClass, K instance, BiPredicate<K, K> predicate) {
        super(objectClass, instance, predicate);
    }

    @Override
    public boolean observe(HarRequest har) {
        return observeJson(har.getPostData() == null ? null : har.getPostData().getText());
    }
}
