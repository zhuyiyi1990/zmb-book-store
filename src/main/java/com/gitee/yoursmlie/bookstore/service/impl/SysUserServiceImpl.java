package com.gitee.yoursmlie.bookstore.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gitee.yoursmlie.bookstore.entity.SysUser;
import com.gitee.yoursmlie.bookstore.mapper.SysUserMapper;
import com.gitee.yoursmlie.bookstore.service.ISysUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {

    @Autowired
    private SysUserMapper userMapper;

}