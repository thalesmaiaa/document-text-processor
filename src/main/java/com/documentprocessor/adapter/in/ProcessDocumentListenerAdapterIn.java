package com.documentprocessor.adapter.in;

import com.documentprocessor.adapter.in.dto.ProcessDocumentMessage;
import com.documentprocessor.core.mappers.DocumentMapper;
import com.documentprocessor.core.port.in.ExtractTextPortIn;
import io.awspring.cloud.sqs.annotation.SqsListener;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ProcessDocumentListenerAdapterIn {

    private final DocumentMapper documentMapper;
    private final ExtractTextPortIn extractTextPortIn;

    @SqsListener(value = "document-processor-queue")
    public void processDocument(Message<ProcessDocumentMessage> message) {
        var payload = message.getPayload();
        var document = documentMapper.toDomain(payload);
        switch (message.getPayload().getStatus()) {
            case STARTED -> extractTextPortIn.execute(document);
            case EXTRACTING_TEXT -> log.info("Document processing for: {}", document.getId());
            case FINISHED -> log.info("Document processing completed for: {}", document.getId());
        }
    }
}
