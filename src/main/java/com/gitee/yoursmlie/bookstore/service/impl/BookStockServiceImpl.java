package com.gitee.yoursmlie.bookstore.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gitee.yoursmlie.bookstore.entity.BookStock;
import com.gitee.yoursmlie.bookstore.mapper.BookStockMapper;
import com.gitee.yoursmlie.bookstore.service.IBookStockService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author 朱一一
 */
@Service
@Slf4j
public class BookStockServiceImpl extends ServiceImpl<BookStockMapper, BookStock> implements IBookStockService {

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public void changeStock(Integer bookId, Integer increment) {
        LambdaQueryWrapper<BookStock> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(BookStock::getBookId, bookId);
        BookStock bookStock = baseMapper.selectOne(wrapper);
        if (bookStock.getTotal() + increment < bookStock.getBorrowedNum()) {
            throw new RuntimeException("库存减少太多");
        }
        bookStock.setTotal(bookStock.getTotal() + increment);
        baseMapper.updateById(bookStock);
    }

}