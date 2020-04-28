package com.newtouch.blockchain.mybatis;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Data;

/**
 * 实体对象基类
 * @author guanhongwei
 *
 */
@Data
public abstract class BaseEntity implements Serializable, Cloneable {

	/** 
	*/ 
	private static final long serialVersionUID = 2906022653764214903L;

	/** 主键· */
	protected String id;

	/** 首次插入时间 */
	protected LocalDateTime firstInsert;

	/** 最后修改时间 */
	protected LocalDateTime lastModified;

	/** 创建人 */
	protected String createUser;

	/** 修改人 */
	protected String updateUser;

	/** 是否已删除 */
	protected Boolean deleted = Boolean.FALSE;

	/** 删除原因 */
	protected String delReason;
	
}
