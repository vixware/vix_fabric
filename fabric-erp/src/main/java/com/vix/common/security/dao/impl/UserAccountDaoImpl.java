package com.vix.common.security.dao.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.vix.common.common.bo.MessageObject;
import com.vix.common.security.dao.IUserAccountDao;
import com.vix.common.security.entity.Role;
import com.vix.common.security.entity.UserAccount;
import com.vix.common.share.entity.BaseEmployee;
import com.vix.core.constant.BizConstant;
import com.vix.core.constant.BizOcConstant;
import com.vix.core.encode.Md5Encoder;
import com.vix.core.encode.Md5EncoderImpl;
import com.vix.core.exception.BizException;
import com.vix.core.persistence.hibernate.dao.impl.BaseHibernateDaoImpl;
import com.vix.core.utils.JSonUtils;
import com.vix.core.utils.StrUtils;
import com.vix.core.utils.kit.PropKit;
import com.vix.mdm.crm.entity.CustomerAccount;
import com.vix.system.config.dao.IServiceConfigServerDao;
import com.vix.system.config.entity.ServiceConfigServer;

@Repository("userAccountDao")
public class UserAccountDaoImpl extends BaseHibernateDaoImpl implements IUserAccountDao {

	private static final long serialVersionUID = 1L;

	@Resource(name="serviceConfigServerDao")
	private IServiceConfigServerDao serviceConfigServerDao;
	
	@Override
	public UserAccount findUserByAccount(String userName) throws Exception{
		//StringBuilder hql = userAccountHqlProvider.findUserByAccount(userName);
		StringBuilder sb = new StringBuilder();
		sb.append(" from ").append(UserAccount.class.getName()).append(" userAccount");
		sb.append(" left join fetch userAccount.roles role");
		  // sb.append(" left join fetch userAccount.userGroups ug left join fetch ug.roles role2 left join fetch role2.authoritys ");
		//sb.append(" left join fetch role.authoritys au");
		//sb.append(" left join fetch au.resources ");
		sb.append(" where userAccount.account=:account ");
   
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("account", userName);
		UserAccount userAccount = findFirstByHqlOrginial(sb.toString(), params);
		return userAccount;
	}
	
	/**
	 * 
	 */
	@Override
	public UserAccount saveOrUpdate(String addRoleIds, String delRoleIds, String accountId, String accountBizType, String account, String password, String employeeId, String enable, String companyCode) throws Exception {

		UserAccount userAccount = null;
		Boolean isAdd = false;
		if(StringUtils.isNotEmpty(accountId) && !accountId.equals("0")){
			userAccount = findEntityById(UserAccount.class, accountId);
		}
		Date now = new Date();
		if (userAccount == null) {
			isAdd = true;
			userAccount = new UserAccount();
			userAccount.setAccountBizType(accountBizType);
			userAccount.setCreateTime(now);
		}

		if (isEntityExist(UserAccount.class, userAccount.getId(), "account", account)) {
			throw new BizException("账号已存在！");
		}
		if (synCheckUserAccountIsRepeatErp2Oc(userAccount)) {
			throw new BizException("账号在OC平台已存在！");
		}
		
		userAccount.setUpdateTime(now);
		userAccount.setAccount(account);
		//加密
		Md5Encoder md = new Md5EncoderImpl();
		userAccount.setInitpassword(password);
		userAccount.setPassword(md.encodeString(password));
		
		userAccount.setEnable(enable);
		userAccount.setCompanyCode(companyCode);
		userAccount.setUserResourceReadType(BizConstant.COMMON_SECURITY_USERMENUTYPE_C);
		
		if (employeeId != null) {
			userAccount.setEmployee(new BaseEmployee(employeeId));
		}

		Set<Role> rSet = userAccount.getRoles();
		if (rSet == null) {
			rSet = new HashSet<Role>();
		}
		if (StringUtils.isNotEmpty(addRoleIds)) {
			for (String idS : addRoleIds.split(",")) {
				if (StringUtils.isNotEmpty(idS)) {
					Role r = findEntityById(Role.class, idS);
					rSet.add(r);
				}
			}
		}
		if (StringUtils.isNotEmpty(delRoleIds)) {
			for (String idS : delRoleIds.split(",")) {
				if (StringUtils.isNotEmpty(idS)) {
					Role r = findEntityById(Role.class, idS);
					rSet.remove(r);
				}
			}
		}

		userAccount.setRoles(rSet);
		saveOrUpdate(userAccount);
		
		//同步到oc
		synUserAccountErp2Oc(isAdd , userAccount);
		return userAccount;
	}

