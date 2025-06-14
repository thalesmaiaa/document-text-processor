package com.documentprocessor.core.mappers;

import com.documentprocessor.adapter.out.persistence.document.DocumentEntity;
import com.documentprocessor.core.domain.Document;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DocumentMapper {

    DocumentEntity toEntity(Document document);
}
