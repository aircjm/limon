package com.aircjm.limon.common.utils.poi;

import com.aircjm.limon.common.exception.CustomException;
import com.alibaba.excel.EasyExcel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.Assert;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Objects;


/**
 * EasyExcel工具类
 *
 * @author aircjm
 */
@Slf4j
public class EasyExcelUtil {

    /**
     * 解析Excel数据
     *
     * @param file  文件
     * @param clazz 对象class
     * @param <T>   类型
     */
    public static <T> List<T> importExcel(MultipartFile file, Class<?> clazz) {
        if (Objects.isNull(clazz)) {
            throw new CustomException("对象为空");
        }
        List<T> data;
        InputStream inputStream = null;
        try {
            inputStream = file.getInputStream();
            data = importExcel(inputStream, clazz);
        } catch (Exception e) {
            throw new CustomException(e.getMessage(), e);
        } finally {
            if (Objects.nonNull(inputStream)) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    log.warn("关流失败");
                }
            }
        }
        return data;
    }

    /**
     * 解析Excel数据
     *
     * @param inputStream 输入流
     * @param clazz       class
     * @param <T>         类型
     * @return 返回
     */
    @SuppressWarnings("unchecked")
    public static <T> List<T> importExcel(InputStream inputStream, Class<?> clazz) {
        if (Objects.isNull(inputStream)) {
            throw new CustomException("inputStream为空");
        }
        List<T> data;
        try {
            BaseImportEvo baseImportEvo = new BaseImportEvo(System.currentTimeMillis());
            EasyExcel.read(inputStream, clazz, baseImportEvo).sheet().doRead();
            data = baseImportEvo.getData();
        } catch (Exception e) {
            throw new CustomException("解析Excel失败", e);
        }
        Assert.notNull(data, "解析结果集为空");
        return data;
    }

}
