package com.newtouch.blockchain.constant;


/**
 * 消息国际化，代码
 * 编码规则：
 * 1.代码长度6位
 * 2.第一位为字母
 * 3.第一位异常一般代码，
 * 		C:Controller层异常；
 * 		S:Service层异常；
 * 		D:Dao层异常；
 * 		I:接口层异常；
 * 		B:业务异常
 * 4.后五位为数字递增，从00001至99999
 * 5.全部异常代码须写明用途
 * @ClassName: MessageCode
 */
public interface MessageCode {
	
	//~~B:业务异常
	
	/**
	  * 系统错误，请联系管理员
	  */
	 String B00001 = "B00001";

	 
	/**
	  * 名称不能为空
	  */
	 String B00002 = "B00002";

	 
	/**
	  * 代码不能为空
	  */
	 String B00003 = "B00003";

	 
	/**
	  * 名称字符长度过长
	  */
	 String B00004 = "B00004";

	 
	/**
	  * 代码字符长度过长
	  */
	 String B00005 = "B00005";

	 
	/**
	  * 数据已存在
	  */
	 String B00006 = "B00006";

	 
	/**
	  * 未查询到数据
	  */
	 String B00007 = "B00007";

	 
	/**
	  * 参数不能为空
	  */
	 String B00008 = "B00008";

	 
	/**
	  * 模板类型不能为空
	  */
	 String B00009 = "B00009";

	 
	/**
	  * 类型不能为空
	  */
	 String B00010 = "B00010";

	 
	/**
	  * 阶段下必需有工序
	  */
	 String B00011 = "B00011";

	 
	/**
	  * 该小类名称已存在
	  */
	 String B00012 = "B00012";

	 
	/**
	  * 该小类代码已存在
	  */
	 String B00013 = "B00013";

	 
	/**
	  * 该大类名称已存在
	  */
	 String B00014 = "B00014";

	 
	/**
	  * 该大类代码已存在
	  */
	 String B00015 = "B00015";

	 
	/**
	  * 用户名不存在
	  */
	 String B00016 = "B00016";

	 
	/**
	  * 用户名与密码不匹配
	  */
	 String B00017 = "B00017";

	 
	/**
	  * 未登录，或登录失效，请登录
	  */
	 String B00018 = "B00018";

	 
	/**
	  *  操作成功！
	  */
	 String B00019 = "B00019";

	 
	/**
	  * 不存在该角色,修改失败
	  */
	 String B00020 = "B00020";

	 
	/**
	  * 角色名称已存在,修改失败
	  */
	 String B00021 = "B00021";

	 
	/**
	  * 角色代码已存在,修改失败
	  */
	 String B00022 = "B00022";

	 
	/**
	  * 操作失败！
	  */
	 String B00023 = "B00023";

	 
	/**
	  * 该用户名已存在,修改失败
	  */
	 String B00024 = "B00024";

	 
	/**
	  * 区块高度不能小于零
	  */
	 String B00025 = "B00025";

	 
	/**
	  * 当前用户没有权限
	  */
	 String B00026 = "B00026";

	 
	/**
	  * 
	  */
	 String B00027 = "B00027";

	 
	/**
	  * 
	  */
	 String B00028 = "B00028";

	 
	/**
	  * 
	  */
	 String B00029 = "B00029";

	 
	/**
	  * 
	  */
	 String B00030 = "B00030";

	 
	/**
	  * 
	  */
	 String B00031 = "B00031";

	 
	/**
	  * 
	  */
	 String B00032 = "B00032";

	 
	/**
	  * 
	  */
	 String B00033 = "B00033";

	 
	/**
	  * 
	  */
	 String B00034 = "B00034";

	 
	/**
	  * 
	  */
	 String B00035 = "B00035";

	 
	/**
	  * 
	  */
	 String B00036 = "B00036";

	 
	/**
	  * 
	  */
	 String B00037 = "B00037";

	 
	/**
	  * 
	  */
	 String B00038 = "B00038";

	 
	/**
	  * 
	  */
	 String B00039 = "B00039";

	 
	/**
	  * 
	  */
	 String B00040 = "B00040";

	 
	/**
	  * 
	  */
	 String B00041 = "B00041";

	 
	/**
	  * 
	  */
	 String B00042 = "B00042";

	 
	/**
	  * 
	  */
	 String B00043 = "B00043";

	 
	/**
	  * 
	  */
	 String B00044 = "B00044";

	 
	/**
	  * 
	  */
	 String B00045 = "B00045";

	 
	/**
	  * 
	  */
	 String B00046 = "B00046";

	 
	/**
	  * 
	  */
	 String B00047 = "B00047";

	 

	
	//~~C:Controller层异常
	
