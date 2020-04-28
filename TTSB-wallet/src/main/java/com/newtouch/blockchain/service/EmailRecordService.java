package com.newtouch.blockchain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.newtouch.blockchain.dao.EmailRecordDao;
import com.newtouch.blockchain.entity.EmailRecord;

@Service
public class EmailRecordService {
	@Autowired
	private EmailRecordDao emailRecordDao;
	
	/**
	 * 新增
	 * @param order
	 */
	public void save(EmailRecord emailRecord) {
		emailRecord.setStatus(EmailRecord.CODE_UNUSED);
		emailRecordDao.insert(emailRecord);
	}
	/**
	 * 修改
	 * @param order
	 */
	public void update(EmailRecord emailRecord) {
		emailRecordDao.updateById(emailRecord);
	}
	/*
	 * 查询所有
	 * @return
	 */
	public List<EmailRecord> findAll(){
		return emailRecordDao.selectAll();
	}
	
	/**
	 * 根据id查询
	 * @param id
	 * @return
	 */
	public EmailRecord findById(String id) {
		return emailRecordDao.selectById(id);
	}
}
