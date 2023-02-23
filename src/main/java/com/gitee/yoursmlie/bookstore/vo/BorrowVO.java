package com.gitee.yoursmlie.bookstore.vo;

import lombok.Data;

import java.util.List;

@Data
public class BorrowVO {

    private Integer userId;

    private List<BorrowDetailVO> borrowList;

}