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
@TableName("book")
public class Book implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 书名
     */
    private String name;

    /**
     * 作者
     */
    private String author;

    /**
     * 借阅单价
     */
    private BigDecimal price;

    /**
     * 类型
     */
    private String type;

}