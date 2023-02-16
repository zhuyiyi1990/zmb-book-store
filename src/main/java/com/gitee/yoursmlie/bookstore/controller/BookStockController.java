package com.gitee.yoursmlie.bookstore.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gitee.yoursmlie.bookstore.common.api.vo.Result;
import com.gitee.yoursmlie.bookstore.entity.BookStock;
import com.gitee.yoursmlie.bookstore.service.IBookStockService;
import com.gitee.yoursmlie.bookstore.vo.StockChangeVO;
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
    public Result<?> add(@RequestBody BookStock bookStock) {
        Result<?> result = new Result<>();
        try {
            bookStockService.save(bookStock);
        } catch (Exception e) {
            return result.error500("添加失败：" + e.getMessage());
        }
        return result.success("添加成功！");
    }

    /**
     * 增加/减少库存
     *
     * @param vo
     * @return
     */
    @RequestMapping(value = "/changeStock", method = {RequestMethod.PUT, RequestMethod.POST})
    public Result<?> changeStock(@RequestBody StockChangeVO vo) {
        Result<?> result = new Result<>();
        try {
            bookStockService.changeStock(vo.getBookId(), vo.getIncrement());
        } catch (Exception e) {
            return result.error500("库存修改失败：" + e.getMessage());
        }
        return result.success("库存修改成功!");
    }

    /**
     * 通过id删除
     *
     * @param id
     * @return
     */
    @DeleteMapping(value = "/delete")
    public Result<?> delete(@RequestParam(name = "id", required = true) String id) {
        Result<?> result = new Result<>();
        try {
            bookStockService.removeById(id);
        } catch (Exception e) {
            return result.error500("删除失败：" + e.getMessage());
        }
        return result.success("删除成功!");
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