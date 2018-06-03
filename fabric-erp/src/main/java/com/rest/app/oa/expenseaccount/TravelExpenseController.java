package com.rest.app.oa.expenseaccount;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.rest.core.base.BaseRestController;
import com.rest.core.exception.RestException;
import com.vix.core.utils.StrUtils;
import com.vix.core.validation.ValidateException;
import com.vix.core.validation.ValidatonUtil;
import com.vix.core.validation.Validator;
import com.vix.core.web.Pager;
import com.vix.oa.travelclaims.entity.TravelExpense;
import com.vix.traceability.service.ITraceabilityService;

/**
 * 报销接口
 * 
 * @ClassFullName com.rest.app.oa.expenseaccount.TravelExpenseController
 *
 * @author bjitzhang
 *
 * @date 2016年1月15日
 *
 */
@Controller
@RequestMapping(value = "restService/app/travelexpense")
public class TravelExpenseController extends BaseRestController {

	@Resource(name = "traceabilityService")
	private ITraceabilityService traceabilityService;

	/**
	 * 获取报销单列表
	 * 
	 * @param request
	 * @param response
	 * @param travelExpense
	 * @throws Exception
	 */
	@RequestMapping(value = "findTravelExpenseList.rs", method = RequestMethod.POST)
	public void findTravelExpenseList(HttpServletRequest request, HttpServletResponse response, TravelExpense travelExpense) throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		List<TravelExpense> travelExpenseList = traceabilityService.findAllDataByConditions(TravelExpense.class, params);
		List<TravelExpense> cList = new ArrayList<TravelExpense>();
		for (TravelExpense c : travelExpenseList) {
			TravelExpense t = new TravelExpense();
			BeanUtils.copyProperties(c, t, new String[] { "" });
			cList.add(t);
		}
		renderResult(response, cList);
	}

	/**
	 * 获取报销单分页列表
	 * 
	 * @param request
	 * @param response
	 * @param pager
	 * @param travelExpense
	 * @throws Exception
	 */
	@RequestMapping(value = "findTravelExpensePager.rs", method = RequestMethod.POST)
	public void findTravelExpensePager(HttpServletRequest request, HttpServletResponse response, Pager pager, TravelExpense travelExpense) throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		if (StrUtils.isNotEmpty(travelExpense.getName())) {
			params.put("name", travelExpense.getName());
		}
		if (StrUtils.isNotEmpty(travelExpense.getCode())) {
			params.put("code", travelExpense.getCode());
		}
		Pager pagerRes = traceabilityService.findPagerByHqlConditions(pager, TravelExpense.class, params);
		renderResultPager(response, pagerRes);
	}

	/**
	 * 根据ID查询报销单详情
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "query.rs", method = RequestMethod.POST)
	public void query(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id = request.getParameter("id");
		TravelExpense t = null;
		if (StringUtils.isNotEmpty(id)) {
			TravelExpense travelExpense = traceabilityService.findEntityByAttributeNoTenantId(TravelExpense.class, "id", id);
			String message = "";
			if (travelExpense == null) {
				message = "对象不存在(id:" + id + ")";
				throw new RestException(HttpStatus.NOT_FOUND, message);
			} else {
				t = new TravelExpense();
				BeanUtils.copyProperties(travelExpense, t, new String[] { "" });
			}
		}
		renderResult(response, t);
	}

	/**
	 * 保存报销单
	 * 
	 */
	@RequestMapping(value = "saveTravelExpense.rs", method = RequestMethod.POST)
	public void saveTravelExpense(HttpServletRequest request, HttpServletResponse response, TravelExpense travelExpense) throws Exception {
		//方式2 使用简单验证器
		Map<String, String> valMsgMap = ValidatonUtil.validator(new Validator() {
			@Override
			protected void validate() {
				//String field, String errorKey, String errorMessage
				//field 对应的实体的属性， errorKey 是自定义的唯一标识，errorMessage 是错误提示
				//如果是自定义的特殊格式教研，请使用 addError
				//addError("moduleCodeKey", "模块编码重复！");
				//System.out.println(getMsgMap());
				validateRequiredString("code", "codeMsg", "请输编码!");
				validateRequiredString("name", "nameMsg", "请输名称!");
			}
		}, travelExpense, false);
		if (!valMsgMap.isEmpty()) {
			renderResultNotValid(response, valMsgMap);
			return;
		}
		travelExpense = traceabilityService.mergeOriginal(travelExpense);
		renderResult(response, travelExpense);
	}

	/**
	 * 删除报销单
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "delete.rs", method = RequestMethod.POST)
	public void delete(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id = request.getParameter("id");
		if (StringUtils.isNotEmpty(id)) {
			TravelExpense travelExpense = traceabilityService.findEntityByAttributeNoTenantId(TravelExpense.class, "id", id);
			if (travelExpense == null) {
				throw new ValidateException("没有查询到待删除的报销单！");
			} else {
				traceabilityService.deleteByEntity(travelExpense);
			}
		}
		renderResult(response, null);
	}
}
