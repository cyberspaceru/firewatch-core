package com.wiley.firewatch.core.observers.har.responce;

import com.wiley.firewatch.core.observers.IObserver;
import net.lightbody.bmp.core.har.HarResponse;

public class ResponseStatusObserver implements IObserver<HarResponse> {
    private int status;

    public ResponseStatusObserver(int status) {
        this.status = status;
    }

    @Override
    public boolean observe(HarResponse har) {
        return har.getStatus() == status;
    }

    @Override
    public String toString() {
        return "Status(" + status + ")";
    }
}
