/**
 * 
 */
package com.vix.common.code;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vix.common.id.ICodeGenerator;
import com.vix.common.id.generator.CodeGenerator;
import com.vix.core.constant.SearchCondition;
import com.vix.core.persistence.hibernate.service.IBaseHibernateService;
import com.vix.system.code.entity.EncodingRulesTableInTheMiddle;

/**
 * 自动根据条件生成编码
 * 
 * @author zhanghaibing
 * 
 * @date 2013-10-15
 */
@Component("autoCreateCode")
@Transactional
public class AutoCreateCode {
	@Autowired
	private IBaseHibernateService baseHibernateService;

	// 根据单据类型获取编码
	public String getBillNO(String billType) throws Exception {
		String code = "";
		ICodeGenerator codeGenerator = new CodeGenerator();
		List<EncodingRulesTableInTheMiddle> encodingRulesTableInTheMiddleList = new ArrayList<EncodingRulesTableInTheMiddle>();
		Boolean isOpenTime = false;
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("billType," + SearchCondition.ANYLIKE, billType);
		encodingRulesTableInTheMiddleList = baseHibernateService.findAllByConditions(EncodingRulesTableInTheMiddle.class, params);
		if (encodingRulesTableInTheMiddleList != null && encodingRulesTableInTheMiddleList.size() > 0) {
			for (EncodingRulesTableInTheMiddle encodingRulesTableInTheMiddle : encodingRulesTableInTheMiddleList) {
				if (encodingRulesTableInTheMiddle != null) {
					if (encodingRulesTableInTheMiddle.getIsOpenTime() != null && encodingRulesTableInTheMiddle.getIsOpenTime() == 1) {
						isOpenTime = true;
					}
					if ("B".equals(encodingRulesTableInTheMiddle.getCodeType())) {
						code = codeGenerator.createRandomCode();
					} else if ("C".equals(encodingRulesTableInTheMiddle.getCodeType())) {
						code = codeGenerator.getCode(encodingRulesTableInTheMiddle.getSequenceID(), encodingRulesTableInTheMiddle.getTimeFormat(), encodingRulesTableInTheMiddle.getSerialNumberBegin(), encodingRulesTableInTheMiddle.getSerialNumberEnd(), encodingRulesTableInTheMiddle.getSerialNumberStep(), encodingRulesTableInTheMiddle.getCodeLength(), isOpenTime);
					} else if ("A".equals(encodingRulesTableInTheMiddle.getCodeType())) {
						code = "";
					}
				}
			}
		}
		return code;
	}
}
