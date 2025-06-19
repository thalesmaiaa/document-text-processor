package com.documentprocessor.core.usecase;

import com.documentprocessor.adapter.in.dto.UploadDocumentRequest;
import com.documentprocessor.core.domain.document.Document;
import com.documentprocessor.core.domain.document.DocumentProcessingStatus;
import com.documentprocessor.core.mappers.DocumentMapper;
import com.documentprocessor.core.port.in.UploadDocumentPortIn;
import com.documentprocessor.core.port.out.DocumentAdapterPortOut;
import com.documentprocessor.core.port.out.DocumentRepositoryPortOut;
import com.documentprocessor.core.port.out.MessageAdapterPortOut;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.ZonedDateTime;

@Component
@RequiredArgsConstructor
public class UploadDocumentUseCase implements UploadDocumentPortIn {

    private final DocumentMapper documentMapper;
    private final MessageAdapterPortOut messageAdapterPortOut;
    private final DocumentAdapterPortOut documentAdapterPortOut;
    private final DocumentRepositoryPortOut documentRepositoryPortOut;

    @Override
    public void execute(UploadDocumentRequest request) throws IOException {
        var file = request.file();
        var fileUrl = documentAdapterPortOut.uploadFileToStorage(file);
        
        var document = new Document();
        document.setUrl(fileUrl.toString());
        document.setType(file.getContentType());
        document.setUploadedAt(ZonedDateTime.now());
        document.setStatus(DocumentProcessingStatus.STARTED);
        document.setOriginalName(file.getOriginalFilename());

        documentRepositoryPortOut.save(document);
        messageAdapterPortOut.sendMessage(documentMapper.toMessage(document));
    }
}
