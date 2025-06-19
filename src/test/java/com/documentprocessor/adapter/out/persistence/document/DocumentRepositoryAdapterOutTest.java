package com.documentprocessor.adapter.out.persistence.document;

import com.documentprocessor.core.domain.document.Document;
import com.documentprocessor.core.mappers.DocumentMapper;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class DocumentRepositoryAdapterOutTest {
    @Test
    void shouldSaveDocument() {
        var documentMapper = mock(DocumentMapper.class);
        var documentRepository = mock(DocumentRepository.class);
        var adapter = new DocumentRepositoryAdapterOut(documentMapper, documentRepository);
        var document = new Document();
        var entity = mock(DocumentEntity.class);
        var savedEntity = mock(DocumentEntity.class);
        var savedDocument = new Document();
        when(documentMapper.toEntity(document)).thenReturn(entity);
        when(documentRepository.save(entity)).thenReturn(savedEntity);
        when(documentMapper.toDomain(savedEntity)).thenReturn(savedDocument);

        var result = adapter.save(document);
        assertThat(result).isEqualTo(savedDocument);
    }
}
