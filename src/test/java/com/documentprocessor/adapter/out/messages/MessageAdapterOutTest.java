package com.documentprocessor.adapter.out.messages;

import com.documentprocessor.core.domain.BaseMessage;
import io.awspring.cloud.sns.core.SnsTemplate;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.mockito.Mockito.*;

class MessageAdapterOutTest {
    @Test
    void shouldSendMessageToSns() {
        var snsTemplate = mock(SnsTemplate.class);
        var message = mock(BaseMessage.class);
        when(message.getTopic()).thenReturn("topic");
        when(message.getPayload()).thenReturn("payload");
        when(message.getHeaders()).thenReturn(null);
        var adapter = new MessageAdapterOut(snsTemplate);
        assertThatCode(() -> adapter.sendMessage(message)).doesNotThrowAnyException();
        verify(snsTemplate).convertAndSend("topic", "payload", (Map<String, Object>) null);
    }
}
