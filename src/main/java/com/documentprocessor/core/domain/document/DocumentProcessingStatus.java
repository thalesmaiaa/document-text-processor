package com.documentprocessor.core.domain.document;

public enum DocumentProcessingStatus {
    STARTED,
    EXTRACTING_TEXT,
    COMPREHENDING_TEXT,
    FINISHED;
}
