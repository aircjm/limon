package com.aircjm.limon.project.system.vo;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.OSSObject;
import lombok.Data;

import java.io.Serializable;

/**
 * @author aircjm
 */
@Data
public class OssFileVo implements Serializable {
    private static final long serialVersionUID = 4495036268617102582L;

    private OSSObject ossObject;
    private OSSClient ossClient;
}
