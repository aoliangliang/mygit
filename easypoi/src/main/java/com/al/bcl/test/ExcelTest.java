package com.al.bcl.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.al.bcl.entity.Person;
import com.al.bcl.util.ExcelUtils;

import lombok.extern.slf4j.Slf4j;
@Slf4j
public class ExcelTest {
	
	public static void exportExcel() throws IOException {
		long start = System.currentTimeMillis();
		List<Person> persons = new ArrayList<Person>();
		for(int i=0;i<5;i++) {
			Person person = new Person();
			person.setName("张三");
			person.setUsername("zhangsan");
			person.setPhone("12345678900");
			person.setImgUrl("http://p1.qhimg.com/bdm/720_444_0/t01028e5f2ec69e423d.jpg");
			persons.add(person);
		}
		log.info("导出Excel所花时间:"+(System.currentTimeMillis()-start));
		ExcelUtils.exportExcel(persons, "员工信息表", "员工信息", Person.class, "员工信息", null);
	}
	public static void main(String[] args) {
		
	}
}
