package com.documentprocessor.core.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Document {

    private Long id;
    private String type;
    private String url;
    private DocumentProcessingStatus status;
    private String uploadedAt;
    private String originalName;
}