	/**
	 * 
	 */
	@Override
	public UserAccount saveOrUpdate(String tenantId, String companyCode, String companyInnerCode, String addRoleIds, String delRoleIds, String accountId, String accountBizType, String account, String password, String employeeId, String enable) throws Exception {

		UserAccount userAccount = null;
		if(StringUtils.isNotEmpty(accountId) && !accountId.equals("0")){//if (accountId != null && accountId > 0) {
			userAccount = findEntityById(UserAccount.class, accountId);
		}
		Date now = new Date();
		if (userAccount == null) {
			userAccount = new UserAccount();
			userAccount.setAccountBizType(accountBizType);
			userAccount.setCreateTime(now);
		}

		if (isEntityExist(UserAccount.class, userAccount.getId(), "account", account)) {
			throw new BizException("账号已存在！");
		}
		if (synCheckUserAccountIsRepeatErp2Oc(userAccount)) {
			throw new BizException("账号在OC平台已存在！");
		}
		

		userAccount.setUpdateTime(now);
		userAccount.setAccount(account);
		userAccount.setPassword(password);
		
		userAccount.setEnable(enable);

		if (employeeId != null) {
			userAccount.setEmployee(new BaseEmployee(employeeId));
		}

		Set<Role> rSet = userAccount.getRoles();
		if (rSet == null) {
			rSet = new HashSet<Role>();
		}
		if (StringUtils.isNotEmpty(addRoleIds)) {
			for (String idS : addRoleIds.split(",")) {
				if (StringUtils.isNotEmpty(idS)) {
					Role r = findEntityById(Role.class, idS);
					rSet.add(r);
				}
			}
		}
		if (StringUtils.isNotEmpty(delRoleIds)) {
			for (String idS : delRoleIds.split(",")) {
				if (StringUtils.isNotEmpty(idS)) {
					Role r = findEntityById(Role.class, idS);
					rSet.remove(r);
				}
			}
		}
		//userAccount.setUuid(uuid);
		userAccount.setRoles(rSet);
		userAccount.setTenantId(tenantId);
		userAccount.setCompanyCode(companyCode);
		userAccount.setCompanyInnerCode(companyInnerCode);
		userAccount.setUserResourceReadType(BizConstant.COMMON_SECURITY_USERMENUTYPE_C);
		
		Boolean isCompAdmin = false;
		if(userAccount.getRoles()!=null){
			for(Role role:userAccount.getRoles()){
				if(role.getRoleCode().contains(BizConstant.ROLE_COMP_PRE_SUPERADMIN)){
					isCompAdmin = true;
					break;
				}
			}
		}
		if(isCompAdmin){
			userAccount.setEmployee(null);
		}
		
		saveOrUpdateOriginal(userAccount);
		return userAccount;
	}
	
	
	/*public UserAccount saveOrUpdateBaseinfoSyn(Boolean isSynMaster,UserAccount ua)throws Exception{
		String userAccountUUID = ua.getUuid();
		
		UserAccount userAccount = null;
		if (StringUtils.isNotEmpty(userAccountUUID)) {
			userAccount = findObjectByUUID(UserAccount.class, userAccountUUID);
		}
		
		String account = ua.getAccount();
		Assert.isTrue(StringUtils.isNotEmpty(account), "账号不能为空!");
		Assert.isTrue(StringUtils.isNotEmpty(ua.getPassword()), "密码不能为空!");
		Assert.isTrue(StringUtils.isNotEmpty(ua.getTenantId()), "承租户标识不能为空!");
		Date now = new Date();
		if (userAccount == null) {
			userAccount = new UserAccount();
			String bizType = ua.getAccountBizType();
			if(StringUtils.isNotEmpty(bizType)){
				bizType = BizConstant.COMMON_SECURITY_ACCOUNT_BizType_Sys;
			}
			userAccount.setAccountBizType(bizType);
			userAccount.setCreateTime(now);
			//设定生效时间
			userAccount.setActiveEndTime(DateUtil.getAddDate(now, 7).getTime());
			
			userAccount.setUuid(ua.getUuid());
		}

		if (isEntityExist(UserAccount.class, userAccount.getId(), "account", account)) {
			throw new BizException("账号已存在！");
		}
		
		userAccount.setUpdateTime(now);
		userAccount.setAccount(account);
		userAccount.setPassword(ua.getPassword());
		userAccount.setEnable(ua.getEnable());
		userAccount.setWxAccount(ua.getWxAccount());

		userAccount.setTenantId(ua.getTenantId());
		//userAccount.setCompanyCode(companyCode);
		//userAccount.setCompanyInnerCode(companyInnerCode);
		String userMenuType = ua.getUserResourceReadType();
		if(StringUtils.isEmpty(userMenuType)){
			userMenuType = BizConstant.COMMON_SECURITY_USERMENUTYPE_C;
		}
		userAccount.setUserResourceReadType(userMenuType);
		saveOrUpdateOriginal(userAccount);
		return userAccount;
	}*/
	
	@Override
	public void updateUserAccountDateByTenantId(String tenantId,Date startDate,Date endDate)throws Exception{
    	StringBuffer sql = new StringBuffer();
		sql.append("update CMN_SEC_USERACCOUNT ua set ua.STARTTIME=? , ua.ENDTIME=? where ua.TENANTID=? ");
		jdbcBatchUpdate(sql.toString(), new Object[]{startDate,endDate,tenantId});
	}
	
