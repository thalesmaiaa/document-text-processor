package com.documentprocessor.core.usecase;

import com.documentprocessor.core.domain.document.Document;
import com.documentprocessor.core.port.in.ExtractTextPortIn;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ExtractTextUseCase implements ExtractTextPortIn {

    public void execute(Document document) {
    }
}
