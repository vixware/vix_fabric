package com.rest.common.account;

import java.util.Date;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.rest.common.account.vo.AccountStatus;
import com.rest.core.base.BaseRestController;
import com.vix.common.security.entity.UserAccount;
import com.vix.common.security.service.IAuthorityService;
import com.vix.common.security.service.IUserAccountService;
import com.vix.common.security.tree.TreeNode;
import com.vix.core.constant.BizConstant;
import com.vix.core.utils.StrUtils;

/**
 * @ClassName: AccountRestController
 * @Description: vix服务器 账号数据的 响应服务
 * @author wangmingchen
 * @date 2015年3月26日 上午10:50:45
 */
@Controller
@RequestMapping(value = "restService/common/userAccount")
public class AccountRestController extends BaseRestController {

	private static Logger logger = LoggerFactory.getLogger(AccountRestController.class);

	@Resource(name = "userAccountService")
	private IUserAccountService userAccountService;

	@Resource(name = "authorityService")
	private IAuthorityService authorityService;

	/**
	 * @Title: queryAccountStatus
	 * @Description: 查询账号最后更新时间
	 */
	@RequestMapping(value = "/queryAccountStatus.rs", method = RequestMethod.POST) // , produces = MediaTypes.JSON_UTF_8
	public void queryAccountStatus(HttpServletRequest request, HttpServletResponse response, String account) throws Exception {
		Assert.isTrue(StrUtils.isNotEmpty(account), "账号不能为空!");
		Date date = userAccountService.findLastUpdateAccountTime(account);

		AccountStatus as = new AccountStatus();
		as.setAccount(account);
		as.setLastUpdateTime(date);

		renderResult(response, as);
	}

	/**
	 * @Title: queryAuthAccount
	 * @Description: 查询账号权限菜单
	 */
	@RequestMapping(value = "/queryMobileAuthAccount.rs", method = RequestMethod.POST) // , produces = MediaTypes.JSON_UTF_8
	public void queryAuthAccount(HttpServletRequest request, HttpServletResponse response, String account) throws Exception {
		logger.info("账号查询:{}.", account);

		Set<TreeNode> menuObjSet = null;
		if (StrUtils.isNotEmpty(account)) {// accountId
			// UserAccount userAccount =
			// userAccountService..findEntityById(UserAccount.class, accountId);
			UserAccount userAccount = userAccountService.findUserByAccount(account);
			if (null != userAccount) {
				String accountId = userAccount.getId();
				String userResourceReadType = userAccount.getUserResourceReadType();
				if (StringUtils.isNotEmpty(userResourceReadType)) {
					if (userResourceReadType.equalsIgnoreCase(BizConstant.COMMON_SECURITY_USERMENUTYPE_C)) {
						menuObjSet = authorityService.findMenuByUser(accountId, BizConstant.COMMON_SECURITY_RESTYPE_M);
					} else if (userResourceReadType.equalsIgnoreCase(BizConstant.COMMON_SECURITY_USERMENUTYPE_U)) {
						menuObjSet = authorityService.findMenuByUser2(accountId, BizConstant.COMMON_SECURITY_RESTYPE_M);
					}
				} else {
					menuObjSet = authorityService.findMenuByUser(accountId, BizConstant.COMMON_SECURITY_RESTYPE_M);
				}
			}
			/*
			 * if (contactPerson == null) { throw new
			 * ValidateException("没有查询到id="+id+"的待删除的联系人！"); }else{ cpTemp = new
			 * ContactPerson(); BeanUtils.copyProperties(contactPerson, cpTemp, new
			 * String[]{"customerAccount","credentialType","contactPersonType",
			 * "crmContactType"}); }
			 */
		}

		/*
		 * for(TreeNode node:menuObjSet){ System.out.println(node.getName());
		 * if(node.getChildren()!=null){ for(TreeNode child:node.getChildren()){
		 * System.out.println("child:"+child.getName());
		 * 
		 * if(child.getChildren()!=null){ for(TreeNode child2:child.getChildren()){
		 * System.out.println("child2##:"+child2.getName());
		 * 
		 * 
		 * } } } } }
		 */
		renderResult4ListByJackson(response, null, menuObjSet);
	}
}