	 /**
	  * 
	  */
	 String C00001 = "C00001";

	 
	/**
	  * 
	  */
	 String C00002 = "C00002";

	 
	/**
	  * 
	  */
	 String C00003 = "C00003";

	 
	/**
	  * 
	  */
	 String C00004 = "C00004";

	 
	/**
	  * 
	  */
	 String C00005 = "C00005";

	 
	/**
	  * 
	  */
	 String C00006 = "C00006";

	 
	/**
	  * 
	  */
	 String C00007 = "C00007";

	 
	/**
	  * 
	  */
	 String C00008 = "C00008";

	 
	/**
	  * 
	  */
	 String C00009 = "C00009";

	 
	/**
	  * 
	  */
	 String C00010 = "C00010";

	 
	/**
	  * 
	  */
	 String C00011 = "C00011";

	 
	/**
	  * 
	  */
	 String C00012 = "C00012";

	 
	/**
	  * 
	  */
	 String C00013 = "C00013";

	 
	/**
	  * 
	  */
	 String C00014 = "C00014";

	 
	/**
	  * 
	  */
	 String C00015 = "C00015";

	 
	/**
	  * 
	  */
	 String C00016 = "C00016";

	 
	/**
	  * 
	  */
	 String C00017 = "C00017";

	 
	/**
	  * 
	  */
	 String C00018 = "C00018";

	 
	/**
	  * 
	  */
	 String C00019 = "C00019";

	 
	/**
	  * 
	  */
	 String C00020 = "C00020";

	 
	/**
	  * 
	  */
	 String C00021 = "C00021";

	 
	/**
	  * 
	  */
	 String C00022 = "C00022";

	 
	/**
	  * 
	  */
	 String C00023 = "C00023";

	 
	/**
	  * 
	  */
	 String C00024 = "C00024";

	 
	/**
	  * 
	  */
	 String C00025 = "C00025";

	 
	/**
	  * 
	  */
	 String C00026 = "C00026";

	 
	/**
	  * 
	  */
	 String C00027 = "C00027";

	 
	/**
	  * 
	  */
	 String C00028 = "C00028";

	 
	/**
	  * 
	  */
	 String C00029 = "C00029";

	 
	/**
	  * 
	  */
	 String C00030 = "C00030";

	 
	/**
	  * 
	  */
	 String C00031 = "C00031";

	 
	/**
	  * 
	  */
	 String C00032 = "C00032";

	 
	/**
	  * 
	  */
	 String C00033 = "C00033";

	 
	/**
	  * 
	  */
	 String C00034 = "C00034";

	 
	/**
	  * 
	  */
	 String C00035 = "C00035";

	 
	/**
	  * 
	  */
	 String C00036 = "C00036";

	 
	/**
	  * 
	  */
	 String C00037 = "C00037";

	 
	/**
	  * 
	  */
	 String C00038 = "C00038";

	 
	/**
	  * 
	  */
	 String C00039 = "C00039";

	 
	/**
	  * 
	  */
	 String C00040 = "C00040";

	 
	/**
	  * 
	  */
	 String C00041 = "C00041";

	 
	/**
	  * 
	  */
	 String C00042 = "C00042";

	 
	/**
	  * 
	  */
	 String C00043 = "C00043";

	 
	/**
	  * 
	  */
	 String C00044 = "C00044";

	 
	/**
	  * 
	  */
	 String C00045 = "C00045";

	 
	/**
	  * 
	  */
	 String C00046 = "C00046";

	 
	/**
	  * 
	  */
	 String C00047 = "C00047";

		
	//~~D:Dao层异常
	
	/**
	  * 
	  */
	 String D00001 = "D00001";

	/**
	  * 
	  */
	 String D00002 = "D00002";

	/**
	  * 
	  */
	 String D00003 = "D00003";

	/**
	  * 
	  */
	 String D00004 = "D00004";

	/**
	  * 
	  */
	 String D00005 = "D00005";

	/**
	  * 
	  */
	 String D00006 = "D00006";

	/**
	  * 
	  */
	 String D00007 = "D00007";

	/**
	  * 
	  */
	 String D00008 = "D00008";

	/**
	  * 
	  */
	 String D00009 = "D00009";

	/**
	  * 
	  */
	 String D00010 = "D00010";

