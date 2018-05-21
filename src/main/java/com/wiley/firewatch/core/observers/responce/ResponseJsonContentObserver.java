package com.wiley.firewatch.core.observers.responce;

import com.wiley.firewatch.core.observers.IObserver;
import com.wiley.firewatch.core.observers.common.JsonObserver;
import net.lightbody.bmp.core.har.HarResponse;

import java.util.function.BiPredicate;

public class ResponseJsonContentObserver<K> extends JsonObserver<K> implements IObserver<HarResponse> {
    public ResponseJsonContentObserver(Class<K> objectClass, K instance, BiPredicate<K, K> predicate) {
        super(objectClass, instance, predicate);
    }

    @Override
    public boolean observe(HarResponse har) {
        return observeJson(har.getContent() == null ? null : har.getContent().getText());
    }
}
