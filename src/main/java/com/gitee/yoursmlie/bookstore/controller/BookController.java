package com.gitee.yoursmlie.bookstore.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gitee.yoursmlie.bookstore.common.api.vo.Result;
import com.gitee.yoursmlie.bookstore.entity.Book;
import com.gitee.yoursmlie.bookstore.service.IBookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author 朱一一
 */
@RestController
@RequestMapping("/book")
@Slf4j
public class BookController {

    @Autowired
    private IBookService bookService;

    /**
     * 分页列表查询
     *
     * @param book
     * @param pageNo
     * @param pageSize
     * @return
     */
    @GetMapping(value = "/list")
    public Result<IPage<Book>> queryPageList(Book book,
                                             @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                             @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {
        QueryWrapper<Book> queryWrapper = new QueryWrapper<>();
        Page<Book> page = new Page<>(pageNo, pageSize);
        IPage<Book> pageList = bookService.page(page, queryWrapper);
        return Result.OK(pageList);
    }

    /**
     * 添加
     *
     * @param book
     * @return
     */
    @PostMapping(value = "/add")
    public Result<String> add(@RequestBody Book book) {
        bookService.save(book);
        return Result.OK("添加成功！");
    }

    /**
     * 编辑
     *
     * @param book
     * @return
     */
    @RequestMapping(value = "/edit", method = {RequestMethod.PUT, RequestMethod.POST})
    public Result<String> edit(@RequestBody Book book) {
        bookService.updateById(book);
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
        bookService.removeById(id);
        return Result.OK("删除成功!");
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @GetMapping(value = "/queryById")
    public Result<Book> queryById(@RequestParam(name = "id") String id) {
        Book book = bookService.getById(id);
        if (book == null) {
            return Result.error("未找到对应数据", null);
        }
        return Result.OK(book);
    }

}