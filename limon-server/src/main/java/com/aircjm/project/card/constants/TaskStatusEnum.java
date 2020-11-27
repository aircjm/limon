package com.aircjm.project.card.constants;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.baomidou.mybatisplus.core.enums.IEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 枚举-任务状态
 * @author chenjiaming
 */

@NoArgsConstructor
@AllArgsConstructor
public enum TaskStatusEnum implements IEnum<Integer> {

    TODO(0, "待处理"),
    DOING(1, "处理中"),
    DONE(2, "已完成"),
    DEL(-1, "已删除");


    @Setter
    @Getter
    @EnumValue
    private int value;


    @Setter
    @Getter
    private String desc;

    @Override
    public Integer getValue() {
        return this.value;
    }
}
