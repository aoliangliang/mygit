package com.al.bcl.entity;

import java.io.Serializable;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

@Data
public class Person implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = 8017842551482491437L;
	
	@Excel(name = "姓名",orderNum = "0",width = 15)
	private String name;
	
	@Excel(name = "用户名",orderNum = "1",width = 15)
	private String username;
	
	@Excel(name = "手机号码",orderNum = "2",width = 15)
	private String phone;
	
	@Excel(name = "图片",orderNum = "3",width = 15,height = 50,type = 2)
	private String imgUrl;
}
