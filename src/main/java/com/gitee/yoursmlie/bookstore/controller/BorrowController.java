package com.gitee.yoursmlie.bookstore.controller;

import com.gitee.yoursmlie.bookstore.common.api.vo.Result;
import com.gitee.yoursmlie.bookstore.vo.BorrowVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * @author 朱一一
 */
@RestController
@RequestMapping("/core")
@Slf4j
public class BorrowController {

    @PostMapping(value = "/borrow")
    public Result<?> borrow(@RequestBody BorrowVO vo) {
        Result<?> result = new Result<>();
        //TODO
        return result.success("借阅成功！");
    }

}