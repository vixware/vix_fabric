package com.vix.common.base.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.util.ReflectionUtils;

import com.vix.common.org.service.IBaseOrganizationChargeOfService;
import com.vix.common.org.service.IBaseOrganizationService;
import com.vix.common.security.entity.ConfigUrlAdd;
import com.vix.common.security.entity.UserAccount;
import com.vix.common.security.service.IAuthorityService;
import com.vix.common.security.service.IUserAccountService;
import com.vix.common.security.tree.TreeNode;
import com.vix.common.security.util.DaysUtils;
import com.vix.common.security.util.SecuritySettingUtil;
import com.vix.common.security.util.SecurityUtil;
import com.vix.common.security.util.handler.VixAuthSuccessHandler;
import com.vix.common.share.entity.BaseEmployee;
import com.vix.common.share.entity.BaseOrganization;
import com.vix.common.share.entity.BaseOrganizationUnit;
import com.vix.core.constant.BizConstant;
import com.vix.core.constant.SearchCondition;
import com.vix.core.constant.SecurityConfigConstant;
import com.vix.core.constant.SecurityScope;
import com.vix.core.utils.CodeUtil;
import com.vix.core.utils.ContextUtil;
import com.vix.core.utils.JSONFlexUtils;
import com.vix.core.utils.SpringUtil;
import com.vix.core.utils.StrUtils;
import com.vix.core.utils.code.CodeDesUtil;
import com.vix.hr.organization.entity.Employee;
import com.vix.nvix.common.base.entity.HomeTemplateDetail;
import com.vix.nvix.oa.attendance.entity.PunchCardRecord;
import com.vix.nvix.oa.constant.PunchCardRecordConstant;
import com.vix.oa.personaloffice.service.IPersonalAttendanceService;
import com.vix.oa.task.taskDefinition.entity.VixTask;

@Controller
@Scope("request")
public class VixSecAction extends BaseSecAction {

	private static final long serialVersionUID = 1L;

	private String validateCode;
	private String zh;

	@Resource(name = "userAccountService")
	private IUserAccountService userAccountService;
	@Autowired
	private IUserAccountService userService;
	@Autowired
	private IAuthorityService authorityService;

	@Autowired
	private IBaseOrganizationService organizationService;
	@Autowired
	private IBaseOrganizationChargeOfService organizationChargeOfService;

	/** 返回结果页面 */
	private String goIndexPage = null;

	private String homeTemplateCode = null;

	private List<HomeTemplateDetail> homeTemplateDetailList = null;
	/**
	 * 跳转页面 根据客户端类型判断 pc pad mobile
	 */
	private String targetPage = null;
	/** 登录参数配置类型 */
	private String loginTypeKey = null;
	/** 登录界面 */
	private String loginPage = null;
	/** 其他应用系统到本系统进行登录验证时穿入的参数 验证成功后的重定向页面 */
	private String service = null;

