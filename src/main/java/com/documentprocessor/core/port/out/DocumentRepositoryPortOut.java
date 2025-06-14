package com.documentprocessor.core.port.out;

import com.documentprocessor.core.domain.Document;

public interface DocumentRepositoryPortOut {

    void save(Document document);
}
