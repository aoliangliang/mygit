package com.newtouch.blockchain.validate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.validation.ConstraintViolation;

/**
 * 校验结果处理
 * @Package com.newtouch.blockchain.validate
 * @ClassName: ValidatorUtil
 */
public class ValidatorUtil {
	
	/**
	 * 是否有错误
	 * @param 
	 * @return boolean
	 */
	public static boolean hasError(Set<?> valRst) {
		if (valRst == null || valRst.isEmpty()) {
			return false;
		}
		return true;
	}
	
	/**
	 * 加工校验结果,做成 属性名为键messageCode为值的结果集
	 * @param 
	 * @return List<Map<String,String>>
	 */
	@SuppressWarnings("rawtypes")
	public static List<Map<String, String>> resolve(Set<?> valRst) {
		if (hasError(valRst)) {
			List<Map<String, String>> rst = new ArrayList<>(6);
			
			for (Object temp : valRst) {
				ConstraintViolation valRstTemp = (ConstraintViolation)temp;
				Map<String, String> map = new HashMap<>(1);
				map.put(valRstTemp.getPropertyPath().toString(), valRstTemp.getMessage());
				rst.add(map);
			}
			return rst;
		}
		return null;
	}
	
	/**
	 * 加工校验结果,取出messageCode
	 * @param 
	 * @return List<String>
	 */
	@SuppressWarnings("rawtypes")
	public static List<String> resolveCode(Set<?> valRst) {
		if (hasError(valRst)) {
			List<String> rst = new ArrayList<>(6);
			
			for (Object temp : valRst) {
				ConstraintViolation valRstTemp = (ConstraintViolation)temp;
				rst.add(valRstTemp.getMessage());
			}
			return rst;
		}
		return null;
	}

}
