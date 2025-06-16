package com.documentprocessor.core.domain;

import org.springframework.messaging.Message;

public abstract class BaseMessage {

    public abstract String getTopic();

    public abstract Message<?> getMessage();
}