	public String goLogin() {
		/** 设置默认模板路径 */
		getServletContext().setAttribute("path", "common");

		// setTenantId4Redirect();//如果是从其他应用跳转过来 则进行tenantId的提取
		// System.out.println("----------------------"+SecurityUtil.getCurrentUserAccount());
		Object userObj = getRequest().getSession().getAttribute(SecurityScope.USER_LOGIN_SUCCESS_USERINFO);
		if (userObj != null) {
			// 已经登录
			if (StrUtils.isNotEmpty(service)) {
				if (!StringUtils.containsIgnoreCase(service, "ticket")) {
					UserAccount ua = (UserAccount) userObj;
					String tenantIdTmp = ua.getTenantId();
					// String ticket = ua.getAccount()+"_"+tenantId;//暂时先测试
					// 后面在整理规则 并加密

					// String ticket = CodeUtil.encodeTicket(ua.getAccount(),
					// tenantId);
					String ticket = CodeDesUtil.encodeTicket(ua.getAccount(), tenantIdTmp);
					service = CodeUtil.appendParam4URL(service, "ticket", ticket, false);
				}
				return "redirectApp";
			} else {
				return "goDefaultAction";
			}
		}

		// System.out.println("-------------"+obj);

		if (StrUtils.isNotEmpty(service)) {
			Cookie redirectUrlCookie = new Cookie("VIX_REDIRECTURL", service);
			redirectUrlCookie.setMaxAge(-1);
			redirectUrlCookie.setPath(SecurityUtil.getCookiePath(getRequest()));

			ReflectionUtils.findMethod(Cookie.class, "setHttpOnly", boolean.class);
			getResponse().addCookie(redirectUrlCookie);
		}

		/**
		 * 后续需要判断客户端浏览器类型 进行登录页面的确认
		 */
		/*
		 * if (StrUtils.isNotEmpty(loginTypeKey)) { loginPage =
		 * SecurityConfigConstant.LOGIN_TYPE_PAGE_NAV_MAP.get(loginTypeKey);
		 * 
		 * Cookie loginTypeKeyCookie = new Cookie("VIX_LOGIN_TYPE_KEY",
		 * loginTypeKey); loginTypeKeyCookie.setMaxAge(-1);
		 * loginTypeKeyCookie.setPath(SecurityUtil.getCookiePath(getRequest()));
		 * ReflectionUtils.findMethod(Cookie.class, "setHttpOnly",
		 * boolean.class); getResponse().addCookie(loginTypeKeyCookie); }
		 */
		loginPage = "/WEB-INF/vixnt/common/login.jsp";

		return "goLogin";
	}

