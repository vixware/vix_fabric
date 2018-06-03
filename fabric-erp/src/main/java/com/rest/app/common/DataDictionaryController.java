/**
 * 
 */
package com.rest.app.common;



import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.rest.core.base.BaseRestController;
import com.vix.core.utils.StrUtils;
import com.vix.core.validation.ValidateException;
import com.vix.system.enumeration.entity.Enumeration;
import com.vix.system.enumeration.service.IEnumerationService;

/**
 * @author Bluesnow
 * 2016年3月10日
 */
@Controller
@RequestMapping(value = "restService/app/common/dataDictionary")
public class DataDictionaryController extends BaseRestController {
	private static Logger logger = LoggerFactory.getLogger(DataDictionaryController.class);
	
	@Autowired
	private IEnumerationService enumerationService;
	
	/**
	 * @Title: post
	 * @Description: 通过编码  单一对象查询
	 */
	@RequestMapping(value = "query.rs", method = RequestMethod.POST)//, produces = MediaTypes.JSON_UTF_8
	public void query(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String enumerationCode = request.getParameter("enumerationCode");
		logger.info("单一查询:{}.",enumerationCode);
		Enumeration enumerationTemp = null;
		if(StrUtils.isNotEmpty(enumerationCode)){
			String hql = "select enumeration from Enumeration enumeration left join fetch enumeration.enumValues where enumeration.enumerationCode = :enumerationCode";
			Map<String,Object> params = new HashMap<String,Object>();
			params.put("enumerationCode", enumerationCode);
			Enumeration enumeration = enumerationService.findEnumerationByHql(hql,params);
			if (enumeration == null) {
				throw new ValidateException("没有查询到enumerationCode="+enumerationCode+"的分类字典！");
			}else{
				enumerationTemp = new Enumeration();
				BeanUtils.copyProperties(enumeration, enumerationTemp,new String[]{"enumerationCategory","enumValues"});
				enumerationTemp.setEnumValues(enumeration.getEnumValues());
			}
		}
		renderResult(response, true, "", enumerationTemp, "enumValues");
	}
}
