package com.documentprocessor.adapter.in;

import com.documentprocessor.adapter.in.dto.UploadDocumentRequest;
import com.documentprocessor.core.port.in.UploadDocumentPortIn;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/documents")
public class DocumentAdapterPortIn {

    private final UploadDocumentPortIn uploadDocumentPortIn;

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PostMapping(consumes = "multipart/form-data")
    public void uploadDocument(@Valid UploadDocumentRequest request) throws IOException {
        uploadDocumentPortIn.execute(request);
    }
}
