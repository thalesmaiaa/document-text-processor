package com.documentprocessor.core.domain;

import org.springframework.messaging.MessageHeaders;

public abstract class BaseMessage<T> {

    public abstract String getTopic();

    public abstract T getPayload();

    public abstract MessageHeaders getHeaders();

}
