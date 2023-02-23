package com.gitee.yoursmlie.bookstore.controller;

import com.gitee.yoursmlie.bookstore.common.api.vo.Result;
import com.gitee.yoursmlie.bookstore.entity.UserBorrowDetail;
import com.gitee.yoursmlie.bookstore.entity.UserBorrowSummary;
import com.gitee.yoursmlie.bookstore.service.IUserBorrowSummaryService;
import com.gitee.yoursmlie.bookstore.vo.BorrowDetailVO;
import com.gitee.yoursmlie.bookstore.vo.BorrowVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author 朱一一
 */
@RestController
@RequestMapping("/core")
@Slf4j
public class BorrowController {

    @Autowired
    private IUserBorrowSummaryService borrowService;

    @PostMapping(value = "/borrow")
    public Result<?> borrow(@RequestBody BorrowVO vo) {
        Result<?> result = new Result<>();
        UserBorrowSummary summary = new UserBorrowSummary();
        BeanUtils.copyProperties(vo, summary);
        List<UserBorrowDetail> detailList = new ArrayList<>();
        if (Objects.nonNull(vo.getBorrowList())) {
            for (BorrowDetailVO detailVO : vo.getBorrowList()) {
                UserBorrowDetail detail = new UserBorrowDetail();
                BeanUtils.copyProperties(detailVO, detail);
                detailList.add(detail);
            }
        }
        borrowService.borrow(summary, detailList);
        return result.success("借阅成功！");
    }

}