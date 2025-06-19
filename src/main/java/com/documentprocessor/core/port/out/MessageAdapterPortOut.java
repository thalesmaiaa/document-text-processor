package com.documentprocessor.core.port.out;

import com.documentprocessor.core.domain.BaseMessage;

public interface MessageAdapterPortOut {

    void sendMessage(BaseMessage<?> message);
}
