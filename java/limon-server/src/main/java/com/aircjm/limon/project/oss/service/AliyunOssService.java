package com.aircjm.limon.project.oss.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author aircjm
 */
public interface AliyunOssService {


    /**
     * 发送文件 并且返回文件url
     *
     * @param file             文件
     * @param originalFilename 文件名
     * @return url
     */
    String putObjectGetUrl(MultipartFile file, String originalFilename);

}
