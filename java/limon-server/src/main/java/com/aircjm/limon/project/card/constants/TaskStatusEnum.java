package com.aircjm.limon.project.card.constants;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * 枚举-任务状态
 *
 * @author chenjiaming
 */

@NoArgsConstructor
@AllArgsConstructor
public enum TaskStatusEnum {

    TODO(0, "待处理"),
    DOING(1, "处理中"),
    DONE(9, "已完成"),
    DEL(-1, "已删除");


    @EnumValue
    private int value;


    private String desc;


    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
