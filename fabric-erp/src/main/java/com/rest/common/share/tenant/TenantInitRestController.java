package com.rest.common.share.tenant;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rest.core.base.BaseRestController;
import com.vix.common.common.bo.MessageObject;
import com.vix.common.org.entity.Organization;
import com.vix.common.org.service.IOrganizationService;
import com.vix.core.utils.JSONFlexUtils;
import com.vix.restService.common.tenant.service.ITenantRestService;
import com.vix.system.base.compOperation.vo.CompanyOperationVO;

import flexjson.JSONDeserializer;

/**
 * @ClassName: TenantInitRestController
 * @Description: 承租户的数据初始化
 * @author wangmingchen
 * @date 2015年3月11日 上午9:13:11
 */
@Controller
@RequestMapping(value = "restService/common/share/tenant")
public class TenantInitRestController extends BaseRestController {

	private static Logger logger = LoggerFactory.getLogger(TenantInitRestController.class);

	@Resource(name = "tenantRestService")
	private ITenantRestService tenantRestService;

	@Autowired
	private IOrganizationService organizationService;

	/**
	 * @Title: init @Description: VIX业务服务器 进行承租户的数据初始化 @param @param
	 *         request @param @param response @param @param
	 *         paramMo @param @return @param @throws Exception 设定文件 @return
	 *         MessageObject 返回类型 @throws
	 */
	@RequestMapping(value = "/init.rs", method = RequestMethod.POST)
	@ResponseBody
	public MessageObject init(HttpServletRequest request, HttpServletResponse response, MessageObject paramMo) throws Exception {// @RequestBody
		MessageObject mo = new MessageObject(true, null);
		if (paramMo == null || StringUtils.isEmpty(paramMo.getJsonObject())) {
			mo.setSuccess(false);
			mo.setMessage("承租户信息不能为空!");
			return mo;
		}
		try {
			CompanyOperationVO vo = new JSONDeserializer<CompanyOperationVO>().use(null, CompanyOperationVO.class).deserialize(paramMo.getJsonObject());
			logger.debug("开始业务服务器初始化承租户{}数据！", vo.getTenantId());
			logger.debug("行业模块:{}.", vo.getIndustryManagementModuleIds());
			Organization org = tenantRestService.save4InitTenant(vo);
			mo.setJsonObject(JSONFlexUtils.toJson(org));
			logger.debug("完成业务服务器初始化承租户{}数据！", org.getTenantId());
		} catch (Exception e) {
			e.printStackTrace();
			mo.setSuccess(false);
			mo.setMessage(e.getMessage());
		}
		logger.debug("完成业务服务器初始化承租户{}数据！", mo.isSuccess());
		return mo;
	}

	/**
	 * @Title: findOrgDataTree @Description: 获取整个承租户的 公司结构 @param @param
	 *         request @param @param response @param @param
	 *         paramMo @param @return @param @throws Exception 设定文件 @return
	 *         MessageObject 返回类型 @throws
	 */
	@RequestMapping(value = "/findOrgDataTree/{tenantId}.rs", method = RequestMethod.POST) 
	@ResponseBody
	public MessageObject findOrgDataTree(@PathVariable("tenantId") String tenantId, HttpServletRequest request, HttpServletResponse response) throws Exception {// @RequestBody
		MessageObject mo = new MessageObject();
		if (StringUtils.isEmpty(tenantId)) {
			mo.setSuccess(false);
			mo.setMessage("承租户标识不能为空!");
			return mo;
		}
		try {
			List<CompanyOperationVO> compList = tenantRestService.findOrgTreeByTenantId(tenantId);
			mo.setJsonObject(JSONFlexUtils.toJson(compList));
		} catch (Exception e) {
			e.printStackTrace();
			mo.setSuccess(false);
			mo.setMessage(e.getMessage());
		}
		return mo;
	}

	@RequestMapping(value = "/findOrgInfo.rs", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public MessageObject findOrgInfo(HttpServletRequest request, HttpServletResponse response) throws Exception {// @RequestBody
		String tenantId = request.getParameter("tenantId");
		MessageObject mo = new MessageObject();
		if (StringUtils.isEmpty(tenantId)) {
			mo.setSuccess(false);
			mo.setMessage("承租户标识不能为空!");
			return mo;
		}
		try {
			Organization org = organizationService.findOrganizationByTenantId(tenantId);
			mo.setSuccess(true);
			mo.setJsonObject(JSONFlexUtils.toJson(org));
		} catch (Exception e) {
			e.printStackTrace();
			mo.setSuccess(false);
			mo.setMessage(e.getMessage());
		}
		return mo;
	}
}
