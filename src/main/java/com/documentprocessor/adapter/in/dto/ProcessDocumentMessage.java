package com.documentprocessor.adapter.in.dto;

import com.documentprocessor.core.domain.document.DocumentProcessingStatus;
import lombok.*;

import java.time.ZonedDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ProcessDocumentMessage {

    private Long id;
    private String type;
    private String url;
    private DocumentProcessingStatus status;
    private ZonedDateTime uploadedAt;
    private String originalName;
}