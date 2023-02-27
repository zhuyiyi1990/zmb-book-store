package com.gitee.yoursmlie.bookstore.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gitee.yoursmlie.bookstore.config.Message;
import com.gitee.yoursmlie.bookstore.entity.*;
import com.gitee.yoursmlie.bookstore.mapper.*;
import com.gitee.yoursmlie.bookstore.service.IUserBorrowSummaryService;
import com.gitee.yoursmlie.bookstore.vo.ReturnDetailVO;
import com.gitee.yoursmlie.bookstore.vo.ReturnVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * @author 朱一一
 */
@Service
@Slf4j
public class UserBorrowSummaryServiceImpl extends ServiceImpl<UserBorrowSummaryMapper, UserBorrowSummary> implements IUserBorrowSummaryService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private BookMapper bookMapper;

    @Autowired
    private BookStockMapper bookStockMapper;

    @Autowired
    private UserBorrowDetailMapper detailMapper;

    @Override
    public void borrowBooks(UserBorrowSummary summary, List<UserBorrowDetail> detailList) {
        //检查是否传入用户id
        if (Objects.isNull(summary.getUserId())) {
            throw new RuntimeException(Message.ERROR_00007);
        }

        //检查用户id是否有效
        User user = userMapper.selectById(summary.getUserId());
        if (Objects.isNull(user)) {
            throw new RuntimeException(Message.ERROR_00008);
        }

        summary.setStatus(0);
        baseMapper.insert(summary);

        Set<Integer> bookIds = new HashSet<>();
        for (int i = 0; i < detailList.size(); i++) {
            UserBorrowDetail detail = detailList.get(i);

            //检查是否传入图书id
            if (Objects.isNull(detail.getBookId())) {
                throw new RuntimeException(Message.ERROR_00009);
            }

            //检查图书id是否有效
            Book book = bookMapper.selectById(detail.getBookId());
            if (Objects.isNull(book)) {
                throw new RuntimeException(Message.ERROR_00002);
            }

            //检查数量是否合法
            if (Objects.isNull(detail.getNum()) || detail.getNum() <= 0) {
                throw new RuntimeException(Message.ERROR_00010);
            }

            //检查库存是否足够
            LambdaQueryWrapper<BookStock> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(BookStock::getBookId, detail.getBookId());
            BookStock bookStock = bookStockMapper.selectOne(wrapper);
            if (Objects.isNull(bookStock) || detail.getNum() > bookStock.getTotal() - bookStock.getBorrowedNum()) {
                throw new RuntimeException("[" + detail.getBookId() + ":" + book.getName() + "]" + Message.ERROR_00011);
            }

            //检查是否重复图书
            if (bookIds.contains(detail.getBookId())) {
                throw new RuntimeException(Message.ERROR_00012);
            }
            bookIds.add(detail.getBookId());

            detail.setBorrowId(summary.getId());
            detail.setSeq(i + 1);
            detail.setReturnedNum(0);
            detail.setStatus(0);
            detailMapper.insert(detail);
            bookStock.setBorrowedNum(bookStock.getBorrowedNum() + detail.getNum());
            bookStockMapper.updateById(bookStock);
        }
    }

    @Override
    public void returnBooks(ReturnVO vo) {
        //检查是否提供id
        if (Objects.isNull(vo.getId())) {
            throw new RuntimeException(Message.ERROR_00013);
        }

        //检查借阅id是否有效
        UserBorrowSummary summary = baseMapper.selectById(vo.getId());
        if (Objects.isNull(summary) || !Objects.equals(summary.getStatus(), 0)) {
            throw new RuntimeException(Message.ERROR_00014);
        }

        List<ReturnDetailVO> returnList = vo.getReturnList();
        if (Objects.nonNull(returnList)) {
            for (ReturnDetailVO detailVO : returnList) {
                //检查是否传入图书id
                if (Objects.isNull(detailVO.getBookId())) {
                    throw new RuntimeException(Message.ERROR_00009);
                }

                LambdaQueryWrapper<UserBorrowDetail> detailWrapper = new LambdaQueryWrapper<>();
                detailWrapper.eq(UserBorrowDetail::getBorrowId, vo.getId());
                detailWrapper.eq(UserBorrowDetail::getBookId, detailVO.getBookId());
                UserBorrowDetail detail = detailMapper.selectOne(detailWrapper);
                if (Objects.isNull(detail)) {
                    throw new RuntimeException(Message.ERROR_00015 + "[bookId:" + detailVO.getBookId() + "]");
                }
                if (detailVO.getNum() > (detail.getNum() - detail.getReturnedNum())) {
                    throw new RuntimeException(Message.ERROR_00016 + "[bookId:" + detailVO.getBookId() + "]");
                }
                detail.setReturnedNum(detail.getReturnedNum() + detailVO.getNum());
                if (Objects.equals(detail.getReturnedNum(), detail.getNum())) {
                    detail.setStatus(1);
                }
                detailMapper.update(detail, detailWrapper);

                //更新库存
                LambdaQueryWrapper<BookStock> bookStockWrapper = new LambdaQueryWrapper<>();
                bookStockWrapper.eq(BookStock::getBookId, detailVO.getBookId());
                BookStock bookStock = bookStockMapper.selectOne(bookStockWrapper);
                bookStock.setBorrowedNum(bookStock.getBorrowedNum() - detailVO.getNum());
                bookStockMapper.updateById(bookStock);
            }
        }

        //如果已还所有图书，更新summary状态
        LambdaQueryWrapper<UserBorrowDetail> detailWrapper = new LambdaQueryWrapper<>();
        detailWrapper.eq(UserBorrowDetail::getStatus, 0);
        if (detailMapper.selectCount(detailWrapper) == 0) {
            summary.setStatus(1);
            baseMapper.updateById(summary);
        }
    }

}