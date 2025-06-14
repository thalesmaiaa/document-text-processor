package com.documentprocessor.adapter.in.dto;

import jakarta.validation.constraints.NotNull;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

public record UploadDocumentRequest(@RequestPart("file") @NotNull MultipartFile file) {
}
