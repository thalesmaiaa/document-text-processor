package com.documentprocessor.core.port.out;

import com.documentprocessor.core.domain.document.Document;

public interface DocumentRepositoryPortOut {

    Document save(Document document);
}
