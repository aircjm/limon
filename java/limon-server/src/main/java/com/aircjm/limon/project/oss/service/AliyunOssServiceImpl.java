package com.aircjm.limon.project.oss.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@Slf4j
public class AliyunOssServiceImpl implements AliyunOssService {
    @Override
    public String putObjectGetUrl(MultipartFile file, String originalFilename) {
        // todo
        return null;
    }
}
