package com.documentprocessor.adapter.out.document;

import org.junit.jupiter.api.Test;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetUrlRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.IOException;
import java.net.URI;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class DocumentAdapterOutTest {
    @Test
    void shouldUploadFileToStorage() throws IOException {
        var s3Client = mock(S3Client.class, RETURNS_DEEP_STUBS);
        var adapter = new DocumentAdapterOut(s3Client);
        var multipartFile = mock(MultipartFile.class);
        var fileName = "file.txt";
        var fileBytes = new byte[]{1, 2, 3};
        var url = URI.create("http://localhost/file.txt").toURL();
        when(multipartFile.getOriginalFilename()).thenReturn(fileName);
        when(multipartFile.getBytes()).thenReturn(fileBytes);
        when(s3Client.utilities().getUrl(any(GetUrlRequest.class))).thenReturn(url);

        var result = adapter.uploadFileToStorage(multipartFile);
        assertThat(result).isEqualTo(url);
        verify(s3Client).putObject(any(PutObjectRequest.class), any(RequestBody.class));
    }
}