	@Override
	public Boolean synUserAccountErp2Oc(Boolean isAdd , UserAccount userAccount)throws Exception{
		try {
			ServiceConfigServer server = serviceConfigServerDao
					.findServiceConfigServer(BizOcConstant.OC_SERVICE_TNT_TYPE_OC);
			if (null != server) {
				String serviceUrl = PropKit.use(
						"core_config_service.properties").get(
						"service.oc.account.syn.add");

				String synUaUrl = server.getServerIpAndPort();
				if (StrUtils.isNotEmpty(synUaUrl)) {
					//Md5Encoder md = new Md5EncoderImpl();
					Map<String, Object> params = new HashMap<String, Object>();
					params.put("account", userAccount.getAccount());
					params.put("password", userAccount.getPassword());//md.encodeString("123")
					params.put("tenantId", userAccount.getTenantId());
					params.put("serverType",BizOcConstant.OC_SERVICE_TNT_TYPE_VIX);
					params.put("startTimeVal", userAccount.getStartTimeStr());
					params.put("endTimeVal", userAccount.getEndTimeStr());
					
					params.put("isAdd", isAdd);
					
					String status = BizConstant.COMMON_BOOLEAN_NO;
					if(StrUtils.isNotEmpty(userAccount.getEnable())){
						status = userAccount.getEnable().equals("1")?"Y":"N";
					}
					params.put("status", status);
					
					String reqUrl = synUaUrl + serviceUrl;
					HttpResponse<String> jsonResponse = Unirest.post(reqUrl)
							.fields(params).asString();
					//System.out.println(jsonResponse.getBody().toString());
					//System.out.println("status:" + jsonResponse.getStatus() +";"+jsonResponse.getBody().toString());
					MessageObject mo = JSonUtils.readValue(jsonResponse
							.getBody().toString(), MessageObject.class);
					return mo.isSuccess();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	@Override
	public Boolean synCheckUserAccountIsRepeatErp2Oc(UserAccount userAccount)throws Exception{
		try {
			ServiceConfigServer server = serviceConfigServerDao
					.findServiceConfigServer(BizOcConstant.OC_SERVICE_TNT_TYPE_OC);
			if (null != server) {
				String serviceUrl = PropKit.use(
						"core_config_service.properties").get(
						"service.oc.account.syn.check");

				String synUaUrl = server.getServerIpAndPort();
				if (StrUtils.isNotEmpty(synUaUrl)) {
					//Md5Encoder md = new Md5EncoderImpl();
					Map<String, Object> params = new HashMap<String, Object>();
					params.put("account", userAccount.getAccount());
					//params.put("password", userAccount.getPassword());//md.encodeString("123")
					//params.put("tenantId", userAccount.getTenantId());
					//params.put("serverType",BizOcConstant.OC_SERVICE_TNT_TYPE_VIX);

					String reqUrl = synUaUrl + serviceUrl;
					HttpResponse<String> jsonResponse = Unirest.post(reqUrl)
							.fields(params).asString();
					//System.out.println(jsonResponse.getBody().toString());
					//System.out.println("status:" + jsonResponse.getStatus() +";"+jsonResponse.getBody().toString());
					MessageObject mo = JSonUtils.readValue(jsonResponse
							.getBody().toString(), MessageObject.class);
					Boolean isOk = mo.isSuccess();
					return !isOk;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * 
	 */
	@Override
	public UserAccount saveOrUpdateUserAccount(String account, String password, String employeeId, String enable) throws Exception {

		UserAccount userAccount = new UserAccount();
		Date now = new Date();

		userAccount.setAccountBizType("sys");
		userAccount.setCreateTime(now);

		if (isEntityExist(UserAccount.class, userAccount.getId(), "account", account)) {
			throw new BizException("账号已存在！");
		}

		userAccount.setUpdateTime(now);
		userAccount.setAccount(account);
		userAccount.setPassword(password);
		userAccount.setEnable(enable);

		if (employeeId != null) {
			userAccount.setCustomerAccount(new CustomerAccount(employeeId));
		}

		saveOrUpdateOriginal(userAccount);
		return userAccount;
	}

	@Override
	public UserAccount updateUserAccount(String account, String password) throws Exception {

		UserAccount userAccount = null;
		if (StringUtils.isNotEmpty(account)) {
			userAccount = findEntityByAttributeNoTenantId(UserAccount.class, "account", account);
			if (userAccount != null) {
				userAccount.setUpdateTime(new Date());
				userAccount.setPassword(password);
				saveOrUpdateOriginal(userAccount);
			}
		}

		return userAccount;
	}

	@Override
	public UserAccount queryUserAccount(String account, String password) throws Exception {

		UserAccount userAccount = null;
		if (StringUtils.isNotEmpty(account) && StringUtils.isNotEmpty(password)) {
			userAccount = findUserByAccountAndPassword(account, password);
		}
		return userAccount;
	}

	public UserAccount findUserByAccountAndPassword(String userName, String password) throws Exception {
		StringBuilder sb = new StringBuilder();
		sb.append(" from ").append(UserAccount.class.getName()).append(" userAccount");
		sb.append(" where userAccount.account=:account ");
		sb.append(" and userAccount.password=:password ");
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("account", userName);
		params.put("password", password);
		UserAccount userAccount = findFirstByHqlOrginial(sb.toString(), params);
		return userAccount;
	}
	
}
