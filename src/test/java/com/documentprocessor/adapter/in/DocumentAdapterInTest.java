package com.documentprocessor.adapter.in;

import com.documentprocessor.adapter.in.dto.UploadDocumentRequest;
import com.documentprocessor.core.port.in.UploadDocumentPortIn;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.mockito.Mockito.*;

class DocumentAdapterInTest {
    @Test
    void shouldUploadDocumentAndCallPort() throws IOException {
        var uploadDocumentPortIn = mock(UploadDocumentPortIn.class);
        var request = mock(UploadDocumentRequest.class);
        var controller = new DocumentAdapterIn(uploadDocumentPortIn);
        assertThatCode(() -> controller.uploadDocument(request)).doesNotThrowAnyException();
        verify(uploadDocumentPortIn).execute(request);
    }
}
