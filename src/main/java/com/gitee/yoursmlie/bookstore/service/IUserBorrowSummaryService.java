package com.gitee.yoursmlie.bookstore.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gitee.yoursmlie.bookstore.entity.UserBorrowDetail;
import com.gitee.yoursmlie.bookstore.entity.UserBorrowSummary;
import com.gitee.yoursmlie.bookstore.vo.ReturnVO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface IUserBorrowSummaryService extends IService<UserBorrowSummary> {

    @Transactional(rollbackFor = Exception.class)
    void borrowBooks(UserBorrowSummary summary, List<UserBorrowDetail> detailList);

    @Transactional(rollbackFor = Exception.class)
    void returnBooks(ReturnVO vo);

}