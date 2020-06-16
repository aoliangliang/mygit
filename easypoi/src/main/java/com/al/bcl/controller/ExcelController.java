package com.al.bcl.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.al.bcl.entity.Person;
import com.al.bcl.util.ExcelUtils;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("excel")
@Slf4j
public class ExcelController {
	
	@GetMapping("export")
	public void exportExcel(HttpServletResponse response) {
		try {
			long start = System.currentTimeMillis();
			List<Person> persons = new ArrayList<Person>();
			for(int i=0;i<5;i++) {
				Person person = new Person();
				person.setName("张三");
				person.setUsername("zhangsan");
				person.setPhone("12345678900");
				person.setImgUrl("D:\\Resources\\images\\96cf6211bcca15dd0f8065e106182cd1.jpg");
				persons.add(person);
			}
			log.info("导出Excel所花时间:"+(System.currentTimeMillis()-start));
			ExcelUtils.exportExcel(persons, "员工信息表", "员工信息", Person.class, "员工信息", response);
		} catch (IOException e) {
			log.error("{}", e.getMessage());
		}
	}
	
	/**
	 * 导入
	 *
	 * @param file
	 */
	@PostMapping(value = "/import")
	public String importExcel(@RequestParam("file") MultipartFile file) {
		String result = null;
	    try {
			long start = System.currentTimeMillis();
			List<Person> personVoList = ExcelUtils.importExcel(file, Person.class);
			log.debug(personVoList.toString());
			log.debug("导入excel所花时间：" + (System.currentTimeMillis() - start));
			result = "导入成功";
		} catch (IOException e) {
			log.error("{}", e.getMessage());
			result = "导入异常";
		}
	    return result;
	}
}
