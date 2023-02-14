package com.gitee.yoursmlie.bookstore.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gitee.yoursmlie.bookstore.common.api.vo.Result;
import com.gitee.yoursmlie.bookstore.entity.BookStock;
import com.gitee.yoursmlie.bookstore.service.IBookStockService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author 朱一一
 */
@RestController
@RequestMapping("/bookStock")
@Slf4j
public class BookStockController {

    @Autowired
    private IBookStockService bookStockService;

    /**
     * 分页列表查询
     *
     * @param pageNo
     * @param pageSize
     * @return
     */
    @GetMapping(value = "/list")
    public Result<IPage<BookStock>> queryPageList(@RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                                  @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {
        QueryWrapper<BookStock> queryWrapper = new QueryWrapper<>();
        Page<BookStock> page = new Page<>(pageNo, pageSize);
        IPage<BookStock> pageList = bookStockService.page(page, queryWrapper);
        return Result.OK(pageList);
    }

    /**
     * 添加
     *
     * @param bookStock
     * @return
     */
    @PostMapping(value = "/add")
    public Result<String> add(@RequestBody BookStock bookStock) {
        bookStockService.save(bookStock);
        return Result.OK("添加成功！");
    }

    /**
     * 编辑
     *
     * @param bookStock
     * @return
     */
    @RequestMapping(value = "/edit", method = {RequestMethod.PUT, RequestMethod.POST})
    public Result<String> edit(@RequestBody BookStock bookStock) {
        bookStockService.updateById(bookStock);
        return Result.OK("编辑成功!");
    }

    /**
     * 通过id删除
     *
     * @param id
     * @return
     */
    @DeleteMapping(value = "/delete")
    public Result<String> delete(@RequestParam(name = "id", required = true) String id) {
        bookStockService.removeById(id);
        return Result.OK("删除成功!");
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @GetMapping(value = "/queryById")
    public Result<BookStock> queryById(@RequestParam(name = "id") String id) {
        BookStock bookStock = bookStockService.getById(id);
        if (bookStock == null) {
            return Result.error("未找到对应数据", null);
        }
        return Result.OK(bookStock);
    }

}