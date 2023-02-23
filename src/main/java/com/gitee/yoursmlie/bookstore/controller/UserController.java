package com.gitee.yoursmlie.bookstore.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gitee.yoursmlie.bookstore.common.api.vo.Result;
import com.gitee.yoursmlie.bookstore.entity.User;
import com.gitee.yoursmlie.bookstore.service.IUserService;
import com.gitee.yoursmlie.bookstore.vo.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

/**
 * @author 朱一一
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    private IUserService userService;

    /**
     * 分页列表查询
     *
     * @param pageNo
     * @param pageSize
     * @return
     */
    @GetMapping(value = "/list")
    public Result<IPage<User>> queryPageList(@RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                             @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        Page<User> page = new Page<>(pageNo, pageSize);
        IPage<User> pageList = userService.page(page, queryWrapper);
        return Result.OK(pageList);
    }

    /**
     * 添加
     *
     * @param vo
     * @return
     */
    @PostMapping(value = "/add")
    public Result<?> add(@RequestBody UserVO vo) {
        Result<?> result = new Result<>();
        User user = new User();
        BeanUtils.copyProperties(vo, user);
        if (Objects.isNull(user.getSex())) {
            user.setSex(0);
        }
        user.setDelFlag(0);
        userService.save(user);
        return result.success("添加成功！");
    }

    /**
     * 编辑
     *
     * @param vo
     * @return
     */
    @RequestMapping(value = "/edit", method = {RequestMethod.PUT, RequestMethod.POST})
    public Result<?> edit(@RequestBody UserVO vo) {
        Result<?> result = new Result<>();
        User user = new User();
        BeanUtils.copyProperties(vo, user);
        userService.updateById(user);
        return result.success("编辑成功!");
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
        userService.removeById(id);
        return result.success("删除成功!");
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @GetMapping(value = "/queryById")
    public Result<User> queryById(@RequestParam(name = "id") String id) {
        User user = userService.getById(id);
        if (user == null) {
            return Result.error("未找到对应数据", null);
        }
        return Result.OK(user);
    }

}