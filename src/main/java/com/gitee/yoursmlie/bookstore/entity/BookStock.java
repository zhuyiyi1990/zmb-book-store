package com.gitee.yoursmlie.bookstore.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @author 朱一一
 */
@Data
@TableName("book_stock")
public class BookStock implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 书本id
     */
    private Integer bookId;

    /**
     * 书本总数
     */
    private Integer total;

    /**
     * 已外借数量
     */
    private Integer borrowedNum;

}