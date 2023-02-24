package com.gitee.yoursmlie.bookstore.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gitee.yoursmlie.bookstore.entity.UserBorrowDetail;
import com.gitee.yoursmlie.bookstore.entity.UserBorrowSummary;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface IUserBorrowSummaryService extends IService<UserBorrowSummary> {

    @Transactional(rollbackFor = Exception.class)
    void borrow(UserBorrowSummary summary, List<UserBorrowDetail> detailList);

}