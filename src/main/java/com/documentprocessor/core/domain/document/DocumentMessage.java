package com.documentprocessor.core.domain.document;

import com.documentprocessor.core.domain.BaseMessage;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.messaging.MessageHeaders;

import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DocumentMessage extends BaseMessage<Document> {

    private Document document;

    @Override
    public String getTopic() {
        return "process_document";
    }

    @Override
    public Document getPayload() {
        return this.document;
    }

    @Override
    public MessageHeaders getHeaders() {
        return new MessageHeaders(Map.of());
    }
}