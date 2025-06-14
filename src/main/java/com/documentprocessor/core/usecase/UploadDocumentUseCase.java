package com.documentprocessor.core.usecase;

import com.documentprocessor.adapter.in.dto.UploadDocumentRequest;
import com.documentprocessor.core.domain.Document;
import com.documentprocessor.core.domain.DocumentProcessingStatus;
import com.documentprocessor.core.port.in.UploadDocumentPortIn;
import com.documentprocessor.core.port.out.DocumentAdapterPortOut;
import com.documentprocessor.core.port.out.DocumentRepositoryPortOut;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class UploadDocumentUseCase implements UploadDocumentPortIn {

    private final DocumentAdapterPortOut documentAdapterPortOut;
    private final DocumentRepositoryPortOut documentRepositoryPortOut;

    @Override
    public void execute(UploadDocumentRequest request) throws IOException {
        var fileUrl = documentAdapterPortOut.uploadFileToStorage(request.file());
        var document = new Document();
        document.setUrl(String.valueOf(fileUrl));
        document.setType(request.file().getContentType());
        document.setStatus(DocumentProcessingStatus.STARTED);

        documentRepositoryPortOut.save(document);
    }
}
