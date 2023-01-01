package com.aircjm.limon.project.oss.service;

import org.springframework.web.multipart.MultipartFile;

public interface AliyunOssService {


    String putObjectGetUrl(MultipartFile file, String originalFilename);

}
