package com.gitee.yoursmlie.bookstore.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;

@Data
@TableName("user_borrow_detail")
public class UserBorrowDetail {

    /**
     * borrow summary id
     */
    private Integer borrowId;

    /**
     * 序列号
     */
    private Integer seq;

    /**
     * 书本id
     */
    private Integer bookId;

    /**
     * 所借数量
     */
    private Integer num;

    /**
     * 已还数量
     */
    private Integer returnedNum;

    /**
     * 借阅时的单价
     */
    private BigDecimal price;

}