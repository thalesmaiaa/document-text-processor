package com.documentprocessor.core.port.in;

import com.documentprocessor.core.domain.document.Document;

public interface ExtractTextPortIn {

    void execute(Document document);
}