	/**
	  * 
	  */
	 String D00011 = "D00011";

	/**
	  * 
	  */
	 String D00012 = "D00012";

	/**
	  * 
	  */
	 String D00013 = "D00013";

	/**
	  * 
	  */
	 String D00014 = "D00014";

	/**
	  * 
	  */
	 String D00015 = "D00015";

	/**
	  * 
	  */
	 String D00016 = "D00016";

	/**
	  * 
	  */
	 String D00017 = "D00017";

	/**
	  * 
	  */
	 String D00018 = "D00018";

	/**
	  * 
	  */
	 String D00019 = "D00019";

	/**
	  * 
	  */
	 String D00020 = "D00020";

	/**
	  * 
	  */
	 String D00021 = "D00021";

	/**
	  * 
	  */
	 String D00022 = "D00022";

	/**
	  * 
	  */
	 String D00023 = "D00023";

	/**
	  * 
	  */
	 String D00024 = "D00024";

	/**
	  * 
	  */
	 String D00025 = "D00025";

	/**
	  * 
	  */
	 String D00026 = "D00026";

	/**
	  * 
	  */
	 String D00027 = "D00027";

	/**
	  * 
	  */
	 String D00028 = "D00028";

	/**
	  * 
	  */
	 String D00029 = "D00029";

	/**
	  * 
	  */
	 String D00030 = "D00030";

	/**
	  * 
	  */
	 String D00031 = "D00031";

	/**
	  * 
	  */
	 String D00032 = "D00032";

	/**
	  * 
	  */
	 String D00033 = "D00033";

	/**
	  * 
	  */
	 String D00034 = "D00034";

	/**
	  * 
	  */
	 String D00035 = "D00035";

	/**
	  * 
	  */
	 String D00036 = "D00036";

	/**
	  * 
	  */
	 String D00037 = "D00037";

	/**
	  * 
	  */
	 String D00038 = "D00038";

	/**
	  * 
	  */
	 String D00039 = "D00039";

	/**
	  * 
	  */
	 String D00040 = "D00040";

	/**
	  * 
	  */
	 String D00041 = "D00041";

	/**
	  * 
	  */
	 String D00042 = "D00042";

	/**
	  * 
	  */
	 String D00043 = "D00043";

	/**
	  * 
	  */
	 String D00044 = "D00044";

	/**
	  * 
	  */
	 String D00045 = "D00045";

	/**
	  * 
	  */
	 String D00046 = "D00046";

	/**
	  * 
	  */
	 String D00047 = "D00047";


	
	//~~I:接口层异常
	
	/**
	  * 
	  */
	 String I00001 = "I00001";

	/**
	  * 
	  */
	 String I00002 = "I00002";

	/**
	  * 
	  */
	 String I00003 = "I00003";

	/**
	  * 
	  */
	 String I00004 = "I00004";

	/**
	  * 
	  */
	 String I00005 = "I00005";

	/**
	  * 
	  */
	 String I00006 = "I00006";

	/**
	  * 
	  */
	 String I00007 = "I00007";

	/**
	  * 
	  */
	 String I00008 = "I00008";

	/**
	  * 
	  */
	 String I00009 = "I00009";

	/**
	  * 
	  */
	 String I00010 = "I00010";

	/**
	  * 
	  */
	 String I00011 = "I00011";

	/**
	  * 
	  */
	 String I00012 = "I00012";

	/**
	  * 
	  */
	 String I00013 = "I00013";

	/**
	  * 
	  */
	 String I00014 = "I00014";

	/**
	  * 
	  */
	 String I00015 = "I00015";

	/**
	  * 
	  */
	 String I00016 = "I00016";

	/**
	  * 
	  */
	 String I00017 = "I00017";

	/**
	  * 
	  */
	 String I00018 = "I00018";

	/**
	  * 
	  */
	 String I00019 = "I00019";

	/**
	  * 
	  */
	 String I00020 = "I00020";

	/**
	  * 
	  */
	 String I00021 = "I00021";

	/**
	  * 
	  */
	 String I00022 = "I00022";

	/**
	  * 
	  */
	 String I00023 = "I00023";

	/**
	  * 
	  */
	 String I00024 = "I00024";

	/**
	  * 
	  */
	 String I00025 = "I00025";

	/**
	  * 
	  */
	 String I00026 = "I00026";

	/**
	  * 
	  */
	 String I00027 = "I00027";

	/**
	  * 
	  */
	 String I00028 = "I00028";

	/**
	  * 
	  */
	 String I00029 = "I00029";

