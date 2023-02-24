package com.gitee.yoursmlie.bookstore.vo;

import lombok.Data;

@Data
public class ReturnDetailVO {

    /**
     * 图书id
     */
    private Integer bookId;

    /**
     * 所还数量
     */
    private Integer num;

}