package com.gitee.yoursmlie.bookstore.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gitee.yoursmlie.bookstore.entity.UserBorrowDetail;
import com.gitee.yoursmlie.bookstore.entity.UserBorrowSummary;
import com.gitee.yoursmlie.bookstore.mapper.UserBorrowSummaryMapper;
import com.gitee.yoursmlie.bookstore.service.IUserBorrowSummaryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 朱一一
 */
@Service
@Slf4j
public class UserBorrowSummaryServiceImpl extends ServiceImpl<UserBorrowSummaryMapper, UserBorrowSummary> implements IUserBorrowSummaryService {

    @Override
    public void borrow(UserBorrowSummary summary, List<UserBorrowDetail> detailList) {
        //TODO
    }

}