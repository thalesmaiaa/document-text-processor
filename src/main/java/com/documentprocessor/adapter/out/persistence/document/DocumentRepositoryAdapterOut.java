package com.documentprocessor.adapter.out.persistence.document;

import com.documentprocessor.core.domain.Document;
import com.documentprocessor.core.mappers.DocumentMapper;
import com.documentprocessor.core.port.out.DocumentRepositoryPortOut;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DocumentRepositoryAdapterOut implements DocumentRepositoryPortOut {

    private final DocumentMapper documentMapper;
    private final DocumentRepository documentRepository;

    @Override
    public void save(Document document) {
        var entity = documentMapper.toEntity(document);
        documentRepository.save(entity);
    }
}
