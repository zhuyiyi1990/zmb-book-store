package com.gitee.yoursmlie.bookstore.vo;

import lombok.Data;

import java.util.List;

@Data
public class ReturnVO {

    private Integer id;

    private List<ReturnDetailVO> returnList;

}