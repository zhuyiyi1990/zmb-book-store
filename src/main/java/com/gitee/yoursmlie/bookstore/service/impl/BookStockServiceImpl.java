package com.gitee.yoursmlie.bookstore.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gitee.yoursmlie.bookstore.config.Message;
import com.gitee.yoursmlie.bookstore.entity.Book;
import com.gitee.yoursmlie.bookstore.entity.BookStock;
import com.gitee.yoursmlie.bookstore.mapper.BookMapper;
import com.gitee.yoursmlie.bookstore.mapper.BookStockMapper;
import com.gitee.yoursmlie.bookstore.service.IBookStockService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author 朱一一
 */
@Service
@Slf4j
public class BookStockServiceImpl extends ServiceImpl<BookStockMapper, BookStock> implements IBookStockService {

    @Autowired
    private BookMapper bookMapper;

    @Override
    public boolean save(BookStock bookStock) {
        //库存量必须>=0，先不用JSR303吧
        if (Objects.isNull(bookStock.getTotal()) || bookStock.getTotal() < 0) {
            throw new RuntimeException(Message.ERROR_00001);
        }

        //检查图书id是否有效
        LambdaQueryWrapper<Book> bookWrapper = new LambdaQueryWrapper<>();
        bookWrapper.eq(Book::getId, bookStock.getBookId());
        if (bookMapper.selectCount(bookWrapper) == 0) {
            throw new RuntimeException(Message.ERROR_00002);
        }

        //检查是否存在此图书库存信息
        LambdaQueryWrapper<BookStock> bookStockWrapper = new LambdaQueryWrapper<>();
        bookStockWrapper.eq(BookStock::getBookId, bookStock.getBookId());
        if (baseMapper.selectCount(bookStockWrapper) > 0) {
            throw new RuntimeException(Message.ERROR_00003);
        }

        //将外借数初始化为0
        bookStock.setBorrowedNum(0);

        return super.save(bookStock);
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public void changeStock(Integer bookId, Integer increment) {
        LambdaQueryWrapper<BookStock> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(BookStock::getBookId, bookId);
        BookStock bookStock = baseMapper.selectOne(wrapper);
        if (Objects.isNull(bookStock)) {
            throw new RuntimeException(Message.ERROR_00005);
        }
        if (bookStock.getTotal() + increment < bookStock.getBorrowedNum()) {
            throw new RuntimeException(Message.ERROR_00004);
        }
        bookStock.setTotal(bookStock.getTotal() + increment);
        baseMapper.updateById(bookStock);
    }

    @Override
    public boolean removeById(Serializable id) {
        BookStock bookStock = baseMapper.selectById(id);
        if (bookStock.getBorrowedNum() > 0) {
            throw new RuntimeException(Message.ERROR_00006);
        }
        return super.removeById(id);
    }

}