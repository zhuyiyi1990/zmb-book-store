package com.gitee.yoursmlie.bookstore.vo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class BorrowDetailVO {

    /**
     * 图书id
     */
    private Integer bookId;

    /**
     * 所借数量
     */
    private Integer num;

    /**
     * 借阅时的单价
     */
    private BigDecimal price;

}