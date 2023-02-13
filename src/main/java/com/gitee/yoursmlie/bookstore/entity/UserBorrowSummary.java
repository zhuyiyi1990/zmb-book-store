package com.gitee.yoursmlie.bookstore.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author 朱一一
 */
@Data
@TableName("user_borrow_list")
public class UserBorrowSummary implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 总借阅金额
     */
    private BigDecimal totalAmount;

    /**
     * 0-正常，1-所有书已还
     */
    private Integer status;

}