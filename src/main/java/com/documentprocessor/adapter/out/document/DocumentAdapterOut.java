package com.documentprocessor.adapter.out.document;

import com.documentprocessor.core.port.out.DocumentAdapterPortOut;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetUrlRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.IOException;
import java.net.URL;

@Component
@RequiredArgsConstructor
public class DocumentAdapterOut implements DocumentAdapterPortOut {

    private final S3Client s3Client;

    @Override
    public URL uploadFileToStorage(MultipartFile multipartFile) throws IOException {
        var objectKey = multipartFile.getOriginalFilename();

        var s3Url = GetUrlRequest.builder()
                .bucket("document-processor")
                .key(objectKey)
                .build();

        var fileUrl = s3Client.utilities().getUrl(s3Url);

        s3Client.putObject(PutObjectRequest.builder()
                .bucket("document-processor")
                .key(objectKey).build(), RequestBody.fromBytes(multipartFile.getBytes()));

        return fileUrl;
    }
}