	/**
	  * 
	  */
	 String I00030 = "I00030";

	/**
	  * 
	  */
	 String I00031 = "I00031";

	/**
	  * 
	  */
	 String I00032 = "I00032";

	/**
	  * 
	  */
	 String I00033 = "I00033";

	/**
	  * 
	  */
	 String I00034 = "I00034";

	/**
	  * 
	  */
	 String I00035 = "I00035";

	/**
	  * 
	  */
	 String I00036 = "I00036";

	/**
	  * 
	  */
	 String I00037 = "I00037";

	/**
	  * 
	  */
	 String I00038 = "I00038";

	/**
	  * 
	  */
	 String I00039 = "I00039";

	/**
	  * 
	  */
	 String I00040 = "I00040";

	/**
	  * 
	  */
	 String I00041 = "I00041";

	/**
	  * 
	  */
	 String I00042 = "I00042";

	/**
	  * 
	  */
	 String I00043 = "I00043";

	/**
	  * 
	  */
	 String I00044 = "I00044";

	/**
	  * 
	  */
	 String I00045 = "I00045";

	/**
	  * 
	  */
	 String I00046 = "I00046";

	/**
	  * 
	  */
	 String I00047 = "I00047";


	
	//~~S:Service层异常
	
		/**
	  * 
	  */
	 String S00001 = "S00001";

	/**
	  * 
	  */
	 String S00002 = "S00002";

	/**
	  * 
	  */
	 String S00003 = "S00003";

	/**
	  * 
	  */
	 String S00004 = "S00004";

	/**
	  * 
	  */
	 String S00005 = "S00005";

	/**
	  * 
	  */
	 String S00006 = "S00006";

	/**
	  * 
	  */
	 String S00007 = "S00007";

	/**
	  * 
	  */
	 String S00008 = "S00008";

	/**
	  * 
	  */
	 String S00009 = "S00009";

	/**
	  * 
	  */
	 String S00010 = "S00010";

	/**
	  * 
	  */
	 String S00011 = "S00011";

	/**
	  * 
	  */
	 String S00012 = "S00012";

	/**
	  * 
	  */
	 String S00013 = "S00013";

	/**
	  * 
	  */
	 String S00014 = "S00014";

	/**
	  * 
	  */
	 String S00015 = "S00015";

	/**
	  * 
	  */
	 String S00016 = "S00016";

	/**
	  * 
	  */
	 String S00017 = "S00017";

	/**
	  * 
	  */
	 String S00018 = "S00018";

	/**
	  * 
	  */
	 String S00019 = "S00019";

	/**
	  * 
	  */
	 String S00020 = "S00020";

	/**
	  * 
	  */
	 String S00021 = "S00021";

	/**
	  * 
	  */
	 String S00022 = "S00022";

	/**
	  * 
	  */
	 String S00023 = "S00023";

	/**
	  * 
	  */
	 String S00024 = "S00024";

	/**
	  * 
	  */
	 String S00025 = "S00025";

	/**
	  * 
	  */
	 String S00026 = "S00026";

	/**
	  * 
	  */
	 String S00027 = "S00027";

	/**
	  * 
	  */
	 String S00028 = "S00028";

	/**
	  * 
	  */
	 String S00029 = "S00029";

	/**
	  * 
	  */
	 String S00030 = "S00030";

	/**
	  * 
	  */
	 String S00031 = "S00031";

	/**
	  * 
	  */
	 String S00032 = "S00032";

	/**
	  * 
	  */
	 String S00033 = "S00033";

	/**
	  * 
	  */
	 String S00034 = "S00034";

	/**
	  * 
	  */
	 String S00035 = "S00035";

	/**
	  * 
	  */
	 String S00036 = "S00036";

	/**
	  * 
	  */
	 String S00037 = "S00037";

	/**
	  * 
	  */
	 String S00038 = "S00038";

	/**
	  * 
	  */
	 String S00039 = "S00039";

	/**
	  * 
	  */
	 String S00040 = "S00040";

	/**
	  * 
	  */
	 String S00041 = "S00041";

	/**
	  * 
	  */
	 String S00042 = "S00042";

	/**
	  * 
	  */
	 String S00043 = "S00043";

	/**
	  * 
	  */
	 String S00044 = "S00044";

	/**
	  * 
	  */
	 String S00045 = "S00045";

	/**
	  * 
	  */
	 String S00046 = "S00046";

	/**
	  * 
	  */
	 String S00047 = "S00047";

	}