package com.aircjm.limon.utils.poi;

import cn.hutool.core.util.ReflectUtil;
import com.aircjm.limon.exception.CustomException;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.exception.ExcelDataConvertException;
import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Excel基础导入对象-接收解析回调
 * 提供基础导入的功能，如有特殊逻辑，可继承此类并覆写本类的方法 自定义实现
 *
 * @author aircjm
 */
@Getter
@Setter
@Slf4j
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class BaseImportEvo<T> extends AnalysisEventListener<T> {

    /**
     * 解析开始时间
     */
    private long startTime;

    /**
     * 解析Excel得到的数据结果集
     */
    private List<T> data = Lists.newArrayList();

    public BaseImportEvo(long startTime) {
        this.startTime = startTime;
    }

    /**
     * 在转换异常 获取其他异常下会调用本接口。抛出异常则停止读取。如果这里不抛出异常则 继续读取下一行。
     *
     * @param exception 异常
     * @param context   文本
     * @throws Exception 异常
     */
    @Override
    public void onException(Exception exception, AnalysisContext context) {
        log.warn("解析失败，但是继续解析下一行:{}", exception.getMessage());
        // 如果是某一个单元格的转换异常 能获取到具体行号
        // 如果要获取头的信息 配合invokeHeadMap使用
        if (exception instanceof ExcelDataConvertException) {
            ExcelDataConvertException excelDataConvertException = (ExcelDataConvertException) exception;
            log.warn("第{}行，第{}列解析异常", excelDataConvertException.getRowIndex(),
                    excelDataConvertException.getColumnIndex());
        }
        throw new CustomException("excel parse error", exception.getCause());
    }

    /**
     * 这里会一行一行的返回头
     *
     * @param headMap 头元素
     * @param context 文本
     */
    @Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
        log.info("解析到一条头数据:{}", JSON.toJSONString(headMap));
    }


    /**
     * Excel的每一行解析完成回调此方法
     *
     * @param vo      对象
     * @param context 文本
     */
    @Override
    public void invoke(T vo, AnalysisContext context) {
        log.info("解析到一条数据:{}", JSON.toJSONString(vo));
        if (Objects.isNull(vo)) {
            log.info("当前数据为空,不添加到data中");
            return;
        }
        try {
            Field[] fields = ReflectUtil.getFields(vo.getClass());
            for (Field field : fields) {
                if (Objects.nonNull(field.getAnnotation(ExcelProperty.class))) {
                    PropertyDescriptor propertyDescriptor = new PropertyDescriptor(field.getName(), vo.getClass());
                    Object obj = propertyDescriptor.getReadMethod().invoke(vo);
                    if (Objects.nonNull(obj)) {
                        data.add(vo);
                        break;
                    }
                }
            }
        } catch (Exception e) {
            log.warn("easyExcel remove NullData error", e);
            // 发生异常则返回原始数据
            data.add(vo);
        }
    }

    /**
     * Excel的所有数据解析完成回调此方法
     *
     * @param context 文本
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        log.info("解析Excel完成,耗时:{}ms", System.currentTimeMillis() - startTime);
    }
}
