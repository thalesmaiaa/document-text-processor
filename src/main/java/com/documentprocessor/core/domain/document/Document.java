package com.documentprocessor.core.domain.document;

import lombok.*;

import java.time.ZonedDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Document {

    private Long id;
    private String type;
    private String url;
    private DocumentProcessingStatus status;
    private ZonedDateTime uploadedAt;
    private String originalName;
}
