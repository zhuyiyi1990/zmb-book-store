package com.gitee.yoursmlie.bookstore.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gitee.yoursmlie.bookstore.entity.Book;
import com.gitee.yoursmlie.bookstore.mapper.BookMapper;
import com.gitee.yoursmlie.bookstore.service.IBookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author 朱一一
 */
@Service
@Slf4j
public class BookServiceImpl extends ServiceImpl<BookMapper, Book> implements IBookService {

}