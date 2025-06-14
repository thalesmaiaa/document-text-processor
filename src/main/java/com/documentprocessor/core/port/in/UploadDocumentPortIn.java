package com.documentprocessor.core.port.in;

import com.documentprocessor.adapter.in.dto.UploadDocumentRequest;

import java.io.IOException;

public interface UploadDocumentPortIn {

    void execute(UploadDocumentRequest uploadDocumentRequest) throws IOException;
}
