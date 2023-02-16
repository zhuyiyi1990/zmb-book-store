package com.gitee.yoursmlie.bookstore.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author 朱一一
 */
@Data
public class UserVO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Integer id;

    /**
     * 真实姓名
     */
    private String name;

    /**
     * 性别（1：男 2：女）
     */
    private Integer sex;

    /**
     * 电话
     */
    private String phone;

}