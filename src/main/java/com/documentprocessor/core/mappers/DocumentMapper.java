package com.documentprocessor.core.mappers;

import com.documentprocessor.adapter.in.dto.ProcessDocumentMessage;
import com.documentprocessor.adapter.out.persistence.document.DocumentEntity;
import com.documentprocessor.core.domain.document.Document;
import com.documentprocessor.core.domain.document.DocumentMessage;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DocumentMapper {

    DocumentEntity toEntity(Document document);

    DocumentMessage toMessage(Document document);

    Document toDomain(DocumentEntity documentEntity);

    Document toDomain(ProcessDocumentMessage documentMessage);

}
