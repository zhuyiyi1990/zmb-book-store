package com.gitee.yoursmlie.bookstore.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gitee.yoursmlie.bookstore.entity.BookStock;
import com.gitee.yoursmlie.bookstore.mapper.BookStockMapper;
import com.gitee.yoursmlie.bookstore.service.IBookStockService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author 朱一一
 */
@Service
@Slf4j
public class BookStockServiceImpl extends ServiceImpl<BookStockMapper, BookStock> implements IBookStockService {

}