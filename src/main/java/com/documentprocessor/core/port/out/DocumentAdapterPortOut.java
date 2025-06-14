package com.documentprocessor.core.port.out;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URL;

public interface DocumentAdapterPortOut {

    URL uploadFileToStorage(MultipartFile multipartFile) throws IOException;
}
