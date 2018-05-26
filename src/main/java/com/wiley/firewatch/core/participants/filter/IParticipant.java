package com.wiley.firewatch.core.participants.filter;

import net.lightbody.bmp.util.HttpMessageContents;
import net.lightbody.bmp.util.HttpMessageInfo;

public interface IParticipant<T> {
    void participate(T request, HttpMessageContents contents, HttpMessageInfo info);
}
