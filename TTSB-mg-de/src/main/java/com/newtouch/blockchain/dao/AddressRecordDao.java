package com.newtouch.blockchain.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.newtouch.blockchain.entity.AddressRecord;
import com.newtouch.blockchain.mybatis.MyBatisInterface;

@Repository
@MyBatisInterface
public interface AddressRecordDao {

	int deleteById(String id);

	int insert(AddressRecord record);

	int updateById(AddressRecord record);

	AddressRecord selectById(String id);

	List<AddressRecord> selectAll();

	AddressRecord findByUserId(@Param("userId") String userId);

	AddressRecord findByAddress(@Param("address") String address);
	
	//根据条件查询账号信息
	List<AddressRecord> findByUserIdOrAddress(AddressRecord addressRecord);

}