	private void initUserMenu() {
		String bizType = ContextUtil.getUserMenuContextType();// 登录客户端类型
		Object menuObj = getSession().getAttribute("userMenu");
		UserAccount userAccount = null;
		if (menuObj == null) {
			userAccount = SecurityUtil.getCurrentUserAccount();
			String userId = userAccount.getId();
			// Long userId = SecurityUtil.getCurrentUserId();
			try {
				// Set<TreeNode> menuObjSet =
				// authorityService.findMenuByUser(userId);
				String userResourceReadType = userAccount.getUserResourceReadType();
				Set<TreeNode> menuObjSet = null;
				if (StringUtils.isNotEmpty(userResourceReadType)) {
					if (userResourceReadType.equalsIgnoreCase(BizConstant.COMMON_SECURITY_USERMENUTYPE_C)) {
						menuObjSet = authorityService.findMenuByUser(userId, bizType);
					} else if (userResourceReadType.equalsIgnoreCase(BizConstant.COMMON_SECURITY_USERMENUTYPE_U)) {
						menuObjSet = authorityService.findMenuByUser2(userId, bizType);
					}
				} else {
					menuObjSet = authorityService.findMenuByUser(userId, bizType);
				}
				/*
				 * for(TreeNode t:menuObjSet){ System.out.println(t.getName());
				 * }
				 */
				// testMenu(menuObjSet);
				getSession().setAttribute("userMenu", menuObjSet);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		if (userAccount != null) {
			String tenantIdTmp = userAccount.getTenantId();
			if (StringUtils.isNotEmpty(tenantIdTmp)) {
				// 设定cookie
				setCookie(getRequest(), getResponse(), tenantIdTmp);
			}
		}

	}

	private void setCookie(HttpServletRequest request, HttpServletResponse response, String tenantId) {
		if (org.apache.commons.lang3.StringUtils.isNotEmpty(tenantId)) {
			Cookie tenantIdCookie = new Cookie("VIX_TENANTID", tenantId);
			tenantIdCookie.setMaxAge(-1);
			tenantIdCookie.setPath(SecurityUtil.getCookiePath(request));

			ReflectionUtils.findMethod(Cookie.class, "setHttpOnly", boolean.class);
			response.addCookie(tenantIdCookie);
		}
	}
	private List<PunchCardRecord> punchCardList;
	public String goIndex() throws Exception {
		String tenantIdTmp = null;
		try {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			if (null != auth) {
				UserAccount user = SecurityUtil.getCurrentUserAccount();
				tenantIdTmp = user.getTenantId();
				// getSession().setAttribute("userInfo", user);
				/*
				 * if(null != getLanguage() && "en".equals(getLanguage())){
				 * Locale.setDefault(Locale.ENGLISH); }else if( null !=
				 * getLanguage() && "zh".equals(getLanguage())){
				 * Locale.setDefault(Locale.CHINESE); }
				 * getServletContext().setAttribute("path", "common");
				 */

				initUserMenu();
				// goIndexPage = user.getLoginPage();

				String empId = SecurityUtil.getCurrentEmpId();

				String companyInnerCode = SecurityUtil.getCurrentUserAccountCompanyInnerCode();
				// 设定组织机构编码 和 数据过滤参数
				setCompanyInnerCode(user.getId(), companyInnerCode);
				// 设定帐号的职员所属部门编码
				setUserAccountEmpOrgUnit(empId, companyInnerCode);
				// 账号 代理信息
				setUserAccountProxy(empId);
				/** 是否签到 */
				Object isDoAttendanceObj = getSession().getAttribute(PunchCardRecordConstant.PCR_IS_DOATTENDANCE);
				if (StrUtils.objectIsNull(isDoAttendanceObj)) {
					punchCardList = loadPunchCardRecord();
					if (null != punchCardList && punchCardList.size() >= 1) {
						getSession().setAttribute(PunchCardRecordConstant.PCR_IS_DOATTENDANCE, "1");
					} else {
						getSession().setAttribute(PunchCardRecordConstant.PCR_IS_DOATTENDANCE, "0");
					}
				}
				/** 是否签退 */
				Object isOutAttendanceObj = getSession().getAttribute(PunchCardRecordConstant.PCR_IS_OUTATTENDANCE);
				if (StrUtils.objectIsNull(isOutAttendanceObj)) {
					if (null == punchCardList) {
						punchCardList = loadPunchCardRecord();
					}
					if (null != punchCardList && punchCardList.size() >= 2) {
						getSession().setAttribute(PunchCardRecordConstant.PCR_IS_OUTATTENDANCE, "1");
					} else {
						getSession().setAttribute(PunchCardRecordConstant.PCR_IS_OUTATTENDANCE, "0");
					}
				}
				/** 签到时间 */
				Object doAttendanceTimeObj = getSession().getAttribute(PunchCardRecordConstant.PCR_DOATTENDANCE_TIME);
				if (StrUtils.objectIsNull(doAttendanceTimeObj)) {
					if (null == punchCardList) {
						punchCardList = loadPunchCardRecord();
					}
					if (null != punchCardList && punchCardList.size() >= 1) {
						PunchCardRecord pcr = punchCardList.get(0);
						if (null != pcr && pcr.getPunchCardDate().length() >= 11) {
							getSession().setAttribute(PunchCardRecordConstant.PCR_DOATTENDANCE_TIME, pcr.getPunchCardDate().substring(11, pcr.getPunchCardDate().length()));
						}
					}
				}
				/** 签退时间 */
				Object outAttendanceTimeObj = getSession().getAttribute(PunchCardRecordConstant.PCR_OUTATTENDANCE_TIME);
				if (StrUtils.objectIsNull(outAttendanceTimeObj)) {
					if (null == punchCardList) {
						punchCardList = loadPunchCardRecord();
					}
					if (null != punchCardList && punchCardList.size() >= 2) {
						PunchCardRecord pcr = punchCardList.get(1);
						if (null != pcr && pcr.getPunchCardDate().length() >= 11) {
							getSession().setAttribute(PunchCardRecordConstant.PCR_OUTATTENDANCE_TIME, pcr.getPunchCardDate().substring(11, pcr.getPunchCardDate().length()));
						}
					}
				}
				// 当前未开始任务数量
				Integer noStartCount = 0;
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("status," + SearchCondition.EQUAL, "0");
				params.put("isTemp," + SearchCondition.EQUAL, "1");
				params.put("isDeleted," + SearchCondition.EQUAL, "0");
				params.put("employee.id," + SearchCondition.EQUAL, user.getEmployee().getId());
				params.put("complete," + SearchCondition.EQUAL, 0);
				List<VixTask> noStartVixTaskList = userService.findAllByConditions(VixTask.class, params);
				if(noStartVixTaskList != null && noStartVixTaskList.size() > 0){
					noStartCount =  noStartVixTaskList.size();
				}
				getRequest().setAttribute("noStartCount", noStartCount);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		if (SecurityUtil.getBindHomeTemplateCode() != null) {
			homeTemplateCode = SecurityUtil.getBindHomeTemplateCode();
		} else {
			// 设置默认的工作台
			homeTemplateCode = "NVIXNT_OA";
		}

		if (SecurityUtil.getBindHomeTemplateId() != null) {
			Map<String, Object> params = getParams();
			params.put("homeTemplate.id," + SearchCondition.EQUAL, SecurityUtil.getBindHomeTemplateId());
			homeTemplateDetailList = baseHibernateService.findAllByConditions(HomeTemplateDetail.class, params);
		}

		getServletContext().setAttribute("path", "common");

		ConfigUrlAdd urlConfig = organizationService.findTargetUrlOfTenantByTenantId(tenantIdTmp);
		if (urlConfig != null) {
			goIndexPage = urlConfig.getUrl();
			String isChain = urlConfig.getIsRedirect();

			if (isChain.endsWith("Y")) {
				return "goTenantChain";
			} else {
				return "goTenant";
			}
		}

		if (StringUtils.isEmpty(goIndexPage)) {
			goIndexPage = SecurityConfigConstant.SECURITY_LOGIN_PAGE_0;
		}
		return "goIndex";

		/*
		 * 2014 08 31 又要求使用tenantId的定制登录方式 下面全部取消
		 * 
		 * //判断登录账户是否配置承租户id USERACCOUNT_CONFIG_URL_NO_TENANTID
		 * if(StringUtils.isEmpty(goIndexPage)){ goIndexPage =
		 * SecurityConfigConstant.SECURITY_LOGIN_PAGE_0_key; }
		 * 
		 * goIndexPage =
		 * SecurityConfigConstant.SECURITY_LOGIN_PAGE_KEY.get(goIndexPage);
		 * 
		 * return "goIndex";
		 */
	}
	@Autowired
	private IPersonalAttendanceService personalAttendanceService;
	private List<PunchCardRecord> loadPunchCardRecord() throws Exception {
		Map<String, Object> params = getParams();
		Employee emp = getEmployee();
		if (null != emp) {
			params.put("userCode," + SearchCondition.EQUAL, emp.getCode());
		}
		params.put("recordDate," + SearchCondition.BETWEENAND, DaysUtils.getBeginDay(new Date()) + "!" + DaysUtils.getEndDay(new Date()));
		return personalAttendanceService.findAllTopEntityByCountAndConditions(PunchCardRecord.class, "recordDate", "asc", 2, params);
	}

	/** 获取职员姓名 */
	public Employee getEmployee() {
		Employee emp = null;
		try {
			String empId = SecurityUtil.getCurrentEmpId();
			if (empId != null) {
				emp = baseHibernateService.findEntityById(Employee.class, empId);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return emp;
	}
	/**
	 * @throws IOException
	 * @throws ServletException
	 * @Title: goLoginMa @Description: 手动API进行登录的验证 参数 ticket @param
	 *         设定文件 @return void 返回类型 @throws
	 */
	public String goLoginMa() throws ServletException, IOException {
		getServletContext().setAttribute("path", "common");
		// j_username
		// j_password
		// tenantId
		String ticketTmp = getRequest().getParameter("ticket");
		String[] uaInfo = CodeDesUtil.decodeTicket(ticketTmp);

		UserDetails ud = null;

		if (uaInfo != null) {
			// String tenantId = uaInfo[0];//承租户id
			String userAccount = uaInfo[1];// 账户名
			// System.out.println(tenantId + "-----" +userAccount);
			// ua = userService.findUserAccountByTicket(ticketTmp);
			// ud = userAccountService.loadUserByUsername(userAccount,
			// tenantId);
			ud = userAccountService.loadUserByUsername(userAccount);
			// 登录成功
			// .modifyCurrentUserCompanyInnerCode(departmentCode,
			// companyInnerCode);
		}
		if (ud == null) {
			return null;
		}

		UserAccount ua = (UserAccount) ud;
		Authentication authentication = SecuritySettingUtil.doAuthLogin(ua, getRequest(), getResponse());
		VixAuthSuccessHandler vixAuthSuccessHandler = SpringUtil.getBean("authSuccessHandler");
		vixAuthSuccessHandler.onAuthenticationSuccess(getRequest(), getResponse(), authentication);
		// return "goLoginMa";//chain到goLogin
		return null;
	}

	/**
	 * 设置companyInnerCode的上下文
	 * 
	 * @throws Exception
	 */
	private void setCompanyInnerCode(String userAccountId, String companyInnerCode) throws Exception {
		String empId = SecurityUtil.getCurrentEmpId();
		// 如果登录账号存在职员则从志愿中读取相关信息 否则读取自身信息
		if (empId != null) {
			BaseOrganization org = organizationService.findCompByEmpId(empId);// 所在公司
			setUserOrgDataAttr(org, companyInnerCode);
		} else {
			// 如果登录账户没有职员 则查询一下是否是公司管理员
			BaseOrganization org = organizationService.findCompByCompSuperAdminId(userAccountId);
			if (org != null) {
				setUserOrgDataAttr(org, companyInnerCode);
			} else {
				// 该登录账户既不是公司管理员 也没有普通职员信息
				ContextUtil.setSessionAttr(SecurityScope.USER_ORG_TYPE, BizConstant.COMMON_BOOLEAN_NO);
				ContextUtil.setSessionAttr(SecurityScope.USER_ORG_INNERCODE, companyInnerCode);// SecurityUtil.getCurrentUserAccountInnerCode()
				ContextUtil.setSessionAttr(SecurityScope.USER_ORG_DATAFILTERTYPE, SecurityScope.USER_ORG_DATAFILTERTYPE_P);
			}

		}

	}

	// 设定账号职员的所属部门
	private void setUserAccountEmpOrgUnit(String empId, String orgUnitCode) throws Exception {
		// String empId = SecurityUtil.getCurrentEmpId();
		if (StringUtils.isNotEmpty(orgUnitCode)) {
			ContextUtil.setSessionAttr(SecurityScope.USERACCOUNT_EMP_ORGUNIT_CODE, orgUnitCode);
			return;
		}
		if (empId != null) {
			BaseEmployee emp = organizationService.findEntityById(BaseEmployee.class, empId);
			String orgUnitId = emp.getOrganizationUnitId();
			if (StringUtils.isNotEmpty(orgUnitId) && !orgUnitId.equals("0")) {// if(orgUnitId!=null
																				// &&
																				// orgUnitId>0){
				BaseOrganizationUnit orgUnit = organizationService.findEntityById(BaseOrganizationUnit.class, orgUnitId);
				orgUnitCode = orgUnit.getCode();
				if (StringUtils.isNotEmpty(orgUnitCode)) {
					ContextUtil.setSessionAttr(SecurityScope.USERACCOUNT_EMP_ORGUNIT_CODE, orgUnitCode);
				}
			}

		}
	}

	// 设定代理的 公司 或者 部门信息
	private void setUserAccountProxy(String empId) throws Exception {
		if (empId != null) {
			Set<BaseOrganization> proxyOrgSet = organizationChargeOfService.findChargeOfOrgByEmpId(empId);
			Set<BaseOrganizationUnit> proxyOrgUnitSet = organizationChargeOfService.findChargeOfOrgUnitByEmpId(empId);

			// 首页使用
			ContextUtil.setSessionAttr(SecurityScope.USERACCOUNT_EMP_PROXY_ORG_OBJ, proxyOrgSet);
			ContextUtil.setSessionAttr(SecurityScope.USERACCOUNT_EMP_PROXY_ORGUNIT_OBJ, proxyOrgUnitSet);

			if (proxyOrgSet != null && !proxyOrgSet.isEmpty()) {
				/*
				 * List<String> proxyOrgId = new ArrayList<String>();
				 * for(BaseOrganization po : proxyOrgSet){
				 * proxyOrgId.add(po.getId()); }
				 */
				List<String> proxyOrgId = new ArrayList<String>();
				for (BaseOrganization po : proxyOrgSet) {
					proxyOrgId.add("'" + po.getCompanyInnerCode() + "'");
				}
				// ContextUtil.setSessionAttr(SecurityScope.USERACCOUNT_EMP_PROXY_ORG_IDS,
				// StringUtils.join(proxyOrgId, ","));
				ContextUtil.setSessionAttr(SecurityScope.USERACCOUNT_EMP_PROXY_ORG_IDS, proxyOrgSet);
			}

			if (proxyOrgUnitSet != null && !proxyOrgUnitSet.isEmpty()) {
				/*
				 * List<String> proxyOrgUnitId = new ArrayList<String>();
				 * for(BaseOrganizationUnit pu : proxyOrgUnitSet){
				 * proxyOrgUnitId.add(pu.getId()); }
				 */
				List<String> proxyOrgUnitId = new ArrayList<String>();
				for (BaseOrganizationUnit pu : proxyOrgUnitSet) {
					proxyOrgUnitId.add("'" + pu.getCode() + "'");
				}
				// ContextUtil.setSessionAttr(SecurityScope.USERACCOUNT_EMP_PROXY_ORGUNIT_IDS,
				// StringUtils.join(proxyOrgUnitId, ","));
				ContextUtil.setSessionAttr(SecurityScope.USERACCOUNT_EMP_PROXY_ORGUNIT_IDS, proxyOrgUnitSet);
			}

		}
	}

	private void setUserOrgDataAttr(BaseOrganization org, String orgInnerCode) throws Exception {
		/*
		 * String orgInnerCode = ""; if(org!=null &&
		 * StrUtils.isNotEmpty(org.getCompanyInnerCode())){ orgInnerCode =
		 * org.getCompanyInnerCode(); }
		 */
		String orgInnerCodeTmp = orgInnerCode;
		if (StringUtils.isEmpty(orgInnerCode)) {
			orgInnerCodeTmp = org.getCompanyInnerCode();
		}
		// Organization orgTop =
		// organizationService.findCompTopByCompInnerCode(org.getCompanyInnerCode());
		if (org != null && org.getParentOrganization() == null) {// StrUtils.isNotEmpty(orgTop)
																	// &&
																	// orgTop.equals(SecurityScope.USER_ORG_DATAFILTERTYPE_N)
			// StrUtils.isNotEmpty(orgInnerCode)
			// orgInnerCode = orgInnerCode.substring(0, 2);
			ContextUtil.setSessionAttr(SecurityScope.USER_ORG_TYPE, BizConstant.COMMON_BOOLEAN_YES);
		} else {
			ContextUtil.setSessionAttr(SecurityScope.USER_ORG_TYPE, BizConstant.COMMON_BOOLEAN_NO);
		}

		ContextUtil.setSessionAttr(SecurityScope.USER_ORG_INNERCODE, orgInnerCodeTmp);

		String orgDataFilterType = SecurityScope.USER_ORG_DATAFILTERTYPE_N;

		if (StrUtils.isNotEmpty(orgInnerCodeTmp)) {
			BaseOrganization orgTop = organizationService.findCompTopByCompInnerCode(orgInnerCodeTmp);
			if (orgTop != null && StrUtils.isNotEmpty(orgTop.getOrgDataFilterType())) {
				orgDataFilterType = orgTop.getOrgDataFilterType();
			}
		}
		ContextUtil.setSessionAttr(SecurityScope.USER_ORG_DATAFILTERTYPE, orgDataFilterType);
	}

	/**
	 * 跳转默认界面
	 * 
	 * @return
	 */
	public String goDefaultPage() {
		// return "defaultPage";

		// 根据自己方法名 在 SecurityConfigConstant 中的 对应的 key SECURITY_LOGIN_PAGE_0_key
		// 在根据客户端访问类型 来拿到 应该跳转的首页
		// 默认key 的 页面
		targetPage = SecurityConfigConstant.getUserIndexPage(SecurityConfigConstant.SECURITY_LOGIN_PAGE_0_key);
		// targetPage = "/WEB-INF/content/common/vix_index.jsp";
		if (StringUtils.isEmpty(targetPage)) {
			// 如果没有拿到 则返系统默认首页面
			targetPage = "defaultPage";
		}
		UserAccount user = SecurityUtil.getCurrentUserAccount();
		if (user != null) {
			if ("superadmin".equals(user.getAccount())) {
				targetPage = "/WEB-INF/content/common/vix_index.jsp";
			} else if ("testwx".equals(user.getAccount())) {
				targetPage = "/WEB-INF/wx/wxp/page/tenantIndex.jsp";
			} else {
				targetPage = "/WEB-INF/vixnt/common/nvix_index.jsp";
			}
		} else {
			targetPage = "/WEB-INF/vixnt/common/nvix_index.jsp";
		}

		return "indexPage";
	}

	/**
	 * 
	 * @return public String goDefaultMobilePage(){ //return "defaultPage";
	 * 
	 *         //根据自己方法名 在 SecurityConfigConstant 中的 对应的 key
	 *         SECURITY_LOGIN_PAGE_0_key 在根据客户端访问类型 来拿到 应该跳转的首页 //默认key 的 页面
	 *         targetPage =
	 *         SecurityConfigConstant.getUserIndexPage(SecurityConfigConstant.
	 *         SECURITY_LOGIN_PAGE_0_key); if(StringUtils.isEmpty(targetPage)){
	 *         //如果没有拿到 则返系统默认首页面 targetPage = "defaultPage"; } return
	 *         "indexPage"; }
	 */

	public String goSetup() {
		return "goSetup";
	}

	public String goNvix() {
		return "goNvix";
	}

	/** 载入内容区 */
	public String loadContent() {
		String target = getRequest().getParameter("target");
		if (null != target && !"".equals(target)) {
			return target;
		}
		return "content";
	}

	/**
	 * 从url中提取tenantId public void setTenantId4Redirect(){ //如果是从其他应用跳转过来进行 重定向
	 * if(StrUtils.isNotEmpty(redirectAppTargetUrl)){ String tenantIdTmp =
	 * URLParamsUtil.getTenantIdByURL(redirectAppTargetUrl);
	 * if(StringUtils.isEmpty(tenantIdTmp)){
	 * //System.out.println("登录验证TENANTID不存在！");
	 * logger.error("登录验证TENANTID不存在！"); }else{
	 * getRequest().setAttribute("tenantId", tenantIdTmp); }
	 * 
	 * } }
	 */
	/**
	 * 设定重定向URL参数 并追加ticket
	 * 
	 * public boolean setRedirectURLPage4Redirect(){
	 * if(StrUtils.isNotEmpty(redirectAppTargetUrl)){ UserAccount ua =
	 * SecurityUtil.getCurrentUserAccount(); String tenantId = ua.getTenantId();
	 * 
	 * String ticket = ua.getAccount()+"_"+tenantId;//暂时先测试 后面在整理规则 并加密
	 * redirectAppTargetUrl = CodeUtil.appendParam4URL(redirectAppTargetUrl,
	 * "ticket", ticket, false); return true; } return false; }
	 */

	/**
	 * 根据ticket得到账户信息
	 * 
	 * @throws Exception
	 */
	public void getUserInforByTicket() throws Exception {
		String ticketTmp = getRequest().getParameter("ticket");
		logger.info("测试ticket");

		// String[] uaInfo = CodeUtil.decodeTicket(ticketTmp);
		String[] uaInfo = CodeDesUtil.decodeTicket(ticketTmp);

		UserAccount ua = null;

		if (uaInfo != null) {
			// String tenantId = uaInfo[0];//承租户id
			// String userAccount = uaInfo[1];//账户名
			// System.out.println(tenantId + "-----" +userAccount);
			ua = userService.findUserAccountByTicket(ticketTmp);
			BaseEmployee employee = ua.getEmployee();
			if (employee != null) {
				ua.setEmployeeName(employee.getName());
			}
			// renderJson(JSONFlexUtils.toJson(ua));
			// renderJson(JSONFlexUtils.toJsonInclude(ua,new
			// String[]{"account","enable"}));
			renderJson(JSONFlexUtils.toJson(ua, new String[]{"password", "passwordConfirm", "employee", "roles", "passwordConfirm", "allAuthoritys", "allRoleCodes", "allArantedAuthorities"}));
		} else {
			renderJson(JSONFlexUtils.toJson(ua));
		}

	}

	/**
	 * @Title: handlerSetUserCompanyInnerCodeByFg @Description: 设置分管公司 @param
	 *         设定文件 @return void 返回类型 @throws
	 */
	public String handlerSetUserCompanyInnerCodeByFg() {
		// UserAccount ua = SecurityUtil.getCurrentUserAccount();
		// System.out.println("CompanyInnerCode:"+ua.getCompanyInnerCode()+";DepartmentCode:"+ua.getDepartmentCode());
		String fgCompanyInnerCode = getRequest().getParameter("fgCompanyInnerCode");
		if (StringUtils.isNotEmpty(fgCompanyInnerCode)) {
			SecuritySettingUtil.modifyCurrentUserCompanyInnerCode(null, fgCompanyInnerCode);
		}
		// ua = SecurityUtil.getCurrentUserAccount();
		// System.out.println("更改后
		// ：CompanyInnerCode:"+ua.getCompanyInnerCode()+";DepartmentCode:"+ua.getDepartmentCode());
		return "goDefaultAction";
	}

	// 获得客户端真实IP地址的方法二：
	public String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	public String goStaffSelf() {
		return "staffSelf";
	}

	public String goSupportedPlatform() {
		return "goSupportedPlatform";
	}

	public String goAboutUs() {
		return "goAboutUs";
	}

	public String goAbout() {
		return "goAbout";
	}

	public String getValidateCode() {
		return validateCode;
	}

	public void setValidateCode(String validateCode) {
		this.validateCode = validateCode;
	}

	public String getZh() {
		return zh;
	}

	public String goPersonalInformation() {
		return "goPersonalInformation";
	}

	public void setZh(String zh) {
		this.zh = zh;
	}

	public String getGoIndexPage() {
		return goIndexPage;
	}

	public void setGoIndexPage(String goIndexPage) {
		this.goIndexPage = goIndexPage;
	}

	public String getTargetPage() {
		return targetPage;
	}

	public void setTargetPage(String targetPage) {
		this.targetPage = targetPage;
	}

	public String getLoginTypeKey() {
		return loginTypeKey;
	}

	public void setLoginTypeKey(String loginTypeKey) {
		this.loginTypeKey = loginTypeKey;
	}

	public String getLoginPage() {
		return loginPage;
	}

	public void setLoginPage(String loginPage) {
		this.loginPage = loginPage;
	}

	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}

	/**
	 * @return the homeTemplateCode
	 */
	public String getHomeTemplateCode() {
		return homeTemplateCode;
	}

	/**
	 * @param homeTemplateCode
	 *            the homeTemplateCode to set
	 */
	public void setHomeTemplateCode(String homeTemplateCode) {
		this.homeTemplateCode = homeTemplateCode;
	}

	/**
	 * @return the homeTemplateDetailList
	 */
	public List<HomeTemplateDetail> getHomeTemplateDetailList() {
		return homeTemplateDetailList;
	}

	/**
	 * @param homeTemplateDetailList
	 *            the homeTemplateDetailList to set
	 */
	public void setHomeTemplateDetailList(List<HomeTemplateDetail> homeTemplateDetailList) {
		this.homeTemplateDetailList = homeTemplateDetailList;
	}

	/**
	 * @return the punchCardList
	 */
	public List<PunchCardRecord> getPunchCardList() {
		return punchCardList;
	}

	/**
	 * @param punchCardList
	 *            the punchCardList to set
	 */
	public void setPunchCardList(List<PunchCardRecord> punchCardList) {
		this.punchCardList = punchCardList;
	}

}