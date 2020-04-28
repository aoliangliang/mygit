package com.newtouch.blockchain.starter;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alibaba.druid.pool.DruidDataSource;

import cn.kklazy.repository.datasource.DynamicDataSourceConfigure;

/**
 * 
 * @since client 1.0
 * @author <a href="mailto:kklazy@live.cn">kk</a>
 */
@Configuration
public class LoadDataSourceConfigure {

	protected final static Log logger = LogFactory.getLog(LoadDataSourceConfigure.class);
	@Value("${ttsb-datasource.username}")
	private String username;
	@Value("${ttsb-datasource.password}")
	private String password;
	@Value("${ttsb-datasource.url}")
	private String url;
	@Value("${ttsb-datasource.driver}")
	private String driver;
//	@Autowired
//	private EncryptConfigure encryptConfigure;
//	@Autowired
//	private DatesourceManagerServiceRPC datesourceManagerServiceRpc;
	
	/**
	 * @return
	 * @throws Exception
	 */
	@Bean
	public DynamicDataSourceConfigure dynamicDataSource() throws Exception {
		return getDataSource();
	}
	
	/**
	 * mybatis 数据源
	 * @return
	 * @throws Exception
	 */
	@Bean(name = "dynamicDataSourceMybatis")
	public DynamicDataSourceConfigure dynamicDataSourceMybatis() throws Exception {
		return getDataSource();
	}
	
	public DynamicDataSourceConfigure getDataSource() throws Exception {

//		DatesourceManagerDTO allowip = datesourceManagerServiceRpc.find(LbiConstant.SYSTEM_CODE);
//
//		DatasourceConfigureDTO m = allowip.getConfigure();
//		List<DatasourceConfigureDTO> master = m.getSublevel();
//		String driver = StringUtils.EMPTY, url = StringUtils.EMPTY, username = StringUtils.EMPTY,
//				password = StringUtils.EMPTY;
//
//		EncryptDTO encrypt = m.getEncrypt();
//
//		for (DatasourceConfigureDTO dto : master) {
//			if (DatasourceConstants.DATABASE_DRIVER.equals(dto.getKey())) {
//				driver = dto.getValue();
//				continue;
//			}
//			if (DatasourceConstants.DATABASE_URL.equals(dto.getKey())) {
//				url = dto.getValue();
//				continue;
//			}
//			if (DatasourceConstants.DATABASE_USERNAME.equals(dto.getKey())) {
//				username = dto.getValue();
//				continue;
//			}
//			if (DatasourceConstants.DATABASE_PASSWORD.equals(dto.getKey())) {
//				password = dto.getValue();
//				continue;
//			}
//		}
//
//		PublicKey key = encryptConfigure.publicKey(encrypt.getPublicKey().getBytes(EncryptConstants.ENCODING),
//				encrypt.getSignAlgorithm());
//
//		if (StringUtils.isNotBlank(driver)) {
//			driver = decrypt(key, StringUtils.trim(driver));
//		}
//		url = decrypt(key, StringUtils.trim(url));
//		username = decrypt(key, StringUtils.trim(username));
//		password = decrypt(key, StringUtils.trim(password));
//
//		DruidDataSource datasource = new DruidDataSource();
//		if (StringUtils.isNotBlank(driver)) {
//			datasource.setDriverClassName(driver);
//		}
//		datasource.setUrl(url);
//		datasource.setUsername(username);
//		datasource.setPassword(password);
		DruidDataSource datasource = new DruidDataSource();
		datasource.setUrl(url);
		datasource.setUsername(username);
		datasource.setPassword(password);
		datasource.setDriverClassName(driver);
		Map<Object, Object> slave = new HashMap<Object, Object>();
		slave.put("slave1", datasource);
		return new DynamicDataSourceConfigure(datasource, slave);
	}

//	/**
//	 * @param key
//	 * @param cipherData
//	 * @return
//	 */
//	public String decrypt(PublicKey key, String cipherData) {
//		String retval = cipherData;
//		try {
//			if (cipherData.startsWith(EncryptConstants.ENCRYPT_PREFIX)) {
//				retval = encryptConfigure.decrypt(key, StringUtils.trim(cipherData.split(StringUtils.BLANK_SPACE)[1]));
//			}
//		} catch (Exception e) {
//			logger.error(e.getMessage(), e);
//			// 解密错误不用处理
//		}
//		return retval;
//	}

}
