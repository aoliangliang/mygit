package com.newtouch.blockchain.dao;

import java.math.BigInteger;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.newtouch.blockchain.entity.Nonce;
import com.newtouch.blockchain.mybatis.MyBatisInterface;
import com.newtouch.blockchain.vo.QueryBillVO;

@Repository
@MyBatisInterface
public interface NonceDao {

	int deleteById(String id);

	int insert(Nonce record);

	int updateStatusById(Nonce record);

	Nonce selectById(String id);

	List<Nonce> selectAll();

	List<Nonce> selectAllQueQuan();

	List<Nonce> selectAllByOwner(@Param("owner") String owner);

	List<Nonce> selectAllByIssuer(@Param("issuer") String issuer);

	Nonce findByQueQuanHash(@Param("queQuanHash") String queQuanHash);

	List<Nonce> findTransferByAddress(@Param("address") String address);

	List<Nonce> findTransferByAddressForBalance(@Param("address") String address);
	
	List<Nonce> findByQueQuan(Nonce nonce);
	List<Nonce> findBillByParam(QueryBillVO queryBillVO);
	List<Nonce> findBillByEntity(QueryBillVO queryBillVO);
	
	BigInteger getMaxNonce();

	List<Nonce> selectNeedSend();

	List<Nonce> selectSent();

	Nonce findQueQuanHashOnChain(@Param("queQuanHash") String queQuanHash);

	int updateInitToSentById(Nonce record);

	int updateToSuccessById(Nonce record);
	
	List<Nonce> findNonceByEntity(QueryBillVO queryBillVO);

}