package com.gitee.yoursmlie.bookstore.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gitee.yoursmlie.bookstore.entity.BookStock;

public interface IBookStockService extends IService<BookStock> {

    /**
     * @param bookId    图书id
     * @param increment 增量，可以为负
     */
    void changeStock(Integer bookId, Integer increment);

}