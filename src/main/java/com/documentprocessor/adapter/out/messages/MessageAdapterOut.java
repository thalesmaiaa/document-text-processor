package com.documentprocessor.adapter.out.messages;

import com.documentprocessor.core.domain.BaseMessage;
import com.documentprocessor.core.port.out.MessageAdapterPortOut;
import io.awspring.cloud.sns.core.SnsTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MessageAdapterOut implements MessageAdapterPortOut {

    private final SnsTemplate snsTemplate;

    public void sendMessage(BaseMessage<?> message) {
        snsTemplate.convertAndSend(message.getTopic(), message.getPayload(), message.getHeaders());
    }
}
