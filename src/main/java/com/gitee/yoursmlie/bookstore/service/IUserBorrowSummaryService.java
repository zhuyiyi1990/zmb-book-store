package com.gitee.yoursmlie.bookstore.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gitee.yoursmlie.bookstore.entity.UserBorrowDetail;
import com.gitee.yoursmlie.bookstore.entity.UserBorrowSummary;

import java.util.List;

public interface IUserBorrowSummaryService extends IService<UserBorrowSummary> {

    void borrow(UserBorrowSummary summary, List<UserBorrowDetail> detailList);

}