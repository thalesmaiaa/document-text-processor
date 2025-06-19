package com.documentprocessor.core.usecase;

import com.documentprocessor.adapter.in.dto.UploadDocumentRequest;
import com.documentprocessor.core.domain.document.Document;
import com.documentprocessor.core.mappers.DocumentMapper;
import com.documentprocessor.core.port.out.DocumentAdapterPortOut;
import com.documentprocessor.core.port.out.DocumentRepositoryPortOut;
import com.documentprocessor.core.port.out.MessageAdapterPortOut;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URI;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.mockito.Mockito.*;

class UploadDocumentUseCaseTest {
    @Test
    void shouldExecuteUploadDocument() throws IOException {
        var documentMapper = mock(DocumentMapper.class);
        var messageAdapterPortOut = mock(MessageAdapterPortOut.class);
        var documentAdapterPortOut = mock(DocumentAdapterPortOut.class);
        var documentRepositoryPortOut = mock(DocumentRepositoryPortOut.class);
        var useCase = new UploadDocumentUseCase(documentMapper, messageAdapterPortOut, documentAdapterPortOut, documentRepositoryPortOut);
        var request = mock(UploadDocumentRequest.class);
        var file = mock(org.springframework.web.multipart.MultipartFile.class);
        var fileUrl = URI.create("http://localhost/file.txt").toURL();
        when(request.file()).thenReturn(file);
        when(documentAdapterPortOut.uploadFileToStorage(file)).thenReturn(fileUrl);
        when(file.getContentType()).thenReturn("text/plain");
        when(file.getOriginalFilename()).thenReturn("file.txt");
        var document = new Document();
        when(documentMapper.toMessage(any())).thenReturn(mock(com.documentprocessor.core.domain.document.DocumentMessage.class));
        when(documentRepositoryPortOut.save(any())).thenReturn(document);

        assertThatCode(() -> useCase.execute(request)).doesNotThrowAnyException();
        verify(documentRepositoryPortOut).save(any(Document.class));
        verify(messageAdapterPortOut).sendMessage(any());
    }
}
