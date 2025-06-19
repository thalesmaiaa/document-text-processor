package com.documentprocessor.adapter.in;

import com.documentprocessor.adapter.in.dto.ProcessDocumentMessage;
import com.documentprocessor.core.domain.document.Document;
import com.documentprocessor.core.domain.document.DocumentProcessingStatus;
import com.documentprocessor.core.mappers.DocumentMapper;
import com.documentprocessor.core.port.in.ExtractTextPortIn;
import org.junit.jupiter.api.Test;
import org.springframework.messaging.Message;

import static org.mockito.Mockito.*;

class ProcessDocumentListenerAdapterInTest {
    @Test
    void shouldCallExtractTextWhenStatusStarted() {
        var documentMapper = mock(DocumentMapper.class);
        var extractTextPortIn = mock(ExtractTextPortIn.class);
        var listener = new ProcessDocumentListenerAdapterIn(documentMapper, extractTextPortIn);
        var processMessage = new ProcessDocumentMessage();
        processMessage.setStatus(DocumentProcessingStatus.STARTED);
        var document = new Document();
        var message = mock(Message.class);
        when(message.getPayload()).thenReturn(processMessage);
        when(documentMapper.toDomain(processMessage)).thenReturn(document);

        listener.processDocument(message);
        verify(extractTextPortIn).execute(document);
    }

    @Test
    void shouldLogWhenStatusExtractingText() {
        var documentMapper = mock(DocumentMapper.class);
        var extractTextPortIn = mock(ExtractTextPortIn.class);
        var listener = new ProcessDocumentListenerAdapterIn(documentMapper, extractTextPortIn);
        var processMessage = new ProcessDocumentMessage();
        processMessage.setStatus(DocumentProcessingStatus.EXTRACTING_TEXT);
        var document = new Document();
        document.setId(123L);
        var message = mock(Message.class);
        when(message.getPayload()).thenReturn(processMessage);
        when(documentMapper.toDomain(processMessage)).thenReturn(document);

        listener.processDocument(message);
        verifyNoInteractions(extractTextPortIn);
    }

    @Test
    void shouldLogWhenStatusFinished() {
        var documentMapper = mock(DocumentMapper.class);
        var extractTextPortIn = mock(ExtractTextPortIn.class);
        var listener = new ProcessDocumentListenerAdapterIn(documentMapper, extractTextPortIn);
        var processMessage = new ProcessDocumentMessage();
        processMessage.setStatus(DocumentProcessingStatus.FINISHED);
        var document = new Document();
        document.setId(456L);
        var message = mock(Message.class);
        when(message.getPayload()).thenReturn(processMessage);
        when(documentMapper.toDomain(processMessage)).thenReturn(document);

        listener.processDocument(message);
        verifyNoInteractions(extractTextPortIn);
    }
}
