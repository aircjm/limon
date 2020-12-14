package com.aircjm.project.system.service;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import com.aircjm.common.exception.CustomException;
import com.aircjm.project.system.vo.OssFileVo;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.OSSObject;
import com.aliyun.oss.model.ObjectListing;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PutObjectResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Date;
import java.util.Objects;

/**
 * oss上传服务
 *
 * @author aircjm
 */
@Service
@Slf4j
public class AliyunOssService {

    /**
     * 连接区域地址
     */
    @Value("${oss.endpoint}")
    private String endpoint;

    /**
     * 连接keyId
     */
    @Value("${oss.accessKeyId}")
    private String accessKeyId;

    /**
     * 连接秘钥
     */
    @Value("${oss.accessKeySecret}")
    private String accessKeySecret;

    /**
     * 需要存储的bucketName
     */
    @Value("${oss.bucketName}")
    private String bucketName;

    /**
     * 保存路径
     */
    @Value("${oss.filePath}")
    private String filePath = "dash/";

    /**
     * 获取文件
     *
     * @param fileName 参数
     * @return 返回值
     */
    public OssFileVo getFile(String fileName) {
        OSSClient ossClient = new OSSClient(this.endpoint, this.accessKeyId, this.accessKeySecret);
        OSSObject ossObject = ossClient.getObject(bucketName, filePath + fileName);
        OssFileVo ossFileVo = new OssFileVo();
        ossFileVo.setOssClient(ossClient);
        ossFileVo.setOssObject(ossObject);
        return ossFileVo;
    }

    /**
     * 上传文件并获取文件URL
     *
     * @param inputStream 输入流
     * @param fileName    文件名称
     * @return 文件URL
     */
    private String uploadFileAndGetUrl(InputStream inputStream, String fileName) {
        OSSClient ossClient = null;
        String objectKey = this.getFileKey(fileName);
        try {
            ossClient = new OSSClient(this.endpoint, this.accessKeyId, this.accessKeySecret);
            // 创建上传Object的Metadata
            ObjectMetadata objectMeta = new ObjectMetadata();
            String fileType = fileName.substring(fileName.lastIndexOf("."));
            // 设置上传内容类型
            objectMeta.setContentType(this.contentType(fileType));
            // 被下载时网页的缓存行为
            objectMeta.setCacheControl("no-cache");
            //上传文件
            PutObjectResult putObjectResult = ossClient.putObject(bucketName, objectKey, inputStream, objectMeta);
            putObjectResult.getETag();
            // 设置URL过期时间为10年  3600L * 1000 * 24 * 365 * 10
            Date expiration = new Date(System.currentTimeMillis() + 3600L * 1000 * 24 * 365 * 10);
            // 生成URL
            URL url = ossClient.generatePresignedUrl(bucketName, filePath + DateUtil.format(DateUtil.date(), DatePattern.PURE_DATETIME_PATTERN) + "/" + fileName, expiration);
            return url.toString();
        } catch (Exception e) {
            log.warn("上传文文件至oss,发生异常:", e);
            throw new CustomException(e.getMessage());
        } finally {
            if (Objects.nonNull(ossClient)) {
                ossClient.shutdown();
            }
        }
    }

    /**
     * 上传文件获得下载地址
     *
     * @param file     文件
     * @param fileName 文件名
     * @return 返回值
     */
    public String putObjectGetUrl(MultipartFile file, String fileName) {
        try {
            InputStream inputStream = file.getInputStream();
            return uploadFileAndGetUrl(inputStream, fileName);
        } catch (IOException e) {
            log.warn("上传文文件至oss,发生IOException异常: ", e);
            throw new CustomException(e.getMessage());
        }
    }

    /**
     * 上传文件获得下载地址
     *
     * @param inputStream input流
     * @param fileName    文件名称
     * @return 返回值
     */
    public String putObjectGetUrl(InputStream inputStream, String fileName) {
        return uploadFileAndGetUrl(inputStream, fileName);
    }

    private String getFileKey(String fileName) {
        return filePath + fileName;
    }

    public void downLoadObject(HttpServletRequest request, HttpServletResponse response) {
        //从前台获取当前下载文件的id值（每个上传到阿里云的文件都会有一个独一无二的id值）
        String fileId = request.getParameter("fileid");
        //从前台获取要下载文件的文件名
        String filename = request.getParameter("filename");
        int i = filename.lastIndexOf("\\");
        filename = filename.substring(i + 1);

        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
        ObjectListing list = ossClient.listObjects(bucketName);
        // 调用ossClient.getObject返回一个OSSObject实例，该实例包含文件内容及文件元信息。
        OSSObject ossObject = ossClient.getObject(bucketName, fileId);
        try (BufferedInputStream in = new BufferedInputStream(ossObject.getObjectContent());
             BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream())) {
            //通知浏览器以附件形式下载
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(filename, "utf-8"));
            byte[] car = new byte[1024];
            int l;
            while ((l = in.read(car)) != -1) {
                out.write(car, 0, l);
            }
            out.flush();
        } catch (IOException e) {
            log.warn("oss下载文件出现 IOException 异常： ", e);
            throw new CustomException(e.getMessage());
        } catch (Exception e) {
            log.warn("oss下载文件出现 Exception 异常：", e);
            throw new CustomException(e.getMessage());
        } finally {
            ossClient.shutdown();
        }
    }


    /**
     * 获取文件类型
     *
     * @param fileType 文件类型
     * @return 返回值
     */
    private String contentType(String fileType) {
        fileType = fileType.toLowerCase();
        String contentType;
        switch (fileType) {
            case "bmp":
                contentType = "image/bmp";
                break;
            case "gif":
                contentType = "image/gif";
                break;
            case "png":
            case "jpeg":
            case "jpg":
                contentType = "image/jpeg";
                break;
            case "html":
                contentType = "text/html";
                break;
            case "txt":
                contentType = "text/plain";
                break;
            case "vsd":
                contentType = "application/vnd.visio";
                break;
            case "ppt":
            case "pptx":
                contentType = "application/vnd.ms-powerpoint";
                break;
            case "doc":
            case "docx":
                contentType = "application/msword";
                break;
            case "xml":
                contentType = "text/xml";
                break;
            case "mp4":
                contentType = "video/mp4";
                break;
            default:
                contentType = "application/octet-stream";
                break;
        }
        return contentType;
    }

    public boolean deleteObject(String fileName) {

        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
        try {
            boolean exist = ossClient.doesObjectExist(bucketName, filePath + fileName);
            if (!exist) {
                log.warn("文件不存在,filePath={}", fileName);
                return false;
            }
            log.info("删除文件,filePath={}", fileName);
            ossClient.deleteObject(bucketName, filePath + fileName);
        } catch (Exception e) {
            log.warn("oss删除文件：{}出现 Exception 异常", fileName, e);
        } finally {
            ossClient.shutdown();
        }
        ossClient.shutdown();
        return true;
    }
